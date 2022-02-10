package MODEL.IMAGE;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class USER {
	private String username;
	private String password;
	private String email;
	private String ip;
	private volatile int quotas;
	private int type;

	public USER() {
		super();
	}

	public USER(String username, String password) {
		super();
		this.username = username;
		this.password = PasswordEncoder.getInstance().encode(password);

		fill_unique();
	}

	public USER(String username, String password, String email, int quotas, int type) {
		super();
		this.username = username;
		this.password = PasswordEncoder.getInstance().encode(password);
		this.email = email;
		this.quotas = quotas;
		this.type = type;

		fill_unique();

	}

	private void fill_unique() {
		// TODO Auto-generated method stub
		try {
			Process SerNumProcess = Runtime.getRuntime().exec("wmic csproduct get UUID");
			BufferedReader sNumReader = new BufferedReader(new InputStreamReader(SerNumProcess.getInputStream()));

			String line = "";
			String s = "";
			while ((line = sNumReader.readLine()) != null) {
				s += line;
			}

			this.ip = InetAddress.getLocalHost().getHostName() + "->" + s.replace("UUID", "").trim();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getQuotas() {
		return quotas;
	}

	public void setQuotas(int quotas) {
		this.quotas = quotas;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public static void main(String[] args) {
	}
}
