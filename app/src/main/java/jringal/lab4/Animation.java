package jringal.lab4;

import android.graphics.Bitmap;



public class Animation {
    protected Bitmap[] frames;
    protected int currentFrame;
    protected long startTime;
    protected double delay;
    protected boolean playedOnce;

    public Animation(){
        frames = null;
        currentFrame = 0;
        startTime = System.nanoTime();
        delay = 0.01;
    }
    public void update(){
        long elapsed = (System.nanoTime() - startTime/1000000);
        if (elapsed > delay){
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length){
            currentFrame = 0;
            playedOnce = true;
        }
    }

}
