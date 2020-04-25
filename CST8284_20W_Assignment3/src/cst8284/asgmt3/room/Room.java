package cst8284.asgmt3.room;
/**Class Name: Room
 * @author Chengzhi Yu (Based on prof version of Assignment 2)
 * @version 1.0
 * Assignment title: CST8284_20W_Assignment_3
 * Assignment due date: March 28 2020
 */
public abstract class Room {
	
	public static final long serialVersionUID=1L;
	private static final String DEFAULT_ROOM = "unknown room number";
	private String roomNumber;
	/**
	 * No arg constructor chains to one arg constructor
	 */
	protected Room() {this(DEFAULT_ROOM);}
	/**
	 * One arg constructor sets the room number
	 * @param roomNum room number to be set in the constructor
	 */
	protected Room(String roomNum) {setRoomNumber(roomNum);}
	/**
	 * Passes the String value to set the room number
	 * @param roomNum to be passed to the constructor
	 */
	public void setRoomNumber(String roomNum) {roomNumber = roomNum;}
	/**
	 * returns the room number
	 * @return String
	 */
	public String getRoomNumber() {return roomNumber;}
	/**
	 * abstract method to be overridden in the child classes
	 * @return int
	 */
    protected abstract int getSeats();
    /**
     * abstract method to be overridden in the child classes
     * @return String
     */
    protected abstract String getRoomType() ;
    /**
     * abstract method to be overridden in the child classes
     * @return String
     */
	protected abstract String getDetails();
	/**
	 * toString method overriden from obj class for formating 
	 * @return String
	 */
	public String toString( ){return getRoomNumber() + " is a " +
		getRoomType() + " with " + getSeats() + " seats; " + getDetails();}
}
