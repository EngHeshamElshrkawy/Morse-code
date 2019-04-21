package com.example.hesham.morsecode;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LightToMorse extends AppCompatActivity {



    TextView textLIGHT_reading;
    TextView tvTextView;
    TextView isSig;
    TextView textLIGHT_available;
    float reading;
    float previousReading;
    float calibratedReading = 0;
    final int MSG_START_TIMER = 0;
    final int MSG_STOP_TIMER = 1;
    final int MSG_UPDATE_TIMER = 2;

    Stopwatch timer = new Stopwatch();
    final int REFRESH_RATE = 100;


    List<Long> incomingData = new ArrayList<>();

    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_START_TIMER:
                    timer.start(); //start timer
                    mHandler.sendEmptyMessage(MSG_UPDATE_TIMER);
                    break;

                case MSG_UPDATE_TIMER:
                    if(timer.getElapsedTime() > 5000){
                        timer.stop();
                    }
                    tvTextView.setText(""+ timer.getElapsedTime());
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIMER,REFRESH_RATE); //text view is updated every second,
                    break;                                  //though the timer is still running
                case MSG_STOP_TIMER:
                    mHandler.removeMessages(MSG_UPDATE_TIMER); // no more updates.
                    timer.stop();//stop timer
                    tvTextView.setText(""+ timer.getElapsedTime());
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_to_morse);

        textLIGHT_reading = findViewById(R.id.LIGHT_reading);
        tvTextView = findViewById(R.id.LIGHT_reading);
        isSig = findViewById(R.id.isSig);
        textLIGHT_available = findViewById(R.id.LIGHT_reading);

        SensorManager mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor LightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(LightSensor != null){
            textLIGHT_available.setText("Sensor.TYPE_LIGHT Available");
            mySensorManager.registerListener(
                    LightSensorListener,
                    LightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);

        }else{
            textLIGHT_available.setText("Sensor.TYPE_LIGHT NOT Available");
        }

    }



    private final SensorEventListener LightSensorListener
            = new SensorEventListener(){

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_LIGHT){
                textLIGHT_reading.setText("LIGHT: " + event.values[0]);
                reading = event.values[0];

            }
            if (reading - previousReading > 100){
                //isSig.setText("1");
                //start();
                if(timer.getElapsedTime() < 100){

                }else {
                    isSig.append("0 " + timer.getElapsedTime() + "\n");
                }
                mHandler.sendEmptyMessage(MSG_STOP_TIMER);
                mHandler.sendEmptyMessage(MSG_START_TIMER);

            }else if (reading - previousReading < -50){
                //isSig.setText("0");
                //stop();
                isSig.append("1 "+ timer.getElapsedTime() + "\n");
                incomingData.add(timer.getElapsedTime());
                mHandler.sendEmptyMessage(MSG_STOP_TIMER);
                mHandler.sendEmptyMessage(MSG_START_TIMER);

            }else{

            }
            previousReading = reading;
        }

    };

    public void Translate(View v){

    }

    public void Calibrate(View v){
        calibratedReading = previousReading;
    }


}


