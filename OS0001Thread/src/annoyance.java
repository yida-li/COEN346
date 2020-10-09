
public class annoyance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Thread t = new Thread(new Hello("babel"));
		
		
		Thread t1 = new Thread(new Hello("cain"));
		
		
		Thread t2 = new Thread(new Hello("esther"));
		
		
		Thread t3 = new Thread(new Hello("michael"));
		
		
		
		t.start();
		t1.start();
		t2.start();
		t3.start();
		
		
	}
	
}
