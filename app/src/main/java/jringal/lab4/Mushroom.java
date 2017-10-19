package jringal.lab4;

/**
 * Created by JD on 6/12/2017.
 */



import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Mushroom extends GameObject {
    private Bitmap sprite;
    protected boolean active;


    public Mushroom(Bitmap Image, int w, int h, int Frames){
        width = w;
        height = h;
        active = true;
        Bitmap[] image = new Bitmap[Frames];
        sprite = Image;
        image[0] = Image;


    }




    public void draw(Canvas canvas){
        canvas.drawBitmap(sprite, x, y, null);
    }
}
