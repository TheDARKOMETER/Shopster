import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderTrackFrame extends JFrame {

	private JPanel contentPane;
	public static OrderTrackFrame orderTrackFrameInstance;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderTrackFrame frame = new OrderTrackFrame();
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
	public OrderTrackFrame() {
		orderTrackFrameInstance = this;
		setUndecorated(true);
		boolean hasOrders = false;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 721, 534);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 84, 510, 391);
		JScrollPane sPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.setLayout(new GridLayout( 0, 1, 10, 10));
		
		JLabel lblNoOrders = new JLabel("No Orders Found");
		lblNoOrders.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNoOrders.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(5, 45, 27, 642);
		contentPane.add(panel_1);
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(24, 63, 769, 44);
		contentPane.add(panel_2);
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(670, 63, 62, 624);
		contentPane.add(panel_3);
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(15, 470, 788, 265);
		contentPane.add(panel_4);
		
		sPane.setBounds(30, 94, 653, 389);
		contentPane.add(sPane);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollBar vertical = sPane.getVerticalScrollBar();
		vertical.setValue(vertical.getMinimum());

		vertical.setUI(new MyScrollbarUI());
		int increment = 30;
		vertical.setUnitIncrement(increment);
		JLabel lblTitle = new JLabel("Your Orders");
		lblTitle.setBackground(Color.WHITE);
		lblTitle.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	
		lblTitle.setForeground(new Color(0, 0, 0));
		lblTitle.setBounds(0, 22, 705, 44);
		contentPane.add(lblTitle);
		
		JLabel lblClose = new JLabel("X");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setForeground(SystemColor.controlDkShadow);
		lblClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClose.setBounds(685, 0, 36, 27);
		contentPane.add(lblClose);
		
		for (int i = 0; i < TrackedOrders.allTrackedOrders.size(); i++) {
			OrderTrackComponent dynamicComponent = new OrderTrackComponent(TrackedOrders.allTrackedOrders.get(i));
			panel.add(dynamicComponent);
			hasOrders = true;
		}
		
		if (!hasOrders) {
			panel.add(lblNoOrders);
		}
	}
	
	public static void refresh() {
		orderTrackFrameInstance.dispose();
	}
}
