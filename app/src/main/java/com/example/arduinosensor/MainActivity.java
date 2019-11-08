package com.example.arduinosensor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private TextView dataView;
    private Button changeData;

    // Get Instance of Firebase Database
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    // Get instance of Firebase Database Parent Node
    private DatabaseReference mRootReference = firebaseDatabase.getReference();

    // Get instance of Firebase Database Child Node "message"
    private DatabaseReference messageNode = mRootReference.child("message");

    // Get instance of Firebase Database Child Node "data"
    private DatabaseReference dataNode = mRootReference.child("data");


    // Test comment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TextView
        dataView = findViewById(R.id.dataView);

        // Button
        changeData = findViewById(R.id.btnChangeData);

        // Event listener for button, when pressed execute
        // the code inside onClick()
        changeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Allows us to change the value of the child node in the database
                // mChildReference.setValue("Changed value");

                // Update the value of data child node on Firebase
                // with a generated random number
                Random rand = new Random();
                int randomNumber = rand.nextInt(11);
                dataNode.setValue(randomNumber);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        messageNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String message = dataSnapshot.getValue(String.class);
                dataView.setText(dataSnapshot.getKey() + ": " + message);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dataNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
