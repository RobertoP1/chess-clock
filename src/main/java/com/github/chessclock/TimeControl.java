package com.github.chessclock;

public class TimeControl extends Thread {

    enum ClockStatus {
        ACTIVE, PAUSED
    }

    public static interface SecondsAware {
        public void oneSecondHasPassed();
    }

    ClockStatus clockStatus;
    private SecondsAware secondsAware;

    public TimeControl() {
        clockStatus = ClockStatus.PAUSED;
    }

    @Override
    public void run() {
        try {
            if (clockStatus == ClockStatus.ACTIVE) {
                Thread.sleep(1000);
                secondsAware.oneSecondHasPassed();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        clockStatus = ClockStatus.PAUSED;
    }

    public void activate() {
        clockStatus = ClockStatus.ACTIVE;
    }

    public boolean isActive() {
        return clockStatus == ClockStatus.ACTIVE;
    }

    public void setSecondsAware(SecondsAware secondsAware) {
        this.secondsAware = secondsAware;
    }
}