package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model;

import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model.Product;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class ProductMgmtApp {
    public static void main(String[] args) {
        // Create an array of Products with company data
        Product[] products = {
                new Product("3128874119", "Banana", new Date(2023, 1, 24), 124, 0.55),
                new Product("2927458265", "Apple", new Date(2022, 12, 9), 18, 1.09),
                new Product("9189927460", "Carrot", new Date(2023, 3, 31), 89, 2.99)         // Add more products as needed
        };
        // Invoke the printProducts method to print the products in various formats
        printProducts(products);
    }

    public static void printProducts(Product[] products) {
        if(products == null || products.length==0){
            ///
            return;
        }
        // Implement code to print products in JSON, XML, and CSV formats
        // Sort the products by product name before printing

        Arrays.sort(products, Comparator.comparing(Product::getName));
        //Streams API
       var sortedProducts =  Arrays.stream(products)
                //.sorted(Comparator.comparing(Product::getName))
               .sorted(Comparator.comparing((p->p.getName())))
               .toList();
       var numProducts= sortedProducts.size();
       for (int i=0;i<sortedProducts.size();i++){
           if((i+1)<numProducts){
               System.out.printf("%s,\n",sortedProducts.get(i).toJOSONString());
           }else {
               System.out.printf(sortedProducts.get(i).toJOSONString()+"\n");
           }
       }

        // Print JSON-formatted list of products
        // Implement JSON formatting and printing here

        // 构建 JSON 格式的字符串
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n"); // JSON 数组的开始

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

        jsonBuilder.append("\n]"); // JSON 数组的结束

        // 打印 JSON 格式的产品列表
        System.out.println("JSON-formatted list of all Products:");
        System.out.println("----------------------------------------------------");
        System.out.println(jsonBuilder.toString());

        // Print XML-formatted list of products
        // Implement XML formatting and printing here
// 构建 XML 格式的字符串
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

        // 打印 XML 格式的产品列表
        System.out.println("XML-formatted list of products:");
        System.out.println("----------------------------------------------------");
        System.out.println(xmlBuilder.toString());
        // Print CSV-formatted list of products
        // Implement CSV formatting and printing here
        // 构建 CSV 格式的字符串
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("productId,name,dateSupplied,quantityInStock,unitPrice\n");

        for (Product product : products) {
            csvBuilder.append(product.getProductId()).append(",");
            csvBuilder.append(product.getName()).append(",");
            csvBuilder.append(product.getDateSupplied()).append(",");
            csvBuilder.append(product.getQuantityInStock()).append(",");
            csvBuilder.append(product.getUnitPrice()).append("\n");
        }

        // 打印 CSV 格式的产品列表
        System.out.println("CSV-formatted list of products:");
        System.out.println("----------------------------------------------------");
        System.out.println(csvBuilder.toString());
    }
}
