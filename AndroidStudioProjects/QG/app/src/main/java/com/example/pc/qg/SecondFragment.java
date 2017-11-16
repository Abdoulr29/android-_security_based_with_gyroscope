package com.example.pc.qg;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Pc on 11/6/2017.
 */

public class SecondFragment extends Fragment {
    public static final String TITLE = "SOLD";
    Handler handler = new Handler();
    int status = 0;
    Button button;
    ProgressDialog progressdialog;

    public static SecondFragment newInstance() {

        return new SecondFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
                showDialog();
            }
        });
        return view;
    }

    public void createDialog() {
        progressdialog = new ProgressDialog(getActivity());
        progressdialog.setIndeterminate(false);

        progressdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progressdialog.setCancelable(true);

        progressdialog.setMax(500);

        progressdialog.show();
    }

    public void showDialog() {
        status = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (status < 500) {
                    status += 1;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            progressdialog.setProgress(status);

                            if (status == 500) {

                                progressdialog.dismiss();
                            }
                        }
                    });
                }
            }
        }).start();
    }
}
