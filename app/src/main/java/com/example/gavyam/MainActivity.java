package com.example.gavyam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * @author		Ilai Shimnoi ilaishimoni@gmail.com
 * @version	    1.0
 * @since		12/2/22
 * My application gets information about state of workers at GavYam and stores data in database
 */

public class MainActivity extends AppCompatActivity {
    Intent gi;
    SQLiteDatabase db;
    HelperDB hlp;

    /**
     * connects java and xml files
     * occurs at app creation
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
    }

    /**
     * inflates and creates OptionsMenu
     * @return	method creates optionsMenu and xml file
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.optionsmenu, menu);

        return true;
    }
    /**
     * gets info from user's choice and sends him to the right activity
     */

    public boolean onOptionsItemSelected(MenuItem item){
        String st = item.getTitle().toString();
        if(st.equals("worker")){
            gi = new Intent(this, workerss.class);
            startActivity(gi);
        }
        if(st.equals("company")){
            gi = new Intent(this, company.class);
            startActivity(gi);

        }
        if(st.equals("credits")){
            gi = new Intent(this, credits.class);
            startActivity(gi);
        }
        if(st.equals("Go Back")){
            finish();
        }
        if(st.equals("Home screen")){
            gi = new Intent(this, MainActivity.class);
            startActivity(gi);
        }



        return true;
    }
    /**
     * user goes one activity back
     */

    public void exit(View view) {
        finish();
    }

    /**
     * user is sent to "order" activity
     */

    public void order(View view) {
        gi = new Intent(this, tables.class);
        startActivity(gi);
    }

    /**
     * user is sent to "meal" activity
     */
    public void meal(View view) {
        gi = new Intent(this, meal.class);
        startActivity(gi);
    }
}