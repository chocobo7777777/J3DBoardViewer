package com.game.viewer;
import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;

import java.awt.Color;

import java.awt.*;
import java.util.HashMap;

public class BoardViewer {
    GLWindow glWindow;
    GLU glu = null;

    int[][] board=null;
    int maxX=0;
    int maxY=0;
    public final static byte[] blackUB = new byte[]{0,0,0};
    public static HashMap<Color, byte[]> colorHashMap =new HashMap();
    public static HashMap<Integer, Color> colorMap =new HashMap();
    static {
        colorMap.put(1,Color.white);
        colorMap.put(2,Color.blue);
        colorMap.put(3,Color.red);
        colorMap.put(4,Color.green);
        colorMap.put(5,Color.yellow);
        initColorMap(colorMap);
    }

    public static void initColorMap(HashMap<Integer, Color> colorMap) {
        colorHashMap.clear();
        for (Color color: colorMap.values()) {
            byte[] colorUB = new byte[3];
            colorUB[0] = (byte) color.getRed();
            colorUB[1] = (byte) color.getGreen();
            colorUB[2] = (byte) color.getBlue();
            colorHashMap.put(color,colorUB);
        }

    }


    public int[][] getBoard() {
        return board;
    }



    public void setBoard(int[][] board) {
        this.board = board;
        this.maxX = board[0].length;
        this.maxY = board.length;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public BoardViewer() {
        GLProfile glProfile = GLProfile.get("GL3bc");
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        glWindow = GLWindow.create(glCapabilities);
        glWindow.setSize(800,800);
        glWindow.setVisible(true);
        glWindow.setPointerVisible(true);
        Animator animator = new Animator(glWindow);
        glWindow.addWindowListener(new WindowAdapter() {
            public void windowDestroyNotify(WindowEvent e) {
                System.exit(0);
            }
        });
        glWindow.addGLEventListener(new GLEventListener() {
            @Override
            public void init(GLAutoDrawable drawable) {
                glu = GLU.createGLU(drawable.getGL());

            }

            @Override
            public void dispose(GLAutoDrawable drawable) {

            }

            @Override
            public void display(GLAutoDrawable drawable) {
                if (board != null ) {
                    drawBoard(board,board[0].length,drawable);
                }
            }

            @Override
            public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
                final GL4bc gl = drawable.getGL().getGL4bc();
            }
        });
        animator.start();

    }



    public void drawBoard(int[][] board, int maxX, GLAutoDrawable drawable) {
        int maxY = board.length;

        final GL3bc gl = drawable.getGL().getGL3bc();
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL2.GL_MODELVIEW);

        gl.glLoadIdentity();
        glu.gluOrtho2D(0, maxX, 0 ,maxY);

        for (int i=0;i <maxY; i++) {
            for (int j=0; j <maxX ;j++) {
                gl.glPushMatrix();
                gl.glTranslatef(j,i,0);
                drawCube(gl, board[j][i]);
                gl.glPopMatrix();
            }
        }
    }

    public void drawCube(GL3bc gl, int value) {
        gl.glBegin(gl.GL_QUADS);
        Color color = colorMap.get(value);
        byte[] cub=null;
        if (color !=null) {
             cub = colorHashMap.get(color);
        } else {
            cub = blackUB;
        }
        gl.glColor3ub(cub[0] , cub[1], cub[2]);
        gl.glVertex2f(0.1f, 0.1f);
        gl.glVertex2f(0.9f, 0.1f);
        gl.glVertex2f(0.9f, 0.9f);
        gl.glVertex2f(0.1f, 0.9f);
        gl.glEnd();
    }


}
