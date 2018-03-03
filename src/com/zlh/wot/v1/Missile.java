package com.zlh.wot.v1;

import java.awt.image.BufferedImage;

public class Missile implements Runnable{
	private int x,y;
	private BufferedImage showImage;
	
	
	Thread t2 = new Thread(this);
	
	public Missile(int x,int y,String status) {
		this.x = x;
		this.y = y;
		status = Tank.getStatus();
		showImage = StaticValue.MissileImage;
		//�����߳�
		t2.start();
	}
	

	public void dead() {
		if(MyFrame.t!=null) {
		MyFrame.t.getAllMissiles().remove(this);
		MyFrame.t.getRemoveMissiles().add(this);
		
		}	
	}
	
	//����ƫ����
	int xmove = 10;
	int ymove = 10;
	//������Ϊ


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public BufferedImage getShowImage() {
		return showImage;
	}
	
	BackGround bg = MyFrame.nowbg;
	
	
	public BackGround getBg() {
		return bg;
	}

	public void setBg(BackGround bg) {
		this.bg = bg;
	}

	//C�߳�
	public void run() {		
		
		//�ı�missile����
		int index = 0;
		if(Tank.getStatus() == "left") {
			index = 1;
		}
		if(Tank.getStatus() == "right") {
			index = 2;
		}
		if(Tank.getStatus() == "up") {
			index = 3;
		}
		if(Tank.getStatus() == "down") {
			index = 4;
		}
		while(true) {
			if(index == 1) {
				x-=xmove;
			}
			if(index == 2) {
				x+=xmove;
			}
			if(index == 3) {
				y-=ymove;
			}
			if(index == 4) {
				y+=ymove;
			}
			showImage = StaticValue.MissileImage;
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
