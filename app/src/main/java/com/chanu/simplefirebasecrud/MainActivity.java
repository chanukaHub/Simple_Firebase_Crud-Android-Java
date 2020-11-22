package com.chanu.simplefirebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    EditText txtId,txtName,txtAddress,txtContact;
    Button btnSave,btnShow,btnUpdate,btnDelete;
    DatabaseReference dbReference;
    Student student;

    private void clearInputs(){
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtId =findViewById(R.id.idEditText);
        txtName =findViewById(R.id.nameEditText);
        txtAddress =findViewById(R.id.addressEditText);
        txtContact =findViewById(R.id.contactEditText);

        btnSave = findViewById(R.id.saveButton);
        btnShow = findViewById(R.id.showButton);
        btnUpdate = findViewById(R.id.updateButton);
        btnDelete = findViewById(R.id.deleteButton);

        student= new Student();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dbReference = FirebaseDatabase.getInstance().getReference().child("student");
                    if(TextUtils.isEmpty(txtId.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter an ID",Toast.LENGTH_SHORT);
                    else if(TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter an Name",Toast.LENGTH_SHORT);
                    else if(TextUtils.isEmpty(txtAddress.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please enter an Address",Toast.LENGTH_SHORT);
                    else{
                        student.setId(txtId.getText().toString().trim());
                        student.setName(txtName.getText().toString().trim());
                        student.setAddress(txtAddress.getText().toString().trim());
                        student.setContact(Integer.valueOf(txtContact.getText().toString().trim()));

                        //dbReference.push().setValue(student);
                        dbReference.child("std1").setValue(student);
                        Toast.makeText(getApplicationContext(),"Student Added Successfully",Toast.LENGTH_SHORT).show();
                        clearInputs();
                    }
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Invalid Contact Number",Toast.LENGTH_SHORT);
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbReference= FirebaseDatabase.getInstance().getReference().child("student").child("std1");
                dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()){
                            txtId.setText(snapshot.child("id").getValue().toString());
                            txtName.setText(snapshot.child("name").getValue().toString());
                            txtAddress.setText(snapshot.child("address").getValue().toString());
                            txtContact.setText(snapshot.child("contact").getValue().toString());
                        }else{
                            Toast.makeText(getApplicationContext(),"No Source to display",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbReference= FirebaseDatabase.getInstance().getReference().child("student");
                dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild("std1")){
                            try {
                                student.setId(txtId.getText().toString().trim());
                                student.setName(txtName.getText().toString().trim());
                                student.setAddress(txtAddress.getText().toString().trim());
                                student.setContact(Integer.valueOf(txtContact.getText().toString().trim()));

                                dbReference = FirebaseDatabase.getInstance().getReference().child("student").child("std1");
                                dbReference.setValue(student);

                                clearInputs();
                                Toast.makeText(getApplicationContext(),"Update Successfully", Toast.LENGTH_SHORT).show();
                            }catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Invalid contact Number", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(getApplicationContext(),"No source to update", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}