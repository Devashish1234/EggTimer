package com.example.eggtimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
    TextView timerText;
    int minute;
    int second;
    Boolean check=false;
    Button click;
    CountDownTimer countDownTimer;
    public void start(View view){
        if (check==false) {
            check = true;
            timerSeekBar.setEnabled(false);
            click.setText("stop");
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000+100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    counter((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    check=false;
                    timerSeekBar.setEnabled(true);
                    timerSeekBar.setProgress(30);
                    click.setText("start");
                    countDownTimer.cancel();
                    timerText.setText("00:30");
                }
            }.start();
        }else{
            check=false;
            timerSeekBar.setEnabled(true);
            timerSeekBar.setProgress(30);
            click.setText("start");
            countDownTimer.cancel();
            timerText.setText("00:30");
        }
    }
    public void counter(int progress){
        minute=progress/60;
        second=progress-(minute*60);
        String string=Integer.toString(second);
        if (string.equals("0")){
            string="00";
        }else if (second<10){
            string="0"+string;
        }
        timerText.setText(Integer.toString(minute)+":"+string);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click=findViewById(R.id.button);
        timerSeekBar= findViewById(R.id.seekBar);
        timerText =findViewById(R.id.textView);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                counter(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
