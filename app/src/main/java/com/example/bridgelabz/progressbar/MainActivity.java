package com.example.bridgelabz.progressbar;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnStartProgress;
    ProgressDialog progressbar;

    private  int progressBarStatus=0;
    private  long filesize=0;
    private Handler progressBarHandler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButtonClick();


    }

    public void addListenerOnButtonClick(){
        btnStartProgress= (Button) findViewById(R.id.button);
        btnStartProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // creating progress bar dialog

                progressbar=new ProgressDialog(v.getContext());
                progressbar.setCancelable(true);
                progressbar.setMessage(" file downloading");
                progressbar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressbar.setProgress(0);
                progressbar.setMax(100);

                progressbar.show();

//reset file size..
                progressBarStatus=0;
                filesize=0;

                new Thread(new Runnable() {
                    public void run() {
                        while (progressBarStatus < 100) {
                            // performing operation
                            progressBarStatus = doOperation();
                            try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                            // Updating the progress bar
                            progressBarHandler.post(new Runnable() {
                                public void run() {
                                    progressbar.setProgress(progressBarStatus);
                                }
                            });
                        }
                        // performing operation if file is downloaded,
                        if (progressBarStatus >= 100) {
                            // sleeping for 1 second after operation completed
                            try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
                            // close the progress bar dialog
                            progressbar.dismiss();
                        }
                    }
                }).start();
            }//end of onClick method
        });
    }
    // checking how much file is downloaded and updating the filesize
    public int doOperation() {
        //The range of ProgressDialog starts from 0 to 10000
        while (filesize <= 10000) {
            filesize++;
            if (filesize == 1000) {
                return 10;
            } else if (filesize == 2000) {
                return 20;
            } else if (filesize == 3000) {
                return 30;
            } else if (filesize == 4000) {
                return 40;//you can add more else if
            } else{
                return 100;
            }
        }//end of while
        return 100;
    }//end of doOperation


}















