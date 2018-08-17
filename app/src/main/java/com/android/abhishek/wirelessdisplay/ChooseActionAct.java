package com.android.abhishek.wirelessdisplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ChooseActionAct extends AppCompatActivity {

    LinearLayout linearLayout;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_action);

        Button type = findViewById(R.id.keyPad);
        Button pattern = findViewById(R.id.pattern);
        linearLayout = findViewById(R.id.chooseLayout);
        progressBar = findViewById(R.id.progressBar);


        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChooseActionAct.this,"Please Wait ...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChooseActionAct.this,HomePage.class);
                intent.putExtra("EXTRA",1);
                startActivity(intent);
            }
        });

        pattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChooseActionAct.this,"Please Wait ...",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChooseActionAct.this,HomePage.class);
                intent.putExtra("EXTRA",2);
                startActivity(intent);
            }
        });
    }
}
