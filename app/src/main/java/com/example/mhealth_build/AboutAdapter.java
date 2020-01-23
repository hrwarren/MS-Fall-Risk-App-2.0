package com.example.mhealth_build;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.ViewHolder>{


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);

        }
    }

    @Override
    public AboutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the chosen layout
        View alertView = inflater.inflate(R.layout.about_popup, parent, false);

        // Return new holder instance
        ViewHolder viewHolder = new ViewHolder(alertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyText item = getValueAt(position);
        MyAdapter.MyCartViewHolder myCartViewHolder = (MyAdapter.MyCartViewHolder)holder;
        if (item != null) {
            setupValuesInWidgets(myCartViewHolder, item);
        }
    }


}




//public class AboutInfo {
//    private String mTitle; //member variable title (message)
//    private boolean mRiskAlert; //member variable of risk alert
//
//    public AlertLog(String title, boolean riskAlert){
//        mTitle = title;
//        mRiskAlert = riskAlert;
//        // Could I use this to display other features like time of alert?
//
//    }
//
//    public String getTitle() {
//        return mTitle;
//    }
//
//    public boolean isRiskAlert() {
//        return mRiskAlert;
//    }
//
//    private static int lastAlertId = 0;
//    private static String alertType;
//
//    public static ArrayList<AlertLog> createAlertLog(int numAlerts) {
//        ArrayList<AlertLog> alerts = new ArrayList<AlertLog>();
//
//        for (int i = 1; i <= numAlerts; i++) {
//            //alerts.add(new AlertLog("Alert Title: " + ++lastAlertId, i <= numAlerts/2));
//            if ( i <= numAlerts/2)  {
//                alertType = "Fall Alert";
//            }    else {
//                alertType = "Battery Alert";
//            }
//            alerts.add(new AlertLog(alertType, i <= numAlerts/2));
//
//
//        }
//
//        return alerts;
//    }
//}