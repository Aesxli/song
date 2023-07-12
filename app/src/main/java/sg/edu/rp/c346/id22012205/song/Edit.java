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

public class Edit extends AppCompatActivity {
    EditText etID,etST2,etS2,etY2;
    Button btnC,btnU,btnD;
    RadioGroup RG;
    RadioButton rb1,rb2,rb3,rb4,rb5;
   Song songe;
   int stars=0;
    @Override
    protected void onPause() {
        super.onPause();
        //Step 1a: Get the user input from the EditText and store it in a variable
        Integer ID= Integer.valueOf((etID.getText().toString()));
        String ST=etST2.getText().toString();
        String S=etS2.getText().toString();
        Integer Y= Integer.valueOf((etY2.getText().toString()));
        Integer SR=Integer.valueOf(stars);
        //Step 1b: Obtain an instance of the SharedPreferences
        SharedPreferences prefs=getPreferences(MODE_PRIVATE);
        //Step 1c: Obtain an instance of the SharedPreferences Editor for update later
        SharedPreferences.Editor prefEdit= prefs.edit();
        //Step 1d: Set a key-value pair in the editor
        prefEdit.putInt("ID", ID);
        prefEdit.putString("Song Title",ST );
        prefEdit.putString("Singer",S);
        prefEdit.putInt("Year", Y);
        prefEdit.putInt("Star",SR);

        //Step 1e:Call commit() to save the changes made to the SharedPreference
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Step 2a:Obtain the SHaredPreferences instance
        SharedPreferences prefs=getPreferences(MODE_PRIVATE);
        //Step 2b: Retrieve the saved data from the SharedPreferences object
        Integer ID = prefs.getInt("ID", songe.getId());
        String ST=prefs.getString("Song Title", songe.getTitle());
        String S=prefs.getString("Singer", songe.getSingers());
        Integer Y = prefs.getInt("Year", songe.getYear());
        Integer SR = prefs.getInt("Star", songe.getStars());
        //with a default value if no matching data
        etID.setHint(String.valueOf(ID));
        etST2.setText(ST);
        etS2.setText(S);
        etY2.setText(String.valueOf(Y));
        if(SR==1){
            boolean b = true;
            rb1.setChecked(b);
        }else if (SR==2) {
            boolean b = true;
            rb2.setChecked(b);
        }else if (SR==3) {
            boolean b = true;
            rb3.setChecked(b);
        }else if (SR==4) {
            boolean b = true;
            rb4.setChecked(b);
        }else if (SR==5) {
            boolean b = true;
            rb5.setChecked(b);
        }
    }
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