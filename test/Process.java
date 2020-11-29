
public class Process extends Thread {

	int processNumber;   // to identify the process number
	int arrivalTime;	 // arrival time of the process
	double burstTime;	     // duration time of the process
	double waitingTime;   
	String status; // started/resumed/paused
	
	
	
	private final Object lock = new Object();
	
	
	private boolean paused = false;  // when process is initialized, lock it for the first instance

	    @Override
	    public void run() {
	    	  while(true){
	              synchronized (lock) {
	                  if(!paused) {
	                      try {
	                          this.lock.wait();
	                      } catch (InterruptedException e) {
	                          e.printStackTrace();
	                      }
	                  }

	              }
	          }
	    }
	
	  
	    public void lockThread() {
	       paused = false;
	        
	    }
	
	   
	    public void awakenThread() {
	        
	        // Restart/Resume my displayThread here
	        paused = true;
	        synchronized(lock) {
	            lock.notify(); // call notify to break the wait, so the thread continues to run
	        }
	       
	    }
	
	
	// constructor to initialize a process    ... how do i incorporate thread?
	public Process(int id, int arrival,int duration) {
	 
	this.processNumber=id;
	this.arrivalTime=arrival;
	this.burstTime=duration;
	
	this.waitingTime=0;
	
	if(this.arrivalTime==1) {
		this.status="ready";
	}
	else {
		this.status="waiting";
	}
	
	}
	
	
	// getters and setters method .. most likely to be called from scheduler
	
	 public int getArrivalTime() {
	        return arrivalTime;
	    }

	    public void setArrivalTime(int x) {
	    	this.arrivalTime=x;
	    }

	    public double getDuration() {
	    	return this.burstTime;
	    }

	    public void setDuration(int duration) {
	    	this.burstTime=duration;
	    }
	
	
	

	
}
