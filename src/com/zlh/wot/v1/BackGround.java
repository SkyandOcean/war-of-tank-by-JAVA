package com.zlh.wot.v1;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
	private BufferedImage showImage;
	
	//�洢�ϰ���ļ���
	List<Obstruction> allObstructions
		=new ArrayList<Obstruction>();
	//����Enemy�ļ���
	List<Enemy> allEnemys
		= new ArrayList<Enemy>();
	//�������Enemy�ļ���
	List<Enemy> removeEnemys
	= new ArrayList<Enemy>();
	
	public BackGround() {
	showImage = StaticValue.bgImage;
	
	//�ϰ����Ų�
	allObstructions.add(new Obstruction(120, 240, 0));
	allObstructions.add(new Obstruction(180, 240, 0));
	allObstructions.add(new Obstruction(300, 240, 1));
	allObstructions.add(new Obstruction(300, 180, 1));
	
	//����Enemy
	allEnemys.add(new Enemy(300,60,1, 60, 300));
	allEnemys.add(new Enemy(360,240,2, 120, 240));
	}
	
	public List<Obstruction> getAllObstructions() {
		return allObstructions;
	}

	public BufferedImage getShowImage() {
		return this.showImage;
	}

	public List<Enemy> getAllEnemys() {
		return allEnemys;
	}

	public List<Enemy> getRemoveEnemys() {
		return removeEnemys;
	}
	
}
