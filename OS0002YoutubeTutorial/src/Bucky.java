
public class Bucky {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	Thread t1 = new Thread(new Tuna("one"));	// importance of runnable from Tuna!!!
	Thread t2 = new Thread(new Tuna("two"));
	Thread t3 = new Thread(new Tuna("three"));
	Thread t4 = new Thread(new Tuna("four"));
	
	t1.start();
	
	t2.start();
	
	t3.start();
	
	t4.start();
	
	
	
	
	
	}

}
