package cs301.cannon;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;

/**
 * Created by chunm18 on 11/1/2015.
 */
public class CannonBall implements Animator {

    //position of cannon
    int xCannon = 100;
    int yCannon = 900;

    //position of touch
    int x;
    int y;
    Path head;

    int count = 0; // counts the number of logical clock ticks

    //interval             Returns the time interval between frames, in milliseconds.
    @Override
    public int interval() {
        return 10;
    }

    //backgroundColor       Returns a color for the background.
    @Override
    public int backgroundColor() {
        // create and return the background color
        return Color.rgb(180, 200, 255);
    }

    //program never pauses
    @Override
    public boolean doPause() {
        return false;
    }

    //program never quits
    @Override
    public boolean doQuit() {
        return false;
    }

    //tick          Action that is performed on clock tick. Draws the cannonball.
    @Override
    public void tick(Canvas canvas) {
        float height = canvas.getHeight(); //get height of canvas
        float width = canvas.getWidth(); //get width of canvas

        //increase tick count
        ++count;

        //create black color
        Paint black = new Paint();
        black.setColor(Color.BLACK);

        if( y > height-400 && x < 400 && ( (x < 110 && y < height-150) || x > 110)) {
            xCannon = x; //set x position of cannon
            yCannon = y; //set y position of cannon
        }

        //create yellow color
        Paint yellow = new Paint();
        yellow.setColor(Color.YELLOW);

        //targets
        canvas.drawRect(width - 900, height-150, width-800, height, yellow); //target one
        canvas.drawRect(width-400, height-200, width-300, height, yellow); //target two

        double velInitial = 90; //initial velocity cannonball
        double xVelocity = 75; //horizontal velocity cannonball
        //initial vertical velocity cannonball
        double yVelInitial = Math.sqrt( (velInitial*velInitial) - (xVelocity*xVelocity) );
        double ppf = (count*5)%800; //position of cannonball moving 20 pixels per frame

        double xBall = xVelocity*ppf + xCannon + 25; //x position of cannonball

        double yVelFinal = yVelInitial - 9.81*ppf; //y velocity of cannonball

        //y position of the cannonball
        double yBall = yCannon + 60;
        yBall = yBall - (yVelFinal*yVelFinal - yVelInitial*yVelInitial) / (-2*9.81);

        //create red color
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);

        //Draws the cannonball in the correct position
        canvas.drawCircle((float)xBall, (float)yBall, 40, redPaint);

        //draws cannon
        head = new Path();
        head.reset();
        head.moveTo(40, height - 90);
        head.lineTo(xCannon, yCannon);
        head.lineTo(xCannon + 50, yCannon + 120);
        head.lineTo(60, height - 100);
        head.lineTo(40, height - 90);
        canvas.drawPath(head, black);

        //cannon base
        canvas.drawCircle(50, height - 80, 50, black);
    }

    // onTouch          Stores x, y coordinates of the place the user touches.
    @Override
    public void onTouch(MotionEvent event) {
        //check if user touches GUI
        if(event.getAction() == MotionEvent.ACTION_UP) {
            x = (int) event.getX(); //get x position of touch
            y = (int) event.getY(); //get y position of touch
        }
    }
}
