package edu.utdallas.taskExecutorImpl;

import java.util.ArrayList;
import java.util.List;

import edu.utdallas.blockingFIFO.MyBlockingFIFO;
import edu.utdallas.blockingFIFO.TaskRunner;
import edu.utdallas.taskExecutor.Task;

public class TaskExecutorImpl {
	private MyBlockingFIFO taskFIFO;
	private List<TaskRunner> threadsPool;
	
	public TaskExecutorImpl(int i) {
		this.taskFIFO = new MyBlockingFIFO();
		this.threadsPool = new ArrayList<TaskRunner>();
		
		for(int j = 0; j < i; j++) {
			String name = "TaskThread" + j;
			TaskRunner newthread = new TaskRunner(this.taskFIFO); 
			this.threadsPool.add(newthread);
			Thread thread = new Thread(newthread);
			thread.setName(name);
			thread.start();
			Thread.yield();
		}
		
	}

	public void addTask(Task myTask) {
		this.taskFIFO.add(myTask);
	}
}
