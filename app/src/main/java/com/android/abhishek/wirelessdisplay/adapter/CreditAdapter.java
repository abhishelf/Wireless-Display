package com.android.abhishek.wirelessdisplay.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.abhishek.wirelessdisplay.R;
import com.squareup.picasso.Picasso;


public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.CustomAdapter>{

    @NonNull
    @Override
    public CustomAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.credit_layout, parent, false);
        return new CustomAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter holder, int position) {
        if (position == 0){
            holder.textView.setText("Dr. Ritesh Kumar Badhai");
            holder.asA.setText("as a Mentor");
            Picasso.get().load(R.drawable.ritesh).into(holder.imageView);
        }else if(position == 1){
            holder.textView.setText("Neha");
            holder.asA.setText("as a Team Member");
        }else if(position == 2){
            holder.textView.setText("Prashant");
            holder.asA.setText("as a Team Member");
            Picasso.get().load(R.drawable.prashant).into(holder.imageView);
        }else if(position == 3){
            holder.textView.setText("Aditya");
            holder.asA.setText("as a Team Member");
            Picasso.get().load(R.drawable.aditya).into(holder.imageView);
        }else if(position == 4){
            holder.textView.setText("Rajeev");
            holder.asA.setText("as a Team Member");
            Picasso.get().load(R.drawable.rajeev).into(holder.imageView);
        }else if(position == 5){
            holder.textView.setText("Abhishek");
            holder.asA.setText("as a Team Member");
            Picasso.get().load(R.drawable.abhishek).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class CustomAdapter extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;
        private TextView asA;
        public CustomAdapter(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.peopleImage);
            textView = itemView.findViewById(R.id.name);
            asA = itemView.findViewById(R.id.asA);
        }
    }
}
