package com.algo;

public class ProductSalesSec {
	private final String productName;
	private String category;
    private final String region;
    private final double price;
    private int quantitySold;
//    public ProductSalesSec(String productName, String region, double price) {
//        this.productName = productName;
//        this.region = region;
//        this.price = price;
//    }
    
   
   

    public ProductSalesSec(String productName, String category, int quantitySold, double price, String region) {
        this.productName = productName;
        this.category = category;
        this.quantitySold = quantitySold;
           this.price = price;
        this.region = region;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }
    public String getCategoryName() {
        return category;
    }
    public double getRegionAsNumeric() {
    	if ("North".equalsIgnoreCase(region)) return 1;
        if ("South".equalsIgnoreCase(region)) return 2;
        if ("East".equalsIgnoreCase(region)) return 3;
        if ("West".equalsIgnoreCase(region)) return 4;
        return 0; // Default value for unknown regions   
    }

}