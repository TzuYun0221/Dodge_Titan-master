package userinterface;

import javax.swing.*;

import userinterface.GameScreen;  


public class GameoverPanel {
	static String name;
	
	ConnectMySQL cn = new ConnectMySQL();
	JFrame f;  
	
	GameoverPanel(){  
		f=new JFrame();  
		JOptionPane.showMessageDialog(f, "         G A M E O V E R !" + "\n        Your score is " + GameScreen.score,
				"Sad,You lose", JOptionPane.WARNING_MESSAGE);     
     
		name = JOptionPane.showInputDialog(f, "Enter your Name", "What's your name ?", JOptionPane.QUESTION_MESSAGE);
		try {
			cn.record();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}  

 }