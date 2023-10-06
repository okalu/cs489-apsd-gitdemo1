package edu.miu.cs.cs489appsd.lab1a.productmgmtapp;

import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model.Product;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class ProductMgmtApp {
    public static void main(String[] args) {
        // Create an array of Products with company data
        Product[] products = {
                new Product(Long.valueOf("3128874119"), "Banana", new Date(2023, 1, 24), 124, 0.55),
                new Product(Long.valueOf("2927458265"), "Apple", new Date(2022, 12, 9), 18, 1.09),
                new Product(Long.valueOf("9189927460"), "Carrot", new Date(2023, 3, 31), 89, 2.99)         // Add more products as needed
        };
        // Invoke the printProducts method to print the products in various formats
        printProducts(products);
    }

    public static void printProducts(Product[] products) {
        if(products == null || products.length==0){
            // Handle the case where there are no products
            return;
        }
        // Implement code to print products in JSON, XML, and CSV formats
        // Sort the products by product name before printing

        Arrays.sort(products, Comparator.comparing(Product::getName));
        // Streams API
//        var sortedProducts =  Arrays.stream(products)
//                .sorted(Comparator.comparing((p->p.getName())))
//                .toList();
//        var numProducts= sortedProducts.size();
//        for (int i=0;i<sortedProducts.size();i++){
//            if((i+1)<numProducts){
//                System.out.printf("%s,\n",sortedProducts.get(i).toJsonString());
//            }else {
//                System.out.printf(sortedProducts.get(i).toJsonString()+"\n");
//            }
//        }

        // Print JSON-formatted list of products
        // Implement JSON formatting and printing here

        // Build a JSON-formatted string
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n"); // Start of JSON array

        for (int i = 0; i < products.length; i++) {
            jsonBuilder.append("  {");
            jsonBuilder.append("\"productId\":\"").append(products[i].getProductId()).append("\",");
            jsonBuilder.append("\"name\":\"").append(products[i].getName()).append("\",");
            jsonBuilder.append("\"dateSupplied\":\"").append(products[i].getDateSupplied()).append("\",");
            jsonBuilder.append("\"quantityInStock\":").append(products[i].getQuantityInStock()).append(",");
            jsonBuilder.append("\"unitPrice\":").append(products[i].getUnitPrice());
            jsonBuilder.append("}");

            if (i < products.length - 1) {
                jsonBuilder.append(",\n");
            }
        }

        jsonBuilder.append("\n]"); // End of JSON array

        // Print JSON-formatted product list
        System.out.println("JSON-formatted list of all Products:");
        System.out.println("----------------------------------------------------");
        System.out.println(jsonBuilder.toString());

        // Print XML-formatted list of products
        // Implement XML formatting and printing here

        // Build an XML-formatted string
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlBuilder.append("<Products>\n");

        for (Product product : products) {
            xmlBuilder.append("  <Product>\n");
            xmlBuilder.append("    <productId>").append(product.getProductId()).append("</productId>\n");
            xmlBuilder.append("    <name>").append(product.getName()).append("</name>\n");
            xmlBuilder.append("    <dateSupplied>").append(product.getDateSupplied()).append("</dateSupplied>\n");
            xmlBuilder.append("    <quantityInStock>").append(product.getQuantityInStock()).append("</quantityInStock>\n");
            xmlBuilder.append("    <unitPrice>").append(product.getUnitPrice()).append("</unitPrice>\n");
            xmlBuilder.append("  </Product>\n");
        }

        xmlBuilder.append("</Products>");

        // Print XML-formatted product list
        System.out.println("XML-formatted list of products:");
        System.out.println("----------------------------------------------------");
        System.out.println(xmlBuilder.toString());

        // Print CSV-formatted list of products
        // Implement CSV formatting and printing here

        // Build a CSV-formatted string
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("productId,name,dateSupplied,quantityInStock,unitPrice\n");

        for (Product product : products) {
            csvBuilder.append(product.getProductId()).append(",");
            csvBuilder.append(product.getName()).append(",");
            csvBuilder.append(product.getDateSupplied()).append(",");
            csvBuilder.append(product.getQuantityInStock()).append(",");
            csvBuilder.append(product.getUnitPrice()).append("\n");
        }

        // Print CSV-formatted product list
        System.out.println("CSV-formatted list of products:");
        System.out.println("----------------------------------------------------");
        System.out.println(csvBuilder.toString());
    }
}
