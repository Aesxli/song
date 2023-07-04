package sg.edu.rp.c346.id22012205.song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Display extends AppCompatActivity {
ListView lvsongs;
Button btnBack;
    ArrayList<Song> alSong;
    ArrayAdapter<String> aaSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        lvsongs=findViewById(R.id.lv);


        DBHelper DBH=new DBHelper(Display.this);
        alSong=DBH.getSongs();
        aaSong= new ArrayAdapter(this, android.R.layout.simple_list_item_1, alSong);
        lvsongs.setAdapter(aaSong);

    }

}