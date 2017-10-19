package jringal.lab4;

import android.view.SurfaceView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Rect;
import android.inputmethodservice.Keyboard;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.InputEvent;

import static android.view.KeyEvent.ACTION_DOWN;
import static android.view.KeyEvent.ACTION_UP;
import static android.view.KeyEvent.KEYCODE_DPAD_DOWN;
import static android.view.KeyEvent.KEYCODE_DPAD_LEFT;
import static android.view.KeyEvent.KEYCODE_DPAD_RIGHT;
import static android.view.KeyEvent.KEYCODE_DPAD_UP;
import static android.view.KeyEvent.KEYCODE_SPACE;


public class GameView extends SurfaceView implements SurfaceHolder.Callback  {
    private Mario mario;
    private GamePad gamePad;
    private Fireball fireball;
    private Paint paint = new Paint();
    public static final int bgwidth = 1024;
    public static final int bgheight = 1024;
    //private Bloober[] bloobers;
    //private Beetle[] beetles;
    //private Koopa[] koopas;
    //private Piranha[] piranhas;
    //private Coin[] coins = new Coin[10];
    private Block[] blocks = new Block[20];
    private Monster[] beetles = new Monster[2];
    private Coin coin;
    private Pipe pipes;
    private int i;
    private int initialy;
    private Bitmap Icons[];
    private GameMap gamemap;
    private GameScreen screen;
    private Background background;
    private GameThread1 gamethread;
    private Mushroom mushroom;

    public GameView(Context context){
        super(context);

        getHolder().addCallback(this);
        gamethread = new GameThread1(this, getHolder());
        setFocusable(true);
        Icons = new Bitmap[30];

        paint.setColor(Color.BLACK);
        paint.setTextSize(60);
        paint.setTextAlign(Paint.Align.CENTER);

        //coins[0] = new Coin(400,400);



        //paint.setColor(Color.BLACK);


    }



    public void drawGameOver(Canvas canvas){
        System.out.println("Game Over Screen called!");
        super.draw(canvas);
        canvas.drawColor(Color.BLACK);
        Rect rect = new Rect();
        rect.set(0,0,getWidth(),getHeight());
        //canvas.drawBitmap
    }

    public void update(){

        fireball.update();
        mario.update();
        if(mario.counter > 5){
            mario.jumping = false;
            mario.falling = true;
        }
        if(mario.y == initialy){
            mario.falling = false;
            mario.counter = 0;
            mario.jumpcounter = 0;
        }
        if (mario.x == mushroom.x) {
            mario.type = 2;
            mushroom.active = false;
            mario.y = 600;
            mario.score += 150;
        }
        for(i=0;i<2;i++) {
            if (fireball.x == beetles[i].x) {
                beetles[i].active = false;
                mario.score += 125;
            }
        }

        if (mario.x == coin.x){
            mario.score += 100;
            coin.active = false;
        }

        //coin.update();
        /*for(i=0;i<2;i++){
            beetles[i].update(); }*/
        pipes.update();
        for(i=0; i<20; i++) {
            blocks[i].update();
        }


        background.update();
    }


