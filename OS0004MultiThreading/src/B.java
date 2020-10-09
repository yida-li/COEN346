
	
	class B extends Thread{
		
public void show() {
			
			for( int i=1;i<=5;i++) {
				System.out.println("Hi B");
				try {
				
					B obj = new B();
					obj.start();
					/*A obj1 = new A();
					obj1.start();*/
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public void run() {
			show();
		}
		
	}