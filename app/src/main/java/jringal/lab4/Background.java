package jringal.lab4;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Background extends Picture {

    public Background(Bitmap Image){
        image = Image;
    }

    public void draw(Canvas canvas){
        //System.out.println("Background draw called!");
        canvas.drawBitmap(image,x,y,null);
        if (x < 0){
            canvas.drawBitmap(image,x+GameView.bgwidth, y,null);
        }
    }
    public void update(){
        x+=nx;

        if(x< -(GameView.bgwidth)){
            x=0;
        }
    }
    public void Vector(int nx){
        this.nx = nx;
    }
}
