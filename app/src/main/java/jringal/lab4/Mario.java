package jringal.lab4;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Tristan on 6/7/2017.
 */

public class Mario extends GameObject {
    private GameView gameView;
    protected boolean alive;
    protected int direction;
    protected int lives;
    protected int score;
    protected int type;
    protected boolean right;
    protected boolean left;
    protected boolean standing;
    protected boolean attacking;
    protected boolean jumping;
    protected boolean falling;
    protected int counter;
    protected int jumpcounter;
    private long startTime;
    public Bitmap sprite;
    private Animation animation = new Animation();
    protected int typecounter;



    public Mario(Bitmap Image, int w, int h, int framenum){
        frameNum = framenum;
        standing = true;
        left = false;
        jumping = false;
        falling = false;
        right  = false;
        attacking = false;
        width = w;
        height = h;
        sprite = Image;
        alive = true;
        typecounter = 0;
        direction = 0;
        type = 0;
        lives = 3;
        score = 0;
        counter = 0;
        jumpcounter = 0;

        Bitmap[] image = new Bitmap[frameNum];
        for(int i = 0; i< image.length; i++){
            image[i] = Bitmap.createBitmap(sprite,i*width,0,width,height);

        }
        animation.frames = image;
        animation.delay = 0.01;
        startTime = System.nanoTime();

    }
    public void UpdateSprite(Bitmap Sprite,int w, int h,int FrameNum){
        System.out.println("UpdateSprite Called");
        sprite = Sprite;


        image = new Bitmap[FrameNum];
        frameNum = FrameNum;
        for(int i = 0; i< image.length; i++){
            image[i] = Bitmap.createBitmap(sprite,i*w,0,w,h);

        }
        animation.currentFrame = 0;
        animation.frames = image;
    }

    public void update(){

        long elapsed = (System.nanoTime() - startTime/1000000);
        if(elapsed > 100){

            startTime = System.nanoTime();
        }
        animation.update();
        if (right) {
            x += 20;
            direction = 2;
        }
        if (left) {
            x -= 20;
            direction = 0;
        }
        /*if(counter > 5){
            System.out.println("counter is at: " + counter);
            jumping = false;
            falling = true;
        }*/
        if(jumping && !falling ){
            y -= 40;
            counter++;
            System.out.println("Mario is going up " + counter + " times");

        }
        if ( falling && !jumping ){
            y += 40;

            System.out.println("Mario is going down " + counter + " times");
        }
         /*if (counter < 0){
             falling = false;
             jumping = false;
             ny = 0;
         }*/



    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.frames[animation.currentFrame],x,y,null);
    }


}