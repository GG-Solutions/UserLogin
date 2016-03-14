package userLoginpage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import java.util.ArrayList;		//allows resizable arrays
import java.util.Collections;

public class TimeTrialRaceGeneration 
{
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
	public void generateTimeTrailRaces(int numOfLanes, ArrayList<ArrayList<Integer>> breaksArray, 
			ArrayList<RaceObject> raceCard, ArrayList<TeamObject> teams, JPanel panel) {
		
		//duplicate the teams array twice
		ArrayList<TeamObject> teams1 = new ArrayList<TeamObject>(teams);
		ArrayList<TeamObject> teams2 = new ArrayList<TeamObject>(teams);
		
		//shuffle the teams2 array
		Collections.shuffle(teams2);
		
		//append the mixed teams to the end of the first duplicated array (teams1)
		teams1.addAll(teams2);
		
		ArrayList<ArrayList<Integer>> breaks = new ArrayList<ArrayList<Integer>>(breaksArray);	//duplicate the breaks array so the duplicate can be modified
		
		//main loop ------------------------------------------------------------------------------------------------------------------------
		//round the number up cause you will always need that 
		for(int i = 0; i < Math.ceil(((teams.size() * 2) / numOfLanes)); i++) {
			
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
			
			//START OF THE LOOP ------------------------------------------------------------------------------------------------------- generate the teams
			//use a while loop instead? then i can have the condition set to false to break from the loop once all the races have been generated
			//and all the conditions were met
				//every team raced twice, etc.
			for(int k = 0; k < numOfLanes; k++) {		//need algorithm to figure out how many races there will be? - wont know how many races there are supposed to be
				
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
				JLabel lblMyTeamName = new JLabel(teams1.get(0).getTeamName());
				lblMyTeamName.setHorizontalAlignment(SwingConstants.LEADING);
				panel.add(lblMyTeamName, "cell 1 " + rowCounter + ",growx,aligny center");
				
				//adding the lane number label under the Lane heading
				JLabel label_1 = new JLabel(Integer.toString(k+1));
				label_1.setHorizontalAlignment(SwingConstants.LEADING);
				panel.add(label_1, "cell 2 " + rowCounter + ",growx,aligny center");
				
				//adding the teams category label under the Category heading
				JLabel lblMixed = new JLabel(teams1.get(0).getCategory());
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
				race.setCategory(teams1.get(0).getCategory());	//get the category from the team
				
				race.addTeamToRace(teams1.get(0));	//store the team to the ArrayList in the race object
				
//				System.out.println(label_3.getName());
				teams1.remove(0);	//remove the team from the duplicated array list so the index will always be 0 to get information
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
