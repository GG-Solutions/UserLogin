package userLoginpage;

public class TeamObject
{
	private String teamName = "";
	private String category = "";
	private int place = -1;		//set to -1 as default? global place that the team is in
	
	//move the flags to the race object instead?
	private char firstRaceTimeFlag = '*';
	private int firstRaceTime = -1; 
	private char secondRaceTimeFlag = '*';
	private int secondRaceTime = -1; 
	private char semiFinalRaceTimeFlag = '*';
	private int semiFinalRaceTime = -1;
	private char finalRaceTimeFlag = '*';
	private int finalRaceTime = -1; 		//not every team will have a final race time?
	private int averagedRaceTime = -1; 
	
	/**
	 * Default constructor.
	 * Inputs - None.
	 * Outputs - Creates a new TeamObject object.
	 */
	public TeamObject() {}
	
	/**
	 * Constructor that sets the teamName.
	 * Inputs - String name - used to set the private teamName string.
	 * Outputs - Creates a new TeamObject object with set teamName
	 */
	public TeamObject(String name) {
		teamName = name;
	}
	
	/**
	 * Constructor that sets the name and category. TESTING CONSTRUCTOR?
	 * Inputs 	- String name - used to set the private teamName string.
	 * 			- String cat - sets the private category string.
	 * Outputs - Creates a new TeamObject object with set teamName and category.
	 */
	public TeamObject(String name, String cat, int time) {
		teamName = name;
		category = cat;
		averagedRaceTime = time;
	}
	
	/**
	 * Sets the private String teamName variable.
	 * Inputs - String name - String to set the teamName variable.
	 * Outputs - None.
	 */
	public void setTeamName(String name) {
		teamName = name;
	}
	
	/**
	 * Sets the private String category variable.
	 * Inputs - String cat - String to set the category variable.
	 * Outputs - None.
	 */
	public void setCategory(String cat) {
		category = cat;
	}
	
	/**
	 * Sets the priavte int firstRaceTime variable.
	 * Inputs - int time - integer to set the firstRaceTime varible.
	 * Outputs - None.
	 */
	public void setFirstRaceTime(int time)
	{
		firstRaceTime = time;
	}
	
	/**
	 * Sets the private int secondRaceTime variable.
	 * Inputs - int time - integer to set the secondRaceTime variable. 
	 * Outputs - None.
	 */
	public void setSecondRaceTime(int time)
	{
		secondRaceTime = time;
	}
	
	/**
	 * Sets the private int semifinalRaceTime variable.
	 * Inputs - int time - integer to set the semiFinalRaceTime variable.
	 * Outputs - None.
	 */
	public void setSemiFinalRaceTime(int time)
	{
		semiFinalRaceTime = time;
	}
	
	/**
	 * Sets the private int finalRaceTime variable.
	 * Inputs - int time - integer to set the finalRaceTime variable.
	 * Outputs - None.
	 */
	public void setFinalRaceTime(int time)
	{
		finalRaceTime = time;
	}
	
	/**
	 * Sets the private int averagedRaceTime variable.
	 * Inputs - int time - integer to set the averagedraceTime variable.
	 * Outputs - None.
	 */
	public void setAveragedRaceTime(int time)
	{
		averagedRaceTime = time;
	}
	
	/**
	 * Gets the private String teamName variable. 
	 * Inputs - None.
	 * Outputs - Returns the teamName variable. 
	 */
	public String getTeamName() {
		return teamName;
	}
	
	/**
	 * Gets the private String category variable.
	 * Inputs - None.
	 * Outputs - Returns the category variable.
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Gets the private int firstRaceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the firstRaceTime variable.
	 */
	public int getFirstRaceTime()
	{
		return firstRaceTime;
	}
	
	/**
	 * Gets the private int secondRaceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the secondRaceTime variable.
	 */
	public int getSecondRaceTime()
	{
		return secondRaceTime;
	}
	
	/**
	 * Gets the private int semiFinalraceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the semiFinalRaceTime variable.
	 */
	public int getSemiFinalRaceTime()
	{
		return semiFinalRaceTime;
	}
	
	/**
	 * Gets the private int finalRaceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the finalRaceTime variable.
	 */
	public int getFinalRaceTime()
	{
		return finalRaceTime;
	}
	
	/**
	 * Gets the private int averageRaceTime variable.
	 * Inputs - None.
	 * Outputs - Returns the averagedRaceTime variable.
	 */
	public int getAveragedRaceTime()
	{
		return averagedRaceTime;
	}
}
