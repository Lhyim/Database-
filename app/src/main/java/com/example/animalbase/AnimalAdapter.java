package com.example.animalbase;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnimalAdapter extends RecyclerView.Adapter {

    private ArrayList<Animal> animalData;
    private View.OnClickListener onClickListener;

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewSpecies;
        public TextView textViewHabitat;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSpecies = itemView.findViewById(R.id.textViewSpecies);
            textViewHabitat = itemView.findViewById(R.id.textViewHabitat);
            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);

        }

        public TextView getTextViewSpecies() {
            return textViewSpecies;
        }

        public TextView getTextViewHabitat() { return textViewHabitat; }

    }

    public void setOnClickListener(View.OnClickListener listener) {
        onClickListener = listener;
    }

    public AnimalAdapter(ArrayList<Animal> arrayList) {
        animalData = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_view, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ContactViewHolder cvh = (ContactViewHolder) holder;
        cvh.getTextViewSpecies().setText(animalData.get(position).getSpecies());
        cvh.getTextViewHabitat().setText(animalData.get(position).getHabitat());
    }

    @Override
    public int getItemCount() {
        return animalData.size();
    }
}
