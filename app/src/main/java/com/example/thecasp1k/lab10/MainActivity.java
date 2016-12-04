package com.example.thecasp1k.lab10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrawingView dv = new DrawingView(this);
        setContentView(dv);
    }

    class DrawingView extends View implements View.OnTouchListener {

        Paint p;
        int myWidth = 0, myHeight = 0;
        int x, y;
        int touchCounter = 0;

        public DrawingView(Context context) {
            super(context);
            setOnTouchListener(this);
            p = new Paint();
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            myWidth = w;
            myHeight = h;
            super.onSizeChanged(w, h, oldw, oldh);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            x = (int) event.getX();
            y = (int) event.getY();
            if (event.getAction() == MotionEvent.ACTION_DOWN)
                touchCounter++;
            invalidate();
            return true;
        }


        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(255, 200, 191, 231);
            int y1 = 100, y2 = 200;
            int xb1 = 0, xb2 = 100;
            int xw1 = 100, xw2 = 200;
            for (int i = 0; y1 < myHeight - 200; i++) {
                while (xb1 < myWidth) {
                    p.setColor(Color.BLACK);
                    canvas.drawRect(xb1, y1, xb2, y2, p);
                    xb1 += 200;
                    xb2 += 200;
                }
                while (xw1 < myWidth) {
                    p.setColor(Color.WHITE);
                    canvas.drawRect(xw1, y1, xw2, y2, p);
                    xw1 += 200;
                    xw2 += 200;
                }
                y1 += 100;
                y2 += 100;
                if (i % 2 == 1){
                    xb1 = 0;
                    xb2 = 100;
                    xw1 = 100;
                    xw2 = 200;
                } else {
                    xb1 = 100;
                    xb2 = 200;
                    xw1 = 0;
                    xw2 = 100;
                }
            }
            p.setTextSize(20);
            p.setTextAlign(Paint.Align.CENTER);
            int mw = myHeight - (myHeight % 100) - 200;
            int mh = myWidth - (myWidth % 100);
            int sy = myHeight - 100;
            int sx = myWidth - (myWidth / 2);
            p.setColor(Color.BLACK);
            String stringToDraw;
            if (x > 0 && y > 0)
                stringToDraw = x + " " + y + " число нажатий " + touchCounter;
            else
                stringToDraw = "Размер доски " + mw + " " + mh;
            canvas.drawText(stringToDraw, sx, sy, p);
        }
    }

}
