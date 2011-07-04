package com.github.chessclock;

import com.github.chessclock.TimeControl.SecondsAware;

public class Game implements SecondsAware {

    TimeControl timeControl;
    Player currentPlayer;
    long[] timeMap = new long[] {};
    SecondsAware view;

    public Game() {
        timeControl = new TimeControl();
        timeControl.setSecondsAware(this);
    }

    public void oneSecondHasPassed() {
        timeMap[currentPlayer.getIndex()]--;
        view.oneSecondHasPassed();
    }

    public long getCurrentSecond() {
        return timeMap[currentPlayer.getIndex()] % 60;
    }

    public void setupDuration(long duration) {
        timeMap = new long[] {duration, duration};
    }

    synchronized public void onPlay(Player player) {
        currentPlayer = player.getOponent();
        timeControl.activate();
    }

    public void toogle() {
        if (timeControl.isActive()) {
            onPlay(currentPlayer);
        }
    }

    public boolean isActive() {
        return currentPlayer != null;
    }

    public void start() {
        currentPlayer = Player.PLAYER_1;
        timeControl.activate();
        timeControl.start();
    }

    public void pause() {
        timeControl.pause();
    }

    public void setSecondsAware(SecondsAware view) {
        this.view = view;
    }

    public long getRemaningSeconds(Player player) {
        return timeMap[player.getIndex()];
    }

    public long getRemaingMinutes(Player player) {
        return timeMap[player.getIndex()] / 60;
    }

}
