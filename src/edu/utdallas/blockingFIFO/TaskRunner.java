package edu.utdallas.blockingFIFO;

import edu.utdallas.taskExecutor.Task;

public class TaskRunner implements Runnable {
	private Task task;
	private MyBlockingFIFO FIFO;

	public TaskRunner(MyBlockingFIFO taskFIFO) {
		this.FIFO = taskFIFO;
	}

	@Override
	public void run() {
		while (true) {
			try {
				task = FIFO.remove();
				task.execute();
			} catch (Exception ex) {

			}
		}
	}
}
