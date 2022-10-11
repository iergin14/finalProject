package lastHw;

public class Chef extends Thread {
	public Integer id = 0;

	public Chef() {
		// TODO Auto-generated constructor stub
	}
	public void sleepp(Integer num) {
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void run() {
		while(true) {
			Boolean check = false;
			Customer tempFood = null;
			synchronized(Main.toBePreparedFood) {
				if(Main.toBePreparedFood.size() != 0) {
					tempFood = Main.toBePreparedFood.poll();
					check = true;
			        System.out.println("Chef started to prepare food of customer " + Integer.toString(id));    
				}
			}
			
			if(check) {
				sleepp(5000);
				synchronized(Main.readyFood) {
					Main.readyFood.add(tempFood);
					System.out.println("Chef prepared food of customer " + Integer.toString(id));
				}
			sleepp(500);
			}
		}
	}
	
}
