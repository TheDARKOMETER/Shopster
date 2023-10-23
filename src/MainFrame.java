import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.TextArea;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.w3c.dom.html.HTMLFrameElement;

import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;

public class MainFrame {
	public static String chatlog = "";
	private JFrame frmShopster;
	private static JTextField txtChatInput;
	private static JTextArea txtAreaChat;
	private JPanel panel;
	long start = System.currentTimeMillis();
	public static Timer timer;
	public Timer stimer;
	public static int Second;
	private JScrollPane sPane;
	private static JButton btnMyCart;
	private static int clientChatCounter = 0;
	private static JButton btnInquireProducts;
	private static JButton btnRecommendProducts;
	private static JButton btnStoreHours;
	private static JButton btnRefundPolicy;
	private static JButton btnContact;
	public static MainFrame mainInstance;
	public static boolean Transacting = false;
	public static boolean hasBalance = false;
	private static JButton btnTrackOrder;
	private JSeparator separator;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmShopster.setLocationRelativeTo(null);

					window.frmShopster.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		mainInstance = this;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static void renderChat() {
		txtAreaChat.setText(chatlog);
	}
	
	public static void sendChatMsg(String message) {
		chatlog += "\nYou: " + message + "\n\n";
		renderChat();
		txtChatInput.setText("");
		Pause(message);
	}
	
	public static void sendBotChatMsg(String message) {
		chatlog += message;
		renderChat();
	}
	
	public static void transact() {
		sendBotChatMsg(ChatBot.askBalance());
		Transacting = true;
	}
	
	private static void checkKeyWords(String message, int clientChatCounter) {
		if (clientChatCounter == 0) {
			Customer.name = message;
			sendBotChatMsg(ChatBot.greetingsPrompt());
			btnInquireProducts.setVisible(true);
			btnInquireProducts.setVisible(true);
			btnRecommendProducts.setVisible(true);
			btnStoreHours.setVisible(true);
			btnRefundPolicy.setVisible(true);
			btnContact.setVisible(true);
			sendBotChatMsg(ChatBot.showCategories());
			sendBotChatMsg(ChatBot.showCommands());
			btnMyCart.setEnabled(true);
			btnTrackOrder.setEnabled(true);
			return;
		} else {
		if (!Transacting) {
		if (message.equalsIgnoreCase("help")) {
			sendBotChatMsg(ChatBot.showCommands());
		}
		else if (message.equalsIgnoreCase("store hours")) {
			sendBotChatMsg(ChatBot.storeHours());
		}
		else if (message.equalsIgnoreCase("inquire clothes")) {
			sendBotChatMsg(ChatBot.inquireProduct("clothes"));
		}
		else if (message.equalsIgnoreCase("inquire products")) {
			sendBotChatMsg(ChatBot.inquireProduct());
		} 
		else if (message.equalsIgnoreCase("inquire electronics")) {
			sendBotChatMsg(ChatBot.inquireProduct("electronics"));
		} 
		else if (message.equalsIgnoreCase("inquire office supplies")) {
			sendBotChatMsg(ChatBot.inquireProduct("office supplies"));
		} else if (message.equalsIgnoreCase("inquire food")) {
			sendBotChatMsg(ChatBot.inquireProduct("food"));
		}
		else if(message.equalsIgnoreCase("recommend products")) {
			sendBotChatMsg(ChatBot.recommendProduct());
		} else if(message.equalsIgnoreCase("recommend electronics")) {
			sendBotChatMsg(ChatBot.recommendProduct("electronics"));
		} else if(message.equalsIgnoreCase("recommend office supplies")) {
			sendBotChatMsg(ChatBot.recommendProduct("office supplies"));
		} else if(message.equalsIgnoreCase("recommend food")) {
			sendBotChatMsg(ChatBot.recommendProduct("food"));
		} else if(message.equalsIgnoreCase("recommend tools")) {
			sendBotChatMsg(ChatBot.recommendProduct("tools"));
		} else if(message.equalsIgnoreCase("recommend clothes")) {
			sendBotChatMsg(ChatBot.recommendProduct("clothes"));
		}
		else if (message.toLowerCase().contains("refund") || message.toLowerCase().contains("return")) {
			sendBotChatMsg(ChatBot.refundPolicy());
		} else if (message.startsWith("search")) {
			sendBotChatMsg(ChatBot.searchProduct(message));
		}
		else if (message.equals("bot32")) {
			return;
		} else if (message.contains("contact")) {
			sendBotChatMsg(ChatBot.storeDetails());
		} else if (message.toLowerCase().contains("greetings") || message.toLowerCase().contains("hello") || message.toLowerCase().contains("hi") || message.toLowerCase().contains("hey")) {
			sendBotChatMsg(ChatBot.greetBack());
		} else if (message.toLowerCase().startsWith("track")) {
			sendBotChatMsg(ChatBot.trackOrder(message));
		} else if (message.toLowerCase().contains("joke")) {
			sendBotChatMsg(ChatBot.makeJokes());
		}
		else {
			sendBotChatMsg(ChatBot.noKeyWordRecognized(message));
		}
		} else {
			// You are now transacting
			boolean validInput = false;
			while (!validInput) {
			try {
			Customer.cash = Double.parseDouble(message);
			validInput = true;
			CartPage.transactOrder();
			Transacting = false;
			hasBalance = true;
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Enter valid input!");
				break;
			}
			}
		}
		}
	}
	
	public static void Timer(String message) {
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			// Does the actual time stuff and bot response delay	
				Second++;
				while(Second == 1) {
					checkKeyWords(message, clientChatCounter);
					clientChatCounter++;
					timer.stop();
					Second = 0;
				}
			}
		});
	}
	
	public void Start_Timer() {
		stimer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			// Does the actual time stuff and bot response delay	
				Second++;
				while(Second == 1) {
					sendBotChatMsg(ChatBot.askName());
					
					stimer.stop();
					Second = 0;
				}
			}
		});
	}
	
	public void start_pause() {
		Second = 0;
		Start_Timer();
		stimer.start();
		
	}
	
	public static void Pause(String message) {
		Second = 0;
		Timer(message);
		timer.start();
	}
	

	public void initializeChat() {
		start_pause();
		renderChat();
	}
	
	private void initialize() {
		
		// clothes
		Product.products.add(new Product("Nike Offcourt",8,"Cheer your team to victory with the Nike Offcourt Slides. Brazil-inspired graphics and colours let you rep your squad. And if that's not enough, the innovative foam midsole lets you slide from the streets to the bleachers to the beaches in all-star comfort.",2500.50,4.75,"clothes"));
		Product.products.add(new Product("NikeCourt Legacy",10,"The NikeCourt Legacy serves up style rooted in tennis culture. They are durable and comfy with heritage stitching and a retro Swoosh. When you pull these on—it's game, set, match.",5000.50,3.24,"clothes"));
		Product.products.add(new Product("Courtside Max90",15,"Show your hoops allegiance in this loose-fitting Los Angeles Lakers T-shirt. Front and back graphics depicting team specifics (like geographic coordinates and year of inception) let everyone know who your squad is, whether you're courtside or streetside.",1500.50,4,"clothes"));
		Product.products.add(new Product("Jordan x Shelflife",8,"Jordan and South Africa's coveted sneaker shop Shelflife come together on this soft, everyday tee. It's made with an all-over wash for a perfectly faded look, and the graphic on the back pays homage to the store's original stained glass window.",1800.50,5,"clothes"));
		Product.products.add(new Product("Jordan Flight",25,"Soft and comfortable, this tee features a woodblock-print inspired graphic that celebrates Jordan's famous free-throw line dunk. Rock 3:51 in style.",3500.50,5,"clothes"));

		// food
		Product.products.add(new Product("Siopao",51,"Heavy and tasty snack with a big variety of flavors to choose from",75.00,4,"food"));
		Product.products.add(new Product("Hotta Rice Pork Steak",35,"For a whole lotta nutrituion, you gotta have a lotta HottaRice.",115.00,5,"food"));
		Product.products.add(new Product("Fundae",25,"Fundae is my simple, after-work, every day reward. Only Fundae allows me to indulge and reward myself with a smooth and creamy, refreshing and fun treat that I truly deserve after a hard day’s work.",35.00,4.1,"food"));
		Product.products.add(new Product("Siomai",40,"This popular dumpling has made its way to the heart of the Filipino’s as evidenced by the hundreds of stalls, eateries, and restaurants who serve them. ",25.00,5,"food"));
		Product.products.add(new Product("One Pan Sandwich",74,"With your components of choice tucked between pieces of your favorite bread, the result is a compact way of enjoying your favorite flavors all in one bite.",85.00,4,"food"));
		
		// electronics
		Product.products.add(new Product("CYBERPOWERPC Gamer",30,"System: Intel Core i5-11400F 2.6GHz 6-Core | Intel B560 Chipset | 8GB DDR4 | 500GB PCI-E NVMe SSD | Genuine Windows 11 Home 64-bit",5500.00,4.9,"electronics"));
		Product.products.add(new Product("Galaxy Z Fold4",30,"Be adventurous, rain or shine. You don't have to sweat the forecast when you've got one of the world's first water-resistant foldable smartphones.6",15000.00,4.2,"electronics"));
		Product.products.add(new Product("Galaxy S22 Ultra",20,"Our fastest, most powerful chip ever. That means, a faster CPU and GPU compared to Galaxy S21 Ultra. It’s an epic leap for smartphone technology.",1500.00,5,"electronics"));
		Product.products.add(new Product("Super Fast Charging (25W)",30,"Give your devices the powerful charging support they deserve. Samsung 25W Wall Charger provides Super Fast Charging at up to 25W for capable devices. Use Samsung original charger with an official Samsung Type C to Type C Charging Cable for optimum results.",500.00,5,"electronics"));
		Product.products.add(new Product("QLED 4K Q70B Smart TV",30,"Quantum Processor 4K drives all-around performance, intelligently optimizing picture, sound, and more to give you a truly breathtaking viewing experience.",35000.00,4,"electronics"));

		// tools
		Product.products.add(new Product("Torque Impact Wrench",6,"The 2767 Milwaukee M18 Fuel 1/2\" High Torque Impact Wrench with Friction Ring eliminates the need to use a pneumatic tool for those stubborn and rusted bolts by delivering 1,000 ft-lbs of fastening torque",500.00,4,"tools"));
		Product.products.add(new Product("Speed Impact Driver Kit",3,"The DCF850 20V MAX Atomic 1/4 in. 3-Speed Impact Driver delivers up to 1825 in-lbs of torque in a compact design of less than 4 inches front to back.",3500.00,5,"tools"));
		Product.products.add(new Product("Cordless Brushed Impact Wrench",51,"The M18 cordless 2-speed 3/8 in. right angle impact wrench offers users maximum access, and ultimate speed and control.",2500.00,3.7,"tools"));
		Product.products.add(new Product("PacTool Snapper Shear",25,"The PacTool SS204 Snapper Shear fiber cement siding shear combines our famous precision-machined cutting head.",1500.00,5,"tools"));
		Product.products.add(new Product("Corded Shear",7,"This new shear comes with a powerful 6.8 amp Milwaukee built motor. The ergonomic tactile grip is designed for more user comfort.",800.00,4,"tools"));

		// office supplies
		Product.products.add(new Product("Fellowes Powershred",6,"The Powershred LX50 cross-cut shredder is a reliable desk side shredder for personal use.",500.00,4,"office supplies"));
		Product.products.add(new Product("Gorilla Tape Clear",5,"Crystal Clear Transparent Gorilla Tape features the strength of Gorilla Tape, in a crystal clear tape that does not yellow outdoors.",90.00,5,"office supplies"));
		Product.products.add(new Product("Clear Double Sided",25,"Gorilla Tough and Clear Mounting Tape is a clear double-sided tape that mounts in an instant.",200.00,4,"office supplies"));
		Product.products.add(new Product("Packaging Tape Clear",61,"When it’s time to pack up, be sure to choose a durable tape that’s tear- and break-resistant.",290.00,5,"office supplies"));
		Product.products.add(new Product("Sharpie Magnum Black",6,"Sanford Magnum 44 Permanent Marker - Large Chisel Point Black",60.00,4,"office supplies"));
				
				
		frmShopster = new JFrame();
		frmShopster.setTitle("Shopster");
		frmShopster.setResizable(false);
		frmShopster.getContentPane().setBackground(new Color(27, 28, 34));
		frmShopster.setBackground(Color.WHITE);
		frmShopster.setBounds(100, 100, 723, 603);
		frmShopster.setUndecorated(true);
		frmShopster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnSend = new JButton("");
		btnSend.setBorder(null);
		Image imgSource1 = new ImageIcon(this.getClass().getResource("/img/send icon.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		btnSend.setIcon(new ImageIcon(imgSource1));
		btnSend.setBounds(622, 532, 72, 44);
		btnSend.setForeground(SystemColor.desktop);
		btnSend.setBackground(new Color(33, 34, 41));
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendChatMsg(txtChatInput.getText());
			}
		});
		frmShopster.getContentPane().setLayout(null);
		frmShopster.getContentPane().add(btnSend);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(21, 100, 676, 351);
		frmShopster.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtAreaChat = new JTextArea();
		txtAreaChat.setSelectedTextColor(new Color(51, 204, 51));
	
		txtAreaChat.setCaretColor(new Color(0, 0, 0));
		txtAreaChat.setBounds(1, 101, 595, 391);
		txtAreaChat.setForeground(new Color(200, 213, 219));
		txtAreaChat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtAreaChat.setBackground(new Color(33, 34, 41));
		txtAreaChat.setEditable(false);
		txtAreaChat.setLineWrap(true);
		txtAreaChat.setWrapStyleWord(true);
		
		txtAreaChat.setText(chatlog);
		sPane = new JScrollPane(txtAreaChat,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sPane.setBounds(-12, -18, 700, 379);
		txtAreaChat.setBorder(new EmptyBorder(35, 35, 35, 35));
		JScrollBar vertical = sPane.getVerticalScrollBar();
		

		vertical.setUI(new MyScrollbarUI());
		
		
		panel.add(sPane);
		
		txtChatInput = new JTextField();
		txtChatInput.setForeground(Color.WHITE);
		txtChatInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendChatMsg(txtChatInput.getText());
				}
			}
		});
		txtChatInput.setBounds(21, 532, 603, 44);
		txtChatInput.setBackground(new Color(33, 34, 41));
		txtChatInput.setBorder(new EmptyBorder(10, 15, 10, 10));
		txtChatInput.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
			}
		});
		frmShopster.getContentPane().add(txtChatInput);
		txtChatInput.setColumns(10);
		
		btnRecommendProducts = new JButton("Recommend Products");
		btnRecommendProducts.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRecommendProducts.setBorder(null);
		btnRecommendProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendChatMsg("recommend products");
			}
		});
		btnRecommendProducts.setForeground(Color.WHITE);
		btnRecommendProducts.setBackground(SystemColor.textHighlight);
		btnRecommendProducts.setBounds(154, 472, 144, 37);
		
		btnStoreHours = new JButton("Store Hours");
		btnStoreHours.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnStoreHours.setBorder(null);
		btnStoreHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendChatMsg("store hours");
			}
		});
		btnStoreHours.setForeground(Color.WHITE);
		btnStoreHours.setBackground(SystemColor.textHighlight);
		btnStoreHours.setBounds(443, 472, 122, 37);
		
		btnRefundPolicy = new JButton("Refund Policy");
		btnRefundPolicy.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRefundPolicy.setBorder(null);
		btnRefundPolicy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendChatMsg("refund policy");
			}
		});
		btnRefundPolicy.setForeground(Color.WHITE);
		btnRefundPolicy.setBackground(SystemColor.textHighlight);
		btnRefundPolicy.setBounds(308, 472, 125, 37);

		
		btnContact = new JButton("Contact Details");
		btnContact.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnContact.setBorder(null);
		btnContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendChatMsg("contact");
			}
		});
		btnContact.setForeground(Color.WHITE);
		btnContact.setBackground(SystemColor.textHighlight);
		btnContact.setBounds(575, 472, 119, 37);
		
		btnInquireProducts = new JButton("Inquire Products");
		btnInquireProducts.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnInquireProducts.setBorder(null);
		btnInquireProducts.setBounds(22, 472, 123, 37);
		btnInquireProducts.setForeground(Color.WHITE);
		btnInquireProducts.setBackground(SystemColor.textHighlight);
		btnInquireProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendChatMsg("inquire products");
			}
		});

		//here
		frmShopster.getContentPane().add(btnInquireProducts);
		frmShopster.getContentPane().add(btnContact);
		frmShopster.getContentPane().add(btnRefundPolicy);
		frmShopster.getContentPane().add(btnStoreHours);
		frmShopster.getContentPane().add(btnRecommendProducts);
		if (clientChatCounter < 1) {
			btnInquireProducts.setVisible(false);
			btnRecommendProducts.setVisible(false);
			btnStoreHours.setVisible(false);
			btnRefundPolicy.setVisible(false);
			btnContact.setVisible(false);
		} else {
			btnInquireProducts.setVisible(true);
			btnRecommendProducts.setVisible(true);
			btnStoreHours.setVisible(true);
			btnRefundPolicy.setVisible(true);
			btnContact.setVisible(true);
		}
		btnMyCart = new JButton("");
		btnMyCart.setBorderPainted(false);
		btnMyCart.setOpaque(false);
		btnMyCart.setIcon(new ImageIcon(MainFrame.class.getResource("/img/cart icon1.png")));
		btnMyCart.setBorder(null);
		btnMyCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CartPage cpage = new CartPage(mainInstance);
				
				cpage.setVisible(true);
			}
		});
		btnMyCart.setForeground(SystemColor.textHighlight);
		btnMyCart.setBackground(Color.WHITE);
		btnMyCart.setBounds(634, 22, 60, 58);
		frmShopster.getContentPane().add(btnMyCart);
		
		btnTrackOrder = new JButton("");
		btnTrackOrder.setFocusPainted(false);
		btnTrackOrder.setOpaque(false);
		btnTrackOrder.setIcon(new ImageIcon(MainFrame.class.getResource("/img/order icon.png")));
		btnTrackOrder.setBackground(Color.WHITE);
		btnTrackOrder.setBorder(null);
		btnTrackOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderTrackFrame otf = new OrderTrackFrame();
				otf.setVisible(true);
			}
		});
		btnTrackOrder.setBounds(550, 25, 53, 53);
		frmShopster.getContentPane().add(btnTrackOrder);
		
		JLabel iblLogo = new JLabel("");
		iblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		Image imgSource = new ImageIcon(this.getClass().getResource("img/logo-black.png")).getImage().getScaledInstance(135, 135, Image.SCALE_DEFAULT);
		iblLogo.setIcon(new ImageIcon(imgSource));
		iblLogo.setBounds(33, 19, 95, 70);
		frmShopster.getContentPane().add(iblLogo);
		
		JLabel lblNewLabel = new JLabel("My Order");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblNewLabel.setForeground(SystemColor.textInactiveText);
		lblNewLabel.setBounds(545, 72, 63, 14);
		frmShopster.getContentPane().add(lblNewLabel);
		
		JLabel lblCart = new JLabel("Cart");
		lblCart.setHorizontalAlignment(SwingConstants.CENTER);
		lblCart.setForeground(SystemColor.textInactiveText);
		lblCart.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblCart.setBounds(631, 72, 63, 14);
		frmShopster.getContentPane().add(lblCart);
		
		separator = new JSeparator();
		separator.setForeground(UIManager.getColor("OptionPane.foreground"));
		separator.setBackground(UIManager.getColor("ScrollBar.trackHighlightForeground"));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(623, 28, 12, 58);
		frmShopster.getContentPane().add(separator);
		
		JLabel lblClose = new JLabel("X");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				frmShopster.dispose();

			}
		});
		lblClose.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClose.setForeground(SystemColor.controlDkShadow);
		lblClose.setBounds(686, 11, 27, 14);
		frmShopster.getContentPane().add(lblClose);
		

		
		initializeChat();
	}
}
