package org.bible;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class currencyconverter
 */
@WebServlet(urlPatterns = { "/currencyconverter" }, initParams = { @WebInitParam(name = "USD_CAD", value = "1.06") })
public class currencyconverter extends HttpServlet {

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {

		super.init(servletConfig);
		String cadConversionRateAsString = servletConfig
				.getInitParameter("USD_CAD");

		if (cadConversionRateAsString == null) {
			log("Missing USD/CAD conversion rate. Using default value: "
					+ cadConversionRate);
			return;
		}

		try {
			cadConversionRate = Double.parseDouble(cadConversionRateAsString);
		} catch (final NumberFormatException e) {
			log("Invalid USD/CAD conversion rate. Using default value: "
					+ cadConversionRate, e);
		}

	}

	private static final long serialVersionUID = 1L;
	private double cadConversionRate;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public currencyconverter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		final PrintWriter out = response.getWriter();		
		String amountInUsdAsString = null;
		double amountInUsd = 1.00;
		double amountInCad = 0.00;

		try {
			amountInUsdAsString = request.getParameter("amountInUsd");
			if (amountInUsdAsString != null){  
				amountInUsd = Double.parseDouble(amountInUsdAsString);
			}
			amountInCad = cadConversionRate * amountInUsd;
			page(out,amountInUsd,amountInCad);

		} catch (final NumberFormatException e) {
			amountInCad = cadConversionRate * amountInUsd;
			page(out,amountInUsd,amountInCad);
			log("USD amount of '" + amountInUsdAsString + "' is not a number.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void page(PrintWriter out, double amountInUsd, double amountInCad){
		
		out.println("<html>");
		out.println("<head><title>Currency Converter</title></head>");
		out.println("<body>");
		out.println("<p>" + amountInUsd
				+ " in US dollars is equivalent to " + amountInCad
				+ " Canadian dollars eh!</p>");
		out.println("</body></html>");		
	}

}
