package edu.utdallas.blockingFIFO;

import java.util.concurrent.Semaphore;

import edu.utdallas.taskExecutor.Task;

public class MyBlockingFIFO {
	private Task[] blockingFIFO;
	private static final int DEFAULT_SIZE = 100;
	private int size;
	private int startFlag = 0;
	private int endFlag = 0;
	private static Semaphore semProducer = new Semaphore(DEFAULT_SIZE);
	private static Semaphore semConsumer = new Semaphore(0);

	public MyBlockingFIFO() {
		this.blockingFIFO = new Task[DEFAULT_SIZE];
		this.size = DEFAULT_SIZE;
	}

	public void add(Task newTask) {

		try {
			semProducer.acquire();
			synchronized(MyBlockingFIFO.class) {
				this.blockingFIFO[endFlag] = newTask;
				endFlag = (endFlag + 1) % this.size;
			}
			semConsumer.release();
		} catch (InterruptedException e) {

		}

	}

	public Task remove() {

		Task temp = null;
		try {
			semConsumer.acquire();
			synchronized(MyBlockingFIFO.class) {
				temp = this.blockingFIFO[startFlag];
				startFlag = (startFlag + 1) % this.size;
			}
			semProducer.release();
		} catch (InterruptedException ex) {

		}

		return temp;
	}
}
