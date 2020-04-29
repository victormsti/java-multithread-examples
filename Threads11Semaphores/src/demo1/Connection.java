package demo1;

import java.util.concurrent.Semaphore;

public class Connection {

	private static Connection instance = new Connection();
	private Semaphore sem = new Semaphore(10, true); // 10 connections at time
	private int connections = 0;

	private Connection() {

	}

	public static Connection getInstance() {
		return instance;
	}

	public void connect() {
		try {
			sem.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			doConnect();
		} finally {
			sem.release(); // keep running event if get an exception
		}
	}
	public void doConnect() {
		
		
		synchronized (this) {
			connections++;
			System.out.println("Current connections: " + connections);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (this) {
			connections--;
		}
	}
}
