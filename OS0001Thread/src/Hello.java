
class Hello implements Runnable {
	
		private String name;
		public Hello(String x) {
			
			this.name=x;
		}
		public void run() {
			
			System.out.println("Hello" + name);
		}
	}
