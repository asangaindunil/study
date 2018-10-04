package com.example.asangaindunil.study.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.asangaindunil.study.R;

public class ViewSessionCard extends AppCompatActivity {
    TextView name, desc, from, to, complete;
    Integer comtext;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_session_card);
        name = findViewById(R.id.vname);
        desc = findViewById(R.id.vdescription);
        from = findViewById(R.id.vfrom);
        to   = findViewById(R.id.uto);
        complete = findViewById(R.id.vwork);
        progressBar = findViewById(R.id.progressBar);

        Bundle extras = getIntent().getExtras();
        name.setText(extras.getString("LessonName"));
        desc.setText(extras.getString("Desc"));
        from.setText(extras.getString("From"));
        to.setText(extras.getString("To"));
        complete.setText(extras.getInt("Comp") + "%");

        comtext = extras.getInt("Comp");
        progressBar.setProgress(comtext);







    }
}
