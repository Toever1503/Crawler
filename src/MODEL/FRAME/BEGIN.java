package MODEL.FRAME;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class BEGIN {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MAIN.getInstance();
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new LOGIN();
		
	}

}
