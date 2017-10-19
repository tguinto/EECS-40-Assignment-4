package jringal.lab4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Tristan on 6/9/2017.
 */

public class GamePad extends Picture {
    private Paint paint;
    public Bitmap[] ImageSet;
    public GamePad(Bitmap Image){
        paint = new Paint();
        paint.setColor(Color.RED);
        image = Image;
        ImageSet = new Bitmap[4];

    }

    public void drawButton1(Canvas canvas,Bitmap Image){
        Rect rect = new Rect();
        rect.set(40,540, 140,740);
        canvas.drawBitmap(Image, null, rect, null);

    }
    public void drawButton2(Canvas canvas,Bitmap Image){
        Rect rect = new Rect();
        rect.set(140,740, 240,940);
        canvas.drawBitmap(Image, null, rect, null);

    }
    public void drawRight(Canvas canvas,Bitmap Image){
        Rect rect = new Rect();
        rect.set(880,740,980,940);
        canvas.drawBitmap(Image, null, rect, null);

    }
    public void drawLeft(Canvas canvas,Bitmap Image){
        Rect rect = new Rect();
        rect.set(740,740,840,940);
        canvas.drawBitmap(Image, null, rect, null);
    }
    public void draw(Canvas canvas){
        drawButton1(canvas, ImageSet[0]);
        drawButton2(canvas,ImageSet[1]);
        drawLeft(canvas,ImageSet[2]);
        drawRight(canvas,ImageSet[3]);

    }
}
