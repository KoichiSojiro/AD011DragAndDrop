package com.example.trannh08.ad011draganddrop;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    String message;
    private android.widget.RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData clipData = new ClipData(view.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(imageView);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDragAndDrop(clipData, myShadow, null, 0);
                } else {
                    view.startDrag(clipData, myShadow, null, 0);
                }
                return true;
            }
        });

        imageView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        Log.d(message, "Action is DragEvent.ACTION_DRAG_STARTED");
                        // Do nothing
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(message, "Action is DragEvent.ACTION_DRAG_ENTERED");
                        int x_cord = (int) event.getX();
                        int y_cord = (int) event.getY();
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.d(message, "Action is DragEvent.ACTION_DRAG_EXITED");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        layoutParams.leftMargin = x_cord;
                        layoutParams.topMargin = y_cord;
                        view.setLayoutParams(layoutParams);
                        break;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        Log.d(message, "Action is DragEvent.ACTION_DRAG_LOCATION");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        break;

                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.d(message, "Action is DragEvent.ACTION_DRAG_ENDED");
                        // Do nothing
                        break;

                    case DragEvent.ACTION_DROP:
                        Log.d(message, "ACTION_DROP event");
                        // Do nothing
                        break;
                    default:
                        break;
                }
                return true;
            }
        });


//            Drawable shape = getResources().getDrawable(R.drawable.ic_action_name);
//            @Override
//            public boolean onDrag(View view, DragEvent event) {
//                int action = event.getAction();
//                switch (action) {
//                    case DragEvent.ACTION_DRAG_STARTED:
//                        //do nothing
//                        break;
//                    case DragEvent.ACTION_DRAG_ENTERED:
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                            view.setBackground(shape);
//                        } else {
//                            view.setBackgroundDrawable(shape);
//                        }
//                        break;
//                    case DragEvent.ACTION_DRAG_EXITED:
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                            view.setBackground(shape);
//                        } else {
//                            view.setBackgroundDrawable(shape);
//                        }
//                        break;
//                    case DragEvent.ACTION_DROP:
//                        View myView = (View) event.getLocalState();
//                        ViewGroup owner = (ViewGroup) myView.getParent();
//                        owner.removeView(myView);
//                        RelativeLayout container = (RelativeLayout) myView;
//                        container.addView(myView);
//                        myView.setVisibility(View.VISIBLE);
//                        break;
//                    case DragEvent.ACTION_DRAG_ENDED:
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                            view.setBackground(shape);
//                        } else {
//                            view.setBackgroundDrawable(shape);
//                        }
//                        default:
//                        break;
//                }
//                return true;
//            }
//        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData clipData = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(imageView);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        imageView.startDragAndDrop(clipData, shadowBuilder, imageView, 0);
                    } else {
                        imageView.startDrag(clipData, shadowBuilder, imageView, 0);
                    }
                    imageView.setVisibility(View.INVISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
