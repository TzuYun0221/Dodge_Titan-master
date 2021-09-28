package userinterface;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import userinterface.GameScreen;
import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.applet.*;

public class GameWindow extends JFrame implements ActionListener{
	
	private GameScreen gameScreen;
 	JMenuBar jmb;
 	JMenu direct;
 	JMenuItem rule, highScore;
 	
	ConnectMySQL cn = new ConnectMySQL(); 
 
 	public GameWindow() {
 		super("Dodge Titan ");
  
 		JMenu direct = new JMenu("Options");
  
 		direct.setMnemonic(KeyEvent.VK_Z);
 		direct.add(rule= new JMenuItem("Rule(R)", KeyEvent.VK_R));
  
 		direct.add(highScore = new JMenuItem("Ranking(H)", KeyEvent.VK_H));  
  
 		rule.addActionListener(this);
 		highScore.addActionListener(this);
  
 		jmb = new JMenuBar();
 		setJMenuBar(jmb);
 		jmb.add(direct);
  
  
 		setSize(800, 505); //JFrame 的尺寸
 		setLocation(400, 225); //打開遊戲時會在螢幕中間
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		setVisible(true);
 		gameScreen = new GameScreen();
 		add(gameScreen); //把GameScreen(JPanel) 加到GameWindow(JPrame)
 		addKeyListener(gameScreen);
 	}
 
 	@Override
 	public void actionPerformed(ActionEvent e) {
  
 		if (e.getSource() == rule) {
 			JOptionPane.showOptionDialog(null, "連續按方向鍵[Up]使出立體機動裝置來躲避巨人及超大型巨人 !!",
 					null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
 		}
  

	  	if (e.getSource() == highScore){
	  	   String getScore = cn.getScore();
	   	JOptionPane.showMessageDialog(null, getScore, "ScoreBoard", JOptionPane.INFORMATION_MESSAGE);
	  }   

 }
 
  

 	public void startGame() {
 		gameScreen.startGame();
 		
 	}
 
 	public static void main(String args[]) {
 		
 		GameWindow gw = new GameWindow();
 		gw.setVisible(true);
 		gw.startGame();
 		
 		Music audioPlayWave = new Music("titan.wav");//音樂名
 		audioPlayWave.start();
 		@SuppressWarnings("unused")
 		int musicOpenLab = 1;
 		
 	}
 	
 	
 	
}


