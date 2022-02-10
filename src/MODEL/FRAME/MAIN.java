package MODEL.FRAME;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import org.json.JSONObject;
import org.w3c.dom.Document;

import MODEL.AUTH;
import MODEL.ENDPOINT;
import MODEL.REQUEST;
import MODEL.TRUYENMOINHAT;
import MODEL.FILE.FILESTORE;
import MODEL.IMAGE.CHAPTER;
import MODEL.IMAGE.IMAGE;
import MODEL.IMAGE.MANGA;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ItemListener;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;
import java.util.regex.Pattern;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JSeparator;
import java.awt.Font;

public class MAIN {

	private JFrame frame;
	private CardLayout layout;
	private JPanel panel_1;
	private JTextField textManga1;
	private JTextField textLink1;
	private JTextField textChap1;
	private JTextField textImg1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table1;
	private JRadioButton rdbtnNovel1;
	private JRadioButton rdbtnManga1;
	private JLabel lblMessage1;
	private DefaultTableModel tableModel1;
	private DefaultTableModel tableModel2;

	private Map<Integer, MANGA> list1;
	private Map<Integer, ExecutorService> multiManga;

	private Map<Integer, CHAPTER> list2;
	private volatile int chapIndex = 1;
	private JCheckBox chckbxSaveOpts1;
	private static int mangaIndex = 1;
	public static int maxThread = 0;
	public static int currentThread = 0;

	private static volatile MAIN main;
	private JTextField textText1;
	private JTextArea textAreaError;
	private JTable table2;
	private JTextField textImage2;
	private JTextField textText2;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextArea textAreaChapterList2;
	private JRadioButton rdbtnNovels2;
	private JRadioButton rdbtnManga2;

	private String root2 = "";
	private String host2 = "";
	private JTextArea textAreaError2;
	private JLabel lblRoot2;

