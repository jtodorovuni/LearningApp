package com.example.learningapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity implements ExerciseAdapter.OnExerciseAction {

    private DatabaseHelper db;
    private List<Exercise> exercises;
    ExerciseAdapter adapter;
    RecyclerView rv;
    Button addB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        db = new DatabaseHelper(this);
        exercises = db.getAll();

        rv = findViewById(R.id.exercisesRV);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ExerciseAdapter(exercises, this);
        rv.setAdapter(adapter);

        addB = findViewById(R.id.addB);

        addB.setOnClickListener(v -> showPopUp(null));

        loadExercises();
    }

    private void loadExercises() {
        exercises.clear();
        exercises.addAll(db.getAll());
        adapter.notifyDataSetChanged();
    }

    private void showPopUp(Exercise exr) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.edit_pop_up, null);

        EditText nameET = dialogView.findViewById(R.id.popUpNameET);
        EditText descriptionET = dialogView.findViewById(R.id.popUpDescriptionET);

        String title = "Ново упражнение";

        if (exr != null) {
            nameET.setText(exr.getName());
            descriptionET.setText(exr.getDescription());
            title = "Редакакция";
        }

        new AlertDialog.Builder(this)
                .setTitle(title)
                .setView(dialogView)
                .setPositiveButton("Запази",
                        (dialog, which) -> {
                            String name = nameET.getText().toString().trim();
                            String description = descriptionET.getText().toString().trim();

                            if (name.isEmpty()) {
                                Toast.makeText(this, "Името е задължително", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (exr == null) {
                                db.insert(name, description);
                            } else {
                                db.update(exr.getId(), name, description);
                            }
                            loadExercises();
                        })
                .setNegativeButton("Отказ", null)
                .show();
    }

    @Override
    public void onEdit(Exercise exercise) {
        showPopUp(exercise);
    }

    @Override
    public void onDelete(Exercise exercise) {
        new AlertDialog.Builder(this)
                .setTitle("Изтриване!")
                .setMessage("Сигурен ли сте, че искате да го изтриете?")
                .setPositiveButton("Да",
                        (dialog, which) ->
                        {
                            db.delete(exercise.getId());
                            loadExercises();
                        }
                ).setNegativeButton("Не", null)
                .show();
    }
}