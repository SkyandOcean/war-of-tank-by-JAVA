package com.zlh.wot.v1;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.zlh.wot.v1.Obstruction;

public class MyFrame extends JFrame implements Runnable{
		//定义场景
	     static BackGround nowbg;
	   
	    public BackGround getNowbg() {
			return nowbg;
		}

		//定义tank
	    static Tank t;
	    
	    //标记游戏是否需要开始
	    public static boolean start = false;

	
	public MyFrame() {
		//加载图片
		StaticValue.init();
		
		nowbg = new BackGround();
		t = new Tank(0,180);
		//告诉tank当前场景??
		t.setBg(nowbg);
		
		//设置标题
		this.setTitle("坦克大战 --张律恒");
		//设置窗体初始位置
		this.setLocation(1000, 300);
		//设置窗体大小
		this.setSize(480, 360);
		//设置固定大小
		this.setResizable(false);
		//处理关闭事件
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//显示窗体
		this.setVisible(true);
		//监听键盘，实现移动
		this.addKeyListener(new Mylistener());
		//开启线程解决刷新问题
		new Thread(this).start();
	}
	
	private class Mylistener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
				int code = e.getKeyCode();
				if(start){
				if(code == KeyEvent.VK_LEFT) {
					t.left();
				}
				if(code == KeyEvent.VK_RIGHT) {
					t.right();
				}
				if(code == KeyEvent.VK_UP) {
					t.up();
				}
				if(code == KeyEvent.VK_DOWN) {
					t.down();	
				}
				if(code == KeyEvent.VK_SPACE) {
					t.shot();
				}
			}else {
				//游戏没有开始
				if(code == KeyEvent.VK_SPACE) {
					start = true;
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			int code = e.getKeyCode();
			if(start) {
				if(code == KeyEvent.VK_LEFT) {
					t.leftstop();
				}
				if(code == KeyEvent.VK_RIGHT) {
					t.rightstop();
				}
				if(code == KeyEvent.VK_UP) {
					t.upstop();
				}
				if(code == KeyEvent.VK_DOWN) {
					t.downstop();	
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	//画出背景
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		//super.paint(g);
		BufferedImage image = new BufferedImage(480, 360, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g2 = image.getGraphics();
		
		if(start) {
			g2.drawImage(nowbg.getShowImage(), 0, 0, this);
			//画出障碍物
			Iterator<Obstruction> its = nowbg.getAllObstructions().iterator();			//迭代器??
			while(its.hasNext()) {
				Obstruction obs = its.next();
				g2.drawImage(obs.getShowImage(), obs.getX(), obs.getY(), this);
			}
			//画出tank
			g2.drawImage(t.getShowImage(), t.getX(), t.getY(),this);
			//画出Enemy
			Iterator<Enemy> its2 = nowbg.getAllEnemys().iterator();			//迭代器??
			while(its2.hasNext()) {
				Enemy enemy = its2.next();
				g2.drawImage(enemy.getShowImage(), enemy.getX(), enemy.getY(), this);
			}
	
			//画出炮弹missile
			Iterator<Missile> its3 = t.getAllMissiles().iterator();			//迭代器??
			if(t.m != null) {
				while(its3.hasNext()) {
					Missile missile = its3.next();
					g2.drawImage(missile.getShowImage(), missile.getX(), missile.getY(), this);
				}
			}
		}else {
			//画开机画面
			g2.drawImage(StaticValue.startImage, 0, 0, this);
		}
		
		g.drawImage(image, 0, 0, this);
	}
	
	public static void main(String[] args) {
		new MyFrame();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			//通过life判断tank是否存活
			if(!t.isLife()) {
				//游戏结束
				//对话框
				JOptionPane.showMessageDialog(null, "您被击毁！游戏结束");
				//关闭虚拟机
				System.exit(0);
			}
			
			//通关，游戏结束
			if(nowbg.getAllEnemys().isEmpty()) {
				//游戏结束
				//对话框
				JOptionPane.showMessageDialog(null, "恭喜通关！游戏结束");
				//关闭虚拟机
				System.exit(0);
			}
			
			try {
				Thread.sleep(100);
				repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
