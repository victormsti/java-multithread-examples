package demo1;

import java.util.Random;

public class App {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Starting");
		Thread t1 = new Thread(new Runnable() {
			Random random = new Random();

			public void run() {
				for (int i = 0; i < 1E8; i++) {
//					if(Thread.currentThread().isInterrupted()){
//						System.out.println("Interrupted");
//						return;
//					}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						System.out.println("Interrupted");
						break;
					}

					Math.asin(random.nextDouble());
				}

			}
		});
		t1.start();

		Thread.sleep(5000);

		t1.interrupt();

		t1.join();

		System.out.println("Finished");
	}

}
