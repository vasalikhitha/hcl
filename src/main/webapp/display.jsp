<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*" %>
    <%@page import="com.hcl.library.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="index.jsp" ><button>Home</button></a>
<%if(request.getAttribute("list")!=null){ %>
<%List<Book> l = (ArrayList<Book>)request.getAttribute("list"); %>
 <table border="2" cellspacing="0" cellpadding="10">
<tr>
<th>Title</th>
<th>Author</th>
<th>Price</th>
<th>Update</th>
<th>Delete</th>
</tr>
<%for(Book b : l){%>
<tr><td><%=b.getTitle() %></td>
<td><%=b.getAuthor() %></td>
<td><%=b.getPrice() %></td>
<td><a href="fill?title=<%=b.getTitle()%>"><button>Update </button></td>
<td><a href="deleteBook?title=<%=b.getTitle()%>"><button>Delete</button></a></td>
</tr>
<%} %>
</table>
<%} %>
<% if(request.getAttribute("message")!=null){ %>
<%=request.getAttribute("message") %>
<%} %>
</body>
</html>