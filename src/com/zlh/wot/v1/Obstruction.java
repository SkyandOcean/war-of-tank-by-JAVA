package com.zlh.wot.v1;

import java.awt.image.BufferedImage;

public class Obstruction {
	private int x,y;//����
	private int type;//����


	private BufferedImage showImage;//ͼƬ
	
	
	public Obstruction(int x, int y, int type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
		showImage = StaticValue.allObstructionImage.get(type);
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

	public int getType() {
		return type;
	}

	public BufferedImage getShowImage() {
		return showImage;
	}



	
}
