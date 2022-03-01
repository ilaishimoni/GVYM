package com.example.gavyam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class tables extends AppCompatActivity {
    Intent gi;

    /**
     occurs during app creation, requests access to database and asks for data based on user's previous click
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
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
}