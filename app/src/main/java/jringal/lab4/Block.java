package jringal.lab4;

/**
 * Created by JD on 6/11/2017.
 */

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Canvas;


public class Block {
    public int x;
    public int y;
    public int status;
    public Paint paint;
    Rect rect;

    public Block(int a, int b)
    {
        x = a;
        y = b;
        status = 1;
        rect = new Rect();
        paint = new Paint();

    }


    public void reset()
    {
        x = -100;
    }

    public void update() {
        if ( x > 800)
            x -= 20;


        rect.set(x, y, x + 100, y + 100);
    }
}
