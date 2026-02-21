package com.algo;
import java.io.*;
import java.sql.*;
import java.util.*;

import com.connection.Dbconn;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;


public class arff_create {
	
	public static void createcsvfile()
	{
		String csvFilePath="G:\\BE2024-2025\\25197 cs g25 Inventory BE PROJECT\\output.csv";
		try{
			Connection con=Dbconn.conn();
		Statement statement = con.createStatement();

            String sqlQuery = "SELECT * FROM sale_product_info";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            FileWriter csvWriter = new FileWriter(csvFilePath);

            // Write column headers
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                csvWriter.append(resultSet.getMetaData().getColumnName(i));
                if (i < columnCount) {
                    csvWriter.append(",");
                }
            }
            csvWriter.append("\n");

            // Write data rows
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    csvWriter.append(resultSet.getString(i));
                    if (i < columnCount) {
                        csvWriter.append(",");
                    }
                }
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
            System.out.println("CSV file created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    
	}
	
	
	public static void main(String[] args) {

		try {
			createcsvfile();
			String testingcsv="G:\\BE2024-2025\\25197 cs g25 Inventory BE PROJECT\\products_datasetN.csv";
			String testingarff="G:\\BE2024-2025\\25197 cs g25 Inventory BE PROJECT\\products_datasetN.arff";
			arff_create.Trainarff(testingcsv,testingarff);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static void Trainarff(String trainingcsv,String trainingarff) {
		try {
			// load CSV
			System.out.println(trainingcsv);
		    CSVLoader loader = new CSVLoader();
		    loader.setSource(new File(trainingcsv));
		    Instances data = loader.getDataSet();

		    // save ARFF
		    System.out.println(trainingarff);
		    ArffSaver saver = new ArffSaver();
		    saver.setInstances(data);
		    saver.setFile(new File(trainingarff));
		    saver.writeBatch();

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}


	

	
}
