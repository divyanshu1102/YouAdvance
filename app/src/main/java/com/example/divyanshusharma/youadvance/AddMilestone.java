package com.example.divyanshusharma.youadvance;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class AddMilestone extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TagAdapter mAdapter;

    private TextView startDate, endDate;
    private Switch today;

    private Calendar mcalendar;
    private int day,month,year;

    private Milestone newMilestone;

    String[] tags={"leadership", "team player", "personal development", "skill development", "error or issue resolution", "decision-making", "creativity and innovation", "delegation", "empowerment", "create your own"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_milestone);

        final ArrayList<Milestone> milestones =  (ArrayList<Milestone>)getIntent().getSerializableExtra("storedMilestones");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new TagAdapter(getApplicationContext(), tags);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        startDate = (TextView) findViewById(R.id.startDate);
        endDate = (TextView) findViewById(R.id.endDate);

        today= (Switch) findViewById(R.id.today);

        //mdob_et=(EditText)findViewById(R.id.dob_et);

        mcalendar= Calendar.getInstance();
        day=mcalendar.get(Calendar.DAY_OF_MONTH);
        year=mcalendar.get(Calendar.YEAR);
        month=mcalendar.get(Calendar.MONTH);

        today.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b==true)
                {
                    // set start and end unclickable
                    // get today's date and set the start and end to that time

                    startDate.setClickable(false);
                    endDate.setClickable(false);

                    SimpleDateFormat mdformat = new SimpleDateFormat("MM/dd/yyyy");
                    String strDate = mdformat.format(mcalendar.getTime());
                    startDate.setText(strDate);
                    endDate.setText(strDate);
                }
                else
                {
                    //set them clickable
                    startDate.setClickable(true);
                    endDate.setClickable(true);
                }
            }
        });

        startDate.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             DateDialog();
                                         }
                                     });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateDialog1();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.saveMilestone);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MultiAutoCompleteTextView milestoneText= (MultiAutoCompleteTextView) findViewById(R.id.milestoneText);
                String enteredText= milestoneText.getText().toString();

                Date start= null, end=null;
                try {
                     start=new SimpleDateFormat("MM/dd/yyyy").parse(startDate.getText().toString());
                     end=new SimpleDateFormat("MM/dd/yyyy").parse(endDate.getText().toString());
                } catch (ParseException e) {
                    Toast.makeText(AddMilestone.this,"Error: Str to Date",Toast.LENGTH_SHORT).show();
                    return;
                }

                Set<Integer> taggedPositions= mAdapter.getTaggedPositions();

                Milestone newMilestone= new Milestone(start, end, enteredText, taggedPositions);
                ArrayList<Milestone> tempMilestones= new ArrayList<Milestone>(milestones);
                tempMilestones.add(newMilestone);

                Intent myIntent = new Intent(AddMilestone.this, allMilestones.class);
                myIntent.putExtra("storedMilestones", tempMilestones);
                AddMilestone.this.startActivity(myIntent);

            }
        });


        
    }

    public void DateDialog(){
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                startDate.setText(monthOfYear + "/" +dayOfMonth  + "/" + year);
            }};
        DatePickerDialog dpDialog=new DatePickerDialog(AddMilestone.this, listener, year, month, day);
        dpDialog.show();
    }

    public void DateDialog1(){
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                endDate.setText(monthOfYear + "/" +dayOfMonth  + "/" + year);
            }};
        DatePickerDialog dpDialog=new DatePickerDialog(AddMilestone.this, listener, year, month, day);
        dpDialog.show();
    }
}
