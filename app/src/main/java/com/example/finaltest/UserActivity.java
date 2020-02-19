package com.example.finaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {


    DatabaseHelper mDatabase;
    List<UserClass> userlist;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        listView = findViewById(R.id.lvUser);
        userlist = new ArrayList<>();

        mDatabase = new DatabaseHelper(this);

loadUsers();

    }

    private  void loadUsers() {


        Cursor cursor = mDatabase.getAllUsers();

        if (cursor.moveToFirst()) {

            do {
                userlist.add(new UserClass(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4)));
            } while (cursor.moveToNext());
            cursor.close();


            UserAdapter userAdapter = new UserAdapter(this, R.layout.list_layout_user, userlist, mDatabase);
            listView.setAdapter(userAdapter);

        }

    }
}
