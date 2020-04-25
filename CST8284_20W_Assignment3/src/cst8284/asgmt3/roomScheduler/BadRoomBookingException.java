package cst8284.asgmt3.roomScheduler;
	/**
	 * Class Name: BadRoomBookingException
	 * @author Chengzhi Yu (Based on prof version of Assignment 2)
	 * @version 1.0
	 * Assignment title: CST8284_20W_Assignment_3
	 * Assignment due date: March 28 2020
	 */
public class BadRoomBookingException extends java.lang.RuntimeException {
	 
		private static final long serialVersionUID = 1L;
			private String header = "Bad room booking entered";
			/**
			 * No arg constructor chains to the two arg constructor
			 */
			public BadRoomBookingException() {	
				this("Bad room booking entered", "Please try again");
			} 
			/**
			 * Two arg constructor creates the bad room booking exception with header and error message
			 * @param header the title of the exception
			 * @param message the detailed message of the exception
			 */
			public  BadRoomBookingException(String header, String message) {
				super(message);
				this.header = header;
			}
			/**
			 * The getter of the header
			 * @return String
			 */
			public String getHeader() {
				return header;
			}
			/**
			 * the setter of the header
			 * @param header the current header
			 */
			public void setHeader(String header) {
				this.header = header;
			}
		}

