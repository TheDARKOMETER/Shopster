import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
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
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductInquiries extends JFrame {

	private JPanel contentPane;
	private ProductComponent dynamicComponent;
	public static ProductInquiries productInqInstance;
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
	public ProductInquiries() 
	{
		productInqInstance = this;
		setBackground(new Color(27, 28, 34));
		setUndecorated(true);

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 819, 725);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(new Color(255, 255, 255));
	
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(5, 45, 27, 642);
		contentPane.add(panel_1);
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(24, 63, 769, 27);
		contentPane.add(panel_2);
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(781, 63, 22, 624);
		contentPane.add(panel_3);
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(15, 677, 788, 58);
		contentPane.add(panel_4);
		
		JLabel lblInquiry = new JLabel("Products Available");
		lblInquiry.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
		lblInquiry.setHorizontalAlignment(SwingConstants.CENTER);
		lblInquiry.setBounds(24, 0, 760, 78);
		lblInquiry.setForeground(new Color(0, 0, 0));
		contentPane.add(lblInquiry);
		
		JPanel panel = new JPanel();
		panel.setRequestFocusEnabled(false);
		panel.setForeground(SystemColor.desktop);
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(0, 1, 15 ,15));
		panel.setAutoscrolls(true);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JScrollPane jsp = new JScrollPane(panel);
		jsp.setBackground(Color.WHITE);
		jsp.setBounds(24, 81, 769, 606);

		// scrollbar
		JScrollBar vertical = jsp.getVerticalScrollBar();
		vertical.setValue(vertical.getMinimum());

		vertical.setUI(new MyScrollbarUI());
		int increment = 30;
		vertical.setUnitIncrement(increment);
	


		jsp.setViewportBorder(null);
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
		lblClose.setBounds(783, 0, 36, 27);
		contentPane.add(lblClose);
		for (int i = 0; i < Product.products.size(); i++) {
			ProductComponent dynamicComponent = new ProductComponent(Product.products.get(i));
			panel.add(dynamicComponent);
		}
	}
	
	public ProductInquiries(String category) {
		productInqInstance = this;
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 800, 544);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Products Available");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setForeground(SystemColor.desktop);
		panel.setBorder(null);
		panel.setBackground(UIManager.getColor("ComboBox.disabledBackground"));
		panel.setLayout(new GridLayout(0, 1, 15 ,15));
		panel.setAutoscrolls(true);

	


		boolean productFound = false;
		JScrollPane jsp = new JScrollPane(panel);
		contentPane.add(jsp, BorderLayout.CENTER);
		if (category.startsWith("search")) {
			for (int i = 0; i < Product.products.size(); i++) {
				String[] prodName = category.split(" ", 2);
				dynamicComponent = new ProductComponent(Product.products.get(i));
				if (Product.products.get(i).getName().toLowerCase().contains(prodName[1].toLowerCase())) {
					panel.add(dynamicComponent);
					productFound = true;
				}
		}
		} else {
			for (int i = 0; i < Product.products.size(); i++) {
				dynamicComponent = new ProductComponent(Product.products.get(i));
				if (Product.products.get(i).getCategory() == category) {
					panel.add(dynamicComponent);
					productFound = true;
				}
		}
		}
		JLabel lblNewLabel_1 = new JLabel("No Products Found");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 65));
		if(!productFound) {
			panel.add(lblNewLabel_1);
		}
//		ProductComponent product1 = new ProductComponent();
//		contentPane.add(product1); 
//		ProductComponent product2 = new ProductComponent();
//		contentPane.add(product2);

	}
	public static void refresh() {
		productInqInstance.dispose();
	}
	
	
	
}
