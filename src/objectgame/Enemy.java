package objectgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;

public abstract class Enemy {
	
	public abstract Rectangle getBound();
	public abstract Rectangle getBound2();
	public abstract Rectangle getBound2_2();
	public abstract Rectangle getBound3();
	public abstract void draw(Graphics g);
	public abstract void update();
	public abstract boolean isOutofScreen();
	public abstract boolean isOver();
	public abstract boolean scoreGot();
	public abstract void setScoreGot(boolean scoreGot);
}