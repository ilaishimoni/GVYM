package com.example.gavyam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class viewMeal extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    Intent gi;
    HelperDB hlp;
    SQLiteDatabase db;
    ListView lvrecords;
    ArrayList<String> tbl;
    ArrayList<String> card_int;
    ArrayAdapter adp;
    Cursor crsr;

    String first;
    String primary;
    String addon;
    String dessert;
    String drink;
    String kid;


    Spinner spn;

    String [] snf = {"","alphabetic_reverse (first meal)", "cola only", "chicken only"};

    int pos;


    @Override
    protected void onResume() {
        super.onResume();

        hlp=new HelperDB(this);

        spn = (Spinner) findViewById(R.id.spin);
        ArrayAdapter<String> spna = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, snf);
        spn.setAdapter(spna);
        spn.setOnItemSelectedListener(this);





        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        hlp=new HelperDB(this);
        db=hlp.getReadableDatabase();
        db.close();



        lvrecords = (ListView) findViewById(R.id.lvrecords);
        lvrecords.setOnItemClickListener(this);
        lvrecords.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        db=hlp.getWritableDatabase();
        tbl = new ArrayList<>();
        card_int = new ArrayList<>();

        crsr = db.query(mealsTable.MEALS_TABLE, null, null, null, null, null, null, null);
        int col1 = crsr.getColumnIndex(mealsTable.FIRST_MEAL);
        int col2 = crsr.getColumnIndex(mealsTable.MAIN_MEAL);
        int col3 = crsr.getColumnIndex(mealsTable.ADD_ON);
        int col4 = crsr.getColumnIndex(mealsTable.DESSERT);
        int col5 = crsr.getColumnIndex(mealsTable.DRINK);
        int col6 = crsr.getColumnIndex(workersTable.KEY_ID);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            first = crsr.getString(col1);
            primary = crsr.getString(col2);
            addon = crsr.getString(col3);
            dessert = crsr.getString(col4);
            drink = crsr.getString(col5);
            kid = crsr.getString(col6);



            String tmp = "" + first + ", " + primary + ", " + addon + ", " + dessert + ", " + drink;
            card_int.add(kid);
            tbl.add(tmp);
            crsr.moveToNext();
        }
        crsr.close();
        db.close();
        adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tbl);
        adp.notifyDataSetChanged();
        lvrecords.setAdapter(adp);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_meal);


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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        pos = position;
        gi = new Intent(this, editmeals.class);
        String rlv = card_int.get(position);
        gi.putExtra("key_id", rlv);
        startActivity(gi);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();


        try{

            //alphabetic reverse (first meal)
            if (position == 1){
                db=hlp.getReadableDatabase();
                String[] columns = null;
                String selection = null;
                String[] selectionArgs = null;
                String groupBy = null;
                String having = null;
                String orderBy = mealsTable.FIRST_MEAL+" DESC";
                String limit = null;

                db.query(mealsTable.MEALS_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(mealsTable.FIRST_MEAL);
                int col2 = crsr.getColumnIndex(mealsTable.MAIN_MEAL);
                int col3 = crsr.getColumnIndex(mealsTable.ADD_ON);
                int col4 = crsr.getColumnIndex(mealsTable.DESSERT);
                int col5 = crsr.getColumnIndex(mealsTable.DRINK);
                int col6 = crsr.getColumnIndex(workersTable.KEY_ID);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    first = crsr.getString(col1);
                    primary = crsr.getString(col2);
                    addon = crsr.getString(col3);
                    dessert = crsr.getString(col4);
                    drink = crsr.getString(col5);
                    kid = crsr.getString(col6);



                    String tmp = "" + first + ", " + primary + ", " + addon + ", " + dessert + ", " + drink;
                    card_int.add(kid);
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tbl);
                adp.notifyDataSetChanged();
                lvrecords.setAdapter(adp);

            }
            if (position == 2){



                db=hlp.getReadableDatabase();
                String[] columns = null;
                String selection = mealsTable.DRINK;
                String[] selectionArgs = {"cola"};
                String groupBy = null;
                String having = null;
                String orderBy = null;
                String limit = null;

                db.query(mealsTable.MEALS_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(mealsTable.FIRST_MEAL);
                int col2 = crsr.getColumnIndex(mealsTable.MAIN_MEAL);
                int col3 = crsr.getColumnIndex(mealsTable.ADD_ON);
                int col4 = crsr.getColumnIndex(mealsTable.DESSERT);
                int col5 = crsr.getColumnIndex(mealsTable.DRINK);
                int col6 = crsr.getColumnIndex(workersTable.KEY_ID);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    first = crsr.getString(col1);
                    primary = crsr.getString(col2);
                    addon = crsr.getString(col3);
                    dessert = crsr.getString(col4);
                    drink = crsr.getString(col5);
                    kid = crsr.getString(col6);



                    String tmp = "" + first + ", " + primary + ", " + addon + ", " + dessert + ", " + drink;
                    card_int.add(kid);
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tbl);
                adp.notifyDataSetChanged();
                lvrecords.setAdapter(adp);

            }
            if (position == 3){
                db=hlp.getReadableDatabase();
                String[] columns = null;
                String selection = mealsTable.MAIN_MEAL;
                String[] selectionArgs = {"chicken"};
                String groupBy = null;
                String having = null;
                String orderBy = null;
                String limit = null;

                db.query(mealsTable.MEALS_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(mealsTable.FIRST_MEAL);
                int col2 = crsr.getColumnIndex(mealsTable.MAIN_MEAL);
                int col3 = crsr.getColumnIndex(mealsTable.ADD_ON);
                int col4 = crsr.getColumnIndex(mealsTable.DESSERT);
                int col5 = crsr.getColumnIndex(mealsTable.DRINK);
                int col6 = crsr.getColumnIndex(workersTable.KEY_ID);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    first = crsr.getString(col1);
                    primary = crsr.getString(col2);
                    addon = crsr.getString(col3);
                    dessert = crsr.getString(col4);
                    drink = crsr.getString(col5);
                    kid = crsr.getString(col6);



                    String tmp = "" + first + ", " + primary + ", " + addon + ", " + dessert + ", " + drink;
                    card_int.add(kid);
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tbl);
                adp.notifyDataSetChanged();
                lvrecords.setAdapter(adp);

            }

        }
        catch (Exception ex){

        }


    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void back(View view) {
        gi = new Intent(this, meal.class);
        startActivity(gi);
    }
}