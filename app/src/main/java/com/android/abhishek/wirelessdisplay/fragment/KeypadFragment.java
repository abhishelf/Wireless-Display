package com.android.abhishek.wirelessdisplay.fragment;


import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.abhishek.wirelessdisplay.R;
import com.android.abhishek.wirelessdisplay.adapter.KeypadRecyclerAdapter;
import com.android.abhishek.wirelessdisplay.util.RecyclerItemClickListener;

import java.io.IOException;
import java.util.ArrayList;

public class KeypadFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button clearBtn;
    private Button changeCase;
    private Button z;
    private TextView text;

    private BluetoothSocket btSocket;

    private ArrayList<String> smallCaps = new ArrayList<>();
    private ArrayList<String> largeCaps = new ArrayList<>();

    private boolean isLargeCaps = true;

    public KeypadFragment() {

    }

    public void setBtSocket(BluetoothSocket btSocket) {
        this.btSocket = btSocket;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        smallCaps.add("0");
        smallCaps.add("1");
        smallCaps.add("2");
        smallCaps.add("3");
        smallCaps.add("4");
        smallCaps.add("5");
        smallCaps.add("6");
        smallCaps.add("7");
        smallCaps.add("8");
        smallCaps.add("9");
        smallCaps.add("a");
        smallCaps.add("b");
        smallCaps.add("c");
        smallCaps.add("d");
        smallCaps.add("e");
        smallCaps.add("f");
        smallCaps.add("g");
        smallCaps.add("h");
        smallCaps.add("i");
        smallCaps.add("j");
        smallCaps.add("k");
        smallCaps.add("l");
        smallCaps.add("m");
        smallCaps.add("n");
        smallCaps.add("o");
        smallCaps.add("p");
        smallCaps.add("q");
        smallCaps.add("r");
        smallCaps.add("s");
        smallCaps.add("t");
        smallCaps.add("u");
        smallCaps.add("v");
        smallCaps.add("w");
        smallCaps.add("x");
        smallCaps.add("y");
        largeCaps.add("0");
        largeCaps.add("1");
        largeCaps.add("2");
        largeCaps.add("3");
        largeCaps.add("4");
        largeCaps.add("5");
        largeCaps.add("6");
        largeCaps.add("7");
        largeCaps.add("8");
        largeCaps.add("9");
        largeCaps.add("A");
        largeCaps.add("B");
        largeCaps.add("C");
        largeCaps.add("D");
        largeCaps.add("E");
        largeCaps.add("F");
        largeCaps.add("G");
        largeCaps.add("H");
        largeCaps.add("I");
        largeCaps.add("J");
        largeCaps.add("K");
        largeCaps.add("L");
        largeCaps.add("M");
        largeCaps.add("N");
        largeCaps.add("O");
        largeCaps.add("P");
        largeCaps.add("Q");
        largeCaps.add("R");
        largeCaps.add("S");
        largeCaps.add("T");
        largeCaps.add("U");
        largeCaps.add("V");
        largeCaps.add("W");
        largeCaps.add("X");
        largeCaps.add("Y");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keypad, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewOfKeypad);
        clearBtn = view.findViewById(R.id.ClearAtFG);
        changeCase = view.findViewById(R.id.changeCase);
        z = view.findViewById(R.id.Z);
        text = view.findViewById(R.id.textViewAtFG);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),5));
        recyclerView.setHasFixedSize(true);
        KeypadRecyclerAdapter adapter = new KeypadRecyclerAdapter(largeCaps);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        if(isLargeCaps){
                            String str = largeCaps.get(position);
                            sendMessage(str);
                            text.setText(str);
                        }else{
                            String str = smallCaps.get(position);
                            sendMessage(str);
                            text.setText(str);
                        }
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLargeCaps){
                    sendMessage("Z");
                    text.setText("Z");
                }else{
                    sendMessage("z");
                    text.setText("z");
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("");
                sendMessage("]");
            }
        });

        changeCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLargeCaps){
                    isLargeCaps = false;
                    KeypadRecyclerAdapter adapter = new KeypadRecyclerAdapter(smallCaps);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    z.setText("z");
                    changeCase.setText("ABC");
                }else {
                    isLargeCaps = true;
                    KeypadRecyclerAdapter adapter = new KeypadRecyclerAdapter(largeCaps);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    z.setText("Z");
                    changeCase.setText("abc");
                }
            }
        });
    }

    private void sendMessage(String str){
        String message = "T"+str;
        Log.e("Send Message",message);
        try {
            if(btSocket != null){
                btSocket.getOutputStream().write(message.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(),"Broken Pipe",Toast.LENGTH_SHORT).show();
        }
    }
}
