package MODEL.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;

import org.json.JSONObject;

import com.sun.management.OperatingSystemMXBean;

import MODEL.AUTH;
import MODEL.ENDPOINT;
import MODEL.REQUEST;
import MODEL.IMAGE.USER;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LOGIN {

	private JFrame frame;
	private JTextField textUsername1;
	private JPasswordField textPassword1;
	private JTextField textUsername2;
	private JPasswordField textPassword2;
	private JTextField textEmail2;
	private JPasswordField textPasswordAgain2;
	private JTextField textForgot;
	private JCheckBox chckbxNewCheckBox;
	private JLabel lblError3;
	private JLabel lblErrorLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LOGIN window = new LOGIN();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		new LOGIN().show();

	}

	/**
	 * Create the application.
	 */
	public LOGIN() {
		initialize();
		File f = new File("uploads/aasnfa.dat");
		if (f.exists() && f.getName().equalsIgnoreCase("aasnfa.dat")) {
			try {
				DataInputStream d = new DataInputStream(new FileInputStream(f));
				USER u = new USER();
				u.setUsername(d.readLine());
				u.setPassword(d.readLine());
				login(u);

				d.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			show();
		}
	}

	public void show() {
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Login");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("uploads\\logo.jpg"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 449, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 240));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		CardLayout card = new CardLayout(0, 0);
		panel.setLayout(card);

		JPanel panel_login = new JPanel();
		panel_login.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_login.setBackground(new Color(255, 250, 250));
		panel.add(panel_login, "login");
		panel_login.setLayout(null);

		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(0, 255, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 433, 44);
		panel_login.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 94, 135, 25);
		panel_login.add(lblNewLabel_1);

		textUsername1 = new JTextField();
		textUsername1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textUsername1.setText(textUsername1.getText());
			}
		});

		textUsername1.setBounds(154, 94, 228, 25);
		panel_login.add(textUsername1);
		textUsername1.setColumns(10);

		textPassword1 = new JPasswordField();
		textPassword1.setColumns(10);
		textPassword1.setBounds(154, 130, 228, 25);
		panel_login.add(textPassword1);

		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(10, 130, 135, 25);
		panel_login.add(lblNewLabel_1_1);

		lblErrorLogin = new JLabel("");
		lblErrorLogin.setForeground(new Color(30, 144, 255));
		lblErrorLogin.setBounds(154, 243, 228, 25);
		panel_login.add(lblErrorLogin);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUsername1.getText();
				String password = String.valueOf(textPassword1.getPassword());
				if (username.isEmpty() | password.isEmpty()) {
					lblErrorLogin.setText("Please enter lack of information");
					return;
				}
				Pattern pattern = Pattern.compile("[$@&\\d\\w]+");
				if (!pattern.matcher(password).matches() | !pattern.matcher(username).matches()) {
					lblErrorLogin.setText("Just accept a-z, 0-9, $, @, &");
					return;
				}

				USER u = new USER(username, password);
				login(u);
			}
		});
		btnLogin.setBackground(new Color(0, 250, 154));
		btnLogin.setBounds(154, 198, 89, 23);
		panel_login.add(btnLogin);

		JLabel lblNewLabel_2 = new JLabel("Forgot password?");
		lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textForgot.setText(null);
				card.show(panel, "forgot");
				refreshLogin();
			}
		});
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(272, 158, 110, 14);
		panel_login.add(lblNewLabel_2);

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshRegister();
				card.show(panel, "register");
				refreshLogin();
			}
		});
		btnRegister.setBackground(new Color(0, 250, 154));
		btnRegister.setBounds(293, 198, 89, 23);
		panel_login.add(btnRegister);

		chckbxNewCheckBox = new JCheckBox("Remember?");
		chckbxNewCheckBox.setBounds(154, 162, 97, 23);
		panel_login.add(chckbxNewCheckBox);

		JPanel panel_register = new JPanel();
		panel.add(panel_register, "register");
		panel_register.setLayout(null);

		JLabel lblRegister = new JLabel("REGISTER");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setForeground(Color.GREEN);
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRegister.setBounds(0, 22, 433, 44);
		panel_register.add(lblRegister);

		JLabel lblNewLabel_1_2 = new JLabel("Username");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(10, 105, 135, 25);
		panel_register.add(lblNewLabel_1_2);

		textUsername2 = new JTextField();
		textUsername2.setText("abc");
		textUsername2.setColumns(10);
		textUsername2.setBounds(154, 105, 228, 25);
		panel_register.add(textUsername2);

		textPassword2 = new JPasswordField();
		textPassword2.setColumns(10);
		textPassword2.setBounds(154, 141, 228, 25);
		panel_register.add(textPassword2);

		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setBounds(10, 141, 135, 25);
		panel_register.add(lblNewLabel_1_1_1);

		JLabel lblRegister2 = new JLabel("");
		lblRegister2.setForeground(new Color(65, 105, 225));
		lblRegister2.setBounds(154, 300, 269, 25);
		panel_register.add(lblRegister2);

		JButton btnBack2 = new JButton("Back");
		btnBack2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel, "login");
				refreshRegister();
				lblRegister2.setText(null);
			}
		});
		btnBack2.setBackground(new Color(0, 250, 154));
		btnBack2.setBounds(293, 266, 89, 23);
		panel_register.add(btnBack2);

		JButton btnRegister_1 = new JButton("Register");
		btnRegister_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = new File("uploads/snajr.dat");
				String prevUser = "";
				if (f.exists()) {
					try {
						DataInputStream file = new DataInputStream(new FileInputStream(f));
						prevUser = String.valueOf(file.readLine());
						file.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				String username = textUsername2.getText().trim();
				String pass1 = String.valueOf(textPassword2.getPassword()).trim();
				String pass2 = String.valueOf(textPasswordAgain2.getPassword()).trim();
				String email = textEmail2.getText().trim();

				if (username.isEmpty() | pass1.isEmpty() | pass2.isEmpty() | email.isEmpty()) {
					lblRegister2.setText("Please enter lack of information");
					return;
				}
				if (pass1.length() < 6) {
					lblRegister2.setText("Password must have more 6 character");
					return;
				}

				Pattern pattern = Pattern.compile("[$@&\\d\\w]+");
				if (!pattern.matcher(pass1).find() | !pattern.matcher(username).find()) {
					lblRegister2.setText("Just accept a-z, 0-9, $, @, &");
					return;
				}
				if (!email.contains("@")) {
					lblRegister2.setText("Email wrong! try again");
					return;
				}

				if (!pass1.equals(pass2)) {
					lblRegister2.setText("Password again not match! try again");
					return;
				}
				USER u = new USER(username, pass1, email, prevUser.isEmpty() ? 10000 : 1000,
						prevUser.isEmpty() ? 0 : 1);
				JSONObject jsonObject = new JSONObject(u);

				RequestBody body = new FormBody.Builder().addEncoded("DATA", jsonObject.toString())
						.add("TYPE", "REGISTER_$").build();

				String resul = REQUEST.sendPost(body, ENDPOINT.LOGIN, null);
				if (resul == null) {
					lblRegister2.setText("Request server got error! contact author to fix");
					return;
				}

				JSONObject result = new JSONObject(resul);
				if (result.get("result").toString().equalsIgnoreCase("success")) {
					lblRegister2.setText("Register successfully! you can login now");
					refreshRegister();
					if (prevUser.isEmpty()) {
						try {
							FileOutputStream file = new FileOutputStream("uploads/snajr.dat");
							file.write(u.getUsername().getBytes());
							file.flush();
							file.close();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				} else {
					lblRegister2.setText(result.get("message").toString());
				}
			}
		});
		btnRegister_1.setBackground(new Color(0, 250, 154));
		btnRegister_1.setBounds(154, 266, 89, 23);
		panel_register.add(btnRegister_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Email");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setBounds(10, 211, 135, 25);
		panel_register.add(lblNewLabel_1_1_1_1);

		textEmail2 = new JTextField();
		textEmail2.setText("aj@gmail.com");
		textEmail2.setColumns(10);
		textEmail2.setBounds(154, 211, 228, 25);
		panel_register.add(textEmail2);

		textPasswordAgain2 = new JPasswordField();
		textPasswordAgain2.setColumns(10);
		textPasswordAgain2.setBounds(154, 177, 228, 25);
		panel_register.add(textPasswordAgain2);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Enter password again");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1_1.setBounds(10, 177, 135, 25);
		panel_register.add(lblNewLabel_1_1_1_1_1);

		JPanel panel_forgot = new JPanel();
		panel.add(panel_forgot, "forgot");
		panel_forgot.setLayout(null);

		JLabel lblForgotPassword = new JLabel("FORGOT PASSWORD");
		lblForgotPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblForgotPassword.setForeground(Color.GREEN);
		lblForgotPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblForgotPassword.setBounds(0, 23, 433, 44);
		panel_forgot.add(lblForgotPassword);

		JLabel lblNewLabel_3 = new JLabel("Enter your email associate with your account");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 78, 413, 24);
		panel_forgot.add(lblNewLabel_3);

		textForgot = new JTextField();
		textForgot.setBounds(63, 113, 334, 24);
		panel_forgot.add(textForgot);
		textForgot.setColumns(10);

		JButton btnBack3 = new JButton("Back");
		btnBack3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel, "login");
				textForgot.setText(null);
				lblError3.setText(null);
			}
		});
		btnBack3.setBackground(new Color(0, 250, 154));
		btnBack3.setBounds(308, 161, 89, 23);
		panel_forgot.add(btnBack3);

		lblError3 = new JLabel("");
		lblError3.setForeground(new Color(65, 105, 225));
		lblError3.setHorizontalAlignment(SwingConstants.CENTER);
		lblError3.setBounds(63, 204, 334, 24);
		panel_forgot.add(lblError3);

		JButton btnSend3 = new JButton("Send");
		btnSend3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = textForgot.getText().trim();
				if (email.isEmpty() | !email.contains("@")) {
					lblError3.setText("Wrong email! try again");
					return;
				}

				Map<String, String> data = new HashMap<>();
				data.put("email", email);
				RequestBody body = new FormBody.Builder().addEncoded("DATA", new JSONObject(data).toString())
						.addEncoded("TYPE", "FORGOT_$").build();
				String response = REQUEST.sendPost(body, ENDPOINT.LOGIN, null);
				if (response == null) {
					lblError3.setText("Request server got error! contact author to fix");
					return;
				}
				JSONObject result = new JSONObject(response);

				if (result.get("result").toString().equalsIgnoreCase("success")) {
					lblError3.setText("Password has sent to your email, please check");
					refreshRegister();
				} else {
					lblError3.setText(result.get("message").toString());
				}
				System.out.println(1);
			}
		});
		btnSend3.setBackground(new Color(0, 250, 154));
		btnSend3.setBounds(63, 161, 89, 23);
		panel_forgot.add(btnSend3);

	}

	protected void login(USER u) {
		// TODO Auto-generated method stub
		RequestBody body = new FormBody.Builder().addEncoded("TYPE", "LOGIN_$")
				.addEncoded("DATA", new JSONObject(u).toString()).build();
		String res = null;
		try {
			res = REQUEST.sendPost(body, ENDPOINT.LOGIN, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(res == null) {
			lblErrorLogin.setText("server got error");
			frame.setVisible(true);
			return;
		}
		System.out.println(res);

		JSONObject jsonObject = new JSONObject(res);

		if (jsonObject.get("result").toString().equalsIgnoreCase("success")) {
			u.setQuotas(jsonObject.getInt("quotas"));
			MAIN.maxThread = jsonObject.getBoolean("isVip") == true ? 10 : 2;

			if (chckbxNewCheckBox.isSelected()) {
				try {
					FileOutputStream fileOutputStream = new FileOutputStream("uploads/aasnfa.dat");
					fileOutputStream.write(u.getUsername().getBytes());
					fileOutputStream.write(("\r\n" + u.getPassword()).getBytes());
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
			AUTH.setAuth(u);
			frame.dispose();
			JOptionPane.showMessageDialog(frame, "LOgin SuCCESSfULLY!");
			MAIN.getInstance().show();
		} else {
			lblErrorLogin.setText(jsonObject.get("message").toString());
			frame.setVisible(true);
		}
	}

	protected void refreshLogin() {
		// TODO Auto-generated method stub
		textUsername1.setText(null);
		textPassword1.setText(null);
		chckbxNewCheckBox.setSelected(false);
		lblErrorLogin.setText(null);
	}

	protected void refreshRegister() {
		// TODO Auto-generated method stub
		textUsername2.setText(null);
		textPassword2.setText(null);
		textPasswordAgain2.setText(null);
		textEmail2.setText(null);
	}

}
