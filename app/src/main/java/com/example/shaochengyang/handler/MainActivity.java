package com.example.shaochengyang.handler;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    ProgressBar progressBar;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById( R.id.textView);
        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        handler = new Handler();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread th = new Thread(){
                    @Override
                    public void run() {
                        for (int i = 0 ; i < 100 ; i ++){
                            final int val = i;
                            String s = "progressBar...";

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText("This is the progress: "+val);
                                    progressBar.setProgress(val);

                                }
                            });


                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"Toast progress",Toast.LENGTH_LONG).show();
                            }
                        });

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText("This is the post delayed!");
                            }
                        },10000);
                        super.run();
                    }
                };
                th.start();
            }
        });


    }
}
