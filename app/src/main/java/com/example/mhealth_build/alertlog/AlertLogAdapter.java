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

// This the adapter for the alert log's recyclerview
// It is what handles the addition and display of items in the recyclerview itself

public class AlertLogAdapter extends
        RecyclerView.Adapter<AlertLogAdapter.ViewHolder>{

    // Initialize the recyclerview's viewholder, i.e. the class responsible for displaying each
    // item in the recyclerview
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
    // What to do when the viewholder is first created
    public AlertLogAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Provide context for the adapter
        // As far as I understand, this means telling it that it was born in AlertLogAdapter,
        // which is called in ActivityThreeFragment, which is running by the grace of MainActivity,
        // and giving it access to all the data therein
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout for the alert message
        View alertView = inflater.inflate(R.layout.item_alertmsg, parent, false);

        // Return new holder instance
        ViewHolder viewHolder = new ViewHolder(alertView);
        return viewHolder;
    }

    // Populating data into item through holder
    @Override
    public void onBindViewHolder(AlertLogAdapter.ViewHolder viewHolder, int position) {

        // Initialize a member of logged alerts
        AlertLog loggedAlert = mLoggedAlerts.get(position);

        // Declare the textviews for the alert's content and title
        TextView textView = viewHolder.alertlogTextView;
        textView.setText(loggedAlert.getTitle());

        // Initialize a button that is pressable when the alert is a fall risk alert, but
        // is greyed out when it's a battery alert
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
