package com.game.base;

import lombok.Data;

@Data
public class Board {
    int x;
    int y;
    int[] data;
    public Board(int x, int y) {
        this.x = x;
        this.y = y;
        this.data = new int[x * y];
    }
    public Board(int x) {
        this.x = x;
        this.y = x;
        this.data = new int[x * x];
    }
}
