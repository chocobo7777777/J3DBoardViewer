package com.game.viewer;
import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;

public class BoardViewer {
    GLWindow glWindow;
    GLU glu = null;

    int[][] board=null;
    int maxX=0;
    int maxY=0;

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
        GLProfile glProfile = GLProfile.get("GL4bc");
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
        glu.gluOrtho2D(0, 20, 0 ,20);


        drawCube(gl);
//        for (int i=0;i <maxY; i++) {
//            for (int j=0; j <maxX ;j++) {
//                gl.glTranslatef(j,0,i);
//                drawCube(gl);
//
//            }
//        }
    }

    public void drawCube(GL3bc gl) {
        gl.glBegin(gl.GL_QUADS);
        gl.glColor3ub((byte) 0x80, (byte) 0x40, (byte) 0x0);
        gl.glVertex2f(0.1f, 0.1f);
        gl.glVertex2f(0.9f, 0.1f);
        gl.glVertex2f(0.9f, 0.9f);
        gl.glVertex2f(0.1f, 0.9f);
        gl.glEnd();
    }


}
