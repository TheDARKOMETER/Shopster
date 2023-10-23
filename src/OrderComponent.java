import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

import java.awt.EventQueue;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderComponent extends JPanel {
	/**
	 * Create the panel.
	 */
	public OrderComponent(Orders order,CartPage cartPageInstance) {
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(645, 237));
		setLayout(null);
		
		JLabel lblProductImage = new JLabel("Product Image");
		lblProductImage.setBounds(15, 19, 200, 200);
		add(lblProductImage);
		lblProductImage.setIcon(new ImageIcon(order.getProductImg()));
		
		JLabel lblProductName = new JLabel(order.getName());
		lblProductName.setForeground(new Color(0, 51, 51));
		lblProductName.setFont(new Font("Roboto Medium", Font.PLAIN, 22));
		lblProductName.setBounds(254, 42, 380, 31);
		add(lblProductName);
		
		JLabel lblPrice = new JLabel(order.getPrice()+"");
		lblPrice.setForeground(Color.GRAY);
		lblPrice.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		lblPrice.setBounds(254, 84, 102, 20);
		add(lblPrice);
		
		JSpinner spinner = new JSpinner();
		spinner.setBorder(new LineBorder(Color.LIGHT_GRAY));
		spinner.setFont(new Font("Tahoma", Font.BOLD, 14));
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				double totalPrice = 0;
				if ((int) spinner.getValue() > order.getProductStock() ) {
					JOptionPane.showMessageDialog(null, "You are ordering more than you can!");
					spinner.setValue(order.getProductStock());
				}
				order.setQuantity((int)spinner.getValue());
				for (int i = 0; i < Orders.orders.size(); i++) {
					totalPrice += Orders.orders.get(i).getTotalProductPrice();
				}
				cartPageInstance.totalPrice = totalPrice;
				cartPageInstance.lblYourTotalIs.setText("Your total is: " + totalPrice);
			}
		});
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(order.getQuantity()), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinner.setBounds(554, 126, 50, 47);
		add(spinner);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setOpaque(false);
		btnRemove.setForeground(Color.RED);
		btnRemove.setBackground(Color.WHITE);
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 11));
	

		int radius = 35;
		btnRemove.setBorder(new RoundedBorder2(radius));

		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Orders.orders.remove(order);
				removeAll();
				revalidate();
				repaint();
				cartPageInstance.refreshOrders();
				double totalPrice = 0;
				for (int i = 0; i < Orders.orders.size(); i++) {
					totalPrice += Orders.orders.get(i).getTotalProductPrice();
				}
				if (Orders.orders.size() > 0) {
					cartPageInstance.lblNewLabel.setText("Showing " + Orders.orders.size() + " Items");
					} else {
						cartPageInstance.lblNewLabel.setText("No products in your cart.");
						cartPageInstance.btnNewButton.setEnabled(false);
					}
				cartPageInstance.lblYourTotalIs.setText("Your total is: " + totalPrice);
			}
		});
		
		btnRemove.setBounds(254, 128, 115, 47);
		add(btnRemove);
	}
}
