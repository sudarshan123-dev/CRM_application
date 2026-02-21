package com.connection;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class Dbconn {

	public static ArrayList<String> Predicted_Demand = new ArrayList<String>();
	
	public static String filepath="E:\\ProjectFinal\\Product.csv";
	public static String arfffilepath="E:\\ProjectFinal\\Product.arff";
	public Dbconn() throws SQLException {
		super();
	}

	public static Connection conn() throws SQLException, ClassNotFoundException {
		Connection con;

		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/crmdb_inventory",
				"root", "admin");
		return (con);
	}

	public static void main(String[] agrs) {
		
		String line = "";  
        String splitBy = ",";  
        try   
        {  
        	Connection con=conn();
        	Statement st=con.createStatement();
        	
        	int i=0;
        //parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader("G:\\BE2024-2025\\25197 cs g25 Inventory BE PROJECT\\products_datasetN.csv"));  
        while ((line = br.readLine()) != null)   //returns a Boolean value  
        {  
        String[] employee = line.split(splitBy); 
        if(i==0){}
        else{
        // use comma as separator  
        System.out.println("Employee [First Name=" + employee[0] + ", Last Name=" + employee[1] + ", Designation=" + employee[2] + ", Contact=" + employee[3] + ", Salary= " + employee[4] + ", City= " + employee[5] +"]");  
        st.executeUpdate("insert into sale_product_info(Product_Name,Category,Quantity_Sold,Sale_Date,Price,Region) values('"+employee[0]+"','"+employee[1]+"','"+employee[2]+"','"+employee[3]+"','"+employee[4]+"','"+employee[5]+"')");
        }
        i++;
        }
        }   
        catch (Exception e)   
        {  
        e.printStackTrace();  
        }  

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String currentDate = dateFormat.format(new Date());
	    System.out.println(currentDate);
			System.out.println("*****************");
		
        
	}
}
