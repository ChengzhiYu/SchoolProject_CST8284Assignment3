package cst8284.asgmt3.roomScheduler;
/**
 * Class Name: Activity
 * @author Chengzhi Yu (Based on prof version of Assignment 2)
 * @version 1.0
 * Assignment title: CST8284_20W_Assignment_3
 * Assignment due date: March 28 2020
 */
public class Activity  {
	private String category, description;
	/**
	 * Two arg constructor creates an Activity obj with category and description
	 * @param category category of the activity
	 * @param description a brif description of the activity
	 */
	public Activity(String category, String description) {
		setCategory(category); setDescription(description);	
	}
	/**
	 * Gets the string value of description
	 * @return String 
	 */
	public String getDescription() {return description;}
	/**
	 * Sets the string value of description
	 * @param description a description of string type
	 */
	public void setDescription(String description) {
		if (description.isEmpty())
			throw new BadRoomBookingException("Missing value. ", "Missing an input value.");
		this.description = description;
		}
	/**
	 * Gets the string value of category
	 * @return String
	 */
	public String getCategory() {return category;}
	/**
	 * Sets the string value of description
	 * @param category a category of string type
	 */
	public void setCategory(String category) {
		if (category.isEmpty())
			throw new BadRoomBookingException("Missing value. ", "Missing an input value.");
		this.category = category;
		}
	/**
	 * toString method overriden from obj class for formating 
	 * @return String
	 */
	@Override
	public String toString() {
		return  "Event: " + getCategory() + "\n" + 
			((getDescription()!="")?"Description: " + getDescription():"") + "\n";
	}
	
}
