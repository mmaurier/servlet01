package org.bible;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class context
 */
@WebServlet("/context")
public class context extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServletContext ctx;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public context() {
        super();
        
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		ctx = config.getServletContext();
		System.out.println("In init " + ctx.getServletContextName());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in doget " + ctx.getServerInfo() );
	}

}
