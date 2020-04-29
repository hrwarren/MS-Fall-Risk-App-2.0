package com.example.mhealth_build.alertlog;

import java.util.ArrayList;

// This class generates the alerts to be displayed in ActionThreeFragment.
// Right now it is just creating filler alerts

public class AlertLog {
    private String mTitle; //member variable title (message)
    private boolean mRiskAlert; //member variable of risk alert

    public AlertLog(String title, boolean riskAlert){
        mTitle = title;
        mRiskAlert = riskAlert;
        // Could I use this to display other features like time of alert?

    }

    // returns the title of the alert
    public String getTitle() {
        return mTitle;
    }

    // returns true if the alert is a risk alert as opposed to a low battery alert
    public boolean isRiskAlert() {
        return mRiskAlert;
    }

    // should I make this variable local to createAlertLog(int)?
    private static String alertType;

    // This is the function that actually fills the alert log
    // Right now, it only takes the number of generated alerts as its argument
    public static ArrayList<AlertLog> createAlertLog(int numAlerts) {

        // Make its array list
        ArrayList<AlertLog> alerts = new ArrayList<AlertLog>();

        // for loop for generating filler alerts
        for (int i = 1; i <= numAlerts; i++) {
            if ( i <= numAlerts/2)  {
                alertType = "Fall Alert";
            }    else {
                alertType = "Battery Alert";
            }
            alerts.add(new AlertLog(alertType, i <= numAlerts/2));


        }

        // spit out the alert array
        return alerts;
    }
}
