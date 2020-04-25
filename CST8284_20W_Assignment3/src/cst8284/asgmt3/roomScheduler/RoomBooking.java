
package cst8284.asgmt3.roomScheduler;
	/**
	 * Class Name RoomBooking
	 * @author Chengzhi Yu (Based on prof version of Assignment 2)
	 * @version 1.0
	 * Assignment title: CST8284_20W_Assignment_3
	 * Assignment due date: March 28 2020
	 */
public class RoomBooking implements java.io.Serializable {
	public static final long serialVersionUID = 1L;
	private ContactInfo contactInfo;
	private Activity activity;
	private TimeBlock timeBlock;
	/**
	 * Constructs a RoomBooking obj with 3 args 
	 * @param contactInfo contact information
	 * @param activity activity
	 * @param timeBlock timeBlock
	 */
	public RoomBooking(ContactInfo contactInfo, Activity activity, TimeBlock timeBlock) {
		if (isNull(contactInfo,activity,timeBlock))
		setContactInfo(contactInfo); setActivity(activity); setTimeBlock(timeBlock);
	}
	/**
	 * default constructor
	 */
	public RoomBooking() {}
	/**
	 * Passes the value of contactInfo to the constructor
	 * @param contactInfo Contact information to be set
	 */
	public void setContactInfo(ContactInfo contactInfo) {this.contactInfo = contactInfo;}
	/**
	 * returns the contactInfo
	 * @return ContactInfo
	 */
	public ContactInfo getContactInfo() {return contactInfo;}
	/**
	 * Passes the value of activity to the constructor
	 * @param activity activity to be set
	 */
	public void setActivity(Activity activity) {this.activity = activity;}
	/**
	 * returns the activity
	 * @return Activity
	 */
	public Activity getActivity() {return activity;}
	/**
	 * Passes the value of timeBlock to the constructor
	 * @param timeBlock time Block to be set
	 */
	public void setTimeBlock(TimeBlock timeBlock) {this.timeBlock = timeBlock;}
	/**
	 * returns the timeBlock
	 * @return TimeBlock
	 */
	public TimeBlock getTimeBlock() {return timeBlock;}
	/**
	 * toString method overriden from obj class for formating 
	 * @return String
	 */
	@Override public String toString() {
		return (getTimeBlock().toString() + getActivity().toString() +  getContactInfo().toString());
	}
	/**
	 * Checks if a null value is inputed by the user
	 * @param contactInfo contactInfo to be checked
	 * @param activity	activity to be checked
	 * @param timeBlock timeBlock to be checked
	 * @return boolean
	 */
	private static boolean isNull(ContactInfo contactInfo, Activity activity, TimeBlock timeBlock) {
		if(contactInfo == null || activity== null || timeBlock == null) {
			throw new BadRoomBookingException("Null value entered. ","An attempt was made to pass a null value to a variable."); 
		}
		return true;
	}
}
