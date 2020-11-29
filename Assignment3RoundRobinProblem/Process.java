//
// Owner of the Simp Process Class API
//
public class Process extends Thread {

	int ProcessID; // to identify the process number
	int Arrival; // arrival time of the process
	double Burst; // duration time of the process
	double Wait; // wait time of the process
	String status; // started/resumed/paused
	private final Object lock = new Object();
	private boolean paused = false; // when process is initialized, lock it for the first instance

	@Override
	public void run() {
		while (true) {
			synchronized (lock) {
				if (!paused) {
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
		paused = true;
		synchronized (lock) {
			lock.notify();
		}
	}

	public Process(int id, int arrival, int duration) {
		this.ProcessID = id;
		this.Arrival = arrival;
		this.Burst = duration;
		this.Wait = 0;
		if (this.Arrival == 1) {
			this.status = "ready";
		} else {
			this.status = "waiting";
		}
	}

	public int getArrivalTime() {
		return Arrival;
	}

	public void setArrivalTime(int x) {
		this.Arrival = x;
	}

	public double getDuration() {
		return this.Burst;
	}

	public void setDuration(int duration) {
		this.Burst = duration;
	}

}

// listening to every single key of your piano
// smelling the absense of your keychain
// escaping the loneliness with my keyboard
// running across every keystone
//
// reading my keystrokes something something reawakening my heart to unlock your
// keychain
// your mother and I miss you so much linda
// i miss you little monkey