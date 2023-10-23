import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;

public class ProductComponent extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProductComponent(Product products) {
		setForeground(UIManager.getColor("InternalFrame.resizeIconShadow"));
		setBorder(new LineBorder(SystemColor.controlHighlight));
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(728, 256));
		setLayout(null);
		
		JLabel lblProductName = new JLabel(products.getName());
		lblProductName.setForeground(new Color(0, 51, 51));
		lblProductName.setFont(new Font("Roboto Medium", Font.PLAIN, 22));
		lblProductName.setBounds(253, 25, 266, 31);
		add(lblProductName);
		
		JLabel lblProductPrice = new JLabel("₱" + products.getPrice());
		lblProductPrice.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProductPrice.setForeground(new Color(73, 183, 63));
		lblProductPrice.setFont(new Font("Roboto", Font.BOLD, 20));
		lblProductPrice.setBounds(537, 30, 165, 20);
		add(lblProductPrice);
		
		// Product Image
		JLabel lblProductImage = new JLabel("Product Image:" );
		lblProductImage.setBackground(Color.LIGHT_GRAY);
		Image imgSource = new ImageIcon(this.getClass().getResource("img/"+products.getName()+".png")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		lblProductImage.setIcon(new ImageIcon(imgSource));
		lblProductImage.setBounds(29, 29, 200, 200);
		add(lblProductImage);
		
		JTextArea txtDescription = new JTextArea();
		txtDescription.setForeground(new Color(105, 105, 105));
		txtDescription.setWrapStyleWord(true);
		txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDescription.setLineWrap(true);
		txtDescription.setText(products.getDescription());
		txtDescription.setEditable(false);
		txtDescription.setBounds(252, 118, 447, 51);
		add(txtDescription);
		
		String lblRatingText = "Product Rating: " + products.getRating();
		String stars = "";
		
		JSpinner spnrQuantity = new JSpinner();
		spnrQuantity.setBorder(new LineBorder(Color.LIGHT_GRAY));
		spnrQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		
		spnrQuantity.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if ((int) spnrQuantity.getValue() > products.getStockAmount()) {
					spnrQuantity.setValue(products.getStockAmount());
					JOptionPane.showMessageDialog(null, "You are ordering more than the current stock amount.");
					return;
				}
			}
		});
		spnrQuantity.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spnrQuantity.setBounds(648, 184, 51, 45);
		add(spnrQuantity);
		
		JButton btnBuy = new JButton("Add to cart");
		btnBuy.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuy.setForeground(SystemColor.WHITE);
		btnBuy.setBorder(null);
		btnBuy.setBackground(new Color(0));
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Orders.orders.size() == 0) {
				Orders ord = new Orders((int)spnrQuantity.getValue(), products.getName(), imgSource, products.getPrice(), products.getStockAmount(), Product.products.indexOf(products));
				addItemToCart(ord);
				} else {
					Orders ord = new Orders((int)spnrQuantity.getValue(), products.getName(), imgSource, products.getPrice(), products.getStockAmount(), Product.products.indexOf(products));
					for (int i = 0; i < Orders.orders.size(); i++) {
						if (Orders.orders.get(i).getName().equals(ord.getName())) {
							JOptionPane.showMessageDialog(null, "You already have that item in your cart.");
							return;
						} else {
							continue;
						}
					}
					addItemToCart(ord);
				}
			}
		});
		btnBuy.setBounds(253, 184, 165, 45);
		JLabel lblStock = new JLabel("Stock Qty: " + products.getStockAmount());
		lblStock.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStock.setBounds(620, 68, 82, 25);

		if (products.getStockAmount() == 0) {
			btnBuy.setEnabled(false);
			spnrQuantity.setEnabled(false);
			lblStock.setText("Out of stock!");
			lblStock.setForeground(Color.RED);
		}

		add(lblStock);
		add(btnBuy);
		
//		for (int i = 0; i < (int) products.getRating(); i++) {
//			stars += "🌟";
//		}
		
		for (int i = 0; i < (int) products.getRating(); i++) {
			stars += "🌟";
		}
		
				
				JLabel lblProductCategory = new JLabel(products.getCategory());
				lblProductCategory.setHorizontalAlignment(SwingConstants.CENTER);
				lblProductCategory.setBorder(null);
				lblProductCategory.setForeground(SystemColor.textHighlight);
				lblProductCategory.setBounds(253, 67, 95, 27);
				add(lblProductCategory);
				lblProductCategory.setBackground(UIManager.getColor("MenuBar.highlight"));
				lblProductCategory.setOpaque(true);
				int radius = 27;
				lblProductCategory.setBorder(new RoundedBorder(radius));
				lblProductCategory.setFont(new Font("Roboto", Font.PLAIN, 12));
				
						JLabel lblRating = new JLabel("Rating: " + products.getRating());
						lblRating.setBounds(364, 73, 82, 14);
						add(lblRating);
						lblRating.setFont(new Font("Tahoma", Font.BOLD, 11));
						JLabel lblStars = new JLabel(stars);
						lblStars.setForeground(new Color(255, 128, 0));
						lblStars.setBounds(437, 74, 102, 14);
						add(lblStars);
		

	}
	public void addItemToCart(Orders ord) {
		Orders.orders.add(ord);
		JOptionPane.showMessageDialog(null, "Added item into cart.");
	}
}
