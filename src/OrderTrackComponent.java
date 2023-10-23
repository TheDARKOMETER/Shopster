import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.security.KeyStore.Entry;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

public class OrderTrackComponent extends JPanel {

	/**
	 * Create the panel.
	 */
	private String orderTracks = "";
	public OrderTrackComponent(TrackedOrders trackedOrder) {
		setBorder(new LineBorder(SystemColor.textHighlight));
		setPreferredSize(new Dimension(635, 300));
		setBackground(Color.WHITE);
		setLayout(null);
		for (int i = 0; i < trackedOrder.getProductNames().size(); i++) {
			orderTracks += (trackedOrder.getProductNames().get(i) + "\t" + trackedOrder.getPrices().get(i) + " (" + trackedOrder.getQuantities().get(i) + "x)\n");
		}
		
		JLabel lblOrderID = new JLabel("Order ID: " + trackedOrder.getOrderID());
		lblOrderID.setForeground(new Color(0, 0, 51));
		lblOrderID.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		lblOrderID.setBounds(28, 23, 577, 14);
		add(lblOrderID);
		JTextArea txtAreaChat = new JTextArea();
		txtAreaChat.setEditable(false);
		txtAreaChat.setBounds(1, 101, 595, 391);
		txtAreaChat.setForeground(Color.BLACK);
		txtAreaChat.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtAreaChat.setBackground(Color.WHITE);
		txtAreaChat.setLineWrap(true);
		txtAreaChat.setText(orderTracks);
		JScrollPane sPane = new JScrollPane(txtAreaChat,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sPane.setBounds(28, 50, 577, 186);
		txtAreaChat.setBorder(new EmptyBorder(15, 15, 15, 15));

		JScrollBar vertical = sPane.getVerticalScrollBar();
		vertical.setValue(vertical.getMinimum());

		vertical.setUI(new MyScrollbarUI());
		int increment = 30;
		vertical.setUnitIncrement(increment);
		
		
		add(sPane);
		
		JLabel lblNewLabel = new JLabel("To be delivered");
		lblNewLabel.setForeground(SystemColor.textInactiveText);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBorder(null);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(30, 254, 150, 28);
		add(lblNewLabel);
		
		JLabel lblTotalPrice = new JLabel("Total Price: " + trackedOrder.getTotalPrice());
		lblTotalPrice.setForeground(new Color(0, 153, 51));
		lblTotalPrice.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalPrice.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		lblTotalPrice.setBounds(343, 247, 262, 42);
		add(lblTotalPrice);
	}
}
