package com.example.shashank.kbc;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainFrame extends Activity implements View.OnClickListener {
    InputStream i;
    InputStreamReader isr;
    BufferedReader br;
    TextView question;
    RadioButton c1, c2, c3, c4;
    Button conf, quit;
    MainFrame m;
    String corrAns, userAns;
    int quesno = 1, correctQues ;
    RadioGroup radgp;
    SharedPreferences s;
    RelativeLayout mainRelLay;
    public String checkTheme = "";
    public String Default = "NA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainframe);
        init();

       //Check Dark Theme If Enable
        s = getSharedPreferences("Dark_Mode", this.MODE_PRIVATE);
        checkTheme = s.getString("checkApplied",Default);
        if(checkTheme.equalsIgnoreCase("true"))
        {
            //Dark Theme
            question.setBackgroundColor(Color.BLACK);
            question.setTextColor(Color.WHITE);
            mainRelLay.setBackgroundColor(Color.parseColor("#4d4d4d"));
            c1.setTextColor(Color.WHITE);
            c2.setTextColor(Color.WHITE);
            c3.setTextColor(Color.WHITE);
            c4.setTextColor(Color.WHITE);
        }

        //Check End
        m = new MainFrame();
        i = this.getResources().openRawResource(R.raw.ques);
        isr = new InputStreamReader(i);
        br = new BufferedReader(isr);
        try {
            updateGui(br);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void init()
    {
        mainRelLay = (RelativeLayout)findViewById(R.id.mainRelLay);
        question = (TextView) findViewById(R.id.question);
        c1 = (RadioButton) findViewById(R.id.c1);
        c2 = (RadioButton) findViewById(R.id.c2);
        c3 = (RadioButton) findViewById(R.id.c3);
        c4 = (RadioButton) findViewById(R.id.c4);
        radgp = (RadioGroup) findViewById(R.id.radgp);
        conf = (Button) findViewById(R.id.conf);
        quit = (Button) findViewById(R.id.quit);
        conf.setOnClickListener(this);
        quit.setOnClickListener(this);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    public void updateGui(BufferedReader br) throws IOException
    {
        question.setText("("+quesno + ")\n" + getQuestion(br));
        c1.setText(getAnswer(br));
        c2.setText(getAnswer(br));
        c3.setText(getAnswer(br));
        c4.setText(getAnswer(br));
        corrAns = getAnswer(br);
        radgp.clearCheck();
    }

    public String getQuestion(BufferedReader br) throws IOException {
        String s;
        s =br.readLine();
        return s;
    }

    public String getAnswer(BufferedReader br) throws IOException
    {
        String s;
        s = br.readLine();
        s = s.trim();
        return s;
    }

    @Override
    public void onClick(View v)
    {
        boolean result;
        if (v.getId() == R.id.conf)
        {
            try
            {
                result = verifyAns();
                if (result == true)
                {
                    if (quesno != 11)
                    {
                        Log.d("dubug"," "+quesno);
                        updateGui(br);
                    }
                    else
                    {
                        Log.d("debug","win");
                        AlertDialog.Builder winMessage = new AlertDialog.Builder(this);
                        winMessage.setTitle("Congratulations!");
                        winMessage.setMessage("You have won the game.");
                        winMessage.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                System.exit(0);
                            }
                        });
                        winMessage.show();
                    }
                } else
                {
                    finish();
                    Intent i = new Intent("com.example.shashank.kbc.GAMEOVER");
                    i.putExtra("correct",correctQues);
                    startActivity(i);
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else if (v.getId() == R.id.quit)
        {
            finish();
            System.exit(0);
        }
    }

    public boolean verifyAns() throws IOException
    {
        RadioButton selectedBt = (RadioButton) findViewById(radgp.getCheckedRadioButtonId());
        try
        {
            userAns = selectedBt.getText().toString();
        }
        catch (NullPointerException n)
        {
            return false;
        }
        if (userAns.equalsIgnoreCase(corrAns))
        {
            quesno++;
            correctQues++;
            return true;
        } else
        {
            return false;
        }
    }
}