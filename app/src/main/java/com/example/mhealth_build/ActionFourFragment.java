package com.example.mhealth_build;

import android.os.Bundle;
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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.mhealth_build.MainActivity.TAG;

public class ActionFourFragment extends Fragment implements View.OnClickListener {

//    private RecyclerView recyclerView;
//    private RecyclerView.LayoutManager layoutManager;
//    private AboutAdapter abtAdapter;
//    private ArrayList<AboutModel> aboutModelArrayList = new ArrayList<>();

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
                View pv1 = LayoutInflater.from(getActivity()).inflate(R.layout.abt_ms_info, null);
                final PopupWindow window1 = new PopupWindow(pv1, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                window1.setOutsideTouchable(true);
                window1.setTouchable(false);
                window1.showAsDropDown(pv1, Gravity.CENTER, 10, 0);
                break;

            case R.id.btn_2:
                View pv2 = LayoutInflater.from(getActivity()).inflate(R.layout.abt_fbm_info, null);
                final PopupWindow window2 = new PopupWindow(pv2, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                window2.setOutsideTouchable(true);
                window2.setTouchable(false);
                window2.showAsDropDown(pv2, Gravity.CENTER, 10, 0);
                break;

            case R.id.btn_3:
                View pv3 = LayoutInflater.from(getActivity()).inflate(R.layout.abt_app_info, null);
                final PopupWindow window3 = new PopupWindow(pv3, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                window3.setOutsideTouchable(true);
                window3.setTouchable(false);
                window3.showAsDropDown(pv3, Gravity.CENTER, 10, 0);
                break;

            case R.id.btn_4:
                View pv4 = LayoutInflater.from(getActivity()).inflate(R.layout.abt_mrg_info, null);
                final PopupWindow window4 = new PopupWindow(pv4, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                window4.setOutsideTouchable(true);
                window4.setTouchable(false);
                window4.showAsDropDown(pv4, Gravity.CENTER, 10, 0);
                break;

        }


//
//        View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.abt_app_info, null);
//        final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//        popupWindow.setOutsideTouchable(true);
//
//
//        //TextView txt_abt = (TextView) v.findViewById(R.id.about_app_txt);
//        popupWindow.setTouchable(false);
//        popupWindow.showAsDropDown(popupView, Gravity.CENTER, 10, 0);

//        DisplayMetrics dm = new DisplayMetrics();
//        //getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//        getWindow().setLayout((int)(width*.8),(int)(height*.5));


        // set modal to be 4/5 width and 1/2 height of screen





    }




    public static Fragment newInstance() { return new ActionFourFragment(); }
}
