package com.example.shashank.kbc;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by Shashank on 9/16/2016.
 */
public class themeSelect extends AppCompatActivity
{
    ToggleButton darkThm;
    public String checkTheme = "";
    public String Default = "NA";
    SharedPreferences s;
    RelativeLayout setLinLay;
    TextView theme,description;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        darkThm = (ToggleButton)findViewById(R.id.toggleButton);
        setLinLay = (RelativeLayout)findViewById(R.id.setLinLay);
        theme = (TextView)findViewById(R.id.theme);
        description = (TextView)findViewById(R.id.description);

        //Check Dark Theme If Enable
        s = getSharedPreferences("Dark_Mode", this.MODE_PRIVATE);
        checkTheme = s.getString("checkApplied",Default);
        if(checkTheme.equalsIgnoreCase("true"))
        {
           //Dark Theme
            darkThm.setChecked(true);
            setLinLay.setBackgroundColor(Color.parseColor("#4d4d4d"));
            theme.setTextColor(Color.WHITE);
            description.setTextColor(Color.parseColor("#b3b3b3"));
        }


    }

    public void checkIfApplied(View view)
    {
        SharedPreferences.Editor editor = s.edit();
        if(darkThm.isChecked())
        {
            //write true
            editor.putString("checkApplied","true");
        }
        else
        {
            //write false
            editor.putString("checkApplied","false");
        }
        editor.commit();
        Toast.makeText(this, "Theme has been applied.", Toast.LENGTH_SHORT).show();
    }
}
