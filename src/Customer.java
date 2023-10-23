import java.util.LinkedList;

public class Customer {
	private static LinkedList<Orders> myCart = new LinkedList<Orders>();
	public static String name;
	public static double cash;
	
	public static void addItemToCart(Orders order) {
		myCart.add(order);
	}
	
	public static LinkedList<Orders> getCartItems() {
		return myCart;
	}
	
}
