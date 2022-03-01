package com.example.gavyam;

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

public class company extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;

    EditText tax;
    EditText company;
    EditText primary;
    EditText secondary;

    Intent gi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        tax = (EditText) findViewById(R.id.First);
        company = (EditText) findViewById(R.id.Primary);
        primary = (EditText) findViewById(R.id.AddOn);
        secondary = (EditText) findViewById(R.id.Dessert);

    }

    public void move_data(View view) {

        ContentValues cv = new ContentValues();
        cv.put(companiesTable.TAX_SERIAL, tax.getText().toString());
        cv.put(companiesTable.COMPANY_NAME, company.getText().toString());
        cv.put(companiesTable.PRIMARY_PHONE, primary.getText().toString());
        cv.put(companiesTable.SECONDARY_PHONE, secondary.getText().toString());
        cv.put(companiesTable.IS_ACTIVE, 1);


        db = hlp.getWritableDatabase();
        db.insert(companiesTable.COMPANIES_TABLE ,null, cv);
        db.close();

        Toast.makeText(this, "Data received successfully", Toast.LENGTH_SHORT).show();

        tax.setText("");
        company.setText("");
        primary.setText("");
        secondary.setText("");



    }

    public void showdata(View view) {
        gi = new Intent(this, viewComapnies.class);
        startActivity(gi);
    }

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