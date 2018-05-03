package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.Helloservice2ServiceStub;
import com.service.Helloservice2ServiceStub.*;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String strParam1 = request.getParameter("txtSo1");
		String strParam2 = request.getParameter("txtSo2");
		Integer so1 = Integer.parseInt(strParam1);
		Integer so2 = Integer.parseInt(strParam2);
		Helloservice2ServiceStub serviceStub = new Helloservice2ServiceStub();
		Sum sum = new Sum();
		sum.setArg0(so1);
		sum.setArg1(so2);
		SumResponseE s = serviceStub.sum(sum);
		response.getWriter().write("" + s.getSumResponse().get_return());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
