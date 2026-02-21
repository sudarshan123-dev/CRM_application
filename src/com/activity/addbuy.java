package com.activity;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.algo.email;
import com.connection.Dbconn;

/**
 * Servlet implementation class addbuy
 */
@WebServlet("/addbuy")
public class addbuy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addbuy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw=response.getWriter();
		String id=request.getParameter("txt_id");
		String Pname=request.getParameter("txt_Pname");
		String Pquantity=request.getParameter("txt_Pquantity");
		String currentPquantity=request.getParameter("Ctxt_Pquantity");
		String Category=request.getParameter("txt_Pdescription");
		String Pprice=request.getParameter("txt_Pprice");
		String Region=request.getParameter("txt_Pexpiry");
		HttpSession session = request.getSession(true);
		String User_Name=(String)session.getAttribute("name");
		String User_Email_ID=(String)session.getAttribute("Uemail");
		try {
			
		Connection con=Dbconn.conn();
		double price=Double.valueOf(Pprice);// price
		int cqty=Integer.valueOf(currentPquantity);// current Qty
		double totalprice=price*cqty;
		int aqty=Integer.valueOf(Pquantity);
		String shopemailid="";
		String shopuser="";
		if(cqty<=aqty)
		{
			int reqty=aqty-cqty;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    String currentDate = dateFormat.format(new Date());
			System.out.println("ID===>"+id);
			System.out.println("Name selected===>"+Pname);
			System.out.println("Pquantity selected===>"+Pquantity);
			System.out.println("CurrentPquantity selected===>"+currentPquantity);
			System.out.println("Category selected===>"+Category);
			System.out.println("Pprice selected===>"+Pprice);
			
			System.out.println("Region selected===>"+Region);
			System.out.println("Price selected===>"+totalprice);
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from help_product_info where id='"+id+"'");
			if(rs.next())
			{
				shopemailid=rs.getString("shop_user_email_id");
				shopuser=rs.getString("shopuser");
			}
			Statement st1=con.createStatement();
			st1.executeUpdate("update help_product_info set qty='"+reqty+"' where id='"+id+"'");
			
			
			PreparedStatement ps=con.prepareStatement("insert into sale_product(Product_Name,Category,Quantity_Sold,Sale_Date,Price,Region,Shop_Name,Shop_Email_Id,User_Name,User_Email_Id,Product_ID,Total_Price) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, Pname);
			ps.setString(2, Category);
			ps.setString(3, currentPquantity);
			ps.setString(4, currentDate);
			ps.setString(5, Pprice);
			ps.setString(6, Region);
			ps.setString(7, shopuser);
			ps.setString(8, shopemailid);
			ps.setString(9, User_Name);
			ps.setString(10, User_Email_ID);
			ps.setString(11, id);
			ps.setString(12, String.valueOf(totalprice));
			ps.executeUpdate();
			
			// sale product dataset
			PreparedStatement ps1=con.prepareStatement("insert into sale_product_info(Product_Name,Category,Quantity_Sold,Sale_Date,Price,Region) values(?,?,?,?,?,?)");
			
			ps1.setString(1, Pname);
			ps1.setString(2, Category);
			ps1.setString(3, currentPquantity);
			ps1.setString(4, currentDate);
			ps1.setString(5, Pprice);
			ps1.setString(6, Region);
			ps1.executeUpdate();
			pw.println("<script> alert('Product Buy Successfully');</script>");
		}
		else
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from help_product_info where id='"+id+"'");
			if(rs.next())
			{
				shopemailid=rs.getString("shop_user_email_id");
				shopuser=rs.getString("shopuser");
			}
			email.mailsend("Not Available Quantity", shopemailid);
			pw.println("<script> alert('Not Available Quantity');</script>");
		}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/BuyProduct.jsp");			
		rd.include(request, response);
	}

}
