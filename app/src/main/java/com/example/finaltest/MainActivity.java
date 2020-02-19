package com.example.finaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper mDatabase;

    EditText editTextFirstName,editTextLastName,editTextPhoneNumber,editTextAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

mDatabase =new DatabaseHelper(this);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextAddress = findViewById(R.id.editTextAddress);

        findViewById(R.id.btnAddUser).setOnClickListener(this);
        findViewById(R.id.tvViewAdded).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnAddUser:
                addUser();
                break;

            case R.id.tvViewAdded:

                Intent intent = new Intent(MainActivity.this,UserActivity.class);
                startActivity(intent);



                break;
        }

    }


    private void addUser(){

        String firstname = editTextFirstName.getText().toString().trim();
        String lastname = editTextLastName.getText().toString().trim();
        String phone = editTextPhoneNumber.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        if(firstname.isEmpty()){
            editTextFirstName.setError("first name is mandatory");
            editTextFirstName.requestFocus();
            return;

        }
        if(lastname.isEmpty()){
            editTextLastName.setError("last name is mandatory");
            editTextLastName.requestFocus();
            return;

        }

        if(phone.isEmpty()){
            editTextPhoneNumber.setError("phone number is mandatory");
            editTextPhoneNumber.requestFocus();
            return;

        }


        if(address.isEmpty()){
            editTextAddress.setError("address is mandatory");
            editTextAddress.requestFocus();
            return;

        }


        if(mDatabase.addUser(firstname,lastname,address,Double.parseDouble(phone)))
            Toast.makeText(this, "User Added", Toast.LENGTH_SHORT).show();

        else
            Toast.makeText(this, "User not Added", Toast.LENGTH_SHORT).show();



    }

    @Override
    protected void onRestart() {
        super.onRestart();
        editTextFirstName.setText("");
        editTextLastName.setText("");
        editTextPhoneNumber.setText("");
        editTextAddress.setText("");
    }
}
