<%@page import="java.io.InputStream,java.sql.*"%>
<%@ page import="java.sql.*" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.connection.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.*"%>
<%
try
{ 
int byteRead;
byte[] buffer = new byte[50*1024*1024];
Connection con = Dbconn.conn();
  String id=request.getParameter("id");
		  
  PreparedStatement pst=con.prepareStatement("Select * from help_product_info where id= '"+id+"'");
  ResultSet rs=pst.executeQuery();
  //int id=request.getAttribute(arg0);
	if(rs.next()){	
		
	  InputStream is =  rs.getBinaryStream(8);
	  if(is!=null)
	  {
		  while ((byteRead =is.read(buffer)) != -1)
	  
		{
	    	response.reset();
			response.setContentType("image/jpg");
			response.setHeader("Content-disposition","attachment; filename=" );
	        response.getOutputStream().write(buffer, 0, byteRead);
	        response.getOutputStream().flush(); 
	        response.getOutputStream().close();
	        out.clear ();
			out = pageContext.pushBody(); 
	 	}
		 
	  }
	}
  
}
catch(Exception e)
{
	e.printStackTrace();
}
%>




