import java.awt.Image;
import java.util.LinkedList;

public class Orders {
	private int quantity;
	private String productName;
	private Image productImg;
	public static LinkedList<Orders> orders = new LinkedList<Orders>();
	private double price;
	private int productStock;
	private int productIndex;
	
	public Orders(int quantity, String productName, Image productImg, double price, int productStock, int productIndex) {
		this.productName = productName;
		this.quantity = quantity;
		this.productImg = productImg;
		this.price = price;
		this.productStock = productStock;
		this.productIndex = productIndex;
	}
	
	public void setName(String name) {
		this.productName = name;
	}
	
	public String getName() {
		return this.productName;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setProductImg(Image productImg) {
		this.productImg = productImg;
	}
	
	public Image getProductImg() {
		return this.productImg;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public double getTotalProductPrice() {
		return this.quantity * this.price;
	}
	
	public int getProductStock() {
		return this.productStock;
	}
	
	public int getProductOrderIndex() {
		return this.productIndex;
	}
}
