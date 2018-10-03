package com.example.asangaindunil.study;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asangaindunil.study.db.Constructor;
import com.example.asangaindunil.study.db.SessionDbHelper;
import com.example.asangaindunil.study.fragment.SessionViewFragment;

import java.util.Calendar;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
    SessionDbHelper sessionDbHelper;
    Button button;
    EditText name, description, from, to, complete;
    String namet, descriptiont, fromt, tot, completet, dayt;
    Calendar calendar;

    Button viewBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewBt = findViewById(R.id.button2);

        viewBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SessionViewFragment.class);
                startActivity(intent);
            }
        });

    }
    public void addinfo(View view)
    {
        sessionDbHelper = new SessionDbHelper(this);

        SQLiteDatabase db = sessionDbHelper.getWritableDatabase();


        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        complete = findViewById(R.id.work);
        calendar =Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        namet = name.getText().toString();
        descriptiont = description.getText().toString();
        fromt = from.getText().toString();
        tot =to.getText().toString();
        completet = complete.getText().toString();


        ContentValues values = new ContentValues();
        values.put(Constructor.Session.Col_1, namet);
        values.put(Constructor.Session.Col_2, descriptiont);
        values.put(Constructor.Session.Col_3, fromt);
        values.put(Constructor.Session.Col_4, tot);
        values.put(Constructor.Session.Col_5, completet);
        values.put(Constructor.Session.Col_6, day);


        long newRowID = db.insert(Constructor.Session.TABLE_NAME,null,values);

        if (newRowID != -1)
        {
            Toast.makeText(getApplicationContext(),"Inserted ", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Inserted  Failed", Toast.LENGTH_SHORT).show();
        }



    }
}
