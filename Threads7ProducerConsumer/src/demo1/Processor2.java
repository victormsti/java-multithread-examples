package demo1;

import java.util.LinkedList;
import java.util.Random;

public class Processor2 {

	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object(); // Used to lock methods instead of
	// the entire class

	public void produce() throws InterruptedException {
		int value = 0;
		while (true) {
			synchronized (lock) {
				while(list.size() == LIMIT) {
					System.out.println("List is full. "
							+ "Waiting to have some space...");
					lock.wait();
				}
				
				list.add(value++);
			}
		}
	}

	public void consume() throws InterruptedException {
		Random random = new Random();
		while (true) {
			synchronized (lock) {
				while(list.size() == 0) {
					System.out.println("List is empty. "
							+ "Waiting to some data inside it...");
					lock.wait();
				}
				
				System.out.print("List size is: " + list.size());
				int value = list.removeFirst();
				System.out.println("; Value is: " + value);
				lock.notify();
			}
			Thread.sleep(random.nextInt(1000));
		}
	}
}
