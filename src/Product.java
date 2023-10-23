import java.awt.PageAttributes.OriginType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import javax.print.attribute.standard.Media;

public class Product {
	private String name;
	private int stockAmount;
	private String description;
	private double price;
	private double rating;
	private String category;
	public static LinkedList<Product> products = new LinkedList<Product>();
	private static Comparator<Product> comp= Comparator.comparingDouble(Product::getRating).reversed()
			.thenComparing(Product::getPrice);
	public static PriorityQueue<Product> recommendedProducts = new PriorityQueue<Product>(comp);
	
	public Product(String name, int stockAmount, String description, double price, double rating, String category) {
		this.name =  name;
		this.stockAmount = stockAmount;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.category = category;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setStockAmount(int stockAmount) {
		this.stockAmount = stockAmount;
	}
	
	public int getStockAmount() {
		return this.stockAmount;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}	


	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public double getRating() {
		return this.rating;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return this.category;
	}

	
}