    @Override
    public void draw(Canvas canvas){

        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();
        float scaleX = width/(float)bgwidth;
        float scaleY = height/(float)bgheight;

        String playerscore = "Score: " + mario.score;
        String playerlives  = "LIVES : " + mario.lives;

        //canvas.drawText(playerscore, 400 , 400 , paint);
        //canvas.drawText(playerlives, 400 ,500 , paint);

        //coins[0].update();
        //canvas.drawBitmap(Icons[3], null, coins[0].rect,null);
        //int pixelw = width/18   ;
        //int pixelh = height/15;
       /* rect2.set(0,0,width,height);
        canvas.drawBitmap(Icons[11],null,rect2,null);
        for (int i = 0; i < 16; ++i){
            for (int j = 0; j <= 18; ++j){
                rect1.set(j*pixelw, i*pixelh,(j+1)*pixelw,(i+1)*pixelh);
                if(screen.screen[j][i] == 1) {
                    canvas.drawBitmap(Icons[1], null, rect1, null);
                }
                if(screen.screen[j][i] == 2){
                    canvas.drawBitmap(Icons[0],null,rect1,null);
                }
            }
        }*/
       if(canvas != null) {
           final int saveState = canvas.save();
           canvas.scale(scaleX, scaleY);
           background.draw(canvas);
           gamePad.draw(canvas);

           if(fireball.active) {
               fireball.draw(canvas);
           }
           if (mushroom.active){
               mushroom.draw(canvas);
           }
           //canvas.drawBitmap(Icons[3],null, coin.rect,null);
           //canvas.drawBitmap(Icons[9],null, beetles[0].rect,null);
           //canvas.drawBitmap(Icons[5],null, beetles[1].rect,null);
           for (i=0;i<2;i++){
               if (beetles[i].active) {
                   beetles[i].draw(canvas);
               }
           }
           if(coin.active) {
               coin.draw(canvas);
           }
           canvas.drawBitmap(Icons[7],null,pipes.rect,null);
           for (i = 0; i < 3; i++){
               canvas.drawBitmap(Icons[1], null, blocks[i].rect,null); }
           canvas.drawBitmap(Icons[6],null,blocks[3].rect,null);
           for (i = 4; i < 20; i++) {
               canvas.drawBitmap(Icons[1], null, blocks[i].rect,null);
           }
           if(fireball.active) {
               fireball.draw(canvas);
           }
           mario.draw(canvas);
           canvas.restoreToCount(saveState);
         //  super.draw(canvas);
          // String playerscore = "Score: " + mario.score;
           //String playerlives  = "LIVES : " + mario.lives;

           canvas.drawText(playerscore, 150 , 100 , paint);
           canvas.drawText(playerlives, 150 ,200 , paint);
       }


    }
    @Override
    public void surfaceCreated(SurfaceHolder holder){
        System.out.println("Surface Created!");
        Bitmap Mario = BitmapFactory.decodeResource(getResources(),R.drawable.mario);
        Icons[0] = Mario;
        Bitmap Brick = BitmapFactory.decodeResource(getResources(),R.drawable.brick);
        Icons[1] = Brick;
        Bitmap Hblock = BitmapFactory.decodeResource(getResources(),R.drawable.hblock);
        Icons[2] = Hblock;
        Bitmap Coin = BitmapFactory.decodeResource(getResources(),R.drawable.coin);
        Icons[3] = Coin;
        Bitmap Beetle = BitmapFactory.decodeResource(getResources(),R.drawable.beetle);
        Icons[4] = Beetle;
        Bitmap Blooper = BitmapFactory.decodeResource(getResources(),R.drawable.blooper);
        Icons[5] = Blooper;
        Bitmap ItemBlock = BitmapFactory.decodeResource(getResources(),R.drawable.itemblock);
        Icons[6] = ItemBlock;
        Bitmap Pipe = BitmapFactory.decodeResource(getResources(),R.drawable.pipe);
        Icons[7] = Pipe;
        Bitmap Pipe2 = BitmapFactory.decodeResource(getResources(),R.drawable.pipe2);
        Icons[8] = Pipe2;
        Bitmap Plant = BitmapFactory.decodeResource(getResources(),R.drawable.plant);
        Icons[9] = Plant;
        Bitmap Plant2 = BitmapFactory.decodeResource(getResources(),R.drawable.plant2);
        Icons[10] = Plant2;
        Bitmap Background = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        Icons[11] = Background;
        Bitmap MarioSpriteR = BitmapFactory.decodeResource(getResources(),R.drawable.mariosprite);
        Icons[12] = MarioSpriteR;
        Bitmap MarioSpriteL = BitmapFactory.decodeResource(getResources(),R.drawable.mariospritel);
        Icons[13] = MarioSpriteL;
        Bitmap MarioSpriteS = BitmapFactory.decodeResource(getResources(),R.drawable.mariosprites);
        Icons[14] = MarioSpriteS;
        Bitmap LeftArrow = BitmapFactory.decodeResource(getResources(),R.drawable.leftarrow);
        Icons[15] = LeftArrow;
        Bitmap RightArrow = BitmapFactory.decodeResource(getResources(),R.drawable.rightarrow);
        Icons[16] = RightArrow;
        Bitmap BlackButton = BitmapFactory.decodeResource(getResources(),R.drawable.blackcircle);
        Icons[17] = BlackButton;
        Bitmap MarioSpriteJr = BitmapFactory.decodeResource(getResources(),R.drawable.mariospritejr);
        Icons[18] = MarioSpriteJr;
        Bitmap MarioSpriteJL = BitmapFactory.decodeResource(getResources(),R.drawable.mariospritejl);
        Icons[19] = MarioSpriteJL;
        Bitmap Fireball = BitmapFactory.decodeResource(getResources(),R.drawable.fireballl);
        Bitmap FireBall = Bitmap.createScaledBitmap(Fireball,160,40,true);
        Icons[20] = Fireball;
        //Bitmap Batman = BitmapFactory.decodeResource(getResources(),R.drawable.batman);
        //Icons[21] = Batman;


        Bitmap BigMarioSpriteR = Bitmap.createScaledBitmap(MarioSpriteR,440,400,true);
        Icons[21] = BigMarioSpriteR;
        Bitmap BigMarioSpriteL = Bitmap.createScaledBitmap(MarioSpriteL,440,400,true);
        Icons[22] = BigMarioSpriteL;
        Bitmap BigMarioSpriteS = Bitmap.createScaledBitmap(MarioSpriteS,187,400,true);
        Icons[23] = BigMarioSpriteS;
        Bitmap Mushroom = BitmapFactory.decodeResource(getResources(),R.drawable.mushroom);
        Icons[24] = Mushroom;
        Bitmap ScaledSquid = Bitmap.createScaledBitmap(Blooper,200,200,true);
        Icons[25] = ScaledSquid;
        Bitmap ScaledPlant = Bitmap.createScaledBitmap(Plant,200,200,true);
        Icons[26] = ScaledPlant;
        Bitmap ScaledCoin = Bitmap.createScaledBitmap(Coin,100,100,true);
        Icons[27] = ScaledCoin;


        background = new Background(Icons[11]);
        background.Vector(-5);
        gamePad = new GamePad(BlackButton);
        gamePad.ImageSet[0] = Icons[17];
        gamePad.ImageSet[1] = Icons[17];
        gamePad.ImageSet[2] = Icons[15];
        gamePad.ImageSet[3] = Icons[16];

        fireball = new Fireball(Icons[20],80,81,4);
        mario = new Mario(MarioSpriteS, 146, 180, 1);
        mario.y = 800;
        coin = new Coin(Icons[27],420,420,1);
        coin.x = 200;
        coin.y = 800;
        beetles[0] = new Monster(Icons[26],400,400,1);
        beetles[0].x = 350;
        beetles[0].y = 550;
        beetles[1] = new Monster(Icons[25],400,433,1);
        beetles[1].x = 200;
        beetles[1].y = 50;
        pipes = new Pipe(300,750);
        mushroom = new Mushroom(Icons[24],400, 400,1);
        mushroom.x = 600;
        mushroom.y = 800;

        for (i = 0; i < 4; i++) {
            blocks[i] = new Block(200+i*100,200);
        }
        for (i=4; i < 20; i++) {
            blocks[i] = new Block(-400 + i*100, 1000);
        }

        //GameThread1 gameThread1 = new GameThread1(this,holder);
        gamethread.setRunning(true);
        gamethread.start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
    //    boolean retry = true;
    //    while(true){
    //        try(GameThread1.)
    //    }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        double ix, iy, fx, fy;
        ix = event.getX();
        iy = event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if((ix >= 1556)&&(ix <= 1725)&&(iy >= 645)&&(iy <= 805)) {
                mario.left = false;
                mario.right = true;
                mario.standing = false;
                System.out.println("Mario should be running right");
                if(mario.typecounter > 3|| mario.type == 2){
                    mario.UpdateSprite(Icons[21],146,400,3);
                }
                else {
                    mario.UpdateSprite(Icons[12], 146, 180, 3);
                }
                return true;

            }
            if((ix >= 1310)&&(ix <= 1475)&&(iy >= 645)&&(iy <= 805)) {
                mario.right = false;
                mario.left = true;
                mario.standing = false;
                System.out.println("Mario should be running left");
                if(mario.typecounter > 3 || mario.type == 2){
                    mario.UpdateSprite(Icons[22],146,400,3);
                }
                else {
                    mario.UpdateSprite(Icons[13], 146, 180, 3);
                }
                return true;
            }
            if((ix >= 238)&&(ix <= 415)&&(iy >= 645)&&(iy <= 810)) {
                if(mario.jumpcounter == 0) {
                    mario.jumping = true;
                    mario.falling = false;
                    mario.standing = false;
                    System.out.println("Mario should be jumping");
                    mario.jumpcounter++;
                } else {
                    System.out.println("Mario cannot jump");
                }
                if(mario.direction == 0 && mario.typecounter < 3){
                    mario.UpdateSprite(Icons[19],170,180,1);
                   mario.left = true;
                } else if(mario.direction == 2&& mario.typecounter < 3){
                   mario.UpdateSprite(Icons[18],170,180,1);
                    mario.right = true;
                }
                initialy = mario.y;
                return true;
            }
            if((ix >= 70)&&(ix <= 240)&&(iy >= 465)&&(iy <= 635)) {
                if(fireball.shotcount ==0) {
                    fireball.active = true;
                    fireball.x = mario.x;
                    fireball.y = mario.y;
                    fireball.shotcount++;
                    mario.typecounter++;
                    System.out.println("Shoot Fireball");
                } else {
                    System.out.println("Fireball already shot");
                }


            }
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            System.out.println("x: " + event.getX() + "  y: " + event.getY());
            mario.left = false;
            mario.right = false;
            mario.standing = true;
            System.out.println("Mario should be standing");
            if(mario.typecounter > 3|| mario.type == 2){
                 mario.UpdateSprite(Icons[23],146,400,1);
            }
            else {
                mario.UpdateSprite(Icons[14], 146, 180, 1);
            }
            mario.frameNum = 1;

            return true;
        }
        return super.onTouchEvent(event);
    }

}
