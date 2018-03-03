package com.zlh.wot.v1;

import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Enemy implements Runnable{
	private int x,y;
	private BufferedImage showImage;
	private int type;
	private int upmax,downmax;
	private String status;
	int xmove = 0;
	int ymove = 0;
	int timer = 0;
	//定义一个坐标去存储 X，Y；
	int  startX,startY;
	BackGround bg = MyFrame.nowbg;
	
	Thread t = new Thread(this);
	
	
	public Enemy(int x, int y, int type, int upmax, int downmax) {
		super();
		this.startX=this.x = x;
		this.startY=this.y = y;
		this.type = type;
		this.upmax = upmax;
		this.downmax = downmax;
		showImage = StaticValue.allEnemyImage.get(0);
		t.start();
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public  int  getType(){
		return type;
	}
	public BufferedImage getShowImage() {
		return showImage;
	}
	
	public Missile shot() {
		if(status == "left") {
			allMissiles.add(m = new Missile(x-15,y+20,"left"));
		}else if(status == "right") {
			allMissiles.add(m = new Missile(x+60,y+20,"right"));
		}else if(status == "up") {
			allMissiles.add(m = new Missile(x+20,y-15,"up"));
		}else if(status == "down") {
			allMissiles.add(m = new Missile(x+20,y+60,"down"));
		}
		return m;
	}
	
	public void dead() {
		if(MyFrame.nowbg!=null) {
		MyFrame.nowbg.getAllEnemys().remove(this);
		MyFrame.nowbg.getRemoveEnemys().add(this);
		
		}	
	}
	
	//定义行为
	public void left() {
		status = "left";
		xmove = -5;
	};
	
	public void right() {
		status = "right";
		xmove = 5;
	};
	
	public void up() {
		status = "up";
		ymove = -5;
	};
	
	public void down() {
		status = "down";
		ymove = 5;
	};
	
	
	
	public BackGround getBg() {
		return bg;
	}

	public void setBg(BackGround bg) {
		this.bg = bg;
	}

	
	@Override
	public void run() {
		int state = 0;
		// TODO Auto-generated method stub
		while(true) {
			
			boolean canleft = true;
			boolean canright = true;
			boolean canup = true;
			boolean candown = true;
			
			if(MyFrame.nowbg!=null) {		//注意!!!指针为空的解决方案!!!!!!!!!!!!!!   _(:З >)_
			for(int i=0; i<MyFrame.nowbg.getAllObstructions().size();i++ ) {
				Obstruction obs = MyFrame.nowbg.getAllObstructions().get(i);
				//不能向右
				if((x>=obs.getX()-60) && (x<=obs.getX()) && (y>obs.getY()-60) && (y<obs.getY()+60)) {
					canright = false;
				}
				//不能向左
				if((x>=obs.getX()) && (x<=obs.getX()+60) && (y>obs.getY()-60) && (y<obs.getY()+60)) {
					canleft = false;
				}
				//不能向上
				if((x>obs.getX()-60) && (x<obs.getX()+60) && (y>=obs.getY()) && (y<=obs.getY()+60)) {
					canup = false;
				}
				//不能向下
				if((x>obs.getX()-60) && (x<obs.getX()+60) && (y>=obs.getY()-60) && (y<=obs.getY())) {
					candown = false;
				}
			}
		}
			
			if(type == 1) {
				if(x<upmax) {
					state = 1;
				}
				else if(x>downmax) {
					state = 0;
				}
				if(state == 1) {
					right();
					x+= xmove;
				}else if(state == 0) {
					left();
					x+= xmove;
				}
				}
			
			
			if(type == 2) {
				if(y<upmax) {
					state = 1;
				}
				else if(y>downmax) {
					state = 0;
				}
				if(state == 1) {
					down();
					y+= ymove;
				}else if(state == 0) {
					up();
					y+= ymove;
				}
			}
	
			timer++;
			if(timer == 5) {
				shot();
				timer = 0;
			}
				
				
				int index = 0;
				if(status.indexOf("up")!=-1) {
					index = 0;
				}
				if(status.indexOf("down")!=-1) {
					index = 1;
				}
				if(status.indexOf("left")!=-1) {
					index = 2;
				}
				if(status.indexOf("right")!=-1) {
					index = 3;
				}
				showImage = StaticValue.allEnemyImage.get(index);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}

}
