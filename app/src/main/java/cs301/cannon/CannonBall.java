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
        float firstLeft = width - 900;
        float firstLeftTop = height-150;
        float firstRight = width-800;
        float secondLeft = width-400;
        float secondLeftTop = height-200;
        float secondRight = width-300;
        canvas.drawRect(firstLeft, firstLeftTop, firstRight, height, yellow); //target one
        canvas.drawRect(secondLeft, secondLeftTop, secondRight, height, yellow); //target two

        double velInitial = 130; //initial velocity cannonball
        double xVelocity = 55; //horizontal velocity cannonball
        //initial vertical velocity cannonball
        double yVelInitial = Math.sqrt( (velInitial*velInitial) - (xVelocity*xVelocity) );
        double ppf = (count*5)%800; //position of cannonball moving 20 pixels per frame

        double xBall = xVelocity*ppf + xCannon + 25; //x position of cannonball

        double yVelFinal = yVelInitial - 9.81*ppf; //y velocity of cannonball

        //y position of the cannonball
        double yBall = yCannon + 60;
        yBall = yBall - (yVelFinal*yVelFinal - yVelInitial*yVelInitial) / (-2*9.81);

        //create red color
        Paint red = new Paint();
        red.setColor(Color.RED);

        //Draws the cannonball in the correct position
        canvas.drawCircle((float)xBall, (float)yBall, 40, red);

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

        //check if cannonball hits a target
        if( xBall > firstLeft && xBall < firstRight && yBall > firstLeftTop && yBall < height ) {
            Path explosion = new Path();
            explosion.reset();
            explosion.moveTo(firstLeft-60, firstLeftTop-60);
            explosion.lineTo(firstLeft+25, firstLeftTop);
            explosion.lineTo(firstLeft+65, firstLeftTop-50);
            explosion.lineTo(firstLeft+90, firstLeftTop+15);
            explosion.lineTo(firstRight+20, firstLeftTop+25);
            explosion.lineTo(firstRight-40, firstLeftTop+80);
            explosion.lineTo(firstRight-10, firstLeftTop+120);
            explosion.lineTo(firstRight-60, firstLeftTop+70);
            explosion.lineTo(firstLeft+30, firstLeftTop+120);
            explosion.lineTo(firstLeft+20, firstLeftTop+70);
            explosion.lineTo(firstLeft, firstLeftTop+60);
            explosion.lineTo(firstLeft+30, firstLeftTop+50);
            explosion.moveTo(firstLeft-60, firstLeftTop-60);
            canvas.drawPath(explosion, red);
        }

        if (xBall > secondLeft && xBall < secondRight && yBall > secondLeftTop && yBall < height) {
            Path explosion = new Path();
            explosion.reset();
            explosion.moveTo(secondLeft-60, secondLeftTop-60);
            explosion.lineTo(secondLeft+25, secondLeftTop);
            explosion.lineTo(secondLeft+65, secondLeftTop-50);
            explosion.lineTo(secondLeft+90, secondLeftTop+15);
            explosion.lineTo(secondRight+20, secondLeftTop+25);
            explosion.lineTo(secondRight-40, secondLeftTop+80);
            explosion.lineTo(secondRight-10, secondLeftTop+120);
            explosion.lineTo(secondRight-60, secondLeftTop+70);
            explosion.lineTo(secondLeft+30, secondLeftTop+120);
            explosion.lineTo(secondLeft+20, secondLeftTop+70);
            explosion.lineTo(secondLeft, secondLeftTop+60);
            explosion.lineTo(secondLeft+30, secondLeftTop+50);
            explosion.moveTo(secondLeft-60, secondLeftTop-60);
            canvas.drawPath(explosion, red);
        }
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
