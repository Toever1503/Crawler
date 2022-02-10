package MODEL.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;

import org.json.JSONObject;

import MODEL.AUTH;
import MODEL.ENDPOINT;
import MODEL.REQUEST;
import MODEL.IMAGE.PasswordEncoder;
import okhttp3.FormBody;

import javax.security.auth.callback.Callback;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class changePassword {
	private static changePassword changePassword;
	private JFrame frame;
	private JPasswordField textNewPassword;
	private JPasswordField textConfirmPassword;
	private JPasswordField textOldPassword;
	private JLabel lblUsername;

	private JLabel lblErrorMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					changePassword window = new changePassword();
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
	public changePassword() {
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

	public void show() {
		try {
			lblUsername.setText(AUTH.getCreadential().getUsername());
			frame.setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
			refreshInstance();
			show();
		}
	}

	private void hide() {
		try {
			frame.setVisible(false);
		} catch (Exception e) {
			// TODO: handle exception
			refreshInstance();
			hide();
		}
	}

	private void refreshInstance() {
		changePassword = new changePassword();
	}

	public static changePassword getInstance() {
		// TODO Auto-generated method stub
		if (changePassword == null)
			changePassword = new changePassword();
		return changePassword;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Change Password");
		frame.setBounds(100, 100, 552, 354);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("uploads\\logo.jpg"));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ĐỔI MẬT KHẨU");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 230, 41);
		panel.add(lblNewLabel);

		JLabel lblUsername1 = new JLabel("Tên đăng nhập");
		lblUsername1.setBounds(31, 85, 209, 24);
		panel.add(lblUsername1);

		lblUsername = new JLabel("");
		lblUsername.setBounds(31, 120, 209, 24);
		panel.add(lblUsername);

		JLabel lblNewPassword = new JLabel("Mật khẩu mới");
		lblNewPassword.setBounds(31, 172, 209, 24);
		panel.add(lblNewPassword);

		textNewPassword = new JPasswordField();
		textNewPassword.setBounds(31, 207, 209, 24);
		panel.add(textNewPassword);
		textNewPassword.setColumns(10);

		JLabel lblXcNhnMt = new JLabel(" Xác nhận mật khẩu mới");
		lblXcNhnMt.setBounds(292, 172, 209, 24);
		panel.add(lblXcNhnMt);

		textConfirmPassword = new JPasswordField();
		textConfirmPassword.setColumns(10);
		textConfirmPassword.setBounds(292, 207, 209, 24);
		panel.add(textConfirmPassword);

		JLabel lblMtKhuC = new JLabel("Mật khẩu cũ");
		lblMtKhuC.setBounds(292, 85, 209, 24);
		panel.add(lblMtKhuC);

		textOldPassword = new JPasswordField();
		textOldPassword.setColumns(10);
		textOldPassword.setBounds(292, 120, 209, 24);
		panel.add(textOldPassword);

		JButton btnCancel = new JButton("Hủy bỏ");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(412, 259, 89, 23);
		panel.add(btnCancel);

		JButton btnChange = new JButton("Thay đổi");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkForm()) {
					PasswordEncoder encoder = PasswordEncoder.getInstance();
					String oldPass = encoder.encode(String.valueOf(textOldPassword.getPassword()).trim());

					if (!AUTH.getCreadential().getPassword().equals(oldPass)) {
						lblErrorMessage.setText("Mật khẩu cũ sai");
						return;
					} else {
						String newPass = String.valueOf(textNewPassword.getPassword()).trim();
						String confirmPass = String.valueOf(textConfirmPassword.getPassword()).trim();

						System.out.println("new->" + newPass + ", confirm->" + confirmPass);
						if (!newPass.equals(confirmPass)) {
							lblErrorMessage.setText("Mật khẩu xác nhận sai!");
							return;
						} else {
							newPass = encoder.encode(newPass);

							Map<String, String> data = new HashMap<>();
							data.put("username", AUTH.getCreadential().getUsername());
							data.put("newpass", newPass);

							FormBody body = new FormBody.Builder().addEncoded("DATA", new JSONObject(data).toString())
									.addEncoded("TYPE", "CHANGE_PASSWORD_$").build();
							String res = REQUEST.sendPost(body, ENDPOINT.LOGIN, null);
							if (res == null) {
								lblErrorMessage.setText("Can't change password now!");
							}
							System.out.println(res);

							JSONObject result = new JSONObject(res);
							if (result.get("result").equals("success")) {
								AUTH.getCreadential().setPassword(newPass);
								lblErrorMessage.setText("Password has changed successfully!");
								refresh();

								try {
									FileOutputStream fileOutputStream = new FileOutputStream("uploads/aasnfa.dat");
									fileOutputStream.write(AUTH.getCreadential().getUsername().getBytes());
									fileOutputStream.write(("\r\n" + AUTH.getCreadential().getPassword()).getBytes());

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
					}
				}
			}
		});
		btnChange.setBounds(317, 259, 89, 23);
		panel.add(btnChange);

		lblErrorMessage = new JLabel("");
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(31, 258, 235, 24);
		panel.add(lblErrorMessage);
	}

	protected void refresh() {
		// TODO Auto-generated method stub
		textOldPassword.setText(null);
		textNewPassword.setText(null);
		textConfirmPassword.setText(null);
	}

	protected boolean checkForm() {
		// TODO Auto-generated method stub

		if (textNewPassword.getText().isEmpty() | textOldPassword.getText().isEmpty()
				| textConfirmPassword.getText().isEmpty()) {
			lblErrorMessage.setText("Dữ liệu không đuọc bỏ trống");
			return false;
		}
		return true;
	}
}
