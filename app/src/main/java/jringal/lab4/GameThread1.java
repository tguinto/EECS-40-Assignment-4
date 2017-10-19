package jringal.lab4;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Tristan on 6/7/2017.
 */

public class GameThread1 extends Thread {
    private GameView gameView;
    private int FPS = 30;
    private double avgFPS;
    public static Canvas c;
    private boolean running;
    private SurfaceHolder sh;
    public GameThread1(GameView gv,SurfaceHolder surfaceHolder){

        super();
        gameView = gv;
        surfaceHolder = sh;
    }
    public void run(){
        System.out.println("Game Thread 1 Start!");
        long startTime;
        long timemilsec;
        long waitTime;


        while(running){
            sh = gameView.getHolder();
            c = sh.lockCanvas();
            if (c != null){
                gameView.update();
                gameView.draw(c);
                sh.unlockCanvasAndPost(c);
                try{
                    sleep(100);
                } catch (InterruptedException e){
                    System.out.println("Error Occured");
                }
            }

        }
    }
    public void setRunning(boolean b) {
        running = b;
    }
}
