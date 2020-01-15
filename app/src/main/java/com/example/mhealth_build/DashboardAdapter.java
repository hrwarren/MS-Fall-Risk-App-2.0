//package com.example.mhealth_build;
//
//import android.content.Context;
//import android.media.Image;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.jjoe64.graphview.GraphView;
//
//import java.util.List;
//
//
//public class DashboardAdapter extends GraphView. {
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView graphTextView;
//        public graph;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//
//            graphTextView = (TextView) itemView.findViewById(R.id.graph_title);
//            Image = (Image) itemView.findViewById(R.id.msg_button);
//
//        }
//
//    }
//
//    // Store a member variable for the logged alerts
//    private List<AlertLog> mLoggedAlerts;
//
//    // Contact array into constructor
//    public AlertLogAdapter(List<AlertLog> loggedAlerts) {
//        mLoggedAlerts = loggedAlerts;
//    }
//
//    @Override
//    public AlertLogAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Context context = parent.getContext();
//        LayoutInflater inflater = LayoutInflater.from(context);
//
//        // Inflate the chosen layout
//        View alertView = inflater.inflate(R.layout.item_alertmsg, parent, false);
//
//        // Return new holder instance
//        ViewHolder viewHolder = new ViewHolder(alertView);
//        return viewHolder;
//    }
//
//    // Populating data into item through holder
//    @Override
//    public void onBindViewHolder(AlertLogAdapter.ViewHolder viewHolder, int position) {
//        AlertLog loggedAlert = mLoggedAlerts.get(position);
//
//        TextView textView = viewHolder.alertlogTextView;
//        textView.setText(loggedAlert.getTitle());
//        Button button = viewHolder.msgButton;
//        button.setText(loggedAlert.isRiskAlert() ? "Fall Risk" : "Low Battery");
//        button.setEnabled(loggedAlert.isRiskAlert());
//
//    }
//
//    // Returns total number of items in the list
//    @Override
//    public int getItemCount() {
//        return mLoggedAlerts.size();
//    }
//
//}
//
