package com.example.jsonconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jsonconvert.model.ticketBookingModel;
import com.example.jsonconvert.testPackage.testPage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikhaellopez.lazydatepicker.LazyDatePicker;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import ru.slybeaver.slycalendarview.SlyCalendarDialog;

public class Ticket_Booking_Home extends AppCompatActivity {

    DatabaseReference mdatabase;
    Button mBooked;
    EditText from_location_ET,to_location_ET,time_ET;
    LazyDatePicker date_ET ;
    String date  , bus_name;
    String amPmChekcer  ;
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_booking_home);

        mdatabase = FirebaseDatabase.getInstance().getReference();
        from_location_ET=findViewById(R.id.from_location_ET);
        to_location_ET=findViewById(R.id.to_location_ET);
        date_ET = findViewById(R.id.lazyDatePicker);
        time_ET = findViewById(R.id.time_ET);

        getSupportActionBar().hide();

        ViewResultActivity activity =new ViewResultActivity();

        Intent o = getIntent();
        bus_name = o.getStringExtra("bus") ;

        from_location_ET.setText(activity.fromDistance);
        to_location_ET.setText(activity.toDistance);

        date_ET.setOnDatePickListener(new LazyDatePicker.OnDatePickListener() {
            @Override
            public void onDatePick(Date dateSelected) {

              //  LazyDatePicker.dateToString(dateSelected, DATE_FORMAT) ;

                date = LazyDatePicker.dateToString(dateSelected, DATE_FORMAT) ;


                TimePickerDialog timePickerDialog = new TimePickerDialog(Ticket_Booking_Home.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                        if (i >= 12) {

                            amPmChekcer = "PM";

                        } else {

                            amPmChekcer = "AM";

                        }

                        time_ET.setText(i + ":" + i1 + " " + amPmChekcer);

                    }
                }, 0, 0, false);

                timePickerDialog.show();


            }
        });


        mBooked = findViewById(R.id.booked);

        mBooked.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String uiD=FirebaseAuth.getInstance().getUid();
                ticketBookingModel modeAl = new ticketBookingModel();
                if (!TextUtils.isEmpty(date)&& !TextUtils.isEmpty(from_location_ET.getText())&&
                        !TextUtils.isEmpty(to_location_ET.getText())&& !TextUtils.isEmpty(time_ET.getText())){
//                    model.setDate_ET(date.toString());
//                    model.setFrom_location_ET(from_location_ET.getText().toString());
//                    model.setTo_location_ET(to_location_ET.getText().toString());
//                    model.setTime_ET(time_ET.getText().toString());
//                    Toast.makeText(getApplicationContext(), "Ticket booked", Toast.LENGTH_SHORT).show();
//                    mdatabase.child("tickets").child(uiD).push().setValue(model);
//                    from_location_ET.setText("");
//                    to_location_ET.setText("");
//                    time_ET.setText("");

                    Intent o = new Intent(getApplicationContext() , testPage.class);
                    o.putExtra("from" , from_location_ET.getText().toString()) ;
                    o.putExtra("to" , to_location_ET.getText().toString()) ;
                    o.putExtra("bus" , bus_name) ;
                    o.putExtra("date" , date) ;
                    o.putExtra("time" , time_ET.getText().toString()) ;
                    startActivity(o);


                }
                else {
                    Toast.makeText(getApplicationContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}
