package com.algo;

import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.attribute.NominalToBinary;

import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.*;

import com.connection.Dbconn;
public class TopProductsPredictionPer {
	
	public static void Databasecsv()
	{
		
		String query = "SELECT * FROM sale_product_info";

        try (
            Connection conn = Dbconn.conn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            FileWriter writer = new FileWriter(Dbconn.filepath)
        ) {
            writer.append("Product Name,Category,Quantity_Sold,Sale_Date,Price,Region\n");

            while (rs.next()) {
                writer.append(rs.getString("Product_Name") + "," +
                              rs.getString("Category") +"," +
                              rs.getString("Quantity_Sold") +"," +
                              rs.getString("Sale_Date") +"," +
                              rs.getString("Price") +"," +
                              rs.getString("Region")+ "\n");
            }

            System.out.println("CSV export complete.");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	public static void Trainarff(String trainingcsv,String trainingarff) {
		try {
			// load CSV
//			System.out.println(trainingcsv);
		    CSVLoader loader = new CSVLoader();
		    loader.setSource(new File(trainingcsv));
		    Instances data = loader.getDataSet();

		    // save ARFF
		   
		    ArffSaver saver = new ArffSaver();
		    saver.setInstances(data);
		    saver.setFile(new File(trainingarff));
		    saver.writeBatch();
		    System.out.println("Done");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void Predicted_Products(String startDate, String endDate)
	{
		try{ 
			Dbconn.Predicted_Demand.clear();
//		startDate = "2024-09-01";  // Example start date
//         endDate = "2024-10-30";    // Example end date

        // Load ARFF data for model training
      //  DataSource source = new DataSource("G:\\BE2024-2025\\25197 cs g25 Inventory BE PROJECT\\products_datasetN.arff");
        DataSource source = new DataSource(Dbconn.arfffilepath);
        Instances data = source.getDataSet();
        data.setClassIndex(data.numAttributes() - 2);

        // Apply filter for categorical data
        NominalToBinary filter = new NominalToBinary();
        filter.setInputFormat(data);
        Instances filteredData = Filter.useFilter(data, filter);

        // Train model
        LinearRegression model = new LinearRegression();
        model.buildClassifier(filteredData);

        // Fetch data from database
        List<ProductSalesSec> productSalesList = getSalesDataFromDatabase(startDate, endDate);

        // Predict demand and calculate percentage
        Map<String, Double> predictedSales = new HashMap<>();
        double totalPredictedSales = 0.0;

        for (ProductSalesSec sales : productSalesList) {
                     
            double predictedDemand = model.classifyInstance(filteredData.instance(0));
            predictedSales.put(sales.getProductName(), predictedSales.getOrDefault(sales.getProductName(), 0.0) + predictedDemand);
            totalPredictedSales += predictedDemand;
        }

        List<Map.Entry<String, Double>> sortedProducts = new ArrayList<>(predictedSales.entrySet());
        sortedProducts.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        System.out.println("Top 3 Predicted Products and Demand Percentage:");
        for (int i = 0; i < 3 && i < sortedProducts.size(); i++)
        {
            Map.Entry<String, Double> entry = sortedProducts.get(i);
            double percentage = (entry.getValue() / totalPredictedSales) * 100;
            String ProductName=entry.getKey();
            String Predicteddata=ProductName+"#"+percentage;
            Dbconn.Predicted_Demand.add(Predicteddata);
            System.out.printf("%d. %s - Predicted Demand: %.2f%%\n", i + 1, ProductName, percentage);
        }
		}
		catch(Exception ex)
		{}
	}
	public static void main(String[] args) throws Exception {
//     Predicted_Products("",""); 
		Databasecsv();
		Trainarff(Dbconn.filepath,Dbconn.arfffilepath);
    }

    private static List<ProductSalesSec> getSalesDataFromDatabase(String startDate, String endDate) {
        List<ProductSalesSec> productSalesList = new ArrayList<>();
        String query = "SELECT Product_Name, Category,Quantity_Sold, Price, Region FROM sale_product_info WHERE Sale_Date BETWEEN ? AND ?";
        try {
        	Connection conn =  Dbconn.conn();
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	String productName = rs.getString("Product_Name").replace(" ","_");
                String category = rs.getString("Category");
                int quantitySold = rs.getInt("Quantity_Sold");
                double price = rs.getDouble("Price");
                String region = rs.getString("Region");

                productSalesList.add(new ProductSalesSec(productName, category, quantitySold,price, region));
           
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productSalesList;
    }
}
