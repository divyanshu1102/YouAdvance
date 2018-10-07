package com.example.divyanshusharma.youadvance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class allMilestones extends AppCompatActivity {

    ArrayList<Milestone> storedMilestones = new ArrayList<Milestone>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_milestones);

        try {
            storedMilestones = (ArrayList<Milestone>) getIntent().getSerializableExtra("storedMilestones");
        }
        catch (Exception e){

        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addNewMilestone);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent myIntent = new Intent(allMilestones.this, AddMilestone.class);
                myIntent.putExtra("storedMilestones", storedMilestones); //Optional parameters
                allMilestones.this.startActivity(myIntent);

            }
        });
    }

}

