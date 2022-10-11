package lastHw;

public class Waiter extends Thread {
	public Integer id = 0;

	public Waiter() {
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
			Boolean check2 = false;
			Customer tempCustomer = null;
			Customer tempCustomer1 = null;
			synchronized(Main.waitTakeOrder) {
				if(Main.waitTakeOrder.size() != 0) {
				tempCustomer = Main.waitTakeOrder.poll();
				check = true;
		        System.out.println("Waiter " + Integer.toString(id) + " went to take the order of Customer "+ Integer.toString(tempCustomer.id));
		       
				}
			}
			synchronized(Main.readyFood) {
				if(Main.readyFood.size() != 0 && !check) {
					tempCustomer1 = Main.readyFood.poll();
			        System.out.println("Waiter " + Integer.toString(id) + " takes food of Customer " + Integer.toString(tempCustomer1.id) );
			        check2 = true;	
				}
			}
			
			if(check) {
				sleepp(3000);
				synchronized(Main.toBePreparedFood) {
					Main.toBePreparedFood.add(tempCustomer);
					System.out.println("Customer " + Integer.toString(tempCustomer.id) + " gave order to Waiter " + Integer.toString(id));
				    System.out.println("Customer " + Integer.toString(tempCustomer.id) + " "+ tempCustomer.order);
				}
			}
			
			if(check2) {
				tempCustomer1.start();
				
			}
			
			sleepp(500);
			
		}
	}
	
	
}
