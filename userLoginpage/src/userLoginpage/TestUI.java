package userLoginpage;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.lang.Integer;

public class TestUI {

	public static JFrame frame;
	
	//testing global variables here
	int numberOfLanes = 3;
	ArrayList<TeamObject> teamsArray;
	ArrayList<RaceObject> racesArray;
	ArrayList<String> categoriesArray;
	
	//test booleans for generating the races only once
	private boolean timeTrialRacesEh = false;
	private boolean semiFinalRacesEh = false;
	private boolean finalRacesEh = false;
	
	ArrayList<ArrayList<Integer>> breaksArray;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestUI window = new TestUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * Inputs - None.
	 * Outputs - None.
	 */
	public TestUI() {
		initialize();
	}

	/**
	 * Initializes the contents of the frame.
	 * Inputs - None.
	 * Outputs 	- Creates a new JFrame and adds the necessary UI components.
	 * 			- Calls the race generation scripts.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 887, 537);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		teamsArray = new ArrayList<TeamObject>();
		racesArray = new ArrayList<RaceObject>();
		breaksArray = new ArrayList<ArrayList<Integer>>();
		categoriesArray = new ArrayList<String>();
		
		//setting some stuff for testing - all teams from Kelowna Race Grid 2015
		teamsArray.add(new TeamObject("KDBC High Frequency", "Womens", 5983));
		teamsArray.add(new TeamObject("ODBRC Rogue Dragons", "Womens", 5570));
		teamsArray.add(new TeamObject("KDBC Sonar Dragons", "Womens", 11018));
		teamsArray.add(new TeamObject("A'Breast of Bridge", "Special", 11118));
		teamsArray.add(new TeamObject("KDBC Knotty Pacemakers", "Mixed", 10824));
		teamsArray.add(new TeamObject("Bust n Loose", "Special", 11339));
		teamsArray.add(new TeamObject("KDBC Dragonflies", "Mixed", 11012));
		teamsArray.add(new TeamObject("KDBC Stroke of Luck", "Mixed", 10723));
		teamsArray.add(new TeamObject("Women on Fire", "Mixed", 11232));
		teamsArray.add(new TeamObject("KDBC Dragon in the Drink", "Mixed", 10333));
		teamsArray.add(new TeamObject("KDBC Valley Vixens", "Mixed", 10622));
		teamsArray.add(new TeamObject("KDBC Flowriders", "Mixed", 10591));
		teamsArray.add(new TeamObject("ODBRC DragonFire", "Mixed", 10435));
		teamsArray.add(new TeamObject("Fire On Water", "Mixed", 10209));
		teamsArray.add(new TeamObject("Despirit Housewives", "Mixed", 10392));
		
		//testing for the breaks
		ArrayList<Integer> q = new ArrayList<Integer>();
		q.add(1030);
		q.add(1100);		
		
		ArrayList<Integer> w = new ArrayList<Integer>();
		w.add(1200);
		w.add(1300);		
		
		ArrayList<Integer> e = new ArrayList<Integer>();
		e.add(1530);
		e.add(1600);		
		
		//adding the breaks to the main breaks array
		breaksArray.add(q);
		breaksArray.add(w);
		breaksArray.add(e);
		
		categoriesArray.add("Mixed");
		categoriesArray.add("Womens");
		categoriesArray.add("Special");
		categoriesArray.add("Mens");
		
		
		JLabel lblSchedule = new JLabel("Schedule");
		lblSchedule.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSchedule.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchedule.setBounds(10, 26, 851, 14);
		frame.getContentPane().add(lblSchedule);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 75, 851, 388);
		frame.getContentPane().add(scrollPane);
		
		//add the 3 differnt panels
		JPanel panel1 = new JPanel();
//		panel1.setVisible(true);
//		scrollPane.setViewportView(panel1);
		panel1.setLayout(new MigLayout("", "[70px][300px][50px][150px][10px][80px][100px]", "[25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px]"));
		
		JPanel panel2 = new JPanel();
//		panel2.setVisible(false);
//		scrollPane.setViewportView(panel2);
		panel2.setLayout(new MigLayout("", "[70px][300px][50px][150px][10px][80px][100px]", "[25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px]"));
		
		JPanel panel3 = new JPanel();
//		panel3.setVisible(false);
//		scrollPane.setViewportView(panel3);
		panel3.setLayout(new MigLayout("", "[70px][300px][50px][150px][10px][80px][100px]", "[25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px][25px:25px:25px]"));
		
		//initialize them first so they can be referenced in the stateChanged method
		JRadioButton rdbtnTimeTrials = new JRadioButton("Time-Trials");
		JRadioButton rdbtnSemiFinals = new JRadioButton("Semi-Finals");
		JRadioButton rdbtnFinals = new JRadioButton("Finals");
		
		rdbtnTimeTrials.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnTimeTrials.isSelected()) {
					//need to set the other panels to invisible and then 
					scrollPane.setViewportView(panel1);		//set the view of the scrollPane
					panel1.setVisible(true);
					panel2.setVisible(false);
					panel3.setVisible(false);
					rdbtnSemiFinals.setSelected(false);	//cant initialize it before it is declared :/
					rdbtnFinals.setSelected(false);
				}
				else {
//					rdbtnTimeTrials.setSelected(true);
				}
			}
		});
		rdbtnTimeTrials.setSelected(true);
		rdbtnTimeTrials.setBounds(44, 50, 134, 23);
		frame.getContentPane().add(rdbtnTimeTrials);
		
		rdbtnSemiFinals.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnSemiFinals.isSelected()) {
					//need to set the other panels to invisible and then 
					scrollPane.setViewportView(panel2);		//set the view of the scrollPane
					panel1.setVisible(false);
					panel2.setVisible(true);
					panel3.setVisible(false);
					rdbtnTimeTrials.setSelected(false);
					rdbtnFinals.setSelected(false);
				}
				else {
//					rdbtnTimeTrials.setSelected(true);
				}
			}
		});
		rdbtnSemiFinals.setEnabled(true);
		rdbtnSemiFinals.setBounds(180, 50, 134, 23);
		frame.getContentPane().add(rdbtnSemiFinals);
		
		rdbtnFinals.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnFinals.isSelected()) {
					//need to set the other panels to invisible and then 
					scrollPane.setViewportView(panel3);		//set the view of the scrollPane
					panel1.setVisible(false);
					panel2.setVisible(false);
					panel3.setVisible(true);
					rdbtnTimeTrials.setSelected(false);
					rdbtnSemiFinals.setSelected(false);
				}
				else {
//					rdbtnFinals.setSelected(true);
				}
			}
		});
		rdbtnFinals.setEnabled(true);
		rdbtnFinals.setBounds(316, 50, 134, 23);
		frame.getContentPane().add(rdbtnFinals);
		
		JButton btnGenerate = new JButton("generate");
		btnGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(rdbtnTimeTrials.isSelected()) {
					scrollPane.setViewportView(panel1);		//set the view of the scrollPane
					if(!timeTrialRacesEh) {
						TimeTrialRaceGeneration times1 = new TimeTrialRaceGeneration();	//create a new TimeTrialRaceGeneration object
						times1.generateTimeTrailRaces(numberOfLanes, breaksArray, racesArray, teamsArray, panel1);	//call generateTimeTrialRaces
						timeTrialRacesEh = true;
					}
				}
				if(rdbtnSemiFinals.isSelected()) {
					//generate the semi-final races
					scrollPane.setViewportView(panel2);		//set the view of the scrollPane
					if(!semiFinalRacesEh) {
						SemiFinalRaceGeneration times2 = new SemiFinalRaceGeneration();
						times2.generateSemiFinalRaces(numberOfLanes, breaksArray, racesArray, teamsArray, panel2, categoriesArray);
						semiFinalRacesEh = true;
					}
				}
				if(rdbtnFinals.isSelected()) {
					//generate the final races
					scrollPane.setViewportView(panel3);		//set the view of the scrollPane
					if(!finalRacesEh) {
						finalRacesEh = true;
					}
				}
//				System.out.println(teamsArray.size()+"\n");
			}
		});
		btnGenerate.setBounds(730, 50, 100, 20);
		frame.getContentPane().add(btnGenerate);
		
		//main menu objects will be added later
//		JMenuBar menuBar = new JMenuBar();
//		frame.setJMenuBar(menuBar);
//		
//		JMenuItem mntmHome = new JMenuItem("Home");
//		mntmHome.setBackground(Color.WHITE);
//		menuBar.add(mntmHome);
//		
//		JMenuItem mntmNewMenuItem = new JMenuItem("Save");
//		mntmNewMenuItem.setBackground(Color.WHITE);
//		menuBar.add(mntmNewMenuItem);
//		
//		JMenuItem mntmSettings = new JMenuItem("Settings");
//		mntmSettings.setBackground(Color.WHITE);
//		menuBar.add(mntmSettings);
//		
//		JMenuItem mntmPrint = new JMenuItem("Print");
//		mntmPrint.setBackground(Color.WHITE);
//		menuBar.add(mntmPrint);
//		
//		JMenuItem mntmBackuprestore = new JMenuItem("BackUp/Restore");
//		mntmBackuprestore.setBackground(Color.WHITE);
//		menuBar.add(mntmBackuprestore);
//		
//		JMenuItem mntmHelp = new JMenuItem("Help");
//		mntmHelp.setBackground(Color.WHITE);
//		menuBar.add(mntmHelp);
//		
//		JMenuItem mntmLogout = new JMenuItem("Logout");
//		mntmLogout.setHorizontalAlignment(SwingConstants.TRAILING);
//		mntmLogout.setBackground(Color.WHITE);
//		menuBar.add(mntmLogout);
	}
}
