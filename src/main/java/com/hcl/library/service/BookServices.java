package com.hcl.library.service;

import java.awt.List;
import java.util.ArrayList;

import com.hcl.library.beans.Book;
import com.hcl.library.deo.BookDAO;
import com.hcl.library.exception.UserDefinedException;



public class BookServices {
	public static ArrayList<Book> displayBook() {
		return BookDAO.displayBook();
	}
	public static Book addBook(Book book) throws UserDefinedException {
		return BookDAO.addBook(book);
	}
	public static void deleteBook(String title ) throws UserDefinedException {
		BookDAO.deleteBook(title );
	}
	public static void updateBook(Book book) throws UserDefinedException{
		BookDAO.updateBook(book);
	}
	public static ArrayList searchBook(String title) {
        // TODO Auto-generated method stub
        return BookDAO.searchBook(title);
    }
}
