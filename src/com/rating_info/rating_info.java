package com.rating_info;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.Dbconn;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class rating_info
 */
@WebServlet("/rating_info")
public class rating_info extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rating_info() {
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
		String txt_shopname,txt_emailid,txt_Pname,txt_Pdescription,star;
		HttpSession session=request.getSession(true);
		txt_shopname=request.getParameter("txt_shopname");
		System.out.println("txt_shopname"+txt_shopname);
		txt_emailid=request.getParameter("txt_emailid");
		System.out.println("txt_emailid"+txt_emailid);
		 txt_Pname=request.getParameter("txt_Pname");
		System.out.println("txt_Pname"+txt_Pname);
		txt_Pdescription=request.getParameter("txt_Pdescription");
		System.out.println("txt_Pdescription"+txt_Pdescription);
		
		star=request.getParameter("star");
		int r = Integer.parseInt(star);
		if (r == 5) {
			star = "1";
		} else if (r == 4) {
			star = "2";

		} else if (r == 3) {
			star = "3";
		} else if (r == 2) {
			star = "4";
		} else if (r == 1) {
			star = "5";
		}
		System.out.println("star"+star);
		String uemailid=(String)session.getAttribute("Uemail");
		
		  try
		  {
			Connection con;
			con = Dbconn.conn();
			String roll=request.getParameter("roll");
			
			String sql="insert into tblfeedback(Shop_emailid,Feedback_Data,Rating_data,user_email_id,Shop_Name,Product_Name) values(?,?,?,?,?,?)";
			PreparedStatement p=(PreparedStatement) con.prepareStatement(sql);
			
			
			p.setString(1,txt_emailid );
			p.setString(2,txt_Pdescription);
			p.setString(3,star);
			p.setString(4,uemailid);
			p.setString(5,txt_shopname);
			p.setString(6,txt_Pname);
			
			int i=p.executeUpdate();
			
				System.out.println("OK ");
				pw.println("<script> alert(' Feed back and Rating Successfuly');</script>");
				RequestDispatcher rd = request.getRequestDispatcher("/U_Show_Product.jsp");
				rd.include(request, response); 
				}
		catch(Exception exc)
		{
			RequestDispatcher rd = request.getRequestDispatcher("/RegisterPage.jsp");
			rd.include(request, response);
			exc.printStackTrace();
		}
	}

}
