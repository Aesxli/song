package sg.edu.rp.c346.id22012205.song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Display extends AppCompatActivity {
    ListView lvsongs;
    Button btnf,btnback;

    ArrayList<Song> alSong;


    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        lvsongs=findViewById(R.id.lv);
        btnf=findViewById(R.id.btnfilter);
        btnback=findViewById(R.id.btnback);
        DBHelper DBH=new DBHelper(Display.this);
        alSong=DBH.getSongs();
        adapter=new CustomAdapter(this,R.layout.row,alSong);
        lvsongs.setAdapter(adapter);
        lvsongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song dsong = alSong.get(position);
                Intent i = new Intent(Display.this,
                        Edit.class);
                i.putExtra("id",position);
                i.putExtra("song", dsong);
                startActivity(i);
            }
        });
        btnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(Display.this);
                ArrayList<Song> fiveS=new ArrayList<>();
                for (Song song: alSong) {
                    if(song.getStars()==5){
                        fiveS.add(song);

                    }
                    ArrayAdapter<Song> adapter=new CustomAdapter(Display.this,R.layout.row,fiveS);
                    lvsongs.setAdapter(adapter);
                }

            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {//when the button is pressed, returns back to the previous activity page
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Display.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}

