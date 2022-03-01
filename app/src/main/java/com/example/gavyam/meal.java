package com.example.gavyam;

import static com.example.gavyam.workersTable.WORKERS_TABLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class meal extends AppCompatActivity {
    Intent gi;

    EditText first;
    EditText primary;
    EditText addon;
    EditText dessert;
    EditText drink;

    SQLiteDatabase db;
    HelperDB hlp;

    /**
     occurs during app creation, requests access to database and asks for data based on user's previous click
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();



        first = (EditText) findViewById(R.id.First);
        primary = (EditText) findViewById(R.id.Primary);
        addon = (EditText) findViewById(R.id.AddOn);
        dessert = (EditText) findViewById(R.id.Dessert);
        drink = (EditText) findViewById(R.id.Drink);
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

    public void insert(View view) {

        String first_et = first.getText().toString();
        String primary_et = primary.getText().toString();
        String addon_et = addon.getText().toString();
        String dessert_et = dessert.getText().toString();
        String drink_et = drink.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put(mealsTable.FIRST_MEAL, first_et);
        cv.put(mealsTable.MAIN_MEAL, primary_et);
        cv.put(mealsTable.ADD_ON, addon_et);
        cv.put(mealsTable.DESSERT, dessert_et);
        cv.put(mealsTable.DRINK, drink_et);


        db = hlp.getWritableDatabase();
        db.insert(mealsTable.MEALS_TABLE, null, cv);
        db.close();
        Toast.makeText(this, "Your information has been received", Toast.LENGTH_SHORT).show();

        first.setText("");
        primary.setText("");
        addon.setText("");
        dessert.setText("");
        drink.setText("");

    }


    public void showdata(View view) {
        gi = new Intent(this, viewMeal.class);
        startActivity(gi);
    }
}