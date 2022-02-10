package MODEL;

import MODEL.IMAGE.USER;

public class AUTH {
	private static USER user = null;

	public static void setAuth(USER u) {
		AUTH.user = u;
	}

	public static USER getCreadential() {
		return AUTH.user;
	}
}
