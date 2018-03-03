package com.zlh.wot.v1;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tank implements Runnable{
	private int x,y;
	private BufferedImage showImage;
	private boolean life = true;
	

	public static String status;
	
	public Tank(int x,int y) {
		this.x = x;
		this.y = y;
		showImage = StaticValue.alltankImage.get(0);
		life = true;
		status = "right";
		//�����߳�
		new Thread(this).start();
	}
	
	public boolean isLife() {
		return life;
	}
	
	public void setLife(boolean life) {
		this.life = life;
	}
	
	Missile m = null;
	List<Missile> allMissiles
	= new ArrayList<Missile>();
	
	List<Missile> removeMissiles
	= new ArrayList<Missile>();
	

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
	
	//tank������
	private void dead() {
		life = false;
	}
	
	
	//����ƫ����
	int xmove = 0;
	int ymove = 0;
	//������Ϊ
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
	
	public void leftstop() {
		status = "left";
		xmove = 0;
	};
	
	public void rightstop() {
		status = "right";
		xmove = 0;
	};
	
	public void upstop() {
		status = "up";
		ymove = 0;
	};
	
	public void downstop() {
		status = "down";
		ymove = 0;
	};

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public static String getStatus() {
		return status;
	}

	public BufferedImage getShowImage() {
		return showImage;
	}
	
	public List<Missile> getAllMissiles() {
		return allMissiles;
	}
	
	public List<Missile> getRemoveMissiles() {
		return removeMissiles;
	}
	
	BackGround bg;
	
	
	public BackGround getBg() {
		return bg;
	}

	public void setBg(BackGround bg) {
		this.bg = bg;
	}

	//B�߳�
	public void run() {
		//�ı�tank����
		while(true) {
			//Tank����Enemy
			for(int i=0;i<bg.getAllEnemys().size();i++) {
				Enemy e = bg.getAllEnemys().get(i);
				
				if(((x>=e.getX()-60) && (x<=e.getX()) && (y>e.getY()-60) && (y<e.getY()+60)) 
						|| ((x>=e.getX()) && (x<=e.getX()+60) && (y>e.getY()-60) && (y<e.getY()+60))
						|| ((x>e.getX()-60) && (x<e.getX()+60) && (y>=e.getY()) && (y<=e.getY()+60)) 
						|| ((x>e.getX()-60) && (x<e.getX()+60) && (y>=e.getY()-60) && (y<=e.getY())))
					{
						//tank����
						showImage = StaticValue.tankDeadImage;
						this.dead();
					}
				}
			
					for(int i=0;i<this.getAllMissiles().size();i++){
						Missile missile = this.getAllMissiles().get(i);
						//Tank����Missile,����
						if(((x>=missile.getX()-60) && (x<=missile.getX()) && (y>missile.getY()-60) && (y<missile.getY()+17)) 
							|| ((x>=missile.getX()) && (x<=missile.getX()+17) && (y>missile.getY()-60) && (y<missile.getY()+17))
							|| ((x>missile.getX()-60) && (x<missile.getX()+17) && (y>=missile.getY()) && (y<=missile.getY()+17)) 
							|| ((x>missile.getX()-60) && (x<missile.getX()+17) && (y>=missile.getY()-60) && (y<=missile.getY())))
							{
								//tank����
								this.dead();
								missile.dead();
							}
					
						//Missile���߽磬��ʧ
						if((missile.getX()>480) || (missile.getX()<0) || (missile.getY()>360) || (missile.getY()<0)) 
							
							{
								missile.dead();
							}
					
						//Enemy����Missile������
						for(int j=0;j<bg.getAllEnemys().size();j++){
							Enemy e=bg.getAllEnemys().get(j);
							if((e.getX()>m.getX()-60) && (e.getX()<m.getX()+17) && (e.getY()>m.getY()-60) && (e.getY()<m.getY()+17)) 
								{	
									e.dead();//��������
									missile.dead();
								}
							}
					
						//Missile����ש��
						for(int k=0;k<bg.getAllObstructions().size();k++) {
							Obstruction obs = bg.getAllObstructions().get(k);
							
							//��������
							if(obs.getType() == 0) {
								if(((missile.getX()> obs.getX()-17) && (missile.getX()< obs.getX()+60) && (missile.getY()> obs.getY()-17) && (missile.getY()< obs.getY()+60) ))
									{
										missile.dead();
									}
							}
							//����ש��
							if(obs.getType() == 1) {
								if(((missile.getX()> obs.getX()-17) && (missile.getX()< obs.getX()+60) && (missile.getY()> obs.getY()-17) && (missile.getY()< obs.getY()+60) ))
									{
										bg.getAllObstructions().remove(obs);
										missile.dead();
									}
								}
						}
					}
			
			
			
			//tank���ϰ���������ϵ
			boolean canleft = true;
			boolean canright = true;
			boolean canup = true;
			boolean candown = true;
			
			for(int i=0;i<bg.getAllObstructions().size();i++) {
			Obstruction obs = bg.getAllObstructions().get(i);
			//��������
			if((x>=obs.getX()-60) && (x<=obs.getX()) && (y>obs.getY()-60) && (y<obs.getY()+60)) {
				canright = false;
			}
			//��������
			if((x>=obs.getX()) && (x<=obs.getX()+60) && (y>obs.getY()-60) && (y<obs.getY()+60)) {
				canleft = false;
			}
			//��������
			if((x>obs.getX()-60) && (x<obs.getX()+60) && (y>=obs.getY()) && (y<=obs.getY()+60)) {
				canup = false;
			}
			//��������
			if((x>obs.getX()-60) && (x<obs.getX()+60) && (y>=obs.getY()-60) && (y<=obs.getY())) {
				candown = false;
			}
			
			}
			if((canleft&&xmove<0) || (canright&&xmove>0) || (canup&&ymove<0) || (candown&&ymove>0)) {
			x+= xmove;
			y+= ymove;
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
			showImage = StaticValue.alltankImage.get(index);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
}