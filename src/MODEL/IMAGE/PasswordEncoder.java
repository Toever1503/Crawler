package MODEL.IMAGE;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
	private static PasswordEncoder passwordEncoder;

	private PasswordEncoder() {
	};

	public static PasswordEncoder getInstance() {

		if (passwordEncoder == null)
			passwordEncoder = new PasswordEncoder();
		return passwordEncoder;
	}

	public String encode(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(input.getBytes("UTF-8"));
			BigInteger hash = new BigInteger(1, digest.digest());
			return hash.toString(16);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
