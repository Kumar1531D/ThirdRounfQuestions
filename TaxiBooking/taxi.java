package TaxiBooking;

import java.util.*;

public class taxi {
	
	static int id = 0;
	int taxiId;
	boolean booked;
	char currentSpot;
	int freeTime;
	int totalEarning;
	List<String> trips;
	
	taxi(){
		id = id +1;
		taxiId = id;
		booked = false;
		currentSpot = 'A';
		freeTime = 6;
		totalEarning = 0;
		trips = new ArrayList();
	}
	
	public void setDetails(boolean booked,char currentSpot,int freeTime,int totalEarning,String trip) {
		this.booked = booked;
		this.currentSpot = currentSpot;
		this.freeTime = freeTime;
		this.totalEarning = totalEarning;
		trips.add(trip);
	}
	
	public void printDetails() {
		System.out.println(" TAXI "+this.taxiId + "Total Earning is : " +this.totalEarning);
		System.out.println("TaxiID    BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");
		
		for(String t : trips) {
			System.out.println(this.taxiId + "           "+t);
		}
		System.out.println("-----------------------------------------------------------------------------------------");
	}

}
