package com.android.abhishek.wirelessdisplay.fragment;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.abhishek.wirelessdisplay.R;
import com.android.abhishek.wirelessdisplay.adapter.RecyclerAdapter;
import com.android.abhishek.wirelessdisplay.util.RecyclerItemClickListener;

import java.io.IOException;
import java.util.ArrayList;

public class PatternFragment extends Fragment{

    private Button submit,clear;
    private RecyclerView recyclerView;

    private ArrayList<Integer> arrayList = new ArrayList<>();
    private RecyclerAdapter recyclerAdapter;

    private BluetoothSocket btSocket;

    public PatternFragment() {

    }

    public void setBtSocket(BluetoothSocket btSocket) {
        this.btSocket = btSocket;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i=0;i<64;i++){
            arrayList.add(0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pattern_frament, container, false);
        submit = view.findViewById(R.id.submitBtn);
        clear = view.findViewById(R.id.clearBtn);
        recyclerView = view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels/10;

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),8));
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter(arrayList,height);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                       if(arrayList.get(position) == 0){
                           arrayList.set(position,1);
                       }else{
                           arrayList.set(position,0);
                       }
                       recyclerAdapter.notifyDataSetChanged();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearPattern();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    private void clearPattern(){
        for(int i=0;i<64;i++){
            arrayList.set(i,0);
        }
        recyclerAdapter.notifyDataSetChanged();
    }

    private void sendMessage(){
        String message = "D";
        for(int i=0;i<8;i++){
            char ch = 0B00000000;
            String str = "";

            for(int j=0;j<8;j++){
                if(arrayList.get((8*i)+j) == 0){
                    str += "1";
                }else{
                    str += "0";
                }
            }

            int ascii = Integer.parseInt(str,2);
            ch = (char) ascii;
            message += ch;
            try {
                if(btSocket != null){
                    btSocket.getOutputStream().write(("D"+ch).getBytes());
                }
            } catch (IOException e) {
                Toast.makeText(getActivity(),"Broken Pipe",Toast.LENGTH_SHORT).show();
            }
        }
        Log.e("Send Message",message);
    }

}
