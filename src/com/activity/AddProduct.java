package com.activity;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.connection.Dbconn;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 50, // 50MB
maxFileSize = 1024 * 2048 * 100, // 100 MB

maxRequestSize = 1024 * 2048 * 1000)
// 1000 MB
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	String getFileName(Part filePart) {

		for (String cd : filePart.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1)
						.substring(fileName.lastIndexOf('\\') + 1);
				// return cd.substring(cd.indexOf('=') + 1).trim().replace("\"",
				// "") ;
			}

		}

		return null;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		PrintWriter pw=response.getWriter();
		System.out.println("Welcome");
		String emailid=(String)session.getAttribute("email");
		String username=(String)session.getAttribute("name");
		try {
			Dbconn db=new Dbconn();
			Connection con=db.conn();
			String category=request.getParameter("txt_category");
			String name=request.getParameter("txt_Pname");
			String qty=request.getParameter("txt_Pquantity");
			String product_info=request.getParameter("txt_Pdescription");
			String extra0=request.getParameter("txt_Pprice");
			String manufacture_date=request.getParameter("txt_Pmanufacture");
			String txt_Region=request.getParameter("txt_Region");
			String txt_shopname=request.getParameter("txt_shopname");
			
			System.out.println("pname===>"+name);
			System.out.println("pquantity===>"+qty);
			System.out.println("pdescription===>"+product_info);
			
			System.out.println("pprice===>"+extra0);
			System.out.println("pmanufacture===>"+manufacture_date);
			System.out.println("txt_Region===>"+txt_Region);
			System.out.println("category===>"+category);
			Part artfile = request.getPart("artfile");
			String artfilename = getFileName(artfile);
			InputStream artinputStream1 = artfile.getInputStream();
			PreparedStatement ps=con.prepareStatement("insert into help_product_info(name,qty,product_description,product_price,manufacture_date,CategoryName,photo_data,Photo_Name,Region_Name,shop_user_email_id,shopuser,Shop_Name) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, qty);
			ps.setString(3, product_info);
			ps.setString(4, extra0);
			ps.setString(5, manufacture_date);
			ps.setString(6, category);
			ps.setBlob(7, artinputStream1);
			ps.setString(8, artfilename);
			ps.setString(9, txt_Region);
			ps.setString(10, emailid);
			ps.setString(11,username );
			ps.setString(12,txt_shopname );
			
			ps.executeUpdate();
			
			pw.println("<script> alert(' Product added Successfully');</script>");
			RequestDispatcher rd = request.getRequestDispatcher("/HomePage.jsp");			
			rd.include(request, response); 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
