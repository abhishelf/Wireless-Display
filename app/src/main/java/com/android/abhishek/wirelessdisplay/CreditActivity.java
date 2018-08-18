package com.android.abhishek.wirelessdisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.abhishek.wirelessdisplay.adapter.CreditAdapter;

public class CreditActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        recyclerView = findViewById(R.id.recyclerViewCredit);

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        CreditAdapter creditAdapter = new CreditAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(creditAdapter);
    }
}
