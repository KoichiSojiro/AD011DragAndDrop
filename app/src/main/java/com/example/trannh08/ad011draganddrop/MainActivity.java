package com.example.trannh08.ad011draganddrop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "AD011";
    private final int IMAGE_WIDTH = 100;
    private final int IMAGE_HEIGHT = 100;

    private ImageView imageView;
    private ViewGroup rootLayout;
    private int deltaX;
    private int deltaY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = (ViewGroup) findViewById(R.id.view_root);
        imageView = (ImageView) rootLayout.findViewById(R.id.imageView);

        /** Important section:
         * 1: define a Mask (the image)
         * 2:  put the Mask on the item to make it become movable one
         * 3: set OnTouchListener for the movable item to get the action and set destination for it
         */
        // 1: define the Mask
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(IMAGE_WIDTH, IMAGE_HEIGHT);
        // 2: put the Mask on it
        imageView.setLayoutParams(layoutParams);
        // 3: set OnTouchListener
        imageView.setOnTouchListener(new ChoiceTouchListener());
    }

    // declare custom class ChoiceTouchListener implements the OnTouchListener
    private final class ChoiceTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent event) {
            int rawX = (int) event.getRawX();
            int rawY = (int) event.getRawY();
            RelativeLayout.LayoutParams layoutParams;

            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    Log.d(TAG, "MotionEvent.ACTION_DOWN");
                    layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    deltaX = rawX - layoutParams.leftMargin;
                    deltaY = rawY - layoutParams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    Log.d(TAG, "MotionEvent.ACTION_UP");
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    Log.d(TAG, "MotionEvent.ACTION_POINTER_DOWN");
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    Log.d(TAG, "MotionEvent.ACTION_POINTER_UP");
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.d(TAG, "MotionEvent.ACTION_MOVE");
                    layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin = rawX - deltaX;
                    layoutParams.topMargin = rawY - deltaY;
                    layoutParams.rightMargin = -IMAGE_HEIGHT;
                    layoutParams.bottomMargin = -IMAGE_WIDTH;
                    view.setLayoutParams(layoutParams);
                    break;
            }

            // refresh the View
            rootLayout.invalidate();
            return true;
        }
    }
}
