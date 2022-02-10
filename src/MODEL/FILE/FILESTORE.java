package MODEL.FILE;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

import MODEL.REQUEST;
import MODEL._H_1A_S;
import MODEL.FRAME.MAIN;
import MODEL.IMAGE.IMAGE;

public class FILESTORE {
	public static String $_r = "TRUYENMOINHAT";

	public static int saveImg(IMAGE img, String path, String host) {

		byte[] content = FILEGET.getImage(img.getLink(), host);
//		byte[] content = REQUEST.getImg(img.getLink(), host);

		if (content == null) {
			MAIN.getInstance().setErrorResponse1("img cannot-download: " + img.getLink());
			return 0;
		}
		BufferedOutputStream bufferedOutputStream = null;
		try {
			bufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(path + "/" + img.getName().replaceFirst("/{1,}", "/")));

			bufferedOutputStream.write(content);
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	// $_1_3a1 is content, _p_2_a is path
	public static int _$1_w_3l(String $_1_3a1, String _p_2_a) {
		try {
			OutputStreamWriter _$_av_1 = new OutputStreamWriter(
					new FileOutputStream(_p_2_a + "/" + _H_1A_S.$_c_h() + ".txt"), StandardCharsets.UTF_8);
			_$_av_1.write($_1_3a1);
			_$_av_1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

		return 1;
	}

	public static void main(String[] args) {
		System.out.println((float) 1);
	}
}
