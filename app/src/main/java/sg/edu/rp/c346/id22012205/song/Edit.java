package sg.edu.rp.c346.id22012205.song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.time.Year;

public class Edit extends AppCompatActivity {
    EditText etID,etST2,etS2,etY2;
    Button btnC,btnU,btnD;
    RadioGroup RG;
    RadioButton rb1,rb2,rb3,rb4,rb5;
   Song songe;
   int stars=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        etID=findViewById(R.id.etID);
        etST2=findViewById(R.id.etSongTitle2);
        etS2=findViewById(R.id.etSingers2);
        etY2=findViewById(R.id.etYear2);
        btnC=findViewById(R.id.btnCancel);
        btnD=findViewById(R.id.btnDelete);
        btnU=findViewById(R.id.btnUpdate);
        RG=findViewById(R.id.rG);
        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);
        rb3=findViewById(R.id.rb3);
        rb4=findViewById(R.id.rb4);
        rb5=findViewById(R.id.rb5);

        Intent i = getIntent();
        songe = (Song) i.getSerializableExtra("song");
        int id = i.getIntExtra("id",0);
        etID.setText(String.valueOf(id+1));
        etST2.setText(songe.getTitle());
        etS2.setText(songe.getSingers());
        etY2.setText(String.valueOf(songe.getYear()));
        int starC=((songe.getStars()));
        if(starC==1){
            RG.check(R.id.rb1);
        } else if (starC==2) {
            RG.check(R.id.rb2);
        }else if (starC==3) {
            RG.check(R.id.rb3);
        }else if (starC==4) {
            RG.check(R.id.rb4);
        }else if (starC==5) {
            RG.check(R.id.rb5);
        }
        btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(Edit.this);
                songe.setTitle(etST2.getText().toString());
                songe.setSingers(etS2.getText().toString());
                songe.setYear(Integer.parseInt(etY2.getText().toString()));
                if(rb1.isChecked()){
                    stars=1;
                } else if (rb2.isChecked()) {
                    stars=2;
                }else if (rb3.isChecked()) {
                    stars=3;
                }else if (rb4.isChecked()) {
                    stars=4;
                }else if (rb5.isChecked()) {
                    stars=5;
                }
                songe.setStars(stars);
                dbh.updateSong(songe);
                dbh.close();
                Intent i=new Intent(Edit.this,Display.class);
                startActivity(i);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(Edit.this);
                dbh.deleteSong(songe.getId());
                Intent i=new Intent(Edit.this,Display.class);
                startActivity(i);
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}