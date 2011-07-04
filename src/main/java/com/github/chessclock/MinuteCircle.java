package com.github.chessclock;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.github.chessclock.TimeControl.SecondsAware;

public class MinuteCircle extends JPanel implements SecondsAware {
    private static final long serialVersionUID = -4819490417234446697L;

    public enum Status {
        ENABLED(new Color(100, 100, 250)), DISABLED(Color.WHITE);
        Color color;

        Status(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }
    private Status status = Status.DISABLED;
    SecondCircle secondCircle;
    private BufferedImage image;
    private Game game;
    private Player player;

    public MinuteCircle(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        secondCircle = new SecondCircle(game, player);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHints(ImageHelper.rh);
        if (image == null)
            image = ImageHelper.getCircle(getWidth());
        g.drawImage(image, 0, 0, null);
        if (game.getRemaningSeconds(player) < 60) {
            g.setColor(Color.RED);
        }
        g.drawString("" + (game.getRemaingMinutes(player)) + " : " + game.getRemaningSeconds(player) % 60,
                getWidth() / 2, getHeight() / 4);
        int x = getWidth() / 2;
        double rotate = game.getRemaingMinutes(player) * ImageHelper.radianTime * -1;
        g.translate(x, x);
        ((Graphics2D) g).rotate(rotate);
        g.drawLine(0, 0, 0, -x);
        ((Graphics2D) g).rotate(rotate * -1);
        g.translate(x * -1, x * -1);
        secondCircle.paintComponent(g);
        this.setBackground(status.getColor());
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        super.setBounds(x, y, w, h);
        image = ImageHelper.getCircle(w);
        secondCircle.setBounds((int) (w * 0.75), (int) (h * 0.25), w / 5, h / 5);
    }

    public void oneSecondHasPassed() {
        repaint();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
