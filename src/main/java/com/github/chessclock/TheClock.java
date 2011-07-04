package com.github.chessclock;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.github.chessclock.TimeControl.SecondsAware;

public class TheClock extends JPanel implements SecondsAware {
    private static final long serialVersionUID = 5620925061624856762L;

    MinuteCircle[] minutesCircles = new MinuteCircle[2];
    JButton but1 = new JButton("1");
    JButton but2 = new JButton("2");
    Game gameStatus;

    public TheClock(int w, int h) {
        // timeControl = new TimeControl(this);
        setLayout(new BorderLayout());
        setSize(w, h);
        add(new Buttons(), BorderLayout.NORTH);
        add(new Clocks(), BorderLayout.CENTER);
        but1.addActionListener(player1Listener);
        but2.addActionListener(player2Listener);
        but1.addKeyListener(changePlayerListener);
        but2.addKeyListener(changePlayerListener);
        this.addKeyListener(changePlayerListener);
    }

    public void setGameStatus(Game gameStatus) {
        this.gameStatus = gameStatus;
    }

    synchronized public void oneSecondHasPassed() {
        // minutesCircles[currentPlayer.i].oneSecondHasPassed();
        minutesCircles[0].repaint();
        minutesCircles[1].repaint();
    }

    KeyListener changePlayerListener = new KeyListener() {
        public void keyPressed(KeyEvent e) {
            if (e.isAltDown()) {
                gameStatus.pause();
            } else {
                gameStatus.toogle();
            }
        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }
    };

    ActionListener player1Listener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            gameStatus.onPlay(Player.PLAYER_1);
        }
    };

    ActionListener player2Listener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            gameStatus.onPlay(Player.PLAYER_2);
        }
    };

    class Buttons extends JPanel {
        private static final long serialVersionUID = 1L;

        public Buttons() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            add(but1);
            add(but2);
        }
    }

    class Clocks extends JPanel {
        private static final long serialVersionUID = 1L;

        public Clocks() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            minutesCircles[0] = new MinuteCircle();
            minutesCircles[1] = new MinuteCircle();
            add(minutesCircles[0]);
            add(minutesCircles[1]);
        }
    }

}
