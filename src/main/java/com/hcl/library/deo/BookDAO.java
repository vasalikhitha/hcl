package com.hcl.library.deo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.hcl.library.beans.Book;
import com.hcl.library.exception.UserDefinedException;
import com.hcl.library.util.DBConnection;





public class BookDAO {

	public static ArrayList<Book> displayBook() {
		ArrayList<Book> bk = new ArrayList<Book>();
		Connection con = DBConnection.getConnection();
		ResultSet rs=null;
		PreparedStatement stmt=null;
		try {
			String sql = "select title,author,price from book";
			stmt = con.prepareStatement(sql);		
			rs = stmt.executeQuery(sql);
			while(rs.next()) 
			{
				
				String title= rs.getString(1);
				String author = rs.getString(2);
				float price = rs.getFloat(3);
				Book b = new Book();
				b.setTitle(title);
				b.setAuthor(author);
				b.setPrice(price);
				bk.add(b);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bk;	
	}
		
	public static Book addBook(Book book) throws UserDefinedException {
		// TODO Auto-generated method stub
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null; 
      try 
      {
    	  if(!searchByTitle(book.getTitle()))
			{
			ps = con.prepareStatement("insert into book values(?,?,?)");
	
			ps.setString(1, book.getTitle() );
     		ps.setString(2, book.getAuthor());
			ps.setFloat(3, book.getPrice());
			ps.executeUpdate();
			}
			else
			{
				   throw new UserDefinedException("Book Title already exists");
			 }
      }
         catch (SQLException e) {         
		    e.printStackTrace();
	     }
   return book;
	}

	public static void deleteBook(String title ) throws UserDefinedException {
		// TODO Auto-generated method stub
			try {
				if(searchByTitle(title))
				{
					Connection con = DBConnection.getConnection();
			        PreparedStatement ps = null;
					String sql = "delete from book where title =?";
					ps = con.prepareStatement(sql);
					ps.setString(1, title );
					ps.executeUpdate();
				}
				else
				{
					   throw new UserDefinedException("Cannot delete as book title doesnotExists");
				 }
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	public static void updateBook(Book book) throws UserDefinedException {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = null;
				try {
					if(searchByTitle(book.getTitle()))
					{
					String sql = "update book set price=? where title=? and author=?";
					ps = con.prepareStatement(sql);
					ps.setFloat(1, book.getPrice());
					ps.setString(2, book.getTitle());
					ps.setString(3,book.getAuthor());
					ps.executeUpdate();
					}
					else
					{
						   throw new UserDefinedException("Book Title doesn't exists cannot update");
					 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	public static ArrayList<Book> searchBook(String title) {
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Book> ls = new ArrayList<Book>();
            
            String str = "select title,author,price from book where title=?";
            try {
                stmt = con.prepareStatement(str);
                stmt.setString(1, title);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    Book b = new Book();
                    b.setTitle(rs.getString("title"));
                    b.setAuthor(rs.getString("author"));
                    b.setPrice(rs.getInt("price"));
                    ls.add(b);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        return ls;
    }
	public static boolean searchByTitle(String title) {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT COUNT(*) FROM book WHERE title = ?");
            ps.setString(1, title);
            rs = ps.executeQuery();
            int count=0;
            while(rs.next()) 
			{
				count = rs.getInt(1);
			}
			if(count ==0)
				return false;
			else
				return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
