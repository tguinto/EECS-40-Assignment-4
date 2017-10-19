package jringal.lab4;

import android.graphics.Paint;
import android.graphics.Rect;

public class Pipe {
    public int x;
    public int y;
    public Paint paint;
    Rect rect;

    public Pipe(int a, int b)
    {
        x = a;
        y = b;
        paint = new Paint();
        rect = new Rect();
    }

    public void update()
    {
        if(x > 800)
            x = -500;
        //x+= 20;
        rect.set(x,y,x+282,y+285);
    }

    public void reset()
    {
        x = -300;
        y = 350;
    }
}
