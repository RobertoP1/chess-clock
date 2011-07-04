package com.github.chessclock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class SecondCircle extends JPanel {
    private static final long serialVersionUID = 1L;
    private Game game;
    private Player player;

    public SecondCircle(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0, 0, 0, 0));
    }

    private BufferedImage image;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHints(ImageHelper.rh);
        if (image == null) {
            image = ImageHelper.getCircle(getWidth());
        }
        g.drawImage(image, getX(), getY(), null);
        int x = getWidth() / 2;
        g.translate(getX() + x, getY() + x);
        double rotate = currentSecond * ImageHelper.radianTime * -1;
        ((Graphics2D) g).rotate(rotate);
        g.drawLine(0, 0, 0, -x);
        ((Graphics2D) g).rotate(rotate * -1);
        g.translate((getX() + x) * -1, (getY() + x) * -1);
        g.drawString("" + currentSecond % 60, getX(), getY() - 10);
        // if(currentSecond < 60){
        // g.fillOval(getX(), getY(), getWidth(), getHeight());
        // }
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        super.setBounds(x, y, w, h);
        image = ImageHelper.getCircle(w);
    }
}
