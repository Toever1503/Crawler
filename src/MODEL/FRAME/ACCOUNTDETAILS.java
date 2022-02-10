package MODEL.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import MODEL.AUTH;
import MODEL.IMAGE.USER;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ACCOUNTDETAILS {
	private static ACCOUNTDETAILS accountdetails;
	private JFrame frame;
	private JLabel lblQuotas;
	private JLabel lblUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ACCOUNTDETAILS window = new ACCOUNTDETAILS();
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
	public ACCOUNTDETAILS() {
		initialize();

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				hide();
				frame.repaint();
			}
		});
	}

	public static ACCOUNTDETAILS getInstance() {
		if (accountdetails == null)
			accountdetails = new ACCOUNTDETAILS();
		return accountdetails;
	}

	public void show() {
		frame.setVisible(true);
		fill();
	}

	public void hide() {
		frame.setVisible(false);
	}

	private void fill() {
		// TODO Auto-generated method stub
		USER u = AUTH.getCreadential();
		lblUsername.setText("Hello " + u.getUsername());
		lblQuotas.setText(String.valueOf(u.getQuotas()));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Account");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("uploads\\logo.jpg"));
		frame.setBounds(100, 100, 358, 232);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		lblUsername = new JLabel("afaf");
		lblUsername.setFont(new Font("Sylfaen", Font.BOLD, 17));
		lblUsername.setForeground(Color.BLUE);
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(66, 39, 215, 33);
		panel.add(lblUsername);

		JLabel lblUsername_1 = new JLabel("Quotas left:");
		lblUsername_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername_1.setBounds(20, 83, 102, 33);
		panel.add(lblUsername_1);

		lblQuotas = new JLabel("afaf");
		lblQuotas.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuotas.setBounds(115, 83, 166, 33);
		panel.add(lblQuotas);

		JButton btnUpgrade = new JButton("Upgrade quotas");
		btnUpgrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MAIN.getInstance().upgrade_quotas();
			}
		});
		btnUpgrade.setBounds(196, 159, 136, 23);
		panel.add(btnUpgrade);
	}
}
