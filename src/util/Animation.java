package util;

import java.util.List;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

//主角腳動的速度
public class Animation {
	private List<BufferedImage> frames; 
	private int frameIndex = 0;
	private int deltaTime;	//前個frame和這個frame的差別
	private long previousTime;
	
	public Animation(int deltaTime) {
		this.deltaTime = deltaTime;
		frames = new ArrayList<BufferedImage>(); //圖片放到這個list
	}
	
	public void update() {
		//抓取裝置上的毫秒
		if(System.currentTimeMillis() - previousTime > deltaTime) {
			frameIndex ++; 	//目前時間減掉上個時間大於時間差，進入下一個frame
			
			if (frameIndex >= frames.size()) {	// 2張圖輪流交替	
				frameIndex = 0;	
			}
			previousTime = System.currentTimeMillis();	
		} 
	}
	
	public void addFrame (BufferedImage frame) {
		frames.add(frame); //加上frame
	}
	
	//找出在哪個frame上
	public BufferedImage getFrame() {
		if(frames.size() > 0) { 	
			return frames.get(frameIndex);	
		}
		return null;
	}
	
}
