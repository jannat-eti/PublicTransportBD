package com.example.jsonconvert;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAddBusActivity extends AppCompatActivity {

    EditText busNameET;
    Button addBusBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_bus);

        init();
    }

    private void init() {

        busNameET = (EditText) findViewById(R.id.bus_name_ET);
        addBusBTN = (Button) findViewById(R.id.add_bus_BTN);

        addBusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String busName = busNameET.getText().toString();
                Bus bus = new Bus();
                bus.setBusName(busName);

                //   BusDbHelper busDbHelper = new BusDbHelper(AdminActivity.this);
                //  busDbHelper.insertData(bus);


                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Bus/All");
                reference.child(busName).setValue(bus);

                Toast.makeText(AdminAddBusActivity.this, "Bus added successfully", Toast.LENGTH_SHORT).show();
                busNameET.setText("");
            }
        });
    }
}
