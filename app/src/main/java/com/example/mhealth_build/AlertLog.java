package com.example.mhealth_build;

import java.util.ArrayList;

public class AlertLog {
    private String mTitle; //member variable msg (message)
    private boolean mRiskAlert;

    public AlertLog(String title, boolean riskAlert){
        mTitle = title;
        mRiskAlert = riskAlert;
        // Could I use this to display other features like time of alert?

    }

    public String getTitle() {
        return mTitle;
    }

    public boolean isRiskAlert() {
        return mRiskAlert;
    }

    private static int lastAlertId = 0;

    public static ArrayList<AlertLog> createAlertLog(int numAlerts) {
        ArrayList<AlertLog> alerts = new ArrayList<AlertLog>();

        for (int i = 1; i <= numAlerts; i++) {
            alerts.add(new AlertLog("Alert Title: " + ++lastAlertId, i <= numAlerts/2));
        }

        return alerts;
    }
}
