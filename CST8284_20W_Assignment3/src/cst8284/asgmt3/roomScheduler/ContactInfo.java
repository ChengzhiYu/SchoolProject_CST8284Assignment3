package cst8284.asgmt3.roomScheduler;
	/**
	 * Class Name: ContactInfo
	 * @author Chengzhi Yu (Based on prof version of Assignment 2)
	 * @version 1.0
	 * Assignment title: CST8284_20W_Assignment_3
	 * Assignment due date: March 28 2020
	 */
public class ContactInfo implements java.io.Serializable{
	private String firstName, lastName, phoneNumber, organization;
	/**
	 * Three arg constructor creates the contact information of name and phone number, chains to the 4 arg constructor
	 * @param firstName first name 
	 * @param lastName 	last name
	 * @param phoneNumber phone number
	 */
	public ContactInfo(String firstName, String lastName, String phoneNumber) {
		this(firstName, lastName, phoneNumber, "Algonquin College");
	}	
	/**
	 * Constructs the contact information of user input
	 * @param firstName first name
	 * @param lastName last name 
	 * @param phoneNumber phone number
	 * @param organization organization
	 */
	public ContactInfo(String firstName, String lastName, String phoneNumber, String organization) {
		setFirstName(firstName); 
		setLastName(lastName); 
		setPhoneNumber(phoneNumber); 
		setOrganization(organization);
		
	}	
	/**
	 * Passes the first name of user input, and checks if the name is in correct format
	 * @param firstName first name
	 */
	public void setFirstName(String firstName) {
		if (isBadName(firstName))
			this.firstName = firstName;
		}
	/**
	 * returns the first name
	 * @return String
	 */
	public String getFirstName() {return firstName;}
	/**
	 * Passes the last name of user input, and checks if the name is in correct format
	 * @param lastName last name to be set
	 */
	public void setLastName(String lastName) {
		if (isBadName(lastName))
			this.lastName = lastName;
		}
	/**
	 * returns the last name;
	 * @return String
	 */
	public String getLastName() {return lastName;}
	/**
	 * Passes the phone number of user input, and checks if the number in correct format
	 * @param phoneNumber the phone number to be set
	 */
	public void setPhoneNumber(String phoneNumber) {
		if(isBadPhoneNumber(phoneNumber))
		this.phoneNumber = phoneNumber;
		}
	/**
	 * returns the phoneNumber
	 * @return String
	 */
	public String getPhoneNumber() {return phoneNumber;}
	/**
	 * passes the organization of user input
	 * @param organization organization
	 */
	public void setOrganization(String organization) {this.organization = organization;}
	/**
	 * returns the organization
	 * @return String
	 */
	public String getOrganization() {return organization;}
	/**
	 * Formats the contact information using toString method
	 * @return String
	 */
	@Override
	public String toString() {
		return "Contact Information: " +
			((getFirstName()!="")?(getFirstName() + " " + getLastName()):"") + "\n" +
			"Phone: " + getPhoneNumber() +  
			((getOrganization().equals(""))?"":("\n" +getOrganization() + "\n"));
	}
	/**
	 * Determines if the name contains illegal characters or is too long
	 * @param name name to be determined
	 * @return boolean
	 */
	private static boolean isBadName(String name ) {
		for(int i = 0; i < name.length(); i++) {
	        char c = name.charAt(i);
	        if(c == '~' || c == '#' || c == '!' || c == '@' ||  c == '$'|| c == '%' || c =='^' || c =='&' || c == '*' || c == '\\' || c =='(' || c == '=' || c == '+' || c == '{' || c == '}' || c == ';' || c == ':' || c == '"' || c == '>' || c == '<' || c == ')' || c == '/' || c == ',' )
	        	throw new BadRoomBookingException("Name contains illegal characters. ","A name cannot include characters other than alphabetic characters, the dash (-), the period (.), and the apostrophe (‘).");                                
		}	            
		if (name.length() > 31) {
			throw new BadRoomBookingException("Name length exceeded. ","The first or last name exceeds the 30 character maximum allowed"); 
		}
	    return true;
	}    
	/**
	 * Determines if the phone number is in correct format
	 * @param phoneNumber phone number to be determined
	 * @return boolean
	 */
	private static boolean isBadPhoneNumber(String phoneNumber) {
		if (phoneNumber.length() != 12 || !phoneNumber.contains("-")) {
			throw new BadRoomBookingException("Bad telephone number. ", "The telephone number must be a 10-digit number separated by ' - ' in the form, e.g. 613-555-1212.");
		}
		return true;
	}
	
}