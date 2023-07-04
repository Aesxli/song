package sg.edu.rp.c346.id22012205.song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnI,btnSL;
   RadioGroup RG;
   RadioButton RB1,RB2,RB3,RB4,RB5;
    EditText etST, etS, etY;
    int stars=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnI=findViewById(R.id.btninsert);
        btnSL=findViewById(R.id.btnshowlist);
        etST=findViewById(R.id.etSongTitle);
        etS=findViewById(R.id.etSingers);
        etY=findViewById(R.id.etYear);
        RG=findViewById(R.id.radioGroup);
        RB1=findViewById(R.id.radioButton1);
        RB2=findViewById(R.id.radioButton2);
        RB3=findViewById(R.id.radioButton3);
        RB4=findViewById(R.id.radioButton4);
        RB5=findViewById(R.id.radioButton5);
        btnI.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                String songtitle=etST.getText().toString();
                String singers=etS.getText().toString();
                int year=Integer.parseInt(etY.getText().toString());
                if(RB1.isChecked()){
                    stars=1;
                } else if (RB2.isChecked()) {
                    stars=2;
                }else if (RB3.isChecked()) {
                    stars=3;
                }else if (RB4.isChecked()) {
                    stars=4;
                }else if (RB5.isChecked()) {
                    stars=5;
                }
                db.insertSong(songtitle,singers,year,stars);
                Toast.makeText(MainActivity.this,"Song Added",Toast.LENGTH_SHORT).show();
            }
        });
        btnSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Display.class);
                startActivity(intent);
            }
        });

    }
}