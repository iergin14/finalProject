package lastHw;

import java.util.Random;

public class Customer extends Thread{
	public Integer id = null;
	public Integer table = -1;
	public String order = null;
	public String[] foodList = {"pasta", "pizza", "beaf"};
	public String[] drinkList = {"cola", "wine", "water", "soda"};
	public Customer() {

			int randNum = getRandomNumberUsingNextInt(0,3);
			int foodRandNum = getRandomNumberUsingNextInt(0,3);
			int drinkRandNum = getRandomNumberUsingNextInt(0,4);
			int randNum2 = getRandomNumberUsingNextInt(0,2);
			
			if(randNum == 0) { //if customer wants one food
				order = "wants to order " + foodList[foodRandNum];
			}else if (randNum == 1) { //if customer wants 2 food
				int foodRandNum2 = getRandomNumberUsingNextInt(0,2);
				while(foodRandNum2 == foodRandNum) {
					foodRandNum2 = getRandomNumberUsingNextInt(0,2);
				}
				order = "wants to order " + foodList[foodRandNum] + " and " + foodList[foodRandNum2];
			}
			
			if(randNum == 2) {	//if customer does not want to eat
				order = "wants to order " + drinkList[drinkRandNum];
			}else if(randNum2 == 0) {
				order +=  " and " + drinkList[drinkRandNum];
			}

	}
	
	public int getRandomNumberUsingNextInt(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}

  public void run() {
	try {
		System.out.println("Customer " + Integer.toString(id) + " is eating");
		Thread.sleep(3000);
		Main.tableList[table]=0;
		System.out.println("Customer " + Integer.toString(id) + " finished eating and left the restuarant");
		if(Main.waitTable.size()>0) {
			Customer waitingCustomer = Main.waitTable.poll();	
			waitingCustomer.table = table;
			Main.tableList[table]=1;
			Main.waitTakeOrder.add(waitingCustomer);
		}
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
  }

}
