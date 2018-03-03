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
		//���峡��
	     static BackGround nowbg;
	   
	    public BackGround getNowbg() {
			return nowbg;
		}

		//����tank
	    static Tank t;
	    
	    //�����Ϸ�Ƿ���Ҫ��ʼ
	    public static boolean start = false;

	
	public MyFrame() {
		//����ͼƬ
		StaticValue.init();
		
		nowbg = new BackGround();
		t = new Tank(0,180);
		//����tank��ǰ����??
		t.setBg(nowbg);
		
		//���ñ���
		this.setTitle("̹�˴�ս --���ɺ�");
		//���ô����ʼλ��
		this.setLocation(1000, 300);
		//���ô����С
		this.setSize(480, 360);
		//���ù̶���С
		this.setResizable(false);
		//����ر��¼�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//��ʾ����
		this.setVisible(true);
		//�������̣�ʵ���ƶ�
		this.addKeyListener(new Mylistener());
		//�����߳̽��ˢ������
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
				//��Ϸû�п�ʼ
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
	
	//��������
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		//super.paint(g);
		BufferedImage image = new BufferedImage(480, 360, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g2 = image.getGraphics();
		
		if(start) {
			g2.drawImage(nowbg.getShowImage(), 0, 0, this);
			//�����ϰ���
			Iterator<Obstruction> its = nowbg.getAllObstructions().iterator();			//������??
			while(its.hasNext()) {
				Obstruction obs = its.next();
				g2.drawImage(obs.getShowImage(), obs.getX(), obs.getY(), this);
			}
			//����tank
			g2.drawImage(t.getShowImage(), t.getX(), t.getY(),this);
			//����Enemy
			Iterator<Enemy> its2 = nowbg.getAllEnemys().iterator();			//������??
			while(its2.hasNext()) {
				Enemy enemy = its2.next();
				g2.drawImage(enemy.getShowImage(), enemy.getX(), enemy.getY(), this);
			}
	
			//�����ڵ�missile
			Iterator<Missile> its3 = t.getAllMissiles().iterator();			//������??
			if(t.m != null) {
				while(its3.hasNext()) {
					Missile missile = its3.next();
					g2.drawImage(missile.getShowImage(), missile.getX(), missile.getY(), this);
				}
			}
		}else {
			//����������
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
			//ͨ��life�ж�tank�Ƿ���
			if(!t.isLife()) {
				//��Ϸ����
				//�Ի���
				JOptionPane.showMessageDialog(null, "�������٣���Ϸ����");
				//�ر������
				System.exit(0);
			}
			
			//ͨ�أ���Ϸ����
			if(nowbg.getAllEnemys().isEmpty()) {
				//��Ϸ����
				//�Ի���
				JOptionPane.showMessageDialog(null, "��ϲͨ�أ���Ϸ����");
				//�ر������
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