	private String root1 = "";
	private JLabel lblRoot1;
	private JTextField textAjax3;
	private JMenuItem mntmENDPOINT;
	private JButton btnAdd2;
	private JTextField textEndpoint1;
	private JTextField textEndpoint2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MAIN window = new MAIN();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});

		MAIN.getInstance().show();
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	private MAIN() {
		initialize();
		list1 = new HashMap<>();
		list2 = new HashMap<Integer, CHAPTER>();
		multiManga = new HashMap<>();

		tableModel1 = (DefaultTableModel) table1.getModel();
		tableModel1.setRowCount(0);
		tableModel2 = (DefaultTableModel) table2.getModel();
		tableModel2.setRowCount(0);

		layout = (CardLayout) panel_1.getLayout();

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		panel.setForeground(new Color(0, 191, 255));
		panel_1.add(panel, "name_41423230318400");
		panel.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("3 Ngày - 15k");
		lblNewLabel_5.setForeground(Color.CYAN);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(83, 49, 196, 177);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel("7 Ngày - 25k");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setForeground(Color.CYAN);
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_5_1.setBounds(344, 49, 196, 177);
		panel.add(lblNewLabel_5_1);

		JLabel lblNewLabel_5_1_1 = new JLabel(
				"<html><body><center>Full - 99k <br>từ tháng sau chỉ còn 15k</center></body></html>");
		lblNewLabel_5_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1_1.setForeground(Color.CYAN);
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_5_1_1.setBounds(616, 49, 233, 177);
		panel.add(lblNewLabel_5_1_1);

		JButton btnNewButton_4 = new JButton("Nâng cấp ngay");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_4.setForeground(new Color(0, 0, 255));
		btnNewButton_4.setBackground(new Color(250, 240, 230));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upgrade_quotas();
			}
		});
		btnNewButton_4.setBounds(323, 248, 217, 52);
		panel.add(btnNewButton_4);

		if (new File("uploads/$Asw!dn.dat").getName().equalsIgnoreCase("$Asw!dn.dat")) {
			setRootFolder();
		}
		refreshENDPOINT(0);
	}

	private void setRootFolder() {
		// TODO Auto-generated method stub
		try {
			FileInputStream fileInputStream = new FileInputStream("uploads/$Asw!dn.dat");
			String s = "";
			while (fileInputStream.available() != 0) {
				s += (char) fileInputStream.read();
			}
			FILESTORE.$_r = s.trim();
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	synchronized public static MAIN getInstance() {
		if (main == null)
			main = new MAIN();
		return main;
	}

	public void show() {
		frame.setVisible(true);
	}

	public void hide() {
		frame.setVisible(false);
	}

	public void setDialog(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Home");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("uploads\\logo.jpg"));
		frame.setBounds(100, 100, 1037, 637);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1021, 22);
		panel.add(menuBar);

		JMenu mnNewMenu = new JMenu("Account");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Detail");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ACCOUNTDETAILS.getInstance().show();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = new File("uploads/aasnfa.dat");
				if (f.exists() && f.getName().equalsIgnoreCase("aasnfa.dat")) {
					f.delete();
				}
				hide();
				new LOGIN();
			}
		});

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Change Password");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePassword.getInstance().show();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);
		mnNewMenu.add(mntmLogout);

		JMenu mnNewMenu_1 = new JMenu("Advanced");
		menuBar.add(mnNewMenu_1);

		mntmENDPOINT = new JMenuItem("Refresh ENDPOINT");
		mntmENDPOINT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmENDPOINT.setEnabled(false);
				refreshENDPOINT(1);
			}
		});
		mnNewMenu_1.add(mntmENDPOINT);

		JSeparator separator = new JSeparator();
		mnNewMenu_1.add(separator);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Change root folder");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fZ1 = new JFileChooser();
				fZ1.setCurrentDirectory(new File(FILESTORE.$_r));
				fZ1.setDialogTitle("Choose 1 folder you like!");

				fZ1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if (fZ1.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					FILESTORE.$_r = fZ1.getSelectedFile().getAbsolutePath();
					JOptionPane.showMessageDialog(frame, "Success!");

					try {
						FileOutputStream fileOutputStream = new FileOutputStream("uploads/$Asw!dn.dat");
						fileOutputStream.write(FILESTORE.$_r.getBytes());
						fileOutputStream.flush();
						fileOutputStream.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);

		JMenu mnNewMenu_2 = new JMenu("Contact | Donate");
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Email");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame,
						"Email1: admin@truyenmoinhat.xyz\r\nEmail2: shiki@animenews.life\r\n Please write subject before send mail to me and use English, Vietnamese, Jappan or Korea");
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Facebook");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(frame,
							"Email1: admin@truyenmoinhat.xyz\r\nEmail2: shiki@animenews.life\r\n Please write subject before send mail to me and use English, Vietnamese, Jappan or Korea");
					Desktop.getDesktop().browse(new URI("https://www.facebook.com/truyenmoinhatz/"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 22, 1021, 29);
		panel.add(toolBar);

		JButton btnNewButton = new JButton("CRAW MANGA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel_1, "name_212772046042900");
			}
		});
		toolBar.add(btnNewButton);

		JButton btnNewButton_2 = new JButton("CRAW BY CHAP");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel_1, "name_212783585074900");
			}
		});
		toolBar.add(btnNewButton_2);

		JButton btnNewButton_1 = new JButton("GET LINK FROM AJAX SITE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel_1, "name_212786254573800");
			}
		});
		toolBar.add(btnNewButton_1);

		JButton btnNewButton_3 = new JButton("Donate to author");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel_1, "name_35271379709700");
			}
		});
		toolBar.add(btnNewButton_3);

		panel_1 = new JPanel();
		panel_1.setBounds(0, 52, 1021, 546);
		panel.add(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));

		JPanel panel_manga = new JPanel();
		panel_1.add(panel_manga, "name_212772046042900");
		panel_manga.setLayout(null);

		JLabel lblNewLabel = new JLabel("Manga Name ( not contain special character like $, !, @...)");
		lblNewLabel.setBounds(10, 11, 338, 27);
		panel_manga.add(lblNewLabel);

		textManga1 = new JTextField();
		textManga1.setText("TENSEI SHITARA KENDESHITA");
		textManga1.setBounds(10, 36, 338, 24);
		panel_manga.add(textManga1);
		textManga1.setColumns(10);

		textLink1 = new JTextField();
		textLink1.setText("https://lnvs.net/tensei-shitara-kendeshita.html");
		textLink1.setColumns(10);
		textLink1.setBounds(10, 98, 338, 24);
		panel_manga.add(textLink1);

		JLabel lblLinkMangalink = new JLabel("Link manga ( full link contains chapters)");
		lblLinkMangalink.setBounds(10, 71, 338, 27);
		panel_manga.add(lblLinkMangalink);

		JLabel lblSelectorChap = new JLabel("Chapter selector (requeired)");
		lblSelectorChap.setBounds(10, 129, 300, 27);
		panel_manga.add(lblSelectorChap);

		textChap1 = new JTextField();
		textChap1.setText("#list-chapter .list-chapter li a\t");
		textChap1.setColumns(10);
		textChap1.setBounds(10, 163, 338, 24);
		panel_manga.add(textChap1);

		JLabel lblSelectorImagerequeired = new JLabel("Image selector (requeired)");
		lblSelectorImagerequeired.setBounds(10, 198, 160, 27);
		panel_manga.add(lblSelectorImagerequeired);

		textImg1 = new JTextField();
		textImg1.setText("#chapter-c img\t");
		textImg1.setColumns(10);
		textImg1.setBounds(10, 226, 153, 24);
		panel_manga.add(textImg1);

		rdbtnManga1 = new JRadioButton("Manga");
		rdbtnManga1.setSelected(true);
		rdbtnManga1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textText1.setEnabled(false);
					textEndpoint1.setEnabled(false);
				}
			}
		});
		buttonGroup.add(rdbtnManga1);
		rdbtnManga1.setBounds(67, 257, 109, 23);
		panel_manga.add(rdbtnManga1);

		rdbtnNovel1 = new JRadioButton("Novel");
		rdbtnNovel1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textText1.setEnabled(true);
					textEndpoint1.setEnabled(true);
				}
			}
		});
		buttonGroup.add(rdbtnNovel1);
		rdbtnNovel1.setBounds(194, 257, 109, 23);
		panel_manga.add(rdbtnNovel1);

		JButton btnAdd1 = new JButton("Add queue");
		btnAdd1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MANGA manga = getForm1();
				if (!(manga != null))
					return;

				list1.put(mangaIndex, manga);
				manga.setRoot(root1.isEmpty() ? FILESTORE.$_r + (manga.getType() ? "/novels" : "/manga") : root1);

				manga.setIndex(mangaIndex);

				Map<String, Object> mangaBody = new HashMap<>();
				mangaBody.put("manga", manga.getManga());
				mangaBody.put("link", manga.getLink());
				mangaBody.put("selector", manga.getSelector());

				JSONObject jsonObject = new JSONObject(mangaBody);
				RequestBody body = new FormBody.Builder().addEncoded("DATA", jsonObject.toString()).build();

				int totalChap = 0;
				List<CHAPTER> chaps = ENDPOINT.getChapManga(body, ENDPOINT.CHAP, null);
				if (chaps != null) {
					manga.setListChap(chaps);
					totalChap = chaps.size();
					tableModel1
							.addRow(new Object[] { mangaIndex++, manga.getManga(), totalChap + " chapters", "0%", 0 });
				} else {
					setErrorResponse1(manga.getManga() + "=> not found chaps");
				}
			}
		});
		btnAdd1.setBounds(140, 413, 109, 23);
		panel_manga.add(btnAdd1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(358, 36, 653, 301);
		panel_manga.add(scrollPane);

		table1 = new JTable();
		table1.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "POS", "MANGA", "DETECH CHAP", "STATUS", "Img downloaded" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, Integer.class, String.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table1.getColumnModel().getColumn(0).setPreferredWidth(15);
		table1.getColumnModel().getColumn(1).setPreferredWidth(279);
		table1.getColumnModel().getColumn(2).setPreferredWidth(89);
		table1.getColumnModel().getColumn(3).setPreferredWidth(60);
		scrollPane.setViewportView(table1);

		JButton btnRefesh1 = new JButton("Refesh");
		btnRefesh1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh1();
			}
		});
		btnRefesh1.setBounds(259, 413, 89, 23);
		panel_manga.add(btnRefesh1);

		lblMessage1 = new JLabel("");
		lblMessage1.setForeground(Color.RED);
		lblMessage1.setBounds(10, 447, 338, 24);
		panel_manga.add(lblMessage1);

		JLabel lblNewLabel_3 = new JLabel("Type");
		lblNewLabel_3.setBounds(10, 258, 51, 19);
		panel_manga.add(lblNewLabel_3);

		JButton btnEdit1 = new JButton("EDIT");
		btnEdit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table1.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(frame, "PLease choose 1 manga");
					return;
				}
				CHAPEDIT.getInstance().show(list1.get(table1.getValueAt(row, 0)));

			}
		});
		btnEdit1.setBounds(902, 348, 109, 23);
		panel_manga.add(btnEdit1);

		JButton btnBegin1 = new JButton("Begin");
		btnBegin1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table1.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(frame, "Please choose 1 manga");
					return;
				}
				MANGA manga = list1.get(table1.getValueAt(row, 0));

				if (manga.getStatus().equalsIgnoreCase("NOTRUN")) {
					if (currentThread < maxThread) {
						new Thread() {
							public void run() {
								setErrorResponse1(manga.getManga() + "->is running");
								currentThread++;
								manga.setStatus("RUNNING");

								ExecutorService executorService = Executors.newFixedThreadPool(10);
								multiManga.put(manga.getIndex(), executorService);
								new TRUYENMOINHAT().beginCraw(manga, executorService);
								executorService.shutdown();
							};
						}.start();
					} else {
						JOptionPane.showMessageDialog(frame, "Maximum 2 manga 1 time run");
					}

				}
			}
		});
		btnBegin1.setBounds(803, 348, 89, 23);
		panel_manga.add(btnBegin1);

		JButton btnRemove1 = new JButton("Remove");
		btnRemove1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table1.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(frame, "Please choose 1 manga");
					return;
				}
				Integer i = (Integer) table1.getValueAt(row, 0);
				MANGA manga = list1.get(i);
				if (manga.getStatus().equalsIgnoreCase("RUNNING")) {
					if (currentThread > 0)
						currentThread--;
				}
				list1.remove(i);
				tableModel1.removeRow(row);
			}
		});
		btnRemove1.setBounds(607, 349, 89, 23);
		panel_manga.add(btnRemove1);

		chckbxSaveOpts1 = new JCheckBox("Save option");
		chckbxSaveOpts1.setBounds(10, 285, 124, 23);
		panel_manga.add(chckbxSaveOpts1);

		JButton btnStop1 = new JButton("Stop");
		btnStop1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table1.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(frame, "Please choose 1 manga");
					return;
				}
				MANGA manga = list1.get(table1.getValueAt(row, 0));
				if (!manga.getStatus().equalsIgnoreCase("COMPLETE")) {
					if (currentThread > 0)
						currentThread--;
					manga.setStatus("STOP");
					try {
						multiManga.get(manga.getIndex()).shutdownNow();
					} catch (Exception e2) {
						// TODO: handle exception
					}
					multiManga.remove(manga.getIndex());
					setErrorResponse1(manga.getManga() + " stopped!");
				}
			}
		});
		btnStop1.setBounds(706, 348, 89, 23);
		panel_manga.add(btnStop1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(358, 383, 653, 115);
		panel_manga.add(scrollPane_1);

		textAreaError = new JTextArea();
		textAreaError.setText("Here is where error appear\r\n");
		textAreaError.setMargin(new Insets(0, 5, 5, 1));
		scrollPane_1.setViewportView(textAreaError);

		JLabel lblSelectorTextonly = new JLabel("Text selector (only novels)");
		lblSelectorTextonly.setBounds(188, 200, 160, 27);
		panel_manga.add(lblSelectorTextonly);

		textText1 = new JTextField();
		textText1.setText("#chapter-c p");
		textText1.setEnabled(false);
		textText1.setColumns(10);
		textText1.setBounds(188, 226, 160, 24);
		panel_manga.add(textText1);

		JButton btnChooseRoot1 = new JButton("Choose folder");
		btnChooseRoot1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser f = new JFileChooser(new File("/"));
				f.setCurrentDirectory(new File(FILESTORE.$_r));
				f.setDialogTitle("Choose 1 folder you like!");
				f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (f.showDialog(frame, "Choose folder") == JFileChooser.APPROVE_OPTION) {
					File file = f.getSelectedFile();
					root1 = file.getPath();
					lblRoot1.setText(root1);
				}
			}
		});
		btnChooseRoot1.setBounds(10, 315, 124, 23);
		panel_manga.add(btnChooseRoot1);

		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(10, 348, 338, 54);
		panel_manga.add(scrollPane_6);

		lblRoot1 = new JLabel("");
		scrollPane_6.setViewportView(lblRoot1);

		JButton btnNewButton_5 = new JButton("Mua vip");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel_1, "name_41423230318400");
			}
		});
		btnNewButton_5.setBackground(new Color(135, 206, 250));
		btnNewButton_5.setForeground(Color.YELLOW);
		btnNewButton_5.setBounds(922, 13, 89, 23);
		panel_manga.add(btnNewButton_5);

		JButton btnClearAll = new JButton("ClearALL");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel1.setRowCount(0);
				list1.clear();
				currentThread = 0;
				chckbxSaveOpts1.setSelected(false);
				textAreaError.setText(null);
				root1 = null;
				refresh1();
			}
		});
		btnClearAll.setBounds(508, 349, 89, 23);
		panel_manga.add(btnClearAll);

		textEndpoint1 = new JTextField();
		textEndpoint1.setEnabled(false);
		textEndpoint1.setColumns(10);
		textEndpoint1.setBounds(188, 313, 160, 24);
		panel_manga.add(textEndpoint1);

		JLabel lblEndpointonlyNovels = new JLabel("Endpoint (only novels)");
		lblEndpointonlyNovels.setBounds(194, 283, 160, 27);
		panel_manga.add(lblEndpointonlyNovels);

		JPanel panel_chap = new JPanel();
		panel_1.add(panel_chap, "name_212783585074900");
		panel_chap.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 35, 452, 214);
		panel_chap.add(scrollPane_2);

		textAreaChapterList2 = new JTextArea();
		textAreaChapterList2.setMargin(new Insets(1, 5, 5, 1));
		scrollPane_2.setViewportView(textAreaChapterList2);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(472, 35, 539, 239);
		panel_chap.add(scrollPane_3);

		table2 = new JTable();
		table2.setModel(new DefaultTableModel(new Object[][] { { null, null, "", null }, },
				new String[] { "POS", "NAME", "DETECT IMAGE", "STATUS" }));
		table2.getColumnModel().getColumn(0).setPreferredWidth(15);
		table2.getColumnModel().getColumn(2).setPreferredWidth(93);
		scrollPane_3.setViewportView(table2);

		btnAdd2 = new JButton("Add queue");
		btnAdd2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addQueue2();
			}
		});
		btnAdd2.setBounds(10, 366, 102, 23);
		panel_chap.add(btnAdd2);

		JLabel lblNewLabel_1 = new JLabel("List chapters per line");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(10, 0, 452, 24);
		panel_chap.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Image selector (required)");
		lblNewLabel_1_1.setForeground(Color.BLUE);
		lblNewLabel_1_1.setBounds(10, 255, 207, 24);
		panel_chap.add(lblNewLabel_1_1);

		textImage2 = new JTextField();
		textImage2.setBounds(10, 277, 207, 24);
		panel_chap.add(textImage2);
		textImage2.setColumns(10);

		textText2 = new JTextField();
		textText2.setEnabled(false);
		textText2.setColumns(10);
		textText2.setBounds(241, 277, 221, 24);
		panel_chap.add(textText2);

		textEndpoint2 = new JTextField();
		textEndpoint2.setEnabled(false);
		textEndpoint2.setColumns(10);
		textEndpoint2.setBounds(241, 334, 221, 24);
		panel_chap.add(textEndpoint2);

		JLabel lblNewLabel_1_1_1 = new JLabel("Text selector (Only for novels)");
		lblNewLabel_1_1_1.setForeground(Color.BLUE);
		lblNewLabel_1_1_1.setBounds(241, 255, 221, 24);
		panel_chap.add(lblNewLabel_1_1_1);

		rdbtnManga2 = new JRadioButton("Manga");
		rdbtnManga2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textText2.setEnabled(false);
					textEndpoint2.setEnabled(false);
				}
			}
		});
		rdbtnManga2.setSelected(true);
		buttonGroup_1.add(rdbtnManga2);
		rdbtnManga2.setBounds(10, 313, 109, 23);
		panel_chap.add(rdbtnManga2);

		rdbtnNovels2 = new JRadioButton("Novels");
		rdbtnNovels2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textText2.setEnabled(true);
					textEndpoint2.setEnabled(true);
				}
			}
		});
		buttonGroup_1.add(rdbtnNovels2);
		rdbtnNovels2.setBounds(121, 313, 109, 23);
		panel_chap.add(rdbtnNovels2);

		JButton btnRefresh2 = new JButton("Refresh");
		btnRefresh2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh2();
			}
		});
		btnRefresh2.setBounds(10, 400, 102, 23);
		panel_chap.add(btnRefresh2);

		JButton btnRemove2 = new JButton("Remove");
		btnRemove2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table2.getSelectedRow();
				if (row == -1) {
					textAreaError2.setText("Please choose 1 record!");
					return;
				}
				list2.remove(table2.getValueAt(row, 0));
				tableModel2.removeRow(row);
			}
		});
		btnRemove2.setBounds(922, 285, 89, 23);
		panel_chap.add(btnRemove2);

		JButton btnBegin = new JButton("Begin");
		btnBegin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (currentThread != 0) {
					textAreaError2.setText("Please stop craw manga before use this feature");
				}

				if (table2.getRowCount() <= 0) {
					textAreaError2.setText("Please add chapter link into table!");
					return;
				}
				if (list2.size() > 10) {
					textAreaError2.setText("Maximum 10 chapters!!!");
					return;
				}

				DecimalFormat f = new DecimalFormat("##.##");
				ExecutorService executorService = Executors.newFixedThreadPool(3);

				if (rdbtnManga2.isSelected()) { // for manga
					int i = 1;
					for (Integer key : list2.keySet()) {
						executorService.execute(new Thread() {
							public void run() {
								CHAPTER chap = list2.get(key);
								if (!chap.getStatus().equalsIgnoreCase("NOTRUN")) {
									return;
								} else {
									chap.setStatus("RUNNING");
									setErrorResponse2(chap.getName() + "-> is running");
									String path = root2 + "/" + chap.getName();
									new File(path).mkdirs();

									int totalImage = chap.getImg().size();
									int i = 0;
									for (IMAGE img : chap.getImg()) {
										try {
											FILESTORE.saveImg(img, path, host2);
											i++;
										} catch (Exception e) {
											// TODO: handle exception
											setErrorResponse2(img.getLink() + " download failed");
										}

										for (int j = 0; j < table2.getRowCount(); ++j) {
											if (table2.getValueAt(j, 1).toString().equalsIgnoreCase(chap.getName())) {
												table2.setValueAt(f.format(i / (totalImage / 100.0)) + "%", j, 3);
											}
										}
									}
									chap.setStatus("complete");
								}

//								for (int j = 0; j < table2.getRowCount(); ++j) {
//									if (table2.getValueAt(j, 1).toString().equalsIgnoreCase(chap.getName())) {
//										table2.setValueAt("completed", j, 3);
//									}
//								}
								try {
									Thread.sleep(((int) Math.random() * 200 - 100 + 1) + 100);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							};
						});
					}
				} else { // for novel
					String imageFolder = root2.concat("/ROOTIMG");
					new File(imageFolder).mkdirs();
					for (Integer key : list2.keySet()) {
						executorService.execute(new Thread() {
							public void run() {
								CHAPTER chap = list2.get(key);
								if (!chap.getStatus().equalsIgnoreCase("NOTRUN")) {
									return;
								} else {
									chap.setStatus("RUNNING");
									setErrorResponse2(chap.getName() + "-> is running");
									String path = root2 + "/" + chap.getName();
									new File(path).mkdirs();

									List<IMAGE> imgList = chap.getImg();
									int totalImage = (imgList == null ? 0 : imgList.size()) + 1;
									int i = 0;
									try {
										FILESTORE._$1_w_3l(chap.getContent(), path);
										i++;
										for (int j = 0; j < table2.getRowCount(); ++j) {
											if (table2.getValueAt(j, 1).toString().equalsIgnoreCase(chap.getName())) {
												table2.setValueAt(f.format(i / (totalImage / 100.0)) + "%", j, 3);
											}
										}
									} catch (Exception e2) {
										// TODO: handle exception
										setErrorResponse2("Text download failed");
									}

									if (imgList != null) {
										for (IMAGE img : imgList) {
											try {
												FILESTORE.saveImg(img, imageFolder, host2);
												i++;
											} catch (Exception e) {
												// TODO: handle exception
												setErrorResponse2(img.getLink() + " download failed");
											}

											for (int j = 0; j < table2.getRowCount(); ++j) {
												if (table2.getValueAt(j, 1).toString()
														.equalsIgnoreCase(chap.getName())) {
													table2.setValueAt(f.format(i / (totalImage / 100.0)) + "%", j, 3);
												}
											}
										}
									}
									chap.setStatus("complete");
								}
								try {
									Thread.sleep(((int) Math.random() * 200 - 100 + 1) + 100);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							};
						});
					}
				}
				executorService.shutdown();

			}
		});
		btnBegin.setBounds(823, 285, 89, 23);
		panel_chap.add(btnBegin);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(472, 313, 539, 140);
		panel_chap.add(scrollPane_4);

		textAreaError2 = new JTextArea();
		textAreaError2.setForeground(Color.RED);
		textAreaError2.setMargin(new Insets(1, 5, 0, 1));
		scrollPane_4.setViewportView(textAreaError2);

		JButton btnRoot2 = new JButton("Folder");
		btnRoot2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(FILESTORE.$_r));
				file.setDialogTitle("Choose folder you want save");
				file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if (file.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					root2 = file.getSelectedFile().getPath();
					lblRoot2.setText(root2);
				}
			}
		});
		btnRoot2.setBounds(128, 366, 89, 23);
		panel_chap.add(btnRoot2);

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(241, 366, 221, 43);
		panel_chap.add(scrollPane_5);

		lblRoot2 = new JLabel("");
		scrollPane_5.setViewportView(lblRoot2);

		JButton btnEDIT2 = new JButton("Edit");
		btnEDIT2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table2.getSelectedRow();
				if (row == -1) {
					setErrorResponse2("Please choose 1 chapter");
					return;
				}

				Integer index = Integer.valueOf(table2.getValueAt(row, 0).toString());
				String newName = table2.getValueAt(row, 1).toString();
				list2.get(index).setName(newName);

				setErrorResponse2("Chapter index " + index + " -> new name: " + newName);
				System.out.println(list2.get(index).getName());
			}
		});
		btnEDIT2.setBounds(724, 285, 89, 23);
		panel_chap.add(btnEDIT2);

		JButton btnNewButton_5_1 = new JButton("Mua vip");
		btnNewButton_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel_1, "name_41423230318400");
			}
		});
		btnNewButton_5_1.setForeground(Color.YELLOW);
		btnNewButton_5_1.setBackground(new Color(135, 206, 250));
		btnNewButton_5_1.setBounds(922, 11, 89, 23);
		panel_chap.add(btnNewButton_5_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Folder host (Only for novels)");
		lblNewLabel_1_1_1_1.setForeground(Color.BLUE);
		lblNewLabel_1_1_1_1.setBounds(241, 312, 221, 24);
		panel_chap.add(lblNewLabel_1_1_1_1);

		JPanel panel_getAjax = new JPanel();
		panel_1.add(panel_getAjax, "name_212786254573800");
		panel_getAjax.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Chapter selector");
		lblNewLabel_2.setBounds(10, 11, 191, 22);
		panel_getAjax.add(lblNewLabel_2);

		textAjax3 = new JTextField();
		textAjax3.setBounds(10, 44, 191, 22);
		panel_getAjax.add(textAjax3);
		textAjax3.setColumns(10);

		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(224, 36, 787, 499);
		panel_getAjax.add(scrollPane_7);

		JTextArea textAreaAjaxScript = new JTextArea();
		textAreaAjaxScript.setLineWrap(true);
		textAreaAjaxScript.setMargin(new Insets(5, 5, 5, 5));
		scrollPane_7.setViewportView(textAreaAjaxScript);

		JButton btnGetScript = new JButton("Get Script");
		btnGetScript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String chapSelector = textAjax3.getText();
				if (chapSelector.isEmpty()) {
					textAreaAjaxScript.setText("Please enter chapter selector");
					return;
				}
				String script = "var jq = document.createElement('script');\r\n"
						+ "    jq.src = \"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\";\r\n"
						+ "    document.getElementsByTagName('head')[0].appendChild(jq);\r\n"
						+ "    jQuery.noConflict();\r\n" + "    function openWindowWithPost(data) {\r\n"
						+ "        var form = document.createElement(\"form\");\r\n"
						+ "        form.target = \"_blank\";\r\n" + "        form.method = \"POST\";\r\n"
						+ "        form.action = '" + ENDPOINT.GETCHAP + "';\r\n"
						+ "        form.style.display = \"none\";\r\n" + "\r\n"
						+ "        var input = document.createElement(\"textarea\");\r\n"
						+ "        input.name = 'DATA';\r\n" + "        input.value = data;\r\n"
						+ "        form.appendChild(input);\r\n" + "        document.body.appendChild(form);\r\n"
						+ "        form.submit();\r\n" + "        document.body.removeChild(form);\r\n" + "      }\r\n"
						+ "  setTimeout(function () {\r\n" + "      let data = '';\r\n" + "      jQuery('"
						+ chapSelector + "').each(function () {\r\n" + "        data += this.href + '<br>';\r\n"
						+ "      });\r\n" + "openWindowWithPost(data);\r\n" + "    }, 1000);";
				textAreaAjaxScript.setText(script);
			}
		});
		btnGetScript.setBounds(10, 96, 191, 23);
		panel_getAjax.add(btnGetScript);

		JButton btnClear3 = new JButton("Clear");
		btnClear3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaAjaxScript.setText(null);
				textAjax3.setText(null);
			}
		});
		btnClear3.setBounds(10, 131, 191, 23);
		panel_getAjax.add(btnClear3);

		JButton btnNewButton_5_1_1 = new JButton("Mua vip");
		btnNewButton_5_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(panel_1, "name_41423230318400");
			}
		});
		btnNewButton_5_1_1.setForeground(Color.YELLOW);
		btnNewButton_5_1_1.setBackground(new Color(135, 206, 250));
		btnNewButton_5_1_1.setBounds(922, 11, 89, 23);
		panel_getAjax.add(btnNewButton_5_1_1);

		JPanel panel_donate = new JPanel();
		panel_1.add(panel_donate, "name_35271379709700");
		panel_donate.setLayout(null);

		JLabel lblPaypal = new JLabel("");
		lblPaypal.setBounds(49, 11, 207, 193);
		lblPaypal.setIcon(new ImageIcon(new ImageIcon("uploads//paypal.jpg").getImage()
				.getScaledInstance(lblPaypal.getWidth(), lblPaypal.getHeight(), Image.SCALE_DEFAULT)));
		panel_donate.add(lblPaypal);

		JButton btnPaypal = new JButton("Here");
		btnPaypal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URL("https://www.paypal.com/paypalme/shikiori").toURI());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				;
			}
		});
		btnPaypal.setBounds(107, 224, 89, 23);
		panel_donate.add(btnPaypal);

		JLabel lblNewLabel_4 = new JLabel("MOMO, ZALOPAY, VIETTELPAY");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(370, 81, 207, 23);
		panel_donate.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("0965901542");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setBounds(370, 115, 207, 23);
		panel_donate.add(lblNewLabel_4_1);
	}

	protected void upgrade_quotas() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(frame,
				"Gửi mail tới tôi với tiêu đề: 'Nâng cấp tài khoản'\r\nvà để lại số điện thoại hoặc email để mình liên hệ lại\r\n\n"
						+ "Email1: admin@truyenmoinhat.xyz\r\n" + "Email2: shiki@animenews.life"
						+ "\r\nBạn sẽ được chuyển hướng đến fanpage!");
		try {
			Desktop.getDesktop().browse(new URI("https://www.facebook.com/truyenmoinhatz/"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	protected void refreshENDPOINT(int type) {
		// TODO Auto-generated method stub
		new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String res = REQUEST.sendPost(null, ENDPOINT.REFRESH, null);
				if (res == null) {
					setDialog("We don't have any other endpoint at moment!");
					return;
				}
				JSONObject resp = new JSONObject(res);
				ENDPOINT.CHAP = resp.get("chap").toString().trim();
				ENDPOINT.IMG = resp.get("img").toString().trim();
				ENDPOINT.LOGIN = resp.get("login").toString().trim();
				if (type == 1) {
					setDialog("update endpoint success!");
				}

				mntmENDPOINT.setEnabled(true);
//				System.out.println(ENDPOINT.CHAP);
//				System.out.println(ENDPOINT.IMG);
//				System.out.println(ENDPOINT.LOGIN);
				System.out.println("done");
			}
		}.start();
	}

	protected void addQueue2() {
		// TODO Auto-generated method stub
		String img = textImage2.getText().trim();
		String type = rdbtnManga2.isSelected() ? "manga" : "novels";
		String text = null;
		String endpoint = null;

		if (root2.isEmpty()) {
			textAreaError2.setText("Please choose folder!\r\n");
			return;
		}

		if (img.isEmpty()) {
			textAreaError2.setText("Please enter image selector!\\r\\n");
			return;
		}

		if (rdbtnNovels2.isSelected()) {
			text = textText2.getText().trim();
			endpoint = textEndpoint2.getText().trim();
			if (text.isEmpty() || endpoint.isEmpty()) {
				textAreaError2.setText("Please enter text selector!\\r\\n");
				return;
			}
		}

		String[] s = textAreaChapterList2.getText().split("\n");

		if (s.length > 10) {
			textAreaError2.setText("Please remove, maximum 10 chapters!\\r\\n");
			return;
		}

		if (s[0].isEmpty()) {
			textAreaError2.setText("Please enter link chapters!\\r\\n");
			return;
		} else if (!s[0].contains("http")) {
			textAreaError2.append(s[0] + "->wrong url\r\n");
			return;
		}
		try {
			host2 = new URL(s[0]).getHost();
		} catch (Exception e) {
			// TODO: handle exception
		}

		Map<String, Object> data = new HashMap<>();
		data.put("type", type);
		data.put("host", host2);
		data.put("quotas", AUTH.getCreadential().getQuotas());
		data.put("endpoint", endpoint);

		Map<String, String> selector = new HashMap<String, String>();
		selector.put("img", img.trim());

		btnAdd2.setEnabled(false);
		if (rdbtnNovels2.isSelected()) { // for novel
			text = textText2.getText();
			selector.put("text", text);
			data.put("selector", selector);

			for (String url : s) {
				new Thread() {
					public void run() {
						try {
							if (url.contains("http")) {
								data.put("link", url.trim());
								JSONObject dataObject = new JSONObject(data);
								RequestBody body = new FormBody.Builder().addEncoded("DATA", dataObject.toString())
										.build();

								Map<String, Object> ln = ENDPOINT.crawLN(body);

								List<IMAGE> ls = (List<IMAGE>) ln.get("img");
								String content = (String) ln.get("text");

								if (content != null) {
									String chapName = url.substring(url.lastIndexOf("/") + 1).replace(".html", "");
									CHAPTER chap = new CHAPTER(chapName, null, ls == null ? null : ls, chapIndex);
									chap.setStatus("NOTRUN");
									chap.setContent(content);
									list2.put(chapIndex, chap);
									tableModel2.addRow(new Object[] { chapIndex++, chapName, "has text", "0%" });
								} else {
									textAreaError2.append(url + " | not found text \r\n");
								}
							} else {
								setErrorResponse2(url + "->WRONG URL");
							}
						} catch (Exception e) {
							// TODO: handle exception
							setErrorResponse2("Server got error!");
						}
						try {
							Thread.sleep(((int) Math.random() * 200 - 100 + 1) + 100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					};
				}.start();
			}

		} else { // for manga
			for (String url : s) {
				new Thread() {
					public void run() {
						try {
							if (url.contains("http")) {
								data.put("link", url.trim());
								data.put("selector", selector);
								JSONObject dataObject = new JSONObject(data);
								RequestBody body = new FormBody.Builder().addEncoded("DATA", dataObject.toString())
										.build();
								List<IMAGE> ls = ENDPOINT.getImg(body, 2);

								if (ls != null) {
									String chapName = url.substring(url.lastIndexOf("/") + 1).replace(".html", "");
									CHAPTER chap = new CHAPTER(chapName, null, ls, chapIndex);
									chap.setStatus("NOTRUN");
									list2.put(chapIndex, chap);
									tableModel2.addRow(new Object[] { chapIndex++, chapName, ls.size(), "0%" });
								} else {
									textAreaError2.append(url + " | Chap not found images\r\n");
								}
							} else {
								textAreaError2.append(url + "->wrong url\r\n");
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							textAreaError2.append("Server got error\r\n");
						}
						try {
							Thread.sleep(((int) Math.random() * 200 - 100 + 1) + 100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					};
				}.start();
			}
		}

		new Thread() {
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				btnAdd2.setEnabled(true);
			};
		}.start();
	}

	protected void refresh2() {
		// TODO Auto-generated method stub
		textAreaChapterList2.setText(null);
		textImage2.setText(null);
		textText2.setText(null);
		lblRoot2.setText(null);

		root2 = null;
		rdbtnManga2.setSelected(false);
		rdbtnNovels2.setSelected(false);
		textEndpoint2.setText(null);
	}

	protected void refresh1() {
		// TODO Auto-generated method stub
		textManga1.setText(null);
		textLink1.setText(null);

		if (!chckbxSaveOpts1.isSelected()) {
			textChap1.setText(null);
			textImg1.setText(null);
			textEndpoint1.setText(null);
			textText1.setText(null);
		}
		lblRoot1.setText(null);

		rdbtnManga1.setSelected(false);
		rdbtnNovel1.setSelected(false);

		System.out.println(AUTH.getCreadential().getQuotas());
	}

	protected MANGA getForm1() {
		// TODO Auto-generated method stub
		String name = textManga1.getText();
		String link = textLink1.getText();
		boolean type = rdbtnManga1.isSelected() ? false : true;

		String chap = textChap1.getText();
		String img = textImg1.getText();
		String text = textText1.getText();
		String endpoint = textEndpoint1.getText();

		if (type) {
			if (text.isEmpty()) {
				lblMessage1.setText("Please chosse text selector");
				return null;
			}
		}

		String host = "";
		try {
			URL url = new URL(link);
			host = url.getHost();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			textAreaError.setText(
					"Please enter full url such as 'https://ja.mangatoro.com/manga/jigoku-no-gouka-de-yaka-re-tsuzuketa-shounen-saikyou-no-honou-tsukai-to-natte-fukkatsu-suru-12220' \r\n");
			return null;
		}
		if (rdbtnNovel1.isSelected()) {
			if (text.isEmpty() || endpoint.isEmpty()) {
				lblMessage1.setText("Please enter lack of field");
				return null;
			}
		}

		if (name.isEmpty() | link.isEmpty() | chap.isEmpty() | img.isEmpty()) {
			lblMessage1.setText("Please enter lack of field");
			return null;
		}
		Map<String, String> selector = new HashMap<>();
		selector.put("chap", chap.trim());
		selector.put("img", img.trim());
		if (type) {
			selector.put("text", text);
		}

		return new MANGA(name.trim().replaceAll("[\\[\\];?$,\\/\\\\#^&*:\\\"@]", "").trim(), selector, host.trim(),
				type, link.trim(), endpoint);
	}

	public void updateStatus(Integer index, String percent, int image) {
		int rows = table1.getRowCount();

		for (int i = 0; i < rows; i++) {
			if (Integer.valueOf(table1.getValueAt(i, 0).toString()) == index) {
				table1.setValueAt(percent, i, 3);
				table1.setValueAt(image, i, 4);
			}
		}
	}

	public void setErrorResponse2(String message) {
		textAreaError2.append(message + "\r\n");
	}

	public void setErrorResponse1(String message) {
		textAreaError.append(message + "\r\n");
	}
}
