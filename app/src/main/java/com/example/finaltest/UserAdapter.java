package com.example.finaltest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

public class UserAdapter extends ArrayAdapter {

    Context mContext;
    int layoutRes;
    List<UserClass> users;
    DatabaseHelper mDatabase;




    public UserAdapter(@NonNull Context mContext, int layoutRes, List<UserClass> users, DatabaseHelper mDatabase) {
        super(mContext,layoutRes,users);
        this.mContext = mContext;
        this.layoutRes = layoutRes;
        this.users = users;
        this.mDatabase = mDatabase;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(layoutRes, null);
        TextView tvFname = v.findViewById(R.id.tv_fname);
        TextView tvLname = v.findViewById(R.id.tv_lname);
        TextView tvadress = v.findViewById(R.id.tv_address);
        TextView tvphone = v.findViewById(R.id.tv_phone);


        final UserClass user = users.get(position);
        tvFname.setText(user.getFname());
        tvLname.setText(user.getLname());
        tvadress.setText(user.getAddress());
        tvphone.setText(String.valueOf(user.getPnumber()));

        v.findViewById(R.id.btn_edit_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser(user);
            }
        });

        v.findViewById(R.id.btn_delete_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(user);

            }
        });

        return  v;

    }


    private void deleteUser(final UserClass user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (mDatabase.deleteUser(user.getId()))
                    loadUsers();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void updateUser(final UserClass user) {
        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.update, null);
        alert.setView(v);
        final AlertDialog alertDialog = alert.create();
        alertDialog.show();

        final EditText etFName = v.findViewById(R.id.editTextFirstName);
        final EditText etLName = v.findViewById(R.id.editTextLastName);
        final EditText etAddress = v.findViewById(R.id.editTextAddress);
        final EditText etPhone = v.findViewById(R.id.editTextPhoneNumber);


        etFName.setText(user.getFname());
        etLName.setText(user.getLname());
        etAddress.setText(user.getAddress());
        etPhone.setText(String.valueOf(user.getPnumber()));


        v.findViewById(R.id.btn_update_employee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etFName.getText().toString().trim();
                String Lname = etFName.getText().toString().trim();
                String addr = etAddress.getText().toString().trim();
                String phn = etPhone.getText().toString().trim();


                if (name.isEmpty()) {
                    etFName.setError(" First Name field is mandatory!");
                    etFName.requestFocus();
                    return;
                }

                if (Lname.isEmpty()) {
                    etLName.setError(" Last Name field is mandatory!");
                    etLName.requestFocus();
                    return;
                }


                if (addr.isEmpty()) {
                    etAddress.setError(" Adress field is mandatory!");
                    etAddress.requestFocus();
                    return;
                }




                if (phn.isEmpty()) {
                    etPhone.setError("Salary field is mandatory!");
                    etPhone.requestFocus();
                    return;
                }

                /*
                String sql = "UPDATE employees SET name = ?, department = ?, salary = ? WHERE id = ?";
                mDatabase.execSQL(sql, new String[]{name, dept, salary, String.valueOf(employee.getId())});
                Toast.makeText(mContext,"employee updated", Toast.LENGTH_SHORT).show();

                 */

                if (mDatabase.updateUser(user.getId(), name, Lname,addr, Double.parseDouble(phn))) {
                    Toast.makeText(mContext,"employee updated", Toast.LENGTH_SHORT).show();
                    loadUsers();
                } else
                    Toast.makeText(mContext,"employee not updated", Toast.LENGTH_SHORT).show();

//                loadEmployees();
                alertDialog.dismiss();
            }
        });
    }


    private  void loadUsers(){


        Cursor cursor = mDatabase.getAllUsers();
        users.clear();
        if(cursor.moveToFirst()){

            do{
                users.add(new UserClass(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getDouble(4)));
            }while (cursor.moveToNext());
            cursor.close();




        }


        notifyDataSetChanged();


    }







}
