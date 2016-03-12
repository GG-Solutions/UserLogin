package userLoginpage;
import java.io.FileInputStream;
import javax.swing.JOptionPane;
import java.io.IOException; 
import java.util.Scanner;

public class LoginInfo {
	static boolean success=false;
	
	// I commented out most of this and made it really easy to login so that we could demo it with out any issues
	// I am currently working on correcting these issues.
	
	public static boolean uLogin(String uLogin, String uPass) throws IOException{
	  //	Scanner s1;
      //  s1=new Scanner(new FileInputStream("users.txt"));
       
       // while(s1.hasNext()) {
        	//String pHname = s1.next();
        	//String pHpass = s1.next();
        	
        	String pHname = "admin";
        	String pHpass = "password";
        	
            if(uLogin.equals(pHname) && uPass.equals(pHpass)) {
            	 success=true;
    //            s1.close();
            }                
      //  }
            
		else if(!success) {
			JOptionPane.showMessageDialog(null,"User Name or Password is Incorrect.");
 //           s1.close();
			
        }
		return success;
	}
	
	public void addUser(String uName, String uPass){
      // this will add a user  
		
	}
	
	public void removeUser(String uName, String uPass){
      // this will remove a user
		
	}
}

