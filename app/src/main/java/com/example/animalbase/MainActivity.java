package com.example.animalbase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    };

    private Animal currentAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAddButton();

    }

        private void initAddButton() {
            Button AddButton = findViewById(R.id.AddButton);
            AddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, AddActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }
    public void onResume() {
        super.onResume();
        ArrayList<Animal> animals;
            AnimalDataSource ds = new AnimalDataSource(this);
            try {
                ds.open();
                animals = ds.getAnimals();
                ds.close();
                RecyclerView animalList = findViewById(R.id.rvAnimals);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                animalList.setLayoutManager(layoutManager);
                AnimalAdapter animalAdapter = new AnimalAdapter(animals);
                animalAdapter.setOnClickListener(onClickListener);
                animalList.setAdapter(animalAdapter);
            } catch (Exception e) {
                Toast.makeText(this, "Error retrieving animal list", Toast.LENGTH_LONG).show();
            }

    }}






