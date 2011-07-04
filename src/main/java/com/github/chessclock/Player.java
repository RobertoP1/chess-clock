package com.github.chessclock;

enum Player {
    PLAYER_1(0), PLAYER_2(1);
    public int i;

    private Player(int i) {
        this.i = i;
    }

    public Player getOponent() {
        return i == 0 ? PLAYER_2 : PLAYER_1;
    }

    public int getIndex() {
        return i;
    }
}