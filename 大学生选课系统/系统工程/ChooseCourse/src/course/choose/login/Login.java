package course.choose.login;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.theme.SubstanceSteelBlueTheme;


public class Login {
	public static void main(String[] args) {
		setColor();
		new PassWord();		
	}
	private static void setColor(){
		JFrame.setDefaultLookAndFeelDecorated(true);
		try {  			
	        UIManager.setLookAndFeel(new SubstanceLookAndFeel()); 	//Ƥ���� ����趨
	        JFrame.setDefaultLookAndFeelDecorated(true);//���ô���  
	        JDialog.setDefaultLookAndFeelDecorated(true);//���öԻ���  
	        SubstanceLookAndFeel.setCurrentTheme(new SubstanceSteelBlueTheme());//��������  
	  
	    } catch (Exception e) {  
	        System.err.println("Something went wrong!");  
	    }
	}

}
