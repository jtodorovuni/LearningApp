package com.example.learningapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseVH> {

    public interface OnExerciseAction{
        void onEdit(Exercise exercise);
        void onDelete(Exercise exercise);
    }

    private List<Exercise> exercises;
    private OnExerciseAction listener;

    public ExerciseAdapter(List<Exercise> exercises , OnExerciseAction listener){
        this.exercises = exercises;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ExerciseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exersice_row, parent, false);

        return new ExerciseVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseVH holder, int position) {

        Exercise exer = exercises.get(position);
        holder.name.setText(exer.getName());
        holder.description.setText(exer.getDescription());

        holder.editB.setOnClickListener(v -> listener.onEdit(exer));
        holder.deleteB.setOnClickListener( v -> listener.onDelete(exer));
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ExerciseVH extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        ImageButton editB;
        ImageButton deleteB;

        public ExerciseVH(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.rowNameTV);
            description = itemView.findViewById(R.id.rowDesriptionTV);
            editB = itemView.findViewById(R.id.rowEditB);
            deleteB = itemView.findViewById(R.id.rowDeleteB);
        }
    }
}
