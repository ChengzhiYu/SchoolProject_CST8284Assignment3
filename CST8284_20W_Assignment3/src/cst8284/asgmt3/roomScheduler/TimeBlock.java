
package cst8284.asgmt3.roomScheduler;

import java.util.Calendar;
/**
 * Class Name: TimeBlock
 * @author Chengzhi Yu (Based on prof version of Assignment 2)
 * @version 1.0
 * Assignment title: CST8284_20W_Assignment_3
 * Assignment due date: March 28 2020
 */
public class TimeBlock implements java.io.Serializable{
	private Calendar startTime, endTime;
	/**
	 * No arg constructor chains to the 2 arg constructor
	 */
	public TimeBlock() {
		this(new Calendar.Builder().set(Calendar.HOUR_OF_DAY, 8).build(),
				new Calendar.Builder().set(Calendar.HOUR_OF_DAY, 24).build());
	}
	/**
	 * Two arg constructor sets start and end time
	 * @param start start time to be set
	 * @param end end time to be set
	 */
	public TimeBlock(Calendar start, Calendar end) {
	
		setStartTime(start); setEndTime(end);
	}
	/**
	 * toString method overriden from obj class for formating 
	 * @return String
	 */
	@Override
	public String toString() {
		return getStartTime().get(Calendar.HOUR_OF_DAY) + ":00 - " + getEndTime().get(Calendar.HOUR_OF_DAY) + ":00\n";
	}
	/**
	 * Passes the startTime to the constructor
	 * @param startTime startTime to be passed
	 */
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
		}
	/**
	 * returns the value of start time
	 * @return Calendar
	 */
	public Calendar getStartTime() {return startTime;}
	/**
	 * Passes the endTime to the constructor
	 * @param endTime the endTime to be passed
	 */
	public void setEndTime(Calendar endTime) {
		
			this.endTime = endTime;	
		}
	/**
	 * returns the value of the end time
	 * @return Calendar
	 */
	public Calendar getEndTime() {return endTime;}
	/**
	 * returns the duration of an event by subtracting the value of end time and the value of start time
	 * @return int
	 */
	public int duration() {
		return (getEndTime().get(Calendar.HOUR_OF_DAY) - getStartTime().get(Calendar.HOUR_OF_DAY));
	}
	/**
	 * Checks if the current event is conflicting with an exist event
	 * @param tb the time block to be tested
	 * @return boolean
	 */
	public boolean overlaps(TimeBlock tb) {
		if ((tb.getStartTime().get(Calendar.DAY_OF_YEAR) != this.getStartTime().get(Calendar.DAY_OF_YEAR))
				|| (tb.getStartTime().get(Calendar.YEAR) != this.getStartTime().get(Calendar.YEAR))) 
			return false;  // can't overlap; not on same date
	    return ((tb.getStartTime().get(Calendar.HOUR_OF_DAY) < getEndTime().get(Calendar.HOUR_OF_DAY))
				&& (tb.getEndTime().get(Calendar.HOUR_OF_DAY) > getStartTime().get(Calendar.HOUR_OF_DAY))) ; 
	}
	/**
	 * Checks if the start and end time are correctly inputed by user
	 * @param startTime start time to be examined
	 * @param endTime end time to be examined
	 * @return boolean
	 * @throws BadRoomBookingException catches the bad input
	 */
	public boolean isEndTimePrecedes(Calendar startTime, Calendar endTime) throws BadRoomBookingException{
		int duration = endTime.get(Calendar.HOUR_OF_DAY) - startTime.get(Calendar.HOUR_OF_DAY);
		if (duration < 0) {
			throw new BadRoomBookingException("End time precedes start time ","The room booking start time must occur before the end time of the room booking.");
		}
		return true;
	}
	/**
	 * Checks if the start and end time are correctly inputed by user
	 * @param startTime start time to be compared
	 * @param endTime end time to be compared
	 * @return boolean
	 * @throws BadRoomBookingException catches the bad input
	 */
	public boolean isZeroTime(Calendar startTime, Calendar endTime) throws BadRoomBookingException{
		int duration = endTime.get(Calendar.HOUR_OF_DAY) - startTime.get(Calendar.HOUR_OF_DAY);
		if (duration == 0) {
			throw new BadRoomBookingException("Zero time room booking ","Start and end time of the room booking are the same. The minimum time for a booking is one hour.");
		}
		return true;
	
	}
	
}
