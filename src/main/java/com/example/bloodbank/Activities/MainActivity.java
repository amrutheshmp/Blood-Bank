package com.example.bloodbank.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.bloodbank.R;

import Utils.VolleySingleton;

import static com.example.bloodbank.R.id.action_text;
import static com.example.bloodbank.R.id.button;

public class MainActivity extends AppCompatActivity {
    RadioButton r;
    Button btn;
    CheckBox a, b, c, d, e, f, g, h;
    int no = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.search_button) {
                    startActivity(new Intent(MainActivity.this, SearchActivity.class));

                }
                return false;

            }
        });
        a = (CheckBox) findViewById(R.id.checkBox);
        b = (CheckBox) findViewById(R.id.checkBox2);
        c = (CheckBox) findViewById(R.id.checkBox3);
        d = (CheckBox) findViewById(R.id.checkBox4);
        e = (CheckBox) findViewById(R.id.checkBox5);
        f = (CheckBox) findViewById(R.id.checkBox6);
        g = (CheckBox) findViewById(R.id.checkBox10);
        h = (CheckBox) findViewById(R.id.checkBox9);
        btn = (Button) findViewById(button);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                StringBuilder result = new StringBuilder();
                result.append("Selected Items:");
                if (a.isChecked()) {
                    result.append("\n A+ Required");
                    no = no + 1;

                }
                if (b.isChecked()) {
                    result.append("\n AB+ Required");
                    no = no + 1;
                }
                if (c.isChecked()) {
                    result.append("\n AB- Required");
                    no = no + 1;
                }
                if (d.isChecked()) {
                    result.append("\n B+Required");
                    no = no + 1;
                }
                if (e.isChecked()) {
                    result.append("\n B- Required");
                    no = no + 1;
                }
                if (f.isChecked()) {
                    result.append("\n O+ Required");
                    no = no + 1;
                }
                if (g.isChecked()) {
                    result.append("\n A- Required");
                    no = no + 1;
                }
                if (h.isChecked()) {
                    result.append("\n O- Required");
                    no = no + 1;
                }

                result.append("\nTotal: " + no + " Blood Groups required");

                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();

                Intent in = new Intent(MainActivity.this,database.class);
                startActivity(in);
            }



        });






    }
}
