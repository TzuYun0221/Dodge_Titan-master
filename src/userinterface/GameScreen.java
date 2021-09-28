package userinterface;

import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

import objectgame.EnemyManagerAll;
import objectgame.Land;
import objectgame.MainChar;

public class GameScreen extends JPanel implements Runnable, KeyListener {
	
	public static final float GRAVITY = 0.2f;	//掉下來的速度
	public static final float GROUNDY = 370; //by pixel.
	public static final int Game_FIRST_State = 0;
	public static final int Game_PLAY_State = 1;
	public static final int Game_OVER_State = 2;
	public static boolean finish;
	public static int score;
	
	private MainChar mainchar;
	private Thread thread;
	private Land land;
	private EnemyManagerAll emAll;
		
	private int gameState = Game_FIRST_State;
	
	
	public GameScreen() {
		mainchar = new MainChar();
		mainchar.setX(100); //主角的位置, 這是first_state時主角的位置
		mainchar.setY(345);
		thread = new Thread(this);
		land  = new Land(this);
		emAll = new EnemyManagerAll(mainchar, this);
//		imageGameOver = Resource.getResourceImage("images/GameOver.png");
	}
	
	public void startGame() {
		thread.start(); 
	}

	@Override
	public void run() {
		while(true) {
			try {
				update();
				repaint();	
				Thread.sleep(4); //背景動的速度
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		switch(gameState) {
		case Game_PLAY_State:
			mainchar.update();	//主角的速度
			land.update(); //背景滾動
			emAll.update();
			if(mainchar.getAlive() == false) {
				gameState = Game_OVER_State;
			new GameoverPanel();	
			}
			break;
		}
	}
	
	public void plusScore(int score) {
		GameScreen.score += score; 
	}
	
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.decode("#3D3D3D"));	//JPanel的顏色
		g.fillRect(0, 0, getWidth(), getHeight());	//填顏色

		switch(gameState) {
			case Game_FIRST_State:
				mainchar.draw(g);
				g.setFont(new Font("SansSerif", Font.BOLD, 20)); 
				g.drawString("那一天，人們回想起了被巨人支配的恐懼....", 250, 100);
				g.drawString("Please press Enter to play", 250, 200);
				break;
			case Game_PLAY_State:
				land.draw(g);
				mainchar.draw(g);
				emAll.draw(g);
				g.setColor(Color.decode("#C70039"));
				g.setFont(new Font("SansSerif", Font.BOLD, 20));
				g.drawString("SCORE: " + String.valueOf(score), 600, 50);
				break;
			case Game_OVER_State:
				land.draw(g);
				mainchar.draw(g);
				emAll.draw(g);
//				g.drawImage(imageGameOver, 100, 50, null);
				g.setFont(new Font("SansSerif", Font.BOLD, 20)); 
				g.drawString("Please press Enter to restart", 250, 200);
				break;
		
		}

	}

	private void resetGame() {
		mainchar.setAlive(true);
		//將主角變回成預設值
		mainchar.setX(100); 
		mainchar.setY(345);
		emAll.reset();
		score = 0;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			if(gameState == Game_FIRST_State) {
				gameState = Game_PLAY_State;
			} else if(gameState == Game_OVER_State) {
				resetGame();	
				gameState = Game_PLAY_State;
			}
			break;
			
		case KeyEvent.VK_UP:{
			if(gameState == Game_PLAY_State) {
				mainchar.jump();
			}
			break;
		}
	
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	
}
