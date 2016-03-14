package userLoginpage;

import java.util.ArrayList;

import javax.swing.JFormattedTextField;

public class RaceObject 
{
	private int raceNumber;
	private int raceTime;			//the time at which the race will take place
	private int numberOfLanes;		//numberOfLanes reference from the setup variable - do we need this?
	private String category;//category variable from the array
	private ArrayList<TeamObject> teamsRacing = new ArrayList<TeamObject>();	//array of teams that are in this race
	private ArrayList<JFormattedTextField> teamRaceTimes = new ArrayList<JFormattedTextField>();	//store the UI that was created?
	
	/**
	 * Sets the private int raceNumber variable.
	 * Inputs - int num - integer to set the racenumber variable.
	 * Outputs - None.
	 */
	public void setRaceNumber(int num) {
		raceNumber = num;
	}
	
	/**
	 * Sets the private int raceTime variable.
	 * Inputs - int time - integer to set the raceTime variable.
	 * Outputs - None.
	 */
	public void setRaceTime(int time) {
		raceTime = time;
	}
	
	/**
	 * Sets the private int numberOfLanes variable.
	 * Inputs - int lanes - integer to set the numberOfLanes variable. 
	 * Outputs - None.
	 */
	public void setNumberOfLanes(int lanes) {
		numberOfLanes = lanes;
	}
	
	/**
	 * Sets the private String category variable for the race.
	 * Inputs - String cat - String to set the category variable.
	 * Outputs - None.
	 */
	public void setCategory(String cat) {
		category = cat;
	}
	
	/**
	 * Gets the private int raceNumber variable.
	 * Inputs - None.
	 * Outputs - Returns the raceNumber variable.
	 */
	public int getRaceNumber() {
		return raceNumber;
	}
	
	/**
	 * Gets the private int raceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the raceTime variable.
	 */
	public int getRaceTime() {
		return raceTime;
	}
	
	/**
	 * Gets the private int numberOfLanes variable.
	 * Inputs - None.
	 * Outputs - Returns the numberOfLanes variable.
	 */
	public int getNumberOfLanes() {
		return numberOfLanes;
	}
	
	/**
	 * Gets the private String category variable.
	 * Inputs - None.
	 * Outputs - Returns the category variable.
	 */
	public String setCategory() {
		return category;
	}
	
	/**
	 * Adds a team to the RaceObject array list of teams that raced in the race
	 * Inputs - TeamObject team - used to add to the private teamsracing ArrayList.
	 * Outputs - None.
	 */
	public void addTeamToRace(TeamObject team) {
		teamsRacing.add(team);
	}
	
	/**
	 * Adds the JFormattedTextField to the private teamRaceTimes Arraylist.
	 * Inputs - JFormattedTextField box - contains the time from the race generation.
	 * Outputs - None.
	 */
	public void addTeamRaceTime(JFormattedTextField box) {
		teamRaceTimes.add(box);
	}
	
	/**
	 * Not sure if this function is needed.
	 * Inputs - 
	 * Outputs - 
	 */
	public void processUI() {
		//procedurally generate the UI here instead?
		//process the panels and stuff
		//how will that help me link it?
		
	}
}
