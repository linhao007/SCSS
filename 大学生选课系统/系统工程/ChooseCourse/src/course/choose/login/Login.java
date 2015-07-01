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
	        UIManager.setLookAndFeel(new SubstanceLookAndFeel()); 	//皮肤包 相关设定
	        JFrame.setDefaultLookAndFeelDecorated(true);//设置窗口  
	        JDialog.setDefaultLookAndFeelDecorated(true);//设置对话框  
	        SubstanceLookAndFeel.setCurrentTheme(new SubstanceSteelBlueTheme());//设置主题  
	  
	    } catch (Exception e) {  
	        System.err.println("Something went wrong!");  
	    }
	}

}
