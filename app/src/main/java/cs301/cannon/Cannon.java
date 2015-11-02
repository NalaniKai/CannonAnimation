package cs301.cannon;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by chunm18 on 11/1/2015.
 */
public class Cannon implements Animator{

    //position of cannon
    int xCannon = 50;
    int yCannon = 500;

    @Override
    public int interval() {
        return 50;
    }

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

    @Override
    public void tick(Canvas canvas) {
        Paint black = new Paint(Color.BLACK);

        int height = canvas.getHeight();

        if( xCannon < 100 && xCannon > 30 &&
            yCannon > height-100 && yCannon < height-25 ) {
            Path head = new Path();
            head.moveTo(xCannon, yCannon);
            head.lineTo(xCannon + 15, yCannon + 30);
            head.lineTo(xCannon - 50, yCannon + 100);
            head.lineTo(xCannon - 60, yCannon + 100);
            head.lineTo(xCannon, yCannon);
            canvas.drawPath(head, black);

            //cannon base
            canvas.drawCircle(xCannon - 30, yCannon + 70, 30, black);
        }
    }

    @Override
    public void onTouch(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            xCannon = (int) event.getX(); //get x position
            yCannon = (int) event.getY(); //get y position
        }
    }
}
