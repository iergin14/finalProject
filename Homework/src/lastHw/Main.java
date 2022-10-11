package lastHw;
import java.util.LinkedList;
import java.util.*;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

    public static Queue<Customer> waitTable= new LinkedList<>();
    public static Queue<Customer> waitTakeOrder = new LinkedList<>();
    public static Queue<Customer> toBePreparedFood = new LinkedList<>();
    public static Queue<Customer> readyFood = new LinkedList<>();
    public static int[] tableList = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

	
	public static void main(String[] args) throws InterruptedException {
	    Random rand = new Random(); 
		
		for (int i = 0; i < 10; i++) {
			Waiter tempW = new Waiter();
			tempW.id = i;
			tempW.start();
		}
		for (int i = 0; i < 2; i++) {
			Chef tempC = new Chef();
			tempC.id = i;
			tempC.start();
		}
			
		
	    
	    for (int i = 0; true; i++) {
	        System.out.println("Customer "+ Integer.toString(i) + " arrived!!!");
	        Integer tableNum = -1 ;
        	for (int j = 0; j < tableList.length; j++) {
        		if(tableList[j] == 0) {
        			tableList[j] = 1;
        			tableNum = j;
        			break;
        			
        		}
        	}
	        if(tableNum == -1) {
	        	System.out.println("Customer "+ Integer.toString(i) + " could not find a table.");
	        	int randNum = rand.nextInt(2);     
	        	if(randNum == 1) {
		        	Customer customer = new Customer();
		        	customer.id = i;
		        	waitTable.add(customer);
		        	System.out.println("Customer "+ Integer.toString(i) + " waits table.");
		        }else {
		        	System.out.println("Customer "+ Integer.toString(i) + " did not want to wait");
		        }
		    	//Thread.sleep(1000);
	        }else {
	        	System.out.println("Customer "+ Integer.toString(i) + " found a table at " + Integer.toString(tableNum));
	        	Customer customer = new Customer();
	        	customer.id = i;
	        	customer.table = tableNum;
	        	waitTakeOrder.add(customer);
	        }
	        
	        Thread.sleep(1903);
	   
	    }
	    
	   
	
	    
	}

}
