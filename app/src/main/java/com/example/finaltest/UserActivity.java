package com.example.finaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {


    DatabaseHelper mDatabase;
    List<UserClass> userlist;
    ListView listView;
    SearchView search;
    List<UserClass> filter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        search = findViewById(R.id.search);
        listView = findViewById(R.id.lvUser);
        userlist = new ArrayList<>();
        filter = new ArrayList<>();

        mDatabase = new DatabaseHelper(this);

loadUsers();


     search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
         @Override
         public boolean onQueryTextSubmit(String query) {
             return false;
         }

         @Override
         public boolean onQueryTextChange(String newText) {


             if(!newText.isEmpty()){
                 filter.clear();

                 for(int i = 0; i< userlist.size();i++){
                     UserClass userClass = userlist.get(i);
                     if(userClass.fname.contains(newText)){
                         filter.add(userClass);
                     }


                 }
                 UserAdapter userAdapter = new UserAdapter(UserActivity.this, R.layout.list_layout_user, filter, mDatabase);
                 listView.setAdapter(userAdapter);

             }
             return false;
         }
     });




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
