package com.github.chessclock;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class Teste {
    static TheClock clock = new TheClock(600, 300);
    static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setJMenuBar(createMenu(frame));
        frame.setSize(600, 360);
        frame.setLocationByPlatform(true);
        frame.setLayout(new BorderLayout());
        frame.add(clock, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Game game = new Game();
        game.setSecondsAware(clock);
        clock.setGameStatus(game);
        game.start();
    }

    private static JMenuBar createMenu(final JFrame c) {
        JMenuBar jMenu = new JMenuBar();
        JMenu itemGame = new JMenu("Game");
        JMenuItem subItemNew = new JMenuItem("New Game");
        itemGame.add(subItemNew);
        jMenu.add(itemGame);

        JMenu itemHelp = new JMenu("Help");
        JMenuItem subItemAbout = new JMenuItem("About");
        subItemAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showCredits(c);
            }
        });
        itemHelp.add(subItemAbout);
        jMenu.add(itemHelp);

        return jMenu;
    }

    public static void showCredits(JFrame c) {
        final JDialog dialog = new JDialog(c);
        dialog.setSize(340, 200);
        dialog.setLocationRelativeTo(c);
        JPanel pnl = new JPanel();
        pnl.add(new JTextArea("Developed by Pedro Santos" + "\r\nEmail: pedrosans@gmail.com"));
        dialog.add(pnl);
        dialog.setVisible(true);
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        Action escapeAction = new AbstractAction() {
            private static final long serialVersionUID = 6644093457754079875L;

            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        };
        dialog.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "ESCAPE");
        dialog.getRootPane().getActionMap().put("ESCAPE", escapeAction);

    }
}
