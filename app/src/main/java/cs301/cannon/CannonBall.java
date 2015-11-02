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

    private int count = 0; // counts the number of logical clock ticks

    //interval             Returns the time interval between frames, in milliseconds.
    @Override
    public int interval() {
        return 30;
    }

    //backgroundColor       Returns a color for the background.
    @Override
    public int backgroundColor() {
        // create and return the background color
        return Color.rgb(180, 200, 255);
    }

    @Override
    public boolean doPause() {
        return false;
    }

    @Override
    public boolean doQuit() {
        return false;
    }

    //tick          Action that is performed on clock tick. Draws the cannonball.
    @Override
    public void tick(Canvas canvas) {
        //increase tick count
        ++count;

        //position of ball moving 15 pixels per frame
        int num = (count * 15) % 800;

        // Draw the cannon in the correct position.
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        canvas.drawCircle(num, num, 60, redPaint);

        Paint black = new Paint();
        black.setColor(Color.BLACK);

        int height = canvas.getHeight();

        if (x < 400 && x > 20 && y > height - 400 && y < height - 40) {
            xCannon = x;
            yCannon = y;
        }

        head = new Path();
        head.reset();
        head.moveTo(40, height-90);
        head.lineTo(xCannon, yCannon);
        head.lineTo(xCannon + 50, yCannon + 120);
        head.lineTo(60, height-100);
        head.lineTo(40, height-90);
        canvas.drawPath(head, black);

        //cannon base
        canvas.drawCircle(50, height-80, 50, black);
    }

    @Override
    public void onTouch(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            x = (int) event.getX(); //get x position
            y = (int) event.getY(); //get y position
        }
    }
}
