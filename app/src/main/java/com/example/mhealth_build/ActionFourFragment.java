package com.example.mhealth_build;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import static com.example.mhealth_build.MainActivity.TAG;

public class ActionFourFragment extends Fragment implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "ActionFourFragment.onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "ActionFourFragment.onCreateview()");
        View v = inflater.inflate(R.layout.action_four_fragment, container, false);

        Button btn_1 = (Button)v.findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this); // calling onClick() method
        Button btn_2 = (Button)v.findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        Button btn_3 = (Button)v.findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        Button btn_4 = (Button)v.findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v){
        int pwnum;


        switch (v.getId()) {
            case R.id.btn_1:
                pwnum = 1;
                break;

            case R.id.btn_2:
                pwnum = 2;
                break;

            case R.id.btn_3:
                pwnum = 3;
                break;

            case R.id.btn_4:
                pwnum = 4;
                break;

        }

        View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.about_popup, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);


        TextView txt_abt = (TextView) v.findViewById(R.id.about_app_text);
        popupWindow.setTouchable(false);
        popupWindow.showAsDropDown(popupView, Gravity.CENTER, 10, 0);

//        DisplayMetrics dm = new DisplayMetrics();
//        //getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//        getWindow().setLayout((int)(width*.8),(int)(height*.5));


        // set modal to be 4/5 width and 1/2 height of screen





    }




    public static Fragment newInstance() {
        return new ActionFourFragment();
    }
}
