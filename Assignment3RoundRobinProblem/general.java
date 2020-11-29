import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class general {

	public static void main(String[] args) throws IOException, InterruptedException {

		Scheduler scheduler = new Scheduler();
		Scanner input = new Scanner(new File("Input.txt"));
		int arrival;
		int burst;
		int processNumber = 0;

		// while loop function that takes 2 arguments from each line and input add them
		// in form of a specific process with the scheduler
		while (input.hasNext()) {

			arrival = Integer.parseInt(input.next());
			burst = Integer.parseInt(input.next());
			Scheduler.addProcess(++processNumber, arrival, burst);
		}

		// writes the output to my desired location
		// c:/Users/yidas/eclipse-workspace/Coen346Assignment2Test/Output.txt
		BufferedWriter writer = new BufferedWriter(new FileWriter("Output.txt"));

		// start threaded scheduler
		scheduler.start();
		writer.write(scheduler.returnString());

		// close object methods
		input.close();
		writer.close();
	}

}
