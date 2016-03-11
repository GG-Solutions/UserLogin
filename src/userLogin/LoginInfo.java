package userLogin;
import java.io.FileInputStream;
import java.io.IOException; 
import java.util.Scanner;

public class LoginInfo {
	static boolean success=false;
	
	public static boolean uLogin(String uLogin, String uPass) throws IOException{
		Scanner s1;
        s1=new Scanner(new FileInputStream("users.txt"));
       
        while(s1.hasNext()) {
        	String pHname = s1.next();
        	String pHpass = s1.next();
            if(uLogin.equals(pHname) && uPass.equals(pHpass)) {
                System.out.println("You are logged in.");
                success=true;
                s1.close();
            }                
        }
		
        if(!success){
            System.out.println("Incorrect password.");
            success=false;
            s1.close();
        }
        return success;
	}
	
	public void addUser(String uName, String uPass){
        
		
	}
	
	public void removeUser(String uName, String uPass){
        
		
	}
}

