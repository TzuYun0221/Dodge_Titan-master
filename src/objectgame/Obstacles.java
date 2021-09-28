package objectgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

import util.Resource;


public class Obstacles extends Enemy {
	
	private BufferedImage image;
	private int posX, posY;
	private Rectangle rect1;
	private Rectangle rect2;
	private Rectangle rect2_2;
	private Rectangle rect3;
	private MainChar mainchar;
	private boolean scoreGot = false;
	
	public Obstacles(MainChar mainchar) {
		this.mainchar = mainchar;

		rect1 = new Rectangle();
		rect2 = new Rectangle();
		rect2_2 = new Rectangle();
		rect3 = new Rectangle();


	}
	
	public void update() {
		posX -= 2;	//障礙物往左移動
		//一般巨人大小
		rect1.x = posX + 55; 
		rect1.y = posY + 25; 
		rect1.width = image.getWidth() -75;
		rect1.height = image.getHeight();
		
		//城牆巨人框架
		rect2.x = posX + 60; 
		rect2.y = posY + 150; 
		rect2.width = image.getWidth() - 50;
		rect2.height = image.getHeight() - 120;
		
		rect2_2.x = posX + 150;
		rect2_2.y = posY + 70;
		rect2_2.width = image.getWidth() - 160;
		rect2_2.height = image.getHeight() - 120;
		
		//空中巨人框架
		rect3.x = posX + 70; 
		rect3.y = posY + 60; 
		rect3.width = image.getWidth() - 70;
		rect3.height = image.getHeight() - 100;

		
	}

	
	//取障礙物大小
	@Override
	public Rectangle getBound() {
		return rect1;
	}
	
	@Override
	public Rectangle getBound2() {
		return rect2;
	} 
	
	public Rectangle getBound2_2() {
		return rect2_2;
	}
	
	public Rectangle getBound3() {
		return rect3;
	}
	
	//製圖
	@Override
	public void draw(Graphics g) {	
		g.drawImage(image, posX, posY, null);
		
	}
	
	public void setX(int x) {
		posX = x;
	}
	public void setY(int y) {
		posY = y;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	//不在螢幕上
	@Override
	public boolean isOutofScreen() {	
		return (posX + image.getWidth() < 0);
	}
	
	//障礙物和主角相撞
	@Override
	public boolean isOver() {
		return (mainchar.getX() > posX);   
			
	}
	
	//分數
	@Override
	public boolean scoreGot() {
		return scoreGot;
	}
	
	//取得分數
	@Override
	public void setScoreGot(boolean scoreGot) {
		this.scoreGot = scoreGot;
	}


}