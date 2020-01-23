package com.example.mhealth_build.alertlog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mhealth_build.R;

import java.util.List;

// how to actually do any of this:
// https://guides.codepath.com/android/using-the-recyclerview

public class AlertLogAdapter extends
        RecyclerView.Adapter<AlertLogAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView alertlogTextView;
        public Button msgButton;

        public ViewHolder(View itemView) {
            super(itemView);

            alertlogTextView = (TextView) itemView.findViewById(R.id.alert_title);
            msgButton = (Button) itemView.findViewById(R.id.msg_button);

        }

    }

    // Store a member variable for the logged alerts
    private List<AlertLog> mLoggedAlerts;

    // Contact array into constructor
    public AlertLogAdapter(List<AlertLog> loggedAlerts) {
        mLoggedAlerts = loggedAlerts;
    }

    @Override
    public AlertLogAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the chosen layout
        View alertView = inflater.inflate(R.layout.item_alertmsg, parent, false);

        // Return new holder instance
        ViewHolder viewHolder = new ViewHolder(alertView);
        return viewHolder;
    }

    // Populating data into item through holder
    @Override
    public void onBindViewHolder(AlertLogAdapter.ViewHolder viewHolder, int position) {
        AlertLog loggedAlert = mLoggedAlerts.get(position);

        TextView textView = viewHolder.alertlogTextView;
        textView.setText(loggedAlert.getTitle());
        Button button = viewHolder.msgButton;
        button.setText(loggedAlert.isRiskAlert() ? "Fall Risk" : "Low Battery");
        button.setEnabled(loggedAlert.isRiskAlert());

    }

    // Returns total number of items in the list
    @Override
    public int getItemCount() {
        return mLoggedAlerts.size();
    }

}
