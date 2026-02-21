package com.algo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.Dbconn;

/**
 * Servlet implementation class Prediction_data
 */
@WebServlet("/Prediction_data")
public class Prediction_data extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prediction_data() {
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
		RequestDispatcher rd = request.getRequestDispatcher("/Admin_PredictionProduct.jsp");
		rd.include(request, response); 
	}

}
