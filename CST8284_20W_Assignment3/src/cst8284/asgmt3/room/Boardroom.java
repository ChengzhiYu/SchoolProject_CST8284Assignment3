package cst8284.asgmt3.room;
/**Class Name: Boardroom
 * @author Chengzhi Yu (Based on prof version of Assignment 2)
 * @version 1.0
 * Assignment title: CST8284_20W_Assignment_3
 * Assignment due date: March 28 2020
 */
public final class Boardroom extends Room{

	private int seats;
	/**
	 * No arg constructor sets the default seats number of 16
	 */
	public Boardroom() {seats = 16;}
	/**
	 * returns the integer number of  seats
	 * @return int
	 */
	protected int getSeats() {return seats;}
	/**
	 * returns the room type in string
	 * @return String
	 */
	protected String getRoomType(){return "board room";}
	/**
	 * returns the String represents the detailed info of the room
	 * @return String
	 */
	protected String getDetails(){return "conference call enabled";}
}
