package MODEL.FRAME;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import MODEL.IMAGE.CHAPTER;
import MODEL.IMAGE.MANGA;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

public class CHAPEDIT {

	private static CHAPEDIT chapedit;
	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private JLabel lblManga;

	private MANGA manga;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CHAPEDIT window = new CHAPEDIT();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CHAPEDIT() {
		initialize();
		tableModel = (DefaultTableModel) table.getModel();

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				hide();
				manga = null;

				frame.repaint();
			}
		});
	}

	public static CHAPEDIT getInstance() {
		if (chapedit == null)
			chapedit = new CHAPEDIT();
		return chapedit;
	}

	public void show(MANGA manga) {
		this.manga = manga;

		frame.setVisible(true);
		initailTable();
		lblManga.setText(manga.getManga());

		initailTable();
	}

	public void hide() {
		frame.setVisible(false);
	}

	private void initailTable() {
		// TODO Auto-generated method stub
		tableModel.setRowCount(0);
		for (CHAPTER chap : manga.getListChap()) {
			tableModel.addRow(new Object[] { chap.getIndex(), chap.getName(), chap.getLink(), chap.getStatus() });
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("EDIT CHAPTERS");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("uploads\\logo.jpg"));
		frame.setBounds(100, 100, 900, 520);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		lblManga = new JLabel("New label");
		lblManga.setBounds(0, 0, 884, 32);
		lblManga.setForeground(Color.BLUE);
		lblManga.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblManga);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 32, 884, 402);
		panel.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, },
				new String[] { "POS", "CHAPTER NAME", "CHAPTER LINK", "STATUS" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(15);
		table.getColumnModel().getColumn(3).setMinWidth(5);
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("EDIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					System.out.println("Please choose record");
					return;
				}

				int check = 0;

				int index = Integer.valueOf(table.getValueAt(row, 0).toString());
				java.util.List<CHAPTER> list = manga.getListChap();

				for (int i = 0; i < list.size(); ++i) {
					if (list.get(i).getIndex() == index) {
						String chapName = table.getValueAt(row, 1).toString();
						list.get(i).setName(chapName);
						JOptionPane.showMessageDialog(frame, "Edit successfully!");
						check = 1;
					}
				}
				if (check == 0) {
					JOptionPane.showMessageDialog(frame, "Please only edit chap name!");
				}
			}
		});
		btnNewButton.setBounds(342, 445, 89, 23);
		panel.add(btnNewButton);

		JButton btnRemove = new JButton("REMOVE");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] rows = table.getSelectedRows();
				if (rows.length == 0) {
					System.out.println("Please choose records");
					return;
				}
				List<CHAPTER> list = manga.getListChap();

				for (int i = 0; i < rows.length; ++i) {
					int row = rows[i];
					int index = Integer.valueOf(table.getValueAt(row, 0).toString());

					for (int j = 0; j < list.size(); ++j) {
						if (list.get(j).getIndex() == index) {
							list.remove(j);
						}
					}
				}
				initailTable();
				System.out.println("Removed successfully!");
			}
		});
		btnRemove.setBounds(441, 445, 89, 23);
		panel.add(btnRemove);

		JLabel lblNewLabel = new JLabel("Only edit chap name");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 434, 168, 36);
		panel.add(lblNewLabel);

		JButton btnLogManga = new JButton("Get log");
		btnLogManga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = manga.getRoot() + "/" + manga.getManga() + "/chapLoi.txt";
				JOptionPane.showMessageDialog(frame, " Xem log tai: " + path);
				FileOutputStream file = null;
				try {
					file = new FileOutputStream(path);
					for (CHAPTER chap : manga.getListChap()) {
						if (!chap.getStatus().equalsIgnoreCase("SUCCESS")) {
							file.write((chap.getLink() + "\r\n").getBytes());
							file.flush();
						}
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						file.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnLogManga.setBounds(540, 445, 89, 23);
		panel.add(btnLogManga);
	}
}
