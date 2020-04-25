package cst8284.asgmt3.room;
/**Class Name: ComputerLab
 * @author Chengzhi Yu (Based on prof version of Assignment 2)
 * @version 1.0
 * Assignment title: CST8284_20W_Assignment_3
 * Assignment due date: March 28 2020
 */
public final class ComputerLab extends Room{

	private static final int DEFAULT_SEATS=30;
	private int seats;
	/**
	 * No arg constructor sets the default seats number of 30
	 */
	public ComputerLab() {seats = DEFAULT_SEATS;}
	/**
	 * returns the integer number of  seats
	 * @return int
	 */
	protected int getSeats(){return seats;}
	/**
	 * returns the String represents the room type
	 * @return String
	 */
	protected String getRoomType(){return "computer lab";}
	/**
	 * returns the String represents the detailed info of the room
	 * @return String
	 */
	protected String getDetails(){return "contains outlets for 30 laptops";}

}
