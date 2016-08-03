package org.bible;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class requestAttributes
 */
@WebServlet("/requestAttributes")
public class requestAttributes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public requestAttributes() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			setMethod1(request);
			getMethod2(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setMethod1(HttpServletRequest req) throws Exception {
		String val = new String("John Doe");
		req.setAttribute("sampleAttribute", val);
	}

	private void getMethod2(HttpServletRequest req) throws Exception {
		String val = (String) req.getAttribute("sampleAttribute");
		System.out.println("Request attribute "+val+" passed through to response");
	}

}
