package com.github.chessclock;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;


public class ImageHelper {
	static public double radianTime = ((2*Math.PI)/60);
	static RenderingHints rh = new RenderingHints(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
	static BufferedImage getCircle(int d){
		BufferedImage image = new BufferedImage(d+1,d+1,BufferedImage.TYPE_4BYTE_ABGR_PRE);
		Graphics2D g = image.createGraphics();
		g.setRenderingHints(rh);
		g.setColor( new Color(0,0,0,0) );
		g.fillRect( 0, 0, d, d);
		g.setColor( Color.BLACK );
		g.drawOval(0, 0, d, d );
//		int r = d/2;
//		g.drawLine(r, r, r, 0);
		return image;
	}
	static BufferedImage getPointer(int d){
		BufferedImage image = new BufferedImage(d,d,BufferedImage.TYPE_4BYTE_ABGR_PRE);
		Graphics2D g = image.createGraphics();
		g.setRenderingHints(rh);
		g.setColor( new Color(0,0,110,155) );
		g.fillRect( 0, 0, d, d);
		g.setColor( Color.BLACK );
		int r = d/2;
		g.drawLine(r, r, r, 0);
		return image;
	}
	static BufferedImage getRect(int w, int h){
		BufferedImage image = new BufferedImage(w,h,BufferedImage.TYPE_4BYTE_ABGR_PRE);
		Graphics2D g = image.createGraphics();
		g.setRenderingHints(rh);
		g.setColor( new Color(0,0,100,155) );
		g.fillRect( 0, 0, w, h);
		g.setColor( Color.BLACK );
		g.draw3DRect(0, 0, w, h, true);
		return image;
	}
}
