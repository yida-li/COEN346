import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.Random;
import java.util.Queue;

public class Scheduler extends Thread {
	double quantumTime = 1; // Quantum time set to 1, or 0 depending on the settings
	String log = ""; // updates and keeps info to write onto Output.txt
	String waitingTimelog = ""; // updates and keep info of waiting processes
	static Queue<Process> processQueue = new ArrayDeque<>(); // initialize readyQueue
	static Queue<Process> waitingQueue = new ArrayDeque<>(); // initialize waitingQueue

	// function to add thread process
	public static void addProcess(int id, int arrival, int duration) {
		Process process = new Process(id, arrival, duration);
		process.start();
		if (process.status == "ready") {
			processQueue.add(process);
		}
		if (process.status == "waiting") {
			waitingQueue.add(process);
		}
	}

	// increments the scheduler global quantum time
	public void IncreaseQuantumClock(double y) {
		quantumTime += y;
	}

	// returns the quantum time based on 10 percent of the process's burst time
	double getQuantumTime(double y) {
		return (double) (0.1 * y);
	}

	// check the waitingQueue to see if there are processes that are due on their
	// arrival time
	public void checkWaitingQueue() {
		if (waitingQueue.peek() == null)
			return;

		for (int i = 0; i < waitingQueue.size(); i++) {

			Process temp = waitingQueue.remove();
			if (temp.arrivalTime <= quantumTime) {
				temp.status = "ready";
				processQueue.add(temp);
			} else
				waitingQueue.add(temp);
		}
	}

	// increments the process that are on waiting for their turn
	public void incrementWaitingTime(double x) {
		for (int i = 0; i < waitingQueue.size(); i++) {
			Process temp = waitingQueue.remove();
			if (temp.status == "waiting" || temp.status == "paused") {
				temp.waitingTime += x;
			}
			waitingQueue.add(temp);
		}
		for (int i = 0; i < processQueue.size(); i++) {
			Process temp = processQueue.remove();
			if (temp.status == "waiting" || temp.status == "paused") {
				temp.waitingTime += x;
			}
			processQueue.add(temp);
		}
	}

	// select the thread with the lowest burst time and switch it to the front of
	// queue
	public void selectThread() {
		if (processQueue.size() >= 2) {
			Process first = processQueue.peek();

			for (int i = 0; i < processQueue.size() + 1; i++) {

				Process temp = processQueue.remove();

				if ((temp.burstTime == first.burstTime) && (temp.waitingTime > first.waitingTime)) {
					// give priority to older process , more waiting time
					processQueue.add(first);
					first = temp;

				} else if (temp.burstTime < first.burstTime) {
					// if the 2 process have same burst time,
					processQueue.add(first);
					first = temp;

				} else if (temp.burstTime > first.burstTime) {
					processQueue.add(temp);
				} else {

				}
			}

			// fairness implementation

			int random = getRandomNumberInRange(1, 3);
			if (random == 3) {
				for (int i = 0; i < processQueue.size() - 1; i++) {
					Process temp = processQueue.remove();
					processQueue.add(temp);
				}
				processQueue.add(first);
			}

			else {
				processQueue.add(first);
				for (int i = 0; i < processQueue.size() - 1; i++) {
					Process temp = processQueue.remove();
					processQueue.add(temp);
				}

			}
		}

	}

	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	// the main method that runs the process, increments wait time, record log,
	// decrements burst time, change process status
	public void runProcess() {
		// choose the thread with least amount of burst time in the readyqueue
		selectThread();

		// pop the front of the queue
		Process temp = (processQueue.remove());

		// open the lock, and awaken the thread
		temp.awakenThread();

		if (temp.status == "ready") {
			temp.status = "started";
			log += ("Time :" + tD(quantumTime) + ", Process " + temp.processNumber + " Process Status :" + temp.status
					+ "\n");

		}

		if (temp.status == "started" || temp.status == "paused") {
			temp.status = "resumed";
			log += ("Time :" + tD(quantumTime) + ", Process " + temp.processNumber + " Process Status :" + temp.status
					+ "\n");

		}

		// increase the schedule time based on the quantum burst time
		IncreaseQuantumClock(getQuantumTime(temp.burstTime));
		// increment waiting time for processes inside waiting queue
		incrementWaitingTime(getQuantumTime(temp.burstTime));
		// decrease process burst time
		temp.burstTime -= getQuantumTime(temp.burstTime);

		if (temp.status == "resumed") {
			temp.status = "paused";
			log += ("Time :" + tD(quantumTime) + ", Process " + temp.processNumber + " Process Status :" + temp.status
					+ "\n");
		}

		// check if waiting list element is ready
		checkWaitingQueue();

		// if burstTime is too low, set finish
		if (temp.burstTime <= 0.1) {
			log += ("Time :" + tD(quantumTime) + ", Process" + temp.processNumber + " Process Status : Finished"
					+ "\n");
			waitingTimelog += ("Process " + temp.processNumber + " waiting time :" + tD(temp.waitingTime) + "\n");

		}
		// or else add process back to queue
		else {
			processQueue.add(temp);

		}

		// lock the thread, synchronized to wait mode....
		temp.lockThread();

	}

	// method to truncated 3 decimal places
	public double tD(double x) {
		Double truncatedDouble = BigDecimal.valueOf(x).setScale(3, RoundingMode.HALF_UP).doubleValue();
		return truncatedDouble;

	}

	public String returnString() throws InterruptedException {

		Thread.sleep(3000);
		return log + waitingTimelog;
	}

	@Override
	public void run() {
		// as long as both queues are not both empty, keep a loop
		while ((processQueue.size() != 0)) {

			runProcess();
		}
	}

}
