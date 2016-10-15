package com.example.shashank.kbc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by Shashank on 8/24/2016.
 */
public class GameOver extends AppCompatActivity implements View.OnClickListener {
    TextView res,title;
    Button tryAgain;
    MainFrame m2;
    public String checkTheme = "";
    public String Default = "NA";
    SharedPreferences s;
    LinearLayout gOLinLay;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        int count = this.getIntent().getExtras().getInt("correct");
        title = (TextView)findViewById(R.id.result);
        res= (TextView)findViewById(R.id.report);
        tryAgain=(Button)findViewById(R.id.t_again);
        gOLinLay = (LinearLayout)findViewById(R.id.gOLinLay);

        //Check Dark Theme If Enable
        s = getSharedPreferences("Dark_Mode", this.MODE_PRIVATE);
        checkTheme = s.getString("checkApplied",Default);
        if(checkTheme.equalsIgnoreCase("true"))
        {
            //Dark Theme
            gOLinLay.setBackgroundColor(Color.parseColor("#4d4d4d"));
        }

        //Check End

        m2 = new MainFrame();
        if(count<=3)
        {
            res.setTextColor(Color.RED);
            res.setText("BAD LUCK!!! \n You gave correct answer of "+count+" questions \n Better luck next time...");
        }
        else
        {
            res.setTextColor(Color.parseColor("#4CAF50"));
            res.setText("Nice..\n You gave correct answer of "+count+" questions");
        }
        tryAgain.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu m)
    {
        super.onCreateOptionsMenu(m);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.main_menu, m);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId()==R.id.setting)
        {
            Intent i1 = new Intent(this,themeSelect.class);
            startActivity(i1);
        }
        return false;
    }

    @Override
    public void onClick(View v)
    {
        finish();
        Intent i = new Intent(this,MainFrame.class);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
