package com.example.mhealth_build.alertlog;

import java.util.ArrayList;

public class AlertLog {
    private String mTitle; //member variable title (message)
    private boolean mRiskAlert; //member variable of risk alert

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
    private static String alertType;

    public static ArrayList<AlertLog> createAlertLog(int numAlerts) {
        ArrayList<AlertLog> alerts = new ArrayList<AlertLog>();

        for (int i = 1; i <= numAlerts; i++) {
            //alerts.add(new AlertLog("Alert Title: " + ++lastAlertId, i <= numAlerts/2));
            if ( i <= numAlerts/2)  {
                alertType = "Fall Alert";
            }    else {
                alertType = "Battery Alert";
            }
            alerts.add(new AlertLog(alertType, i <= numAlerts/2));


        }

        return alerts;
    }
}
