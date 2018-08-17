package com.android.abhishek.wirelessdisplay;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.abhishek.wirelessdisplay.fragment.KeypadFragment;
import com.android.abhishek.wirelessdisplay.fragment.PatternFragment;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final UUID MY_UUID_INSECURE = UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");

    private BluetoothAdapter myBluetooth = null;
    private BluetoothSocket btSocket = null;
    private Set<BluetoothDevice> pairedDevices;

    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        int flag = intent.getIntExtra("EXTRA",-1);
        if(flag == -1){
            finish();
        }

        if(flag == 1){
            // Keypad
            KeypadFragment keypadFragment = new KeypadFragment();
            keypadFragment.setBtSocket(btSocket);
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentLayout,keypadFragment).commit();
        }else{
            // Pattern
            PatternFragment patternFragment = new PatternFragment();
            patternFragment.setBtSocket(btSocket);
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentLayout,patternFragment).commit();
        }

        try {bluetooth_connect_device();} catch (Exception e) {}
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.ON){
            sendMessage("OS");
        }else if(id == R.id.OFF){
            sendMessage("T]");
        }else if(id == R.id.RESET){
            sendMessage("OS");
        }else if(id == R.id.LAMPTEST){
            sendMessage("T[");
        }else if(id == R.id.BLINK){
            sendMessage("T*");
        }else if(id == R.id.CREDIT){

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void bluetooth_connect_device() throws IOException {
        try {
            myBluetooth = BluetoothAdapter.getDefaultAdapter();
            address = myBluetooth.getAddress();
            pairedDevices = myBluetooth.getBondedDevices();
            if (pairedDevices.size()>0) {
                for(BluetoothDevice bt : pairedDevices) {
                    address=bt.getAddress().toString();
                    Toast.makeText(this,"Connected", Toast.LENGTH_SHORT).show();
                }
            }

        }
        catch(Exception we){Toast.makeText(this,"Not Connected !!!", Toast.LENGTH_SHORT).show();}
        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice bluetoothDevice = myBluetooth.getRemoteDevice(address);
        btSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(MY_UUID_INSECURE);
        btSocket.connect();
    }

    private void sendMessage(String str){
        String message = str;
        Log.e("Send Message",message);
        try {
            if(btSocket != null){
                btSocket.getOutputStream().write(message.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,"Broken Pipe",Toast.LENGTH_SHORT).show();
        }
    }
}
