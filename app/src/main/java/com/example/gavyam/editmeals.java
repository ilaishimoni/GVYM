package com.example.gavyam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class editmeals extends AppCompatActivity {
    Intent gi;
    HelperDB hlp;
    SQLiteDatabase db;
    Cursor crsr;

    EditText first;
    EditText primary;
    EditText addon;
    EditText drink;
    EditText dessert;

    String first_et;
    String primary_et;
    String addon_et;
    String drink_et;
    String dessert_et;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmeals);

        hlp = new HelperDB(this);

        Intent si = getIntent();
        String id = si.getStringExtra("key_id");

        String slc = mealsTable.KEY_ID + "=?";
        String[] selectionArgs = {id};
        db = hlp.getReadableDatabase();
        crsr = db.query(mealsTable.MEALS_TABLE, null, slc, selectionArgs, null, null, null);
        int col1 = crsr.getColumnIndex(mealsTable.FIRST_MEAL);
        int col2 = crsr.getColumnIndex(mealsTable.MAIN_MEAL);
        int col3 = crsr.getColumnIndex(mealsTable.ADD_ON);
        int col4 = crsr.getColumnIndex(mealsTable.DESSERT);
        int col5 = crsr.getColumnIndex(mealsTable.DRINK);

        crsr.moveToFirst();

        first_et = crsr.getString(col1);
        primary_et = crsr.getString(col2);
        addon_et = crsr.getString(col3);
        drink_et = crsr.getString(col4);
        dessert_et = crsr.getString(col5);

        first = (EditText) findViewById(R.id.first_m);
        primary = (EditText) findViewById(R.id.primarym);
        addon = (EditText) findViewById(R.id.addon);
        drink = (EditText) findViewById(R.id.drink);
        dessert = (EditText) findViewById(R.id.dessert);

        first.setText(first_et);
        primary.setText(primary_et);
        addon.setText(addon_et);
        drink.setText(drink_et);
        dessert.setText(dessert_et);

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

    public void updt(View view) {
        String first_check = first.getText().toString();
        String primary_check = primary.getText().toString();
        String dessert_check = dessert.getText().toString();
        String addon_check = addon.getText().toString();
        String drink_check = drink.getText().toString();

        ContentValues cv = new ContentValues();

        cv.put(mealsTable.FIRST_MEAL, first_check);
        cv.put(mealsTable.MAIN_MEAL, primary_check);
        cv.put(mealsTable.DESSERT, dessert_check);
        cv.put(mealsTable.ADD_ON, addon_check);
        cv.put(mealsTable.DRINK, drink_check);


        db = hlp.getWritableDatabase();
        db.update(mealsTable.MEALS_TABLE, cv, mealsTable.FIRST_MEAL+"=?", new String[]{first_et});
        db.update(mealsTable.MEALS_TABLE, cv, mealsTable.MAIN_MEAL+"=?", new String[]{primary_et});
        db.update(mealsTable.MEALS_TABLE, cv, mealsTable.ADD_ON+"=?", new String[]{addon_et});
        db.update(mealsTable.MEALS_TABLE, cv, mealsTable.DESSERT+"=?", new String[]{dessert_et});
        db.update(mealsTable.MEALS_TABLE, cv, mealsTable.DRINK+"=?", new String[]{drink_et});


        db.close();

        Toast.makeText(this, "Data updated successfully ", Toast.LENGTH_SHORT).show();
        finish();
    }
}