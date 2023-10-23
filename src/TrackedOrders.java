import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class TrackedOrders {
	private ArrayList<Integer> quantities;
	private ArrayList<String> productNames;
	private ArrayList<Double> productPrices;
	private double totalPrice;
	private int orderId;
	private static int globalId = 1000;
	public static LinkedList<TrackedOrders> allTrackedOrders = new LinkedList<TrackedOrders>();
	
	public TrackedOrders(ArrayList<Integer> quantities, ArrayList<String> productNames, ArrayList<Double> productPrices, double totalPrice) {
		this.quantities = quantities;
		this.productNames = productNames;
		this.productPrices = productPrices;
		this.totalPrice = totalPrice;
		TrackedOrders.globalId += 20;
		this.orderId = globalId + (1 + (int)(Math.random() * ((15 - 1) + 1)));
	}

	public TrackedOrders() {

	}
	public ArrayList<Integer> getQuantities() {
		return this.quantities;
	}
	
	public ArrayList<Double> getPrices() {
		return this.productPrices;
	}
	
	public double getTotalPrice() {
		return this.totalPrice;
	}
	
	public int getOrderID() {
		return this.orderId;
	}
	
	public ArrayList<String> getProductNames() {
		return this.productNames;
	}
}
