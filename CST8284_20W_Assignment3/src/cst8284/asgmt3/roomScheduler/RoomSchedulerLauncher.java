
package cst8284.asgmt3.roomScheduler;
import cst8284.asgmt3.room.*;
	/**
	 * Class Name: RoomSchedulerLauncher
	 * @author Chengzhi Yu (Based on prof version of Assignment 2)
	 * @version 1.0
	 * Assignment title: CST8284_20W_Assignment_3
	 * Assignment due date: March 28 2020
	 */
public class RoomSchedulerLauncher {
	/**
	 * the main method of the program, instantiates a new room of ComputerLab with a room number of B119
	 * @param args the main method args
	 */
	public static void main(String[] args) {
		Room room = new ComputerLab();
		room.setRoomNumber("B119");
		while (!new RoomScheduler(room).loadAccount()) {}
	}
	
}
