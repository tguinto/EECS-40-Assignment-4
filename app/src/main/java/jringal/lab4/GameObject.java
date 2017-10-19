package jringal.lab4;
import android.graphics.Bitmap;
import android.graphics.Rect;



public abstract class GameObject {
    protected int x, y, nx, ny;
    protected int width, height;
    protected Bitmap[] image;
    public int frameNum;

    public Rect getRectangle(){
        return new Rect(x, y, x+width, y+height);
    }



}
