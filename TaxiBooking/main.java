package TaxiBooking;

import java.util.*;

public class main {
	
	public static List<taxi> createTaxi(int n) {
		
		List<taxi> trip = new ArrayList();
		
		for(int i=1;i<=n;i++) {
			taxi t = new taxi();
			trip.add(t);
		}
		
		return trip;
		
	}
	
	public static List<taxi> getFreeTaxi(List<taxi> trip,char pickupPoint,int pickupTime){
		
		List<taxi> freeTaxi = new ArrayList();
		
		for(taxi t : trip) {
			if(t.freeTime<=pickupTime && Math.abs((t.currentSpot-'A')-(pickupPoint-'A'))<=pickupTime-t.freeTime) {
				freeTaxi.add(t);
			}
		}
		
		return freeTaxi;
	}
	
	public static void bookTaxi(int customerID,List<taxi> freeTaxi,char pickupPoint,char dropPoint,int pickupTime) {
		int min = 10000;
		
		taxi bookedTaxi = null;
		
		char currentSpot = 'M';
		
		int freeTime = 6;
		
		int earning = 0;
		
		String trip = "";
		
		for(taxi t : freeTaxi) {
			int distanceOfCustomerTaxi = Math.abs((t.currentSpot-'A')-(pickupPoint-'A'));
			
			if(distanceOfCustomerTaxi<min) {
				bookedTaxi = t;
				
				currentSpot = dropPoint;
				
				int distanceOfPickupDrop = Math.abs((pickupPoint-'A')-(dropPoint-'A'))*15;
				
				int dropTime = pickupTime + (distanceOfPickupDrop/15);
				
				freeTime = dropTime;
				
				earning = (distanceOfPickupDrop-5)*10+100;
				
				trip = customerID + "             " +customerID+"            "+pickupPoint+"      "+dropPoint+"        "+pickupTime+"        "+dropTime+"          "+earning;
				
				min = distanceOfCustomerTaxi;
			}
			
		}
		
		bookedTaxi.setDetails(true, currentSpot, freeTime, earning+bookedTaxi.totalEarning, trip);
		
		System.out.println("Taxi "+bookedTaxi.taxiId+" Booked");
		
		
	}

	public static void main(String[] args) {
		
		List<taxi> taxis = createTaxi(4);
		
		Scanner in = new Scanner(System.in);
		Scanner str = new Scanner(System.in);
		
		int id = 1;
		
		while(true) {
			System.out.println("1.Book taxi");
			System.out.println("2.Print Details");
			System.out.println("Enter your choice : ");
			
			int ch = in.nextInt();
			
			switch(ch) {
				case 1:
					int customerID = id;
					System.out.println("Enter the pickup point");
					char pickupPoint = str.next().charAt(0);
					System.out.println("Enter the Drop point");
					char dropPoint = str.next().charAt(0);
					System.out.println("Enter the pickup time");
					int pickupTime = in.nextInt();
					
					if(pickupPoint<'A' || pickupPoint>'F' || dropPoint<'A' || dropPoint>'F') {
						System.out.println("Invalid Pickup or Drop Point");
						return;
					}
					
					List<taxi> freeTaxi = getFreeTaxi(taxis,pickupPoint,pickupTime);
					
					if(freeTaxi.size()==0) {
						System.out.println("No taxi is available");
						return;
					}
					
					Collections.sort(freeTaxi,(a,b)-> a.totalEarning-b.totalEarning);
					
					bookTaxi(customerID,freeTaxi,pickupPoint,dropPoint,pickupTime);
					
					break;
					
				case 2:
					
					for(taxi t : taxis) {
						t.printDetails();
					}
					break;
					
				default:
					return;
			}
		}

	}

}
