package jringal.lab4;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import static android.graphics.PorterDuff.Mode.CLEAR;

/**
 * Created by Tristan on 6/9/2017.
 */

public class Fireball  extends GameObject{
    protected int shotcount;
    private int counter;
    private Animation animation = new Animation();
    private Bitmap sprite;
    private long startTime;
    protected boolean active;
    public Fireball(Bitmap Image, int w, int h,int numFrames){
        width = w;

        height = h;
        active = false;

        Bitmap[] image = new Bitmap[numFrames];
        sprite = Image;
        counter = 0;
        animation.currentFrame = 0;
        for(int i = 0; i< image.length; i++){
            image[i] = Bitmap.createBitmap(sprite,i*width,0,width,height);

        }

        animation.frames = image;
        animation.delay = 0.01;
        startTime = System.nanoTime();
    }
    public void update(){
        if (active) {
            long elapsed = (System.nanoTime() - startTime/1000000);
            if(elapsed > 100){

                startTime = System.nanoTime();
            }
            System.out.println("Fireball animation update");
            animation.update();
            x += 50;
            counter++;
            if(counter == 8){
                active = false;
            }
        }
        else if (!active){
            shotcount = 0;
            counter = 0;
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.frames[animation.currentFrame], x, y, null);
    }

}
