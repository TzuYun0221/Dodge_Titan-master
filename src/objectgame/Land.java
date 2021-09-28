package objectgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
//import java.util.Random;

import userinterface.GameScreen;
import static userinterface.GameScreen.GROUNDY;
import util.Resource;


public class Land {
	
	private List<ImageLand> listImage;
	private BufferedImage imageLand1, imageLand2;

	public Land(GameScreen game) {
		imageLand1 = Resource.getResourceImage("images/background1.png");
		imageLand2 = Resource.getResourceImage("images/background1.png");
		listImage = new ArrayList<ImageLand>(); 
		
		//背景有幾個frames
		int numberOfLandTitle = 19740 / imageLand1.getWidth(); 
		//設定重複背景
		for (int i = 0; i < numberOfLandTitle; i++) {
			ImageLand imageLand = new ImageLand();
			imageLand.posX = (int) (i * imageLand1.getWidth()); 
			imageLand.image = imageLand1;
			listImage.add(imageLand); 
		} 
	} 
	
	//背景轉動
	public void update() {
		for(ImageLand imageLand: listImage) {
			imageLand.posX --;
		}
		
		ImageLand firstElement = listImage.get(0); 
		if(firstElement.posX + imageLand1.getWidth() < 0) {		//當第一個frame跑完
			firstElement.posX = listImage.get(listImage.size() - 1).posX	
					+ imageLand1.getWidth();	
			listImage.add(firstElement);	//加frame
			listImage.remove(0);
		}
	}
	
	
	//背景
	public void draw(Graphics g) {
		for (ImageLand imageLand:listImage) {
			g.drawImage(imageLand1, imageLand.posX, (int) GROUNDY - 410, null); 
		}
	}
	
	//ImageLand 類別
	private class ImageLand {
		int posX;
		BufferedImage image; 
	}

}
