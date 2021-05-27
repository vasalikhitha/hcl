package com.hcl.library.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hcl.library.beans.Book;
import com.hcl.library.exception.UserDefinedException;
import com.hcl.library.service.BookServices;

@WebServlet("/addBook")
public class AddServlet extends HttpServlet{
		
		private static final long serialVersionUID = 1L;
		public void init() throws ServletException{
			System.out.println("Servlet initialised");
		}
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
			String title = req.getParameter("title");
			String author = req.getParameter("author");
			String price = req.getParameter("price" );
			
			Book b= new Book(); 
			b.setTitle(title);
			b.setAuthor(author);
			b.setPrice(Float.parseFloat(price));
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			try {
				BookServices.addBook(b);
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				req.setAttribute("message","Added Successfully" );
				rd.include(req, res);

			} catch (UserDefinedException e1) {
				RequestDispatcher rd = req.getRequestDispatcher("failure.jsp");
				req.setAttribute("message","Book Already exists" );
				rd.forward(req, res);
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
		{
			doGet(req,res);
		}
		public void destroy() {
			System.out.println("servlet destroyed");
		}

	}

