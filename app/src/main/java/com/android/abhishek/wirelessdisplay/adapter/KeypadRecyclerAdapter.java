package com.android.abhishek.wirelessdisplay.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.abhishek.wirelessdisplay.R;

import java.util.ArrayList;

public class KeypadRecyclerAdapter extends RecyclerView.Adapter<KeypadRecyclerAdapter.CustomAdapter>{

    private ArrayList<String> arrayList;

    public KeypadRecyclerAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_layout, parent, false);
        return new CustomAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter holder, int position) {
        holder.button.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CustomAdapter extends RecyclerView.ViewHolder{

        Button button;

        public CustomAdapter(View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.buttonKeyPad);
        }
    }
}
