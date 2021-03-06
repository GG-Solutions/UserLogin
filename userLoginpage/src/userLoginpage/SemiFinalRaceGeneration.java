package userLoginpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class SemiFinalRaceGeneration  {
	private int timeBetweenRaces = 20;	//stored in minutes
	private int currentTime;	//stores the current time to generate the schedule times
	private int startTime = 900;	//day starting time	
	
	private int rowCounter = 0;		//counting the rows for proper placement while generating UI in the mig layout
	
	/**
	 * This function completely generates the Time Trial Races. It is called when the user first goes to the schedule from teh main menu.
	 * Inputs 	- int numOfLanes - From global variable.
	 * 			- ArrayList<ArrayList<Integer>> breaksArray - Global ArrayList that is storing the breaks for the event.
	 * 			- ArrayList<RaceObject> raceCards - Global ArrayList that has all the races.
	 * 			- ArrayList<TeamObject> teamsArray - Global ArrayList that has all the teams.
	 * 			- JPanel panel - Panel initialized in TestUI.
	 * Outputs 	- Adding UI components to the input JPanel panel.
	 * 			- Adds the generated races to the RaceCards ArrayList.
	 */
	public void generateSemiFinalRaces(int numOfLanes, ArrayList<ArrayList<Integer>> breaksArray, 
			ArrayList<RaceObject> raceCard, ArrayList<TeamObject> teams, JPanel panel, ArrayList<String> categoriesArray) {
		
		ArrayList<TeamObject> tm = new ArrayList<TeamObject>(teams);		//duplicate the teams array
		
//		for(int i = 0; i < teams.size(); i++) {
//			System.out.println(teams.get(i).getTeamName() + " - " + tm.get(i).getAveragedRaceTime());
//		}
		
		//sort the duplicated tm ArrayList based on the averagedRacetime in ascending order before separating by category
			//this makes them stay sorted before separation
		Collections.sort(tm, new Comparator<TeamObject>() {
			public int compare(TeamObject o1, TeamObject o2) {
				return String.format("%06d", o1.getAveragedRaceTime()).compareTo(String.format("%06d", o2.getAveragedRaceTime()));
			}
		});
		
//		System.out.println();
//		System.out.println();
//		
//		for(int i = 0; i < teams.size(); i++) {
//			System.out.println(tm.get(i).getTeamName() + " - " + tm.get(i).getAveragedRaceTime());
//		}
		
		//empty multidimensional arraylist to separate the teams by category
		ArrayList<ArrayList<TeamObject>> tmCat	= new ArrayList<ArrayList<TeamObject>>();	//do i need the whole teamobject stored? - prob easiest
		
		//loop through the teams and add them to the correct amount of spots based on the category name
		for(int i = 0; i < categoriesArray.size() - 1; i++) {
			tmCat.add(new ArrayList<TeamObject>());
		}
		
		//loop through all the teams
		for(int i = 0; i < teams.size(); i++) {
			//loop through the categories to find a match
			for(int j = 0; j < categoriesArray.size(); j++) {
				//check if the teams category matches the one at the index of the categoriesArray
				if(tm.get(0).getCategory() == categoriesArray.get(j)) {
					//do some stuff and add to the tmcat arraylist
					TeamObject temp1 = new TeamObject();
					temp1 = tm.get(0);
					ArrayList<TeamObject> temp2 = new ArrayList<TeamObject>();
					temp2.add(temp1);
					tmCat.get(j).add(temp1);
					tm.remove(0);
					break;
				}
			}
		}
		
		//print out everything in tmCat for TESTING
//		for(int i = 0; i < tmCat.size(); i++) {
//			for(int j = 0; j < tmCat.get(i).size(); j++) {
//				System.out.println(i + " - " + tmCat.get(i).get(j).getCategory() + " - " + tmCat.get(i).size());
//			}
//		}
		
		ArrayList<ArrayList<Integer>> breaks = new ArrayList<ArrayList<Integer>>(breaksArray);	//duplicate the breaks array so the duplicate can be modified
		
		//main loop ------------------------------------------------------------------------------------------------------------------------
		//each team races once
		for(int i = 0; i < Math.ceil(teams.size() / numOfLanes); i++) {
			
			RaceObject race = new RaceObject();	//create a new raceCard to change
			
			//figure out the raceTime
			if(i == 0) {
				currentTime = startTime;
			}
			else {
				//race time generation
				if((currentTime + timeBetweenRaces) >= breaks.get(0).get(0)) {
					currentTime = breaks.get(0).get(1);
					breaks.remove(0);
					//add ability to recommend a time change of the break?
						//refer to programming notes doc
				}
				//if no breaks were detected so just add to the current time
				else {
					currentTime += timeBetweenRaces;
				}
			}
			
			//format to the correct time by doing mod 60
			String t = Integer.toString(currentTime);
			int time = Integer.parseInt(Integer.toString(currentTime).substring(t.length() - 2));	//get the last 2 digits of currentTime
			currentTime /= 100;		//abruptly cut off the last 2 digits
			currentTime *= 100;		//add two 0's back on to the currentTime
			//mod 60 if the last two digits are above or equal to 60
			if(time >= 60 ) {
				currentTime += 100;
				time %= 60;
			}
			currentTime += time;	//add the formatted minutes back to the currentTime
			
			
			//add the race label "Race # _ at"
			JLabel raceNumberLabel = new JLabel("Race # " + (raceCard.size()+1) + " at");	//auto-increment the race number
			raceNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
			panel.add(raceNumberLabel, "flowx,cell 0 " + rowCounter + ",aligny center");
			
			//input mask for the time input
			MaskFormatter raceTimeMask = null;
			try {
				raceTimeMask = new MaskFormatter(" ##h:##m");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			//the time field set to non-editable in the beginning
			JFormattedTextField timeField = new JFormattedTextField(raceTimeMask);
			timeField.setText(String.format("%04d", currentTime));	//format output to four 0's
			timeField.setEditable(false);
			panel.add(timeField, "cell 1 " + rowCounter);
			timeField.setColumns(8);
			
			//edit button for the time field
			JButton editButton = new JButton("edit");
			editButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(editButton.getText() == "edit") {
						editButton.setText("done");
						timeField.setEditable(true);
					}
					else {
						
						//get the race number
						//loop through the remaining races and change the times
							//also change the text boxes
						
//						currentTime = Integer.valueOf(timeField.getText());
//						
//						for(int j = raceCard.get().getRaceNumber(); j < raceCard.size(); j++) {
//							
//						}
						
						editButton.setText("edit");
						timeField.setEditable(false);
						//change all the times on all the other races here
					}
				}
			});
			editButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
			editButton.setForeground(Color.BLUE);
//			editButton.setBounds(0, 0, 40, 15);
			panel.add(editButton, "cell 1 " + rowCounter);
			
			rowCounter += 1;
			
			//create place label
			JLabel lblPlace = new JLabel("Place");
			lblPlace.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblPlace, "flowx,cell 0 " + rowCounter + ",growx,aligny center");
			
			//create team name label
			JLabel lblTeamName = new JLabel("Team Name");
			lblTeamName.setHorizontalAlignment(SwingConstants.LEADING);
			panel.add(lblTeamName, "cell 1 " + rowCounter + ",growx,aligny center");
			
			//create lane label
			JLabel lblLane = new JLabel("Lane");
			lblLane.setHorizontalAlignment(SwingConstants.LEADING);
			panel.add(lblLane, "cell 2 " + rowCounter + ",growx,aligny center");
			
			//create category label
			JLabel lblCategory = new JLabel("Category");
			lblCategory.setHorizontalAlignment(SwingConstants.LEADING);
			panel.add(lblCategory, "cell 3 " + rowCounter + ",growx,aligny center");
			
			//create flag label
			JLabel lblFlag = new JLabel("*");
			lblFlag.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblFlag, "cell 4 " + rowCounter + ",aligny center");
			
			//create time label
			JLabel lblTime = new JLabel("Time");
			lblTime.setHorizontalAlignment(SwingConstants.LEADING);
			panel.add(lblTime, "cell 5 " + rowCounter + ",growx,aligny center");
			
			rowCounter += 1;
			
//			for(int k = 0; k < tmCat.size(); k++) {
////				for(int j = 0; j < tmCat.get(k).size(); j++) {
//					System.out.println(k + " - " + tmCat.get(k) + " - " + tmCat.get(k).size());
////				}
//			}
			
			//get the numOfLanes amount of teams from then multi-dimensional arraylist for this race
			ArrayList<TeamObject> theseTeams = new ArrayList<TeamObject>();
			for(int k = 0; k < numOfLanes; k++) {
				//if there are still team objects left in the arraylist
				if(tmCat.get(0).size() > 0) {
					if(((tmCat.get(0).size() / 2) <= 2) && (k == 0)) {
						theseTeams.addAll(tmCat.get(0));
						tmCat.remove(0);
						break;	//break if 2 or less teams are left so that there is always 2 teams racing, never 1
					}
					else {
						theseTeams.add(tmCat.get(0).get(0));	//get the object at the first index all the time
						tmCat.get(0).remove(0);
					}
				}
				//delete the index
				else {
					tmCat.remove(0);	//remove the first dimension
					break;	//no more objects left to take out
				}
			}
			
			int tempSize = theseTeams.size();	//not sure why i need this to make it work yet
			
			//START OF THE LOOP ------------------------------------------------------------------------------------------------------- 
			for(int k = 0; k < tempSize; k++) {
				
				race = new RaceObject();	//does this refresh the last RaceObject?
				
				if(k == 0) {
					rowCounter += 0;
				}
				else {
					rowCounter += 1;
				}
				
				//adding the place label under the Place heading
				JLabel lblNewLabel = new JLabel("-");	//set it to a dash and change it when the times are locked in?
				lblNewLabel.setName("lblNewLabel" + (i + 1));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel, "flowx,cell 0 " + rowCounter + ",growx,aligny center");
				
				//adding the team name label under the Team Name heading
				JLabel lblMyTeamName = new JLabel(theseTeams.get(0).getTeamName());
				lblMyTeamName.setHorizontalAlignment(SwingConstants.LEADING);
				panel.add(lblMyTeamName, "cell 1 " + rowCounter + ",growx,aligny center");
				
				//adding the lane number label under the Lane heading
				JLabel label_1 = new JLabel(Integer.toString(k+1));
				label_1.setHorizontalAlignment(SwingConstants.LEADING);
				panel.add(label_1, "cell 2 " + rowCounter + ",growx,aligny center");
				
				//adding the teams category label under the Category heading
				JLabel lblMixed = new JLabel(theseTeams.get(0).getCategory());
				lblMixed.setHorizontalAlignment(SwingConstants.LEADING);
				panel.add(lblMixed, "cell 3 " + rowCounter + ",growx,aligny center");
				
				//adding the space character label under the * heading for the time change flag
				JLabel label_2 = new JLabel(" ");	//first set it to just a space character
				label_2.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(label_2, "cell 4 " + rowCounter + ",aligny center");
				
				//input mask for the time input for each row
				MaskFormatter timeMask = null;
				try {
					timeMask = new MaskFormatter("##m:##s.##ms");
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				//adding the formatted text field label under the Time heading
				JFormattedTextField label_3 = new JFormattedTextField(timeMask);
				label_3.setHorizontalAlignment(SwingConstants.LEADING);
				label_3.setName("label_" + (i + 1) + "_" + k);
				panel.add(label_3, "cell 5 " + rowCounter + ",growx,aligny center");
				
				//add the lock button on the first loop
				if(k == 0) {
					JButton btnNewButton = new JButton("Lock");
					btnNewButton.setHorizontalAlignment(SwingConstants.LEADING);
					btnNewButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							if(btnNewButton.getText() == "Lock") {
								//need to loop through the panel instead?
								for(int l = 0; l < numOfLanes; l++) {
									label_3.setEnabled(false);
//									panel.getComponents().equals("label_" + race.getRaceNumber() + "_");
									//need to get the other variable names
									//if it contains the sting "_" + (i + 1) + "_"
										//c
								}
								//TODO Change the place of the corresponding teams instead of having the dash
								btnNewButton.setText("Unlock");
							}
							else {
								label_3.setEnabled(true);
								btnNewButton.setText("Lock");
							}
						}
					});
					btnNewButton.setBounds(0, 0, 100, 20);
					panel.add(btnNewButton, "cell 6 " + rowCounter);
				}
				
				//add the print button on the second loop
				if(k == 1) {
					JButton btnNewButton = new JButton("Print");
					btnNewButton.setHorizontalAlignment(SwingConstants.LEADING);
					btnNewButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							//export a pdf to print out
							JFileChooser saving = new JFileChooser();
							saving.showSaveDialog(null);
						}
					});
					btnNewButton.setBounds(0, 0, 100, 20);
					panel.add(btnNewButton, "cell 6 " + rowCounter);
				}
				
				//set everything in the race object
				race.setRaceNumber(i + 1);		//set the race number
				race.setRaceTime(currentTime);		//set the race time
				race.setCategory(teams.get(0).getCategory());	//get the category from the team
				
				race.addTeamToRace(theseTeams.get(0));	//store the team to the ArrayList in the race object
				
//				System.out.println(label_3.getName());
				
				theseTeams.remove(0);	//remove the team from the duplicated array list so the index will always be 0 to get information
			}
			//END OF FOR LOOP FOR THE TEAMS --------------------------------------------------------------------------------------------------------
			
			raceCard.add(race);		//lastly, add the created RaceObject to the global ArrayList
			
			rowCounter += 1;
		}
		//END OF FOR LOOP FOR THE RACES ----------------------------------------------
	}
	
	
	//method to check if all the times are filled in and locked?
		//can loop through the raceCards array and use 
	//if they are, open up the semi-finals radio button
	public void changeRaceTimes() {
		
	}
	
}
