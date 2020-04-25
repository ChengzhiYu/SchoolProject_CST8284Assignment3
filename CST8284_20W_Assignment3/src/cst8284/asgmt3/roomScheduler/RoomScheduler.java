
package cst8284.asgmt3.roomScheduler;
import java.util.Scanner;
import cst8284.asgmt3.room.Room;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
	/**Class Name: RoomScheduler
	 * @author Chengzhi Yu (Based on prof version of Assignment 2)
	 * @version 1.0
	 * Assignment title: CST8284_20W_Assignment_3
	 * Assignment due date: March 28 2020
	 */
public class RoomScheduler {
	private Room room;
	private static Scanner scan = new Scanner(System.in);
	private ArrayList<RoomBooking> roomBookings = new ArrayList<>(); 
	
	private static final int 
	   DISPLAY_ROOM_INFORMATION = 1, ENTER_ROOM_BOOKING = 2, 
	   DELETE_BOOKING = 3, CHANGE_BOOKING = 4, 
	   DISPLAY_BOOKING = 5, DISPLAY_DAY_BOOKINGS = 6, 
	   SAVE_BOOKINGS_TO_FILE = 7, LOAD_BOOKINGS_FROM_FILE = 8,
	   EXIT = 0;
	/**
	 * One arg constructor which has a setRoom() method
	 * @param room type of the room
	 */
	public RoomScheduler(Room room) {setRoom(room);
	}
	/**
	 * Sets the value of current obj room
	 * @param room room to be set
	 */
	private void setRoom(Room room) {this.room = room;
	}
	/**
	 * Gets the type and value of room
	 * @return Room
	 */
	private Room getRoom() {
		return room;
	}
	/**
	 * The switch for the main mathod allowing space to excute of exception handling 
	 * @return boolean
	 */
	public boolean loadAccount() {
		boolean accountLoaded = false;
		try {
			launch();
		accountLoaded = true;
		}
		catch(BadRoomBookingException e) {
			System.out.println(e.getHeader() + e.getMessage());
		}
		//catch (Exception ex) {
			//System.out.println("General exception thrown; source unknown");
		//}
		return accountLoaded;	
	}
	/**
	 * Laucher of the program, looping through the functions
	 */
	public void launch() {
		int choice = 0;
		if (new File("CurrentRoomBookings.book").exists()) loadBookingsFromFile();
		do {
		   choice = displayMenu();
		   executeMenuItem(choice);
		} while (choice != EXIT);		
	}
	/**
	 * Displays the options to excute the program
	 * @return int
	 */
	private int displayMenu() {
		System.out.println("Enter a selection from the following menu:");
		System.out.println(
			DISPLAY_ROOM_INFORMATION + ". Display room information\n" +
			ENTER_ROOM_BOOKING + ". Enter a room booking\n" +
			DELETE_BOOKING +". Remove a room booking\n" + 
			CHANGE_BOOKING + ". Change a room booking\n" + 
			DISPLAY_BOOKING  + ". Display a booking\n" +
			DISPLAY_DAY_BOOKINGS + ". Display room bookings for the whole day\n" +
			SAVE_BOOKINGS_TO_FILE + ". Backup current bookings to file\n" +
			LOAD_BOOKINGS_FROM_FILE + ". Load current bookings from file\n" +
			EXIT + ". Exit program");
		int ch = scan.nextInt();
		scan.nextLine();  // 'eat' the next line in the buffer
		System.out.println(); // add a space before next menu output
		return ch;
	}
	/**
	 * Calls the method corresponding to the functions the users choose
	 * @param choice the integer that the users selected corresponding to the function they want to use
	 */
	
	private  void executeMenuItem(int choice) {
		switch (choice) {
			case DISPLAY_ROOM_INFORMATION:
				displayRoomInfo();
			    break;			    
			case ENTER_ROOM_BOOKING: 
				saveRoomBooking(makeBookingFromUserInput()); 
				break;
			case DELETE_BOOKING:  
				System.out.println("Enter booking to delete");
				System.out.println(	deleteBooking(makeCalendarFromUserInput(null, true))?
				   "Booking deleted": "Booking not deleted");
				break;
			case CHANGE_BOOKING:			
				System.out.println("Enter booking to change");
				if (!changeBooking(makeCalendarFromUserInput(null, true)))
					System.out.println("Cannot change room booking");
				break;
			case DISPLAY_BOOKING: 
				displayBooking(makeCalendarFromUserInput(null, true));
				break;
			case DISPLAY_DAY_BOOKINGS: 
				displayDayBookings(makeCalendarFromUserInput(null, false)); 
				break;
			case SAVE_BOOKINGS_TO_FILE:
				System.out.println(saveBookingsToFile()?
					"Current room bookings backed up to file":
					"Could not backup room bookings to file");
				break;
			case LOAD_BOOKINGS_FROM_FILE:
				System.out.println(loadBookingsFromFile() != null?
					"Current room bookings loaded from file":
					"Room bookings could not be loaded from file");
				break;
			case EXIT: 
				System.out.println("Exiting Room Booking Application\n\n"); 
				break;
			default: System.out.println("Invalid choice: try again. (Select " + EXIT + " to exit.)\n");
		}
		System.out.println();  // add blank line after each output
	}
	/**
	 * A service method that makes it easier when getting the users' respond
	 * @param s a description of the current function
	 * @return String user input
	 */
    private static String getResponseTo(String s) {
    	System.out.print(s);
		return(scan.nextLine());
    }
	/**
	 * Generates a new booking based on user input, including contact information, activity and the time span
	 * @return RoomBooking
	 */
    private static RoomBooking makeBookingFromUserInput() { 
 
    	String[] fullName = getResponseTo("Enter Client Name (as FirstName LastName): ").split(" ");
 		String phoneNumber = getResponseTo("Phone Number (e.g. 613-555-1212): ");
		String organization = getResponseTo("Organization (optional): ");
		String category = getResponseTo("Enter event category: ");
		String description = getResponseTo("Enter detailed description of event: ");
				
		Calendar startCal = makeCalendarFromUserInput(null, true);
		Calendar endCal = makeCalendarFromUserInput(startCal, true);

		ContactInfo contactInfo = new ContactInfo(fullName[0], fullName[1], phoneNumber, organization);
		Activity activity = new Activity(category, description);
		TimeBlock timeBlock = new TimeBlock(startCal, endCal);
		if (!timeBlock.isZeroTime(startCal, endCal))
			throw new BadRoomBookingException(" ","");
		if(!timeBlock.isEndTimePrecedes(startCal, endCal))
			throw new BadRoomBookingException(" ","");
		
		return (new RoomBooking(contactInfo, activity, timeBlock));
    }
    /**
     * Creates a calendar that includes year, month, date and hour based on user input
     * @param initCal used to determine if a new calendar will be needed
     * @param requestHour used to determine if the end time will be needed for the user to input
     * @return Calendar
     */
    private static Calendar makeCalendarFromUserInput(Calendar initCal, boolean requestHour) {
    	Calendar cal = Calendar.getInstance(); cal.clear();
    	String date = "";
    	int hour = 0;	
    	boolean needNewCal = (initCal==null);
    	
   		if (needNewCal ) {
   			date = getResponseTo("Event Date (entered as DDMMYYYY): ");
   			if (!isBadCalendar(date)) {
   				throw new BadRoomBookingException("Bad Calendar date was entered ","The correct format is DDMMYYYY.");
   				}
   		}
   		int day = needNewCal ? Integer.parseInt(date.substring(0,2)) : initCal.get(Calendar.DAY_OF_MONTH);
   		int month = needNewCal ? Integer.parseInt(date.substring(2,4))-1 : initCal.get(Calendar.MONTH);
   		int year = needNewCal ? Integer.parseInt(date.substring(4,8)) : initCal.get(Calendar.YEAR);
   		
   		if (requestHour) 				
   			hour = processTimeString(getResponseTo((needNewCal?"Start":"End") +" Time: "));
				
   		cal.set(year, month, day, hour, 0);
		
		return cal;
    }
    /**
     * An exception controller to ensure the calendar is the right format 
     * @param date the date to be determined
     * @return boolean
     * @throws BadRoomBookingException catches the bad input
     */
    private static boolean isBadCalendar(String date) throws BadRoomBookingException {
    	
    	int day = Integer.parseInt(date.substring(0,2));
    	int month = Integer.parseInt(date.substring(2,4))-1;
    	int year = Integer.parseInt(date.substring(4,8));

    	Calendar cal = new GregorianCalendar();
    	
    	cal.set(year, month, day);
    	if(cal.getActualMaximum(Calendar.DAY_OF_MONTH)+1 < Integer.parseInt(date.substring(0,2)) && date.length() != 8) {
    		throw new BadRoomBookingException("Bad Calendar date was entered ","The correct format is DDMMYYYY.");	
    	}
    	
    	return true;	
    }
    /**
     * A service method that trims the user input and convert the numeric string to int
     * @param t the numeric string that user input indicating the time span
     * @return int
     */
	private static int processTimeString(String t) {
		int hour = 0;
		t = t.trim();
		if (t.contains ("pm") || (t.contains("p.m."))) hour = Integer.parseInt(t.split(" ")[0]) + 12;
		if (t.contains("am") || t.contains("a.m.")) hour = Integer.parseInt(t.split(" ")[0]);
		if (t.contains(":")) hour = Integer.parseInt(t.split(":")[0]);
		return hour;
	}
	/**
	 * Locates the room booking the user interested
	 * @param cal the calendar to be located
	 * @return RoomBooking to be returned
	 */
    private RoomBooking findBooking(Calendar cal) {
    	Calendar oneHourLater = Calendar.getInstance();
    	oneHourLater.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY)+1);
    	TimeBlock findTB = new TimeBlock(cal, oneHourLater);    		    	
    	RoomBooking rb = new RoomBooking();
    	rb.setTimeBlock(findTB);
    	SortRoomBookingByCalendar sortRB = new SortRoomBookingByCalendar();
    	int index = Collections.binarySearch(getRoomBookings(), rb, sortRB);
    	
    	if (index>=0){
    		
    		return getRoomBookings().get(index);
    	} 	
    	return null;
    
    }
    /**
     * Formats and displays the room information
     */
    private void displayRoomInfo() {
    	System.out.println(getRoom().toString());
    }
    /**
     * Saves a room booking to the array list where no other bookings are already saved
     * @param roomBooking the entry to be saved
     * @return boolean
     */
	private boolean saveRoomBooking(RoomBooking roomBooking) {	
		
		TimeBlock tb = roomBooking.getTimeBlock();  // Check this TimeBlock to see if already booked
		
		Calendar cal = (Calendar)tb.getStartTime().clone(); // use its Calendar
		int hour = cal.get(Calendar.HOUR_OF_DAY);//Get first hour of block
		for (; hour < tb.getEndTime().get(Calendar.HOUR_OF_DAY); hour++){ //Loop through each hour in TimeBlock
			cal.set(Calendar.HOUR_OF_DAY, hour); // set next hour
		   if (findBooking(cal)!=null) {  // TimeBlock already booked at that hour, can't add appointment
		    	System.out.println("Cannot save booking; that time is already booked");
				return false;
		}  // else time slot still available; continue loop to next hour
		getRoomBookings().add(roomBooking);  
	
    	SortRoomBookingByCalendar sortRB = new SortRoomBookingByCalendar();
    	Collections.sort(getRoomBookings(),sortRB);
    	
 
		System.out.println("Booking time and date saved."); 
	}
		 
		return true;
	}
	/**
	 * Displays all the room booking information of a certain day
	 * @param cal the calendar to be shown
	 * @return RoomBooking
	 */
	private RoomBooking displayBooking(Calendar cal) {  
		RoomBooking booking = findBooking(cal);
		int hr = cal.get(Calendar.HOUR_OF_DAY);
		System.out.print((booking!=null) ?
		   "---------------\n"+ booking.toString()+"---------------\n": 
  	       "No booking scheduled between "+ hr + ":00 and " + (hr + 1) + ":00\n"
		);
		return booking;
	}
	/**
	 * To remove an entry in the array list
	 * @param cal the calendar to be deleted
	 * @return boolean
	 */
	private boolean deleteBooking(Calendar cal)	{
		RoomBooking rb = displayBooking(cal);
		if(rb != null)
		   if(getResponseTo("Press 'Y' to confirm deletion, " +
			  "any other key to abort: ").toUpperCase().equals("Y")) { 
				getRoomBookings().remove(rb);
				return true;	
		   }
		return false;	
	}
	/**
	 * To change an entry in the array list
	 * @param cal the calendar to be changed
	 * @return boolean
	 */
	private boolean changeBooking(Calendar cal) {
		RoomBooking rb = displayBooking(cal);  // existing RoomBooking
		if (rb == null) return false;
		Calendar startCal = makeCalendarFromUserInput(cal, false);
		startCal.set(Calendar.HOUR_OF_DAY,processTimeString(getResponseTo("Enter New Start Time: ")));
		Calendar endCal = makeCalendarFromUserInput(cal, false);
		endCal.set(Calendar.HOUR_OF_DAY,processTimeString(getResponseTo("Enter New End Time: ")));
		TimeBlock tb = new TimeBlock(startCal, endCal);  // new TimeBlock
		for (RoomBooking rbook: getRoomBookings())  // check this won't collide with existing TimeBlock
			if (!rbook.equals(rb) &&   // ignore rb already in ArrayList
			   (rbook.getTimeBlock().overlaps(tb))) return false;
		System.out.println("Booking has been changed to:");
		rb.setTimeBlock(tb);
		return (displayBooking(startCal) != null);
	}
	/**
	 * 
	 * Displays a room booking of a whole day from 8:00 to 24:00
	 * @param cal the day calendar to be displayed
	 */
	private void displayDayBookings(Calendar cal) {				  	
	
		for (int hrCtr = 8; hrCtr < 24; hrCtr++) {
			cal.set(Calendar.HOUR_OF_DAY, hrCtr);
			
			Calendar oneHourLater = Calendar.getInstance();
	    	oneHourLater.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY)+1);
			
			TimeBlock findTB = new TimeBlock(cal,oneHourLater);			
		
			RoomBooking rb = new RoomBooking();
	    	rb.setTimeBlock(findTB);
	    	
	    	SortRoomBookingByCalendar sortRB = new SortRoomBookingByCalendar();
	    	int index = Collections.binarySearch(getRoomBookings(), rb, sortRB);
	    	
	    	if (index>=0){
	    		rb = getRoomBookings().get(index);
	    	}
	    	else{
	    		rb = null;
	    	}
	    	int hr = cal.get(Calendar.HOUR_OF_DAY);
			System.out.print((rb!=null) ?
			   "---------------\n"+ rb.toString()+"---------------\n": 
	  	       "No booking scheduled between "+ hr + ":00 and " + (hr + 1) + ":00\n");
		if (rb !=null) hrCtr = rb.getTimeBlock().getEndTime().get(Calendar.HOUR_OF_DAY) - 1;
		}		
	}
	/**
	 * Outputs entry to an outside file
	 * @return boolean
	 */
	private boolean saveBookingsToFile(){
	   try( FileOutputStream fos=new FileOutputStream("CurrentRoomBookings.book");
		    ObjectOutputStream oos=new ObjectOutputStream(fos);
		  ){
		    for(RoomBooking rb:getRoomBookings()) oos.writeObject(rb);
		    return true;
	      }
	    catch(IOException ex){return false;}
	}
	/**
	 * Inputs entry from an outside file
	 * @return ArrayList
	 */
	private ArrayList<RoomBooking> loadBookingsFromFile(){
	   ArrayList<RoomBooking> ar = new ArrayList<>();
       try( FileInputStream fis=new FileInputStream("CurrentRoomBookings.book");
		    ObjectInputStream ois =new ObjectInputStream(fis);
	      ){
			while(true)
			   ar.add((RoomBooking)(ois.readObject()));
	      } 
       catch (EOFException ex) {return ar;}
	   catch (IOException | ClassNotFoundException e){return null;} 
	}
	/**
	 * returns a array list of the type of RoomBooking
	 * @return ArrayList
	 */
	private ArrayList<RoomBooking> getRoomBookings() {return roomBookings;}
	
   
}

