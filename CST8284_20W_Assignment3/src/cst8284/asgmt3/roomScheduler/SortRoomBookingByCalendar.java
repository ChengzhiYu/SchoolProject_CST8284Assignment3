
package cst8284.asgmt3.roomScheduler;
import java.util.Comparator;
	/**
	 * Class Name: SortRoomBookingByCalendar
	 * @author Chengzhi Yu (Based on prof version of Assignment 2)
	 * @version 1.0
	 * Assignment title: CST8284_20W_Assignment_3
	 * Assignment due date: March 28 2020
	 */
	public class SortRoomBookingByCalendar implements Comparator<RoomBooking>  {
	/**
	 * Compares two RoomBookings by subtracting their value in milliseconds
	 * @return int
	 */
		@Override
		public int compare(RoomBooking c1, RoomBooking c2) {
			
		
			return (int)(c1.getTimeBlock().getStartTime().getTimeInMillis() - 
					c2.getTimeBlock().getStartTime().getTimeInMillis());
		}
	
	}
