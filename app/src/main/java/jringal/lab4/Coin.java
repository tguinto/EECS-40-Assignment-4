package jringal.lab4;

/**
 * Created by JD on 6/11/2017.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Coin extends GameObject {
    private Bitmap sprite;
    protected boolean active;
    /*public int x;
    public int y;
    public int status;
    public Paint paint;
    Rect rect;*/

    public Coin (Bitmap Image, int w, int h, int Frames/*int a, int b*/)
    {
        width = w;
        height = h;
        active = true;
        Bitmap[] image = new Bitmap[Frames];
        sprite = Image;
        image[0] = Image;
        /*x = a;
        y = b;
        status = 1;
        rect = new Rect();
        paint = new Paint();*/

    }


    //public void reset()
    {
        x = -100;
    }

    /*public void update() {
        if ( x > 800)
            x -= 20;


        rect.set(x, y, x + 200, y + 200);
    }*/
    public void draw(Canvas canvas){
        canvas.drawBitmap(sprite, x, y, null);
    }
}
