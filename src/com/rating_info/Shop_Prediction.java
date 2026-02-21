package com.rating_info;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algo.TopProductsPredictionPer;
import com.connection.Dbconn;

/**
 * Servlet implementation class Shop_Prediction
 */
@WebServlet("/Shop_Prediction")
public class Shop_Prediction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Shop_Prediction() {
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
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		TopProductsPredictionPer.Databasecsv();
		TopProductsPredictionPer.Trainarff(Dbconn.filepath,Dbconn.arfffilepath);
		TopProductsPredictionPer.Predicted_Products(startDate,endDate);   
		pw.println("<script> alert('Predicted Demand');</script>");
		RequestDispatcher rd = request.getRequestDispatcher("/Show_PredictionProduct.jsp");
		rd.include(request, response); 
	}

}
