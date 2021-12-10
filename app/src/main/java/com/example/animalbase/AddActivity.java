package com.example.animalbase;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


public class AddActivity extends AppCompatActivity {
    private Animal currentAnimal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        currentAnimal = new Animal();
        initSaveButton();
        setForEditing(true);
        initTextChangeEvents();
    }

    private void setForEditing(boolean enabled) {
            EditText editSpecies = findViewById(R.id.editSpecies);
            EditText editHabitat = findViewById(R.id.editHabitat);
            EditText editColor = findViewById(R.id.editColor);
            Button buttonSave = findViewById(R.id.buttonSave);

            editSpecies.setEnabled(enabled);
            editHabitat.setEnabled(enabled);
            editColor.setEnabled(enabled);
            buttonSave.setEnabled(enabled);

        }

    private void initSaveButton() {
        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                AnimalDataSource ds = new AnimalDataSource(AddActivity.this);
                try {
                    ds.open();
                    if (currentAnimal.getAnimalID() == -1) {
                        wasSuccessful = ds.insertAnimal(currentAnimal);
                        if(wasSuccessful) {
                            int newId = ds.getLastAnimalID();
                            currentAnimal.setAnimalID(newId);
                        }
                    } else {
                        wasSuccessful = ds.updateAnimal(currentAnimal);
                    }
                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }
                if (wasSuccessful) {
                    setForEditing(false);
                }

            }
        });
    }




    private void initTextChangeEvents() {
        EditText speciesEdit = findViewById(R.id.editSpecies);
        speciesEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentAnimal.setSpecies(speciesEdit.getText().toString());
                currentAnimal.setAnimalID(-1);
            }
        });
        EditText habitatEdit = findViewById(R.id.editHabitat);
        habitatEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentAnimal.setHabitat(habitatEdit.getText().toString());
            }
        });
        EditText colorEdit = findViewById(R.id.editColor);
        colorEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentAnimal.setColor(colorEdit.getText().toString());
            }


        });
    }}




