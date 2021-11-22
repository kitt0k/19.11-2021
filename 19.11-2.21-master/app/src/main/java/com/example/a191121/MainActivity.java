package com.example.a191121;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> users = new ArrayList<String>();
    ArrayList<String> selectedUsers = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView usersList;
    EditText LastName,FirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Collections.addAll(users, "Фамилия: Кто-то \nИмя: Кто-то ");
        LastName = findViewById(R.id.LastName);
        FirstName = findViewById(R.id.FirstName);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, users);
        usersList = findViewById(R.id.usersList);
        usersList.setAdapter(adapter);

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                String user = adapter.getItem(position);
                if(usersList.isItemChecked(position)) {
                    selectedUsers.add(user);

                }
                else {
                    selectedUsers.remove(user);
                }

            }
        });
    }

    public void add(View view){

        EditText LastName = findViewById(R.id.LastName);
        EditText FirstName = findViewById(R.id.FirstName);
        String user = LastName.getText().toString();
        String user1 = FirstName.getText().toString();
        if(!user.isEmpty())
        {
            adapter.add("Фамилия: " + user + "\nИмя: " + user1);
            LastName.setText("");
            FirstName.setText("");
            adapter.notifyDataSetChanged();
        }
    }
    public void delete(View view){
        for(int i=0; i< selectedUsers.size();i++){
            adapter.remove(selectedUsers.get(i));
        }
        usersList.clearChoices();
        selectedUsers.clear();

        adapter.notifyDataSetChanged();
    }

}