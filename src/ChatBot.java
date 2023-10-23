import java.util.HashMap;
import java.util.Random;

public class ChatBot {

	
	public static String greetingsPrompt() {
		return "Bot: Hello " + Customer.name + "! I'm here to help! \n";
	}
	
	public static String showCategories() {
		return "\nBot: Categories are [clothes, food, electronics, tools, and office supplies]\n";
	}
	
	public static String showCommands() {
		return "\nBot: Type inquire (category name) to view product selection. To view all products type inquire products."
				+ " Type recommend (category) for a recommendation on the highest rated and best value products. Type (help) to see this message again.\n";
	}
	
	public static String storeHours() {
		return "Bot: The department store is open at the hours of 9:00AM - 10:00PM\n";
	}
	
	public static String inquireProduct(String selectedCategory) {
		ProductInquiries prodInq = new ProductInquiries(selectedCategory);
		prodInq.setLocationRelativeTo(null);
		prodInq.setVisible(true);
		return "Bot: Showing you a list of products under specified category of " + selectedCategory + "\n";
	}
	public static String inquireProduct() {
		ProductInquiries prodInq = new ProductInquiries();
		prodInq.setLocationRelativeTo(null);
		prodInq.setVisible(true);
		return "Bot: Showing you a list of all products" + "\n";
	}
	public static String recommendProduct() {
		RecommendInquiries recommInq = new RecommendInquiries();
		recommInq.setLocationRelativeTo(null);
		recommInq.setVisible(true);
		return "Bot: Showing you a list of recommended products\n";
	}
	public static String recommendProduct(String category) {
		RecommendInquiries recommInq = new RecommendInquiries(category);
		recommInq.setLocationRelativeTo(null);
		recommInq.setVisible(true);
		return "Bot: Showing you a list of recommended products under specified category of " + category + "\n";
	}
	public static String searchProduct(String productName) {
		ProductInquiries searchInq = new ProductInquiries(productName);
		searchInq.setLocationRelativeTo(null);
		searchInq.setVisible(true);
		return ("Bot: Searching for product\n");
	}
	public static String noKeyWordRecognized(String message) {
		return ("Bot: I'm sorry I didn't recognize what [" + message + "] was. Type help for a list of commands I know.\n");
	}
	
	public static String storeDetails() {
		return "Bot: You can reach us at the following contact details:\r\n"
				+ "\r\n"
				+ "Phone: 556-5555\r\n"
				+ "Email: shopster@shoplike.io\r\n"
				+ "Address: 21 Negra Arroyo Lane St., Los Santos\r\n"
				+ "Our customer service hours are 9:00AM - 10:00PM.\r\n"
				+ "\r\n"
				+ "Please don't hesitate to contact us, we will be happy to help you.\r\n"
				+ "\r\n"
				+ "Best,\r\n"
				+ "Shopster\r\n";
	}
	public static String askBalance() {
		return "\nBot: Please enter your balance amount.\n";
	}
	
	public static String trackOrder(String message) {
		String[] args = message.split(" ", 2);
		boolean foundOrder = false;
		try {
		for (TrackedOrders to : TrackedOrders.allTrackedOrders) {
			System.out.println(to.getOrderID());
			if (to.getOrderID() == Integer.parseInt(args[1])) {
				foundOrder = true;
			} else {
				continue;
			}
		}
		if (foundOrder) {
			return "Bot: Your order is currently awaiting for delivery\n";
		} else {
			return "Bot: Your order does not exist\n";
		}
		} catch(NumberFormatException nfe) {
			return "Bot: Enter a valid input!\n";
		}
	}
	public static String greetBack() {
		HashMap<Integer, String> responses = new HashMap<Integer, String>();
		responses.put(1, "Hello " + Customer.name);
		responses.put(2, "Howdy " + Customer.name);
		responses.put(3, "Heya " + Customer.name);
		responses.put(4, "Yooooooooo " + Customer.name);
		responses.put(5, "What's up my bro " + Customer.name);
		Random random = new Random();
		int randIndex =  1 + (int)(Math.random() * ((responses.size() - 1) + 1));
		return ("Bot: " + responses.get(randIndex) + "\n");
	}
	
	public static String makeJokes() {
		HashMap<Integer, String> jokes = new HashMap<Integer, String>();
		jokes.put(1, "Bot: How does Moses make tea? He brews.\n");
		jokes.put(2, "Bot: Can February march? No, but April may.\n");
		jokes.put(3, "Bot: How do trees get online? They just log on!\n");
		jokes.put(4, "Bot: How do you throw a space party?, You Planet\n");
		jokes.put(5, "Bot: Two artists had an art contest. It ended in a draw!\n");
		int randIndex =  1 + (int)(Math.random() * ((jokes.size() - 1) + 1));
		return jokes.get(randIndex);
	}
	
	public static String refundPolicy() {
		return "Bot: Dear " + Customer.name + ",\r\n"
				+ "\r\n"
				+ "Thank you for reaching out to us regarding your recent purchase. We are sorry to hear that you are not satisfied with your order. We would be happy to assist you with a refund or return.\r\n"
				+ "\r\n"
				+ "To process your request, please follow these instructions:\r\n"
				+ "\r\n"
				+ "For a refund:\r\n"
				+ "\r\n"
				+ "Please provide us with your order number\r\n"
				+ "Explain the reason for your request\r\n"
				+ "We will process your refund and it will be credited back to your original payment method\r\n"
				+ "For a return:\r\n"
				+ "\r\n"
				+ "Please provide us with your order number\r\n"
				+ "Explain the reason for your request\r\n"
				+ "We will provide you with a return shipping label and instructions on how to return the item(s)\r\n"
				+ "Once we receive the returned item(s), we will process your refund or exchange\r\n"
				+ "If you have any further questions or concerns, please do not hesitate to reach out to us. We appreciate your business and apologize for any inconvenience this may have caused.\r\n"
				+ "\r\n"
				+ "Best,\r\n"
				+ "Shopster\n";
	}
	public static String askName() {
		return "Bot: Hello! I'm here to help, but first, what's your name?\n";
	}
	
	public static String thankCustomer() {
		return "Bot: Thank your for your purchase! Your balance is now " + Customer.cash + ".\n";
	}
	
}
