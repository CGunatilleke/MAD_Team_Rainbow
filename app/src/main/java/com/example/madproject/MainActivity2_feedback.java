package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2_feedback extends AppCompatActivity {

    EditText etMessage;
    Button btnSubmit, btnViewFB;

    DatabaseReference dbRef;
    Feedback fbObj;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_feedback);

        etMessage = findViewById(R.id.etMessage);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnViewFB = findViewById(R.id.btnViewFB);



        dbRef = FirebaseDatabase.getInstance().getReference().child("Feedback");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertFeedbackData();
            }
        });

        btnViewFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity2_feedback.this,viewFeedbacks.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });

    }

    public void ClearControls() {
        etMessage.setText("");

    }

    public void InsertFeedbackData() {
          String message= etMessage.getText().toString();
          Feedback fbObj= new Feedback(message);

        try {
            if (TextUtils.isEmpty(etMessage.getText().toString())) {
                Toast.makeText(getApplicationContext(), "please add some feedback", Toast.LENGTH_SHORT).show();

            } else {
                fbObj.setMessage(etMessage.getText().toString().trim());

                dbRef.push().setValue(fbObj);
                // dbRef.child("Book1").setValue(bkObj);
                Toast.makeText(getApplicationContext(), "Data inserted Successfully", Toast.LENGTH_SHORT).show();
               // dbRef.child(String.valueOf(maxId+1)).setValue(fbObj);

                ClearControls();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}