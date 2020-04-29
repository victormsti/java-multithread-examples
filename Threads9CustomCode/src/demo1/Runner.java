package demo1;

public class Runner {

	private int count = 0;
	
	private void increment() {
		for (int i = 0; i < 1000; i++) {
			count++;
		}
	}

	public void firstThread() throws InterruptedException {
		Thread.sleep(2000);
		synchronized (this) {
			increment();
		}
			
	}

	public void secondThread() throws InterruptedException {
		Thread.sleep(3000);
		synchronized (this) {
			increment();
		}
	}

	public void finished() throws InterruptedException {
		synchronized (this) {
			System.out.println("Count is: " + count);
		}
		
	}

}
