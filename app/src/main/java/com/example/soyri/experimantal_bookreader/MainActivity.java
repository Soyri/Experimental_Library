package com.example.soyri.experimantal_bookreader;

import android.app.ActionBar;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TouchEventView touchEventView;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView textview_bookText = findViewById(R.id.textView_bookText_id);
        textview_bookText.setText("You should take position of the view with View.getX() and View.getY() to get x and y of the upper left corner and also assuming You know the radius (or able to obtain width/height of the view to determine radius). After that, obtain xTouch and yTouch using MotionEvent.getX() and MotionEvent.getY() and check if:\n" +
                "\n" +
                "double centerX = x + radius;\n" +
                "double centerY = y + radius;\n" +
                "double distanceX = xTouch - centerX;\n" +
                "double distanceY = yTouch - centerY;\n" +
                "\n" +
                "boolean isInside() {\n" +
                "    return (distanceX * distanceX) + (distanceY * distanceY) <= radius * radius;\n" +
                "}The formula is just interpretation of schools geometry for determining if dot is inside circle area or not. Refer to circle equation for Cartesian coordinates for more details.\n" +
                "\n" +
                "Values explanation is:\n" +
                "\n" +
                "(x + radius) and (y + radius) is the center of circle.\n" +
                "\n" +
                "(xTouch - (x + radius)) is distance from touch point to center by X.\n" +
                "\n" +
                "(yTouch - (y + radius)) is distance from touch point to center by Y." );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



public class TouchEventView extends View {
    GestureDetector gestureDetector;
    Context context;
    Window window;

    public TouchEventView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, new GestureListener());
        this.context = context;

    }


    private class GestureListener implements GestureDetector.OnGestureListener {



        @Override
        public boolean onDown(MotionEvent e) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            int leftOnDownTouchZone_End= 100;
            int rightOnDowTouchZone_Start = width - 100;
            int onDownTouchZone = 0;
            boolean isInsideTouchZone = (onDownTouchZone <= leftOnDownTouchZone_End || onDownTouchZone >= rightOnDowTouchZone_Start);
            if (isInsideTouchZone) {
                //Swipper Library???
                return true;
            }
        return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            // If the Android version is lower than Jellybean, use this call to hide
            // the status bar.
            if (Build.VERSION.SDK_INT < 16) {
                window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
            else {
                View touchEventView = MainActivity.this.getWindow().getDecorView();
                // Hide the status bar.
                touchEventView .setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
                // Remember that you should never show the action bar if the
                // status bar is hidden, so hide that too if necessary.
                ActionBar actionBar = getActionBar();
                assert actionBar != null;
                actionBar.hide();}
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }
}
}