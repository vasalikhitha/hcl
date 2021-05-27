package com.hcl.library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hcl.library.beans.Book;
import com.hcl.library.service.BookServices;
@WebServlet("/displayBookDetails")
public class DisplayServlet extends HttpServlet{
		
		private static final long serialVersionUID = 1L;
		public void init() throws ServletException{
			System.out.println("Servlet initialised");
		}
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {	
			BookServices service = new BookServices();
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			ArrayList<Book>	 list = new ArrayList<Book>();
			list = service.displayBook();
			RequestDispatcher rd = req.getRequestDispatcher("display.jsp");
			req.setAttribute("list", list );
			rd.forward(req, res);

		}
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
		{
			doGet(req,res);
		}
		public void destroy() {
			System.out.println("servlet destroyed");
		}
	}

