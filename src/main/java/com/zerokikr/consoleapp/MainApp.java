package com.zerokikr.consoleapp;

import java.util.List;
import java.util.Scanner;

import org.hibernate.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;





public class MainApp 
{
    public static void main(String[] args) {
       
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
    			  .addAnnotatedClass(Buyer.class)
                  .addAnnotatedClass(Item.class)
                  .addAnnotatedClass(Purchase.class)
                  .buildSessionFactory();
    
    
    //createTableItems(factory);
        
    System.out.println("Please enter command. Press e to exit");
    
    try {
    	Scanner scanner = new Scanner(System.in);
    	
    	while (scanner.hasNext()) {
    		
    		String s = scanner.nextLine();
    		
    		if (s.equals("e")) {
    			System.out.println("EXIT");
    			break;
    		}
    		
    		String[] commands = s.split(" ");
    		
    		if (commands[0].equals("buy")) {
    			buy (factory, commands[1], commands[2]);    			
    			System.out.println(commands[1] + " purchased " + commands[2]);
    			break;
    		}
    		
    		else if (commands[0].equals("showProductsByPerson")) {
    			showProductsByPerson(factory, commands[1]);
    			break;
    		}
    		
    		else if (commands[0].equals("findPersonsByProduct")) {
    			findPersonsByProduct(factory, commands[1]);
    			break;
    		}
    		
    		else if (commands[0].equals("removePerson")) {
    			removePerson(factory, commands[1]);
    			break;
    		}
    		
    		else if (commands[0].equals("removeProduct")) {
    			removeProduct(factory, commands[1]);
    			break;
    		}
    		
    		else {
    			System.out.println("The following commands are supported:");
    			System.out.println("buy buyer_name item_name");
    			System.out.println("showProductsByPerson person_name");
    			System.out.println("findPersonsByProduct item_name");
    			System.out.println("removePerson person_name");
    			System.out.println("removeProduct product_name");
    		}
    	}
    	
    	scanner.close();
    }
    
    finally {
    	factory.close();
    	
}
        	
    }  
    
    private static void buy(SessionFactory factory, String buyerName, String itemName) {
	   Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			
			Buyer buyer = (Buyer) session.createQuery("FROM Buyer b WHERE b.name = :name") // Buyer is the name of the class
					.setParameter("name", buyerName).getSingleResult();
			
			Item item = (Item) session.createQuery("FROM Item i WHERE i.name = :name") // Item is the name of the class
					.setParameter("name", itemName).getSingleResult();
			
			PurchaseId purchaseId = new PurchaseId();
			
			purchaseId.setBuyerId(buyer.getId());
			purchaseId.setItemId(item.getId());
			
			Purchase purchase = new Purchase();
			purchase.setPurchaseId(purchaseId);
			purchase.setPurchasePrice(item.getPrice());
			
			session.save(purchase);
			session.getTransaction().commit();

		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			factory.close();
			session.close();
		}
    }
    
    private static void showProductsByPerson (SessionFactory factory, String buyerName) {
    	Session session = factory.getCurrentSession();
    	
    	try {
    		session.beginTransaction();
    		
    		Buyer buyer = (Buyer) session.createQuery("FROM Buyer b WHERE b.name = :name")
    				.setParameter("name", buyerName).getSingleResult();
    		
    		List<Purchase> purchases = buyer.getPurchases();
    		
    		System.out.println("Items purchased by " + buyer.getName() + ": ");
    		
    		for (Purchase p: purchases) {
    			
    			System.out.println(p.getItem().getName() + " " + p.getItem().getPrice());
    		}		
    		
			session.getTransaction().commit();
			
    	}
    	
    	catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			factory.close();
			session.close();
		}
    }
    
    public static void findPersonsByProduct(SessionFactory factory, String itemName) {
    	Session session = factory.getCurrentSession();
    	
    	try {
    		session.beginTransaction();
    		
    		Item item = (Item) session.createQuery("FROM Item i WHERE i.name = :name")
    				.setParameter("name", itemName).getSingleResult();
    		
    		List<Purchase> purchases = item.getPurchases();
    		
    		System.out.println("Buyers who purchased " + item.getName() + ": ");
    		
    		for (Purchase p: purchases) {
    			
    			System.out.println(p.getBuyer().getName());
    		}		
    		
			session.getTransaction().commit();
			
    	}
    	
    	catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			factory.close();
			session.close();
		}
    	
    }
    
    public static void removePerson(SessionFactory factory, String buyerName) {
    	
    Session session = factory.getCurrentSession();
    
    try {
    		
    	session.beginTransaction();
    	
    	Buyer buyer = (Buyer) session.createQuery("FROM Buyer b WHERE b.name = :name")
				.setParameter("name", buyerName).getSingleResult();
    	
    	session.delete(buyer);

    	session.getTransaction().commit();
	}
    
	catch (Exception e) {
		e.printStackTrace();
	}
	
	finally {
		factory.close();
		session.close();
	}
	
    }
    
    public static void removeProduct(SessionFactory factory, String itemName) {
    	
        Session session = factory.getCurrentSession();
        
        try {
        		
        	session.beginTransaction();
        	
        	Item item = (Item) session.createQuery("FROM Item i WHERE i.name = :name")
    				.setParameter("name", itemName).getSingleResult();
        	
        	session.delete(item);

        	session.getTransaction().commit();
    	}
        
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	finally {
    		factory.close();
    		session.close();
    	}
    		

        	
        }

    private static void createTableItems(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		try {
			Buyer snake = new Buyer ("Snake");
			Buyer mai = new Buyer ("Mai");
			Buyer batman = new Buyer ("Batman");
			Buyer drake = new Buyer ("Drake");
			
			Item apple = new Item ("Apple", 10);
			Item chips = new Item ("Chips", 15);
			Item phones = new Item ("Headphones", 100);
			Item paint = new Item ("Paint", 140);
			Item boat = new Item ("Boat", 999);
			Item shovel = new Item ("Shovel", 89);
			
			session.beginTransaction();
			
			session.save(snake);
			session.save(mai);
			session.save(batman);
			session.save(drake);
			session.save(apple);
			session.save(chips);
			session.save(phones);
			session.save(paint);
			session.save(boat);
			session.save(shovel);
			
				
			session.getTransaction().commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			factory.close();
			session.close();
		}
	}
    
    
    
    
}

