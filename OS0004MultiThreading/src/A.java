
class A extends Thread{
		public void show() {
			
			for( int i=1;i<=5;i++) {
				System.out.println("Hi A");
				try {
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
