package MODEL;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _H_1A_S {
	public static String $_c_h() {
		String $_4 = "lbcoxa_yvgdj6p4z7rqu15fs0928wm3eitknh";
		String $_ = "";
		for (int $z_ = 0; $z_ < 10; ++$z_) {
			$_ += $_4.charAt((int) (Math.random() * 36) + 0);
		}
		return $_.isEmpty() ? null : $_;
	}

	public static void main(String[] args) {
		ExecutorService exe = Executors.newFixedThreadPool(3);

		exe.execute(new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 10; ++i) {
					try {
						Thread.sleep(1100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(i);
				}
			}
		});
		exe.execute(new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 10; ++i) {
					try {
						Thread.sleep(1100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("b->" + i);
				}
			}
		});
		exe.shutdown();
		System.out.println(exe.isTerminated());
	}
}
