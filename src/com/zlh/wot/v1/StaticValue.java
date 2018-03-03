package com.zlh.wot.v1;

import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * �������Ǽ������е�ͼƬ
 */
public class StaticValue {
	
	public static List<BufferedImage> alltankImage=new ArrayList<BufferedImage>();
	public static BufferedImage startImage = null;
	public static BufferedImage bgImage = null;
	
	public static List<BufferedImage> allEnemyImage=new ArrayList<BufferedImage>();
	public static List<BufferedImage> allObstructionImage=new ArrayList<BufferedImage>();
	public static BufferedImage tankDeadImage = null;
	public static BufferedImage MissileImage = null;

	public static String imagePath=System.getProperty("user.dir")+"/bin/";
	
	//��ȫ��ͼƬ��ʼ�� 
	public static void init(){
		//tank��4��ͼƬ
		for(int i=1;i<=4;i++){		
			try {
				alltankImage.add(ImageIO.read(new File(imagePath+i+".gif")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		try {
			startImage=ImageIO.read(new File(imagePath+"start.gif"));
			bgImage=ImageIO.read(new File(imagePath+"stage.gif"));
			tankDeadImage=ImageIO.read(new File(imagePath+"over.gif"));
			MissileImage=ImageIO.read(new File(imagePath+"missile"+".gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=1;i<=5;i++){
			try {
					allEnemyImage.add(ImageIO.read(new File(imagePath+"enemy"+i+".gif")));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=1;i<=2;i++){
			try {
				allObstructionImage.add(ImageIO.read(new File(imagePath+"ob"+i+".gif")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	}
}
