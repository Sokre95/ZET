package fer.ruazosa.ruazosa16_zet.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.R;
import fer.ruazosa.ruazosa16_zet.activities.RouteDetailsActivity;
import fer.ruazosa.ruazosa16_zet.model.Trip;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripHolder> {

    private ArrayList<Trip> trips = new ArrayList<>();
    private Context c;

    private static final String DEPARTURE_TIME = "Departure time";
    private static final String LINE_NUMBER = "Line number";
    private static final String DIRECTION = "Direction";
    private static final String DIRECTION_NAME = "Direction name";

    String lineNumber;
    int direction;
    String directionName;

    TripHolder viewHolder;

    public TripAdapter(Context c) {
        this.c = c;
    }

    public TripAdapter(Context c, String lineNumber, int direction, String directionName) {
        this.c = c;
        this.lineNumber = lineNumber;
        this.direction = direction;
        this.directionName = directionName;
    }

    public TripAdapter(ArrayList<Trip> trips, Context c, String lineNumber, int direction, String directionName) {
        this.trips = trips;
        this.c = c;
        this.lineNumber = lineNumber;
        this.direction = direction;
        this.directionName = directionName;
    }

    public void setTrips(ArrayList<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public TripHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_layout_trip, parent, false);
        viewHolder = new TripHolder(itemLayoutView, c, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TripHolder holder, int position) {
        holder.startingPoint.setText(trips.get(position).getStartingPoint().getName());
        holder.destination.setText(trips.get(position).getDestination().getName());
        holder.departureTime.setText(trips.get(position).departureTimeAsString());
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    public ArrayList<Trip> getTrips() {
        return this.trips;
    }

    public static class TripHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context c;
        private TripAdapter t;

        @BindView(R.id.starting_point)
        TextView startingPoint;
        @BindView(R.id.destination)
        TextView destination;
        @BindView(R.id.departure_time)
        TextView departureTime;

        public TripHolder(View view, Context c, TripAdapter t) {
            super(view);
            view.setOnClickListener(this);
            this.c = c;
            this.t = t;
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString(DEPARTURE_TIME, departureTime.getText().toString());
            bundle.putString(LINE_NUMBER, t.lineNumber);
            bundle.putInt(DIRECTION, t.direction);
            bundle.putString(DIRECTION_NAME, t.directionName);

            Intent i =  new Intent(c, RouteDetailsActivity.class);
            i.putExtra("DATA", bundle);
            this.c.startActivity(i);
        }
    }

}