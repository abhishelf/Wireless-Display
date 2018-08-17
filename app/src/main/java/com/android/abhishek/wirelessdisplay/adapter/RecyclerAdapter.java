package com.android.abhishek.wirelessdisplay.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.android.abhishek.wirelessdisplay.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomAdapter> {

    private ArrayList<Integer> arrayList;
    private int height;

    public RecyclerAdapter(ArrayList<Integer> arrayList, int height) {
        this.arrayList = arrayList;
        this.height = height;
    }

    @NonNull
    @Override
    public CustomAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.radio_layout, parent, false);
        return new CustomAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomAdapter holder, final int position) {
        holder.radioButton.getLayoutParams().height = height;
        if(arrayList.get(position) == 0){
            holder.radioButton.setChecked(false);
        }else{
            holder.radioButton.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return 64;
    }

    public class CustomAdapter extends RecyclerView.ViewHolder{

        RadioButton radioButton;
        public CustomAdapter(View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.radio);
        }
    }
}
