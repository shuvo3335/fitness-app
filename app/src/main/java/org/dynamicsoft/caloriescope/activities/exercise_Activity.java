package org.dynamicsoft.caloriescope.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import org.dynamicsoft.caloriescope.R;
import org.dynamicsoft.caloriescope.exercise.Chest;
import org.dynamicsoft.caloriescope.exercise.Hip_butt;
import org.dynamicsoft.caloriescope.exercise.LegThigh;
import org.dynamicsoft.caloriescope.exercise.Shoulder;
import org.dynamicsoft.caloriescope.exercise.abs;
import org.dynamicsoft.caloriescope.exercise.arms;
import org.dynamicsoft.caloriescope.exercise.back;

import static org.dynamicsoft.caloriescope.activities.MainActivity.i10;
import static org.dynamicsoft.caloriescope.activities.MainActivity.i2;
import static org.dynamicsoft.caloriescope.activities.MainActivity.i3;
import static org.dynamicsoft.caloriescope.activities.MainActivity.i4;
import static org.dynamicsoft.caloriescope.activities.MainActivity.i5;
import static org.dynamicsoft.caloriescope.activities.MainActivity.i6;
import static org.dynamicsoft.caloriescope.activities.MainActivity.i7;
import static org.dynamicsoft.caloriescope.activities.MainActivity.i8;

public class exercise_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SensorEventListener
{
    public GridView exercise_grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_with_drawer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //To set Person's name in Nav Drawer
        SharedPreferences pref = getApplicationContext().getSharedPreferences("AppData", 0);
        TextView NavDrawerUserString = navigationView.getHeaderView(0).findViewById(R.id.NavDrawerUserString);
        NavDrawerUserString.setText(pref.getString("UserName", "Welcome"));

        //Handling heart rate activities visibility, this chunk of code must exist in each activity!
        SensorManager mSensorManager;
        Menu nav_Menu = navigationView.getMenu();
        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE) != null) {
            nav_Menu.findItem(R.id.nav_heart_rate).setVisible(true);
        } else {
            nav_Menu.findItem(R.id.nav_heart_rate_camera).setVisible(true);
        }

        exercise_grid = findViewById(R.id.exercise_view);
        final String [] exercise_list = getResources().getStringArray(R.array.exercise_name);
        ArrayAdapter <String> gridAdapter = new ArrayAdapter<String>(this,R.layout.grid_viewing,
                                                        R.id.exercise_text,exercise_list);
        exercise_grid.setAdapter(gridAdapter);
        exercise_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position==0)
                {
                    Intent intent = new Intent(exercise_Activity.this, abs.class);
                    startActivity(intent);
                }
                if (position==1)
                {
                    Intent arms_intent = new Intent(exercise_Activity.this, arms.class);
                    startActivity(arms_intent);
                }
                if (position==2)
                {
                    Intent back_intent = new Intent(exercise_Activity.this, back.class);
                    startActivity(back_intent);
                }
                if (position==3)
                {
                    Intent chest_intent = new Intent(exercise_Activity.this, Chest.class);
                    startActivity(chest_intent);
                }
                if (position==4)
                {
                    Intent legIntent = new Intent(exercise_Activity.this, LegThigh.class);
                    startActivity(legIntent);
                }
                if (position==5)
                {
                    Intent hipsIntent = new Intent( exercise_Activity.this, Hip_butt.class);
                    startActivity(hipsIntent);
                }
                if (position==6)
                {
                    Intent shoulderIntent = new Intent(exercise_Activity.this, Shoulder.class);
                    startActivity(shoulderIntent);
                }
            }
        });
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
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_exit) {
            exercise_Activity.this.moveTaskToBack(true);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            super.onBackPressed();
            //startActivity(i0);finish();
        } /*else if (id == R.id.nav_news) {
            startActivity(i1);
            finish();
        }*/ else if (id == R.id.nav_about) {
            startActivity(i2);
            finish();
        } else if (id == R.id.nav_settings) {
            startActivity(i3);
            finish();
        } else if (id == R.id.nav_calculator) {
            startActivity(i4);
            finish();
        } else if (id == R.id.nav_heart_rate) {
            startActivity(i5);
            finish();
        } else if (id == R.id.nav_heart_rate_camera) {
            startActivity(i6);
            finish();
        } else if (id == R.id.nav_hearing_wellbeing) {
            startActivity(i7);
            finish();
        } /*else if (id == R.id.nav_videos) {
            startActivity(i8);
            finish();
        }*/
        else if (id == R.id.nav_exercise){
        }
        else if (id==R.id.nav_maps)
        {
            startActivity(i10);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
