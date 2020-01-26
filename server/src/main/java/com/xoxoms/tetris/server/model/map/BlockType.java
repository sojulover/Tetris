package com.xoxoms.tetris.server.model.map;

import lombok.Getter;

public enum BlockType {

    I(new int[][]{
            {1},
            {1},
            {1},
            {1}
    }),
    J(new int[][]{
            {0, 2},
            {0, 2},
            {2, 2}
    }),
    L(new int[][]{
            {3, 0},
            {3, 0},
            {3, 3}
    }),
    O(new int[][]{
            {4, 4},
            {4, 4}
    }),
    S(new int[][]{
            {0, 5, 5},
            {5, 5, 0}
    }),
    T(new int[][]{
            {6, 6, 6},
            {0, 6, 0}
    }),
    Z(new int[][]{
            {7, 7, 0},
            {0, 7, 7}
    })
    ;

    BlockType(int[][] defaultForm) {

        this.defaultForm = defaultForm;
    }

    @Getter
    private int[][] defaultForm;

    public static BlockType pickRandom() {

        return BlockType.values()[(int) (Math.random() * BlockType.values().length)];
    }
}
