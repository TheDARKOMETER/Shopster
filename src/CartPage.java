import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CartPage extends JFrame {
	public static double totalPrice = 0;
	private JPanel contentPane;
	public static CartPage cartPageInstance;
	public JLabel lblYourTotalIs;
	public JLabel lblNewLabel;
	private JScrollPane sPane;
	private JPanel panel;
	public JButton btnNewButton;
	private JLabel lblClose;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CartPage frame = new CartPage();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CartPage(MainFrame mainInstance) {
		cartPageInstance = this;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 766, 676);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setUndecorated(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(5, 45, 40, 529);
		contentPane.add(panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(718, 63, 85, 624);
		contentPane.add(panel_3);
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(15, 548, 788, 27);
		contentPane.add(panel_4);
		
		JLabel lblMain = new JLabel("Your Cart");
		lblMain.setFont(new Font("Roboto Medium", Font.PLAIN, 21));
		lblMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblMain.setBounds(5, 0, 693, 78);
		lblMain.setForeground(new Color(0, 0, 0));
	
		contentPane.add(lblMain);
		lblNewLabel = new JLabel("Showing Items");

		if (Orders.orders != null) {
		lblNewLabel = new JLabel("Showing " + Orders.orders.size() + " Items");
		} else {
		lblNewLabel = new JLabel("No products in your cart.");
		}
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(55, 97, 331, 33);
		contentPane.add(lblNewLabel);
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(16, 103, 769, 27);
		contentPane.add(panel_2);
		btnNewButton = new JButton("Checkout");
		btnNewButton.setBorder(null);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!MainFrame.hasBalance) {
				JOptionPane.showMessageDialog(null, "\nShopster chat bot is asking for your balance.");
				MainFrame.transact();
				setVisible(false);
				} else {
					transactOrder();
				
				}
			}
		});
		btnNewButton.setBounds(554, 578, 161, 45);
		contentPane.add(btnNewButton);
		if (Orders.orders.size() == 0) {
			btnNewButton.setEnabled(false);
		} else {
			btnNewButton.setEnabled(true);
		}
		
		panel = new JPanel();
		
		panel.setRequestFocusEnabled(false);
		panel.setForeground(SystemColor.desktop);
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setAutoscrolls(true);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		sPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.setLayout(new GridLayout( 0, 1, 15, 15));
		sPane.setBounds(39, 127, 686, 429);
		contentPane.add(sPane);
		
		loadOrders();
		lblYourTotalIs = new JLabel("Your total is: " + totalPrice);
		lblYourTotalIs.setForeground(Color.DARK_GRAY);
		lblYourTotalIs.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		lblYourTotalIs.setBounds(36, 581, 331, 33);
		contentPane.add(lblYourTotalIs);
		
		lblClose = new JLabel("X");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setForeground(SystemColor.controlDkShadow);
		lblClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClose.setBounds(730, 0, 36, 27);
		contentPane.add(lblClose);

		JScrollBar vertical = sPane.getVerticalScrollBar();
		vertical.setValue(vertical.getMinimum());

		vertical.setUI(new MyScrollbarUI());
		int increment = 30;
		vertical.setUnitIncrement(increment);
	}
	
	public CartPage() {
		// TODO Auto-generated constructor stub
	}

	public static void transactOrder() {
		
		if (Customer.cash < totalPrice) {
			JOptionPane.showMessageDialog(null, "Insufficient Funds. Consider removing some items from your cart");
		} else {
			TrackedOrders addTrackOrder;
			ArrayList<String> productNames = new ArrayList<String>();
			ArrayList<Integer> quantities = new ArrayList<Integer>();
			ArrayList<Double> productPrices = new ArrayList<Double>();
			for (int i = 0; i < Orders.orders.size(); i++) {
				productNames.add(Orders.orders.get(i).getName());
				quantities.add(Orders.orders.get(i).getQuantity());
				productPrices.add(Orders.orders.get(i).getPrice());
				Product productOrdered = Product.products.get(Orders.orders.get(i).getProductOrderIndex());
				productOrdered.setStockAmount(productOrdered.getStockAmount() - Orders.orders.get(i).getQuantity());
			}
			addTrackOrder = new TrackedOrders(quantities, productNames, productPrices, totalPrice);
			TrackedOrders.allTrackedOrders.add(addTrackOrder);
			JOptionPane.showMessageDialog(null, "Purchase successful.");
			Customer.cash -= totalPrice;
			totalPrice = 0;
			MainFrame.sendBotChatMsg(ChatBot.thankCustomer());
			if (RecommendInquiries.recInqInstance != null) {
			RecommendInquiries.refresh();
			} if (ProductInquiries.productInqInstance != null) {
			ProductInquiries.refresh();
			} if (OrderTrackFrame.orderTrackFrameInstance != null) {
				OrderTrackFrame.refresh();
			}
			Orders.orders.clear();
			cartPageInstance.dispose();
		}
	}
	
	public void loadOrders() {
		for (int i = 0; i < Orders.orders.size(); i++) {
			OrderComponent dynamicComponent = new OrderComponent(Orders.orders.get(i), cartPageInstance);
			panel.add(dynamicComponent);
			totalPrice += Orders.orders.get(i).getTotalProductPrice();
		}
	}
	
	public void refreshOrders() {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		loadOrders();
	}
	
}
