package com.example.mhealth_build;

import java.util.ArrayList;

import java.util.ArrayList;

public class Dashboard {


    private String mGraphTitle; // title of graph
    private Integer mPeriod;

    public Dashboard(String title, Integer period){
        mGraphTitle = title;
        mPeriod = period;
        // Could I use this to display other features like time of alert?

    }

    public String getTitle() {
        return mGraphTitle;
    }

    public Integer getPeriod() { return mPeriod; }

    private static String title;

    // creates the list of graphs seen in ActionTwo
    public static ArrayList<Dashboard> createDashboard(int graphType) {
        ArrayList<Dashboard> graphs = new ArrayList<Dashboard>();

        for (int i = 1; i <= graphType; i++) {
            //alerts.add(new AlertLog("Alert Title: " + ++lastAlertId, i <= numAlerts/2));

            if (graphType == 1) {
                title = "24 hours";
            } else if (graphType == 2) {
                title = "1 week";
            } else if (graphType == 3) {
                title = "1 month";
            }else if (graphType == 4) {
                title = "1 year";
            }

            graphs.add(new Dashboard(title, graphType));

        }
        return graphs;
    }
}
