package com.example.hesham.morsecode;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MorseToLight extends AppCompatActivity {


    //Represent letters with corresponding morse code
    final String LTR_A = ".-";
    final String LTR_B = "-...";
    final String LTR_C = "-.-.";
    final String LTR_D = "-..";
    final String LTR_E = ".";
    final String LTR_F = "..-.";
    final String LTR_G = "--.";
    final String LTR_H = "....";
    final String LTR_I = "..";
    final String LTR_J = ".---";
    final String LTR_K = "-.-";
    final String LTR_L = ".-..";
    final String LTR_M = "--";
    final String LTR_N = "-.";
    final String LTR_O = "---";
    final String LTR_P = ".--.";
    final String LTR_Q = "--.-";
    final String LTR_R = ".-.";
    final String LTR_S = "...";
    final String LTR_T = "-";
    final String LTR_U = "..-";
    final String LTR_V = "...-";
    final String LTR_W = ".--";
    final String LTR_X = "-..-";
    final String LTR_Y = "-.--";
    final String LTR_Z = "--..";
    final String LTR_SPC = "/";
    final String WRD_SPC = "|";

    //Represents the time for each element of morse code
    final int dot = 100; //on
    final int dash = 300; //on
    final int spc_btwn_pts = 100; //off
    final int spc_btwn_ltrs = 200; //off
    final int spc_btwn_wrds = 400; //off

    //
    EditText userEditText;
    TextView convertedTextView;

    //
    String userText;
    String convertedText = "";
    char charInUserText;

    //
    boolean flashLightStatus = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse_to_light);
        userEditText =  findViewById(R.id.useredittext);
        convertedTextView = findViewById(R.id.convertedtextview);
    }


    public void TextToMorse(View v){
        userText = userEditText.getText().toString().trim().toUpperCase();
        convertedText = "";
        for (int i = 0, n = userText.length(); i < n; i++) {
            charInUserText = userText.charAt(i);
            switch(charInUserText){
                case 'A':
                    convertedText += LTR_A;
                    convertedText += LTR_SPC;
                    break;
                case 'B':
                    convertedText += LTR_B;
                    convertedText += LTR_SPC;

                    break;
                case 'C':
                    convertedText += LTR_C;
                    convertedText += LTR_SPC;

                    break;
                case 'D':
                    convertedText += LTR_D;
                    convertedText += LTR_SPC;

                    break;
                case 'E':
                    convertedText += LTR_E;
                    convertedText += LTR_SPC;

                    break;
                case 'F':
                    convertedText += LTR_F;
                    convertedText += LTR_SPC;

                    break;
                case 'G':
                    convertedText += LTR_G;
                    convertedText += LTR_SPC;

                    break;
                case 'H':
                    convertedText += LTR_H;
                    convertedText += LTR_SPC;

                    break;
                case 'I':
                    convertedText += LTR_I;
                    convertedText += LTR_SPC;

                    break;
                case 'J':
                    convertedText += LTR_J;
                    convertedText += LTR_SPC;

                    break;
                case 'K':
                    convertedText += LTR_K;
                    convertedText += LTR_SPC;

                    break;
                case 'L':
                    convertedText += LTR_L;
                    convertedText += LTR_SPC;

                    break;
                case 'M':
                    convertedText += LTR_M;
                    convertedText += LTR_SPC;

                    break;
                case 'N':
                    convertedText += LTR_N;
                    convertedText += LTR_SPC;

                    break;
                case 'O':
                    convertedText += LTR_O;
                    convertedText += LTR_SPC;

                    break;
                case 'P':
                    convertedText += LTR_P;
                    convertedText += LTR_SPC;

                    break;
                case 'Q':
                    convertedText += LTR_Q;
                    convertedText += LTR_SPC;

                    break;
                case 'R':
                    convertedText += LTR_R;
                    convertedText += LTR_SPC;

                    break;
                case 'S':
                    convertedText += LTR_S;
                    convertedText += LTR_SPC;

                    break;
                case 'T':
                    convertedText += LTR_T;
                    convertedText += LTR_SPC;

                    break;
                case 'U':
                    convertedText += LTR_U;
                    convertedText += LTR_SPC;

                    break;
                case 'V':
                    convertedText += LTR_V;
                    convertedText += LTR_SPC;

                    break;
                case 'W':
                    convertedText += LTR_W;
                    convertedText += LTR_SPC;

                    break;
                case 'X':
                    convertedText += LTR_X;
                    convertedText += LTR_SPC;

                    break;
                case 'Y':
                    convertedText += LTR_Y;
                    convertedText += LTR_SPC;

                    break;
                case 'Z':
                    convertedText += LTR_Z;
                    convertedText += LTR_SPC;

                    break;
                case ' ':
                    convertedText += WRD_SPC;

                default :

            }

        }
        convertedTextView.setText("");
        convertedTextView.setText(convertedText);
        MorseToLight(convertedText);
    }

    public void MorseToLight(String converted){
        for (int i = 0, n = converted.length(); i < n; i++) {
            if(converted.charAt(i) == '.'){
                flashLightOn();
                sleep(dot);
                flashLightOff();
                sleep(spc_btwn_pts);

            }else if (converted.charAt(i) == '-'){
                flashLightOn();
                sleep(dash);
                flashLightOff();
                sleep(spc_btwn_pts);
            }else if (converted.charAt(i) == '/'){
                flashLightOff();
                sleep(spc_btwn_ltrs);
            }else if (converted.charAt(i) == '|'){
                flashLightOff();
                sleep(spc_btwn_wrds);
            }

        }


    }

    public void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void flashLightOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
            flashLightStatus = true;
        } catch (CameraAccessException e) {
        }
    }

    private void flashLightOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
            flashLightStatus = false;
        } catch (CameraAccessException e) {
        }
    }

    public void LightToMorse(View v){
        Intent lighttomorse = new Intent(MorseToLight.this, LightToMorse.class);
        startActivity(lighttomorse);
    }
}
