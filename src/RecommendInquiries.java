import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.CardLayout;
import java.awt.Dimension;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RecommendInquiries extends JFrame {

	public static JPanel contentPane;
	public static RecommendInquiries recInqInstance;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductInquiries frame = new ProductInquiries();
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
	public RecommendInquiries() 
	{
		recInqInstance = this;
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 825, 693);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(5, 45, 27, 642);
		contentPane.add(panel_1);
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(24, 45, 769, 32);
		contentPane.add(panel_2);
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(781, 63, 22, 624);
		contentPane.add(panel_3);
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(15, 630, 788, 57);
		contentPane.add(panel_4);
		
		JLabel lblInquiry = new JLabel("Recommended Products");
		lblInquiry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblInquiry.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		lblInquiry.setHorizontalAlignment(SwingConstants.CENTER);
		lblInquiry.setBounds(24, 0, 769, 78);
		lblInquiry.setForeground(new Color(0, 0, 0));
		contentPane.add(lblInquiry);
		
		JPanel panel = new JPanel();
		panel.setForeground(SystemColor.desktop);
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(0, 1, 15 ,15));
		panel.setAutoscrolls(true);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JScrollPane jsp = new JScrollPane(panel);
		jsp.setBackground(Color.WHITE);
		jsp.setBounds(24, 66, 769, 578);

		// scrollbar
		JScrollBar vertical = jsp.getVerticalScrollBar();
		vertical.setValue(vertical.getMinimum());

		vertical.setUI(new MyScrollbarUI());
		int increment = 30;
		vertical.setUnitIncrement(increment);		
		
		contentPane.add(jsp);
		
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
		lblClose.setBounds(789, 0, 36, 27);
		contentPane.add(lblClose);
		for (int i = 0; i < Product.products.size(); i++) {
			Product.recommendedProducts.add(Product.products.get(i));
		}
		while (!Product.recommendedProducts.isEmpty()) {
			ProductComponent dynamicComponent = new ProductComponent(Product.recommendedProducts.poll());
			panel.add(dynamicComponent);
		}
	}
	
	public RecommendInquiries(String category) {
		recInqInstance = this;
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 800, 544);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Recommended Products");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setForeground(SystemColor.desktop);
		panel.setBorder(null);
		panel.setBackground(UIManager.getColor("ComboBox.disabledBackground"));
		panel.setLayout(new GridLayout(0, 1, 15 ,15));
		panel.setAutoscrolls(true);

	


		
		JScrollPane jsp = new JScrollPane(panel);
		contentPane.add(jsp, BorderLayout.CENTER);
		for (int i = 0; i < Product.products.size(); i++) {
			if (Product.products.get(i).getCategory() == category) {
				Product.recommendedProducts.add(Product.products.get(i));
			} 
		}
		while (!Product.recommendedProducts.isEmpty()) {
			ProductComponent dynamicComponent = new ProductComponent(Product.recommendedProducts.poll());
			panel.add(dynamicComponent);
			}
		

//		ProductComponent product1 = new ProductComponent();
//		contentPane.add(product1); 
//		ProductComponent product2 = new ProductComponent();
//		contentPane.add(product2);

	}
	
	public static void refresh() {
		recInqInstance.dispose();
	}
	
}
