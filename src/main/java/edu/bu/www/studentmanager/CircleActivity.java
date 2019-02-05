package edu.bu.www.studentmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CircleActivity extends AppCompatActivity {

    private static final int timeout = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Circle(this));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(CircleActivity.this, StitchActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, timeout);

    }

    public class Circle extends View {

        public Circle(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int x = getWidth();
            int y = getHeight();
            int r = 500;

            Paint paint = new Paint();

            //Set the color based on the preference chosen by the user
            SharedPreferences mySharedPreferences = getSharedPreferences("Background Color", MODE_PRIVATE);
            int selectedColor = mySharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
            if (selectedColor != getResources().getColor(R.color.colorPrimary)){
            paint.setColor(selectedColor);
            }

            paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);
            paint.setColor(Color.BLUE);
            canvas.drawCircle(x / 2, y / 2, r, paint);
        }
    }
}
