package com.game;

import com.game.viewer.BoardViewer;
import com.jogamp.nativewindow.WindowClosingProtocol;
import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.event.WindowListener;
import com.jogamp.newt.event.WindowUpdateEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import jogamp.opengl.glu.GLUquadricImpl;

import java.util.Random;
import java.util.random.RandomGenerator;


public class Main {
    public static void main(String[] args) {
        int[][] board = {
                {1,3,4,5},
                {2,3,4,5},
                {3,3,4,5},
                {4,3,4,5}
        };

        BoardViewer boardViewer = new BoardViewer();
        boardViewer.setBoard(board);
    }
}