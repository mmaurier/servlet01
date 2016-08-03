package org.bible;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class buycoffe
 */
@WebServlet(
		urlPatterns = { "/buycoffe" }, 
		initParams = { 
				@WebInitParam(name = "sumatra", value = "10.99"), 
				@WebInitParam(name = "mocha", value = "7.99"), 
				@WebInitParam(name = "espresso", value = "9.99"),
				@WebInitParam(name = "morningblend", value = "6.99")
		})
public class buycoffe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Map coffeeUnitPrices = new HashMap(89);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buycoffe() {
        super();

    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		final Enumeration enum1 = config.getInitParameterNames();
		while (enum1.hasMoreElements()) {
			final String name = (String) enum1.nextElement();
			final String value = getInitParameter(name);
			final Double unitPrice = new Double(value);
			coffeeUnitPrices.put(name, unitPrice);
		}		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		final HttpSession session = request.getSession(true);
		//call getRequestedQuantities() to retrieve coffee name(s) and amounts from request
		final Map requestedQuantities = getRequestedQuantities(request);
		//capture and refresh the current session shopping cart
		updateShoppingCart(session, requestedQuantities);
		//final String userName = request.getParameter("User");
		final String userName = "Susan"; //later we'll retrieve user name from the actual user
		displayShoppingCart(out, userName, session);
	}
	
	//this method iterates the request parameters and returns the quantities requested
	private Map getRequestedQuantities(HttpServletRequest request) {
		final Enumeration enum2 = request.getParameterNames();
		final Map requestedQuantities = new HashMap(89);
		while (enum2.hasMoreElements()) {
			final String name = (String) enum2.nextElement();
			final double quantity = Double.parseDouble(request
					.getParameter(name));
			requestedQuantities.put(name, new Double(quantity));
		}
		return requestedQuantities;
	}
	
	public void updateShoppingCart(final HttpSession session,
			final Map requestedQuantities) {
		for (final Iterator i = coffeeUnitPrices.keySet().iterator(); i
				.hasNext();) {
			final String eachCoffeeName = (String) i.next();
			final Double requestedQuantity = (Double) requestedQuantities
					.get(eachCoffeeName);
			final Double newQuantity = getNewQuantity(eachCoffeeName,
					requestedQuantity, session);
			//update the current session with the current shopping cart version
			session.setAttribute(eachCoffeeName, newQuantity);
		}
	}	
	
	private Double getNewQuantity(final String coffeeName,
			final Double requestedQuantity, final HttpSession session) {
		final Object shopcartQuantityAsObject = session.getAttribute(coffeeName);
		if (shopcartQuantityAsObject == null) {
			return requestedQuantity;
		} else {
			final Double shopcartQuantity = (Double) shopcartQuantityAsObject;
			return new Double(requestedQuantity.doubleValue()
					+ shopcartQuantity.doubleValue());
		}
	}
	
	//render the current shopping cart
	private void displayShoppingCart(final PrintWriter out,
			final String userName, final HttpSession session) {
		out.println("<html>");
		out.println("<body>");
		out.println("<p>Shopping cart for " + userName + "</p>");
		out.println("<table>");
		printShopcartHeader(out);
		Enumeration coffeeTypesInShopcart = session.getAttributeNames();
		while (coffeeTypesInShopcart.hasMoreElements()) {
			final String eachCoffeeName = (String) coffeeTypesInShopcart
					.nextElement();
			displayLineItem(out, session, eachCoffeeName);
		}
		out.println("</table>");
		out.println("</body>");
	}
	
	private void printShopcartHeader(final PrintWriter out) {
		out.println("<tr>");
		out.println("<th>Coffee Type</th>");
		out.println("<th>Price / lb</th>");
		out.println("<th>Quantity (lb)</th>");
		out.println("<th>Price</th>");
		out.println("</tr>");
	}

	private void displayLineItem(final PrintWriter out,
			final HttpSession session, final String coffeeName) {
		final Double unitPrice = (Double) coffeeUnitPrices.get(coffeeName);
		final Double quantity = (Double) session.getAttribute(coffeeName);
		final Double eachCoffeeTotalPrice = new Double(unitPrice.doubleValue()
				* quantity.doubleValue());
		out.println("<tr>");
		out.println("<td>" + coffeeName + "</td>");
		out.println("<td>" + unitPrice + "</td>");
		out.println("<td>" + quantity + "</td>");
		out.println("<td>" + eachCoffeeTotalPrice + "</td>");
		out.println("</tr>");	
	}
}
