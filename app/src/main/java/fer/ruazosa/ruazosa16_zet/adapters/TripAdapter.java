package fer.ruazosa.ruazosa16_zet.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.R;
import fer.ruazosa.ruazosa16_zet.model.Trip;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripHolder> {

    private ArrayList<Trip> trips = new ArrayList<>();
    private Context c;

    public TripAdapter(Context c) {
        this.c = c;
    }

    public TripAdapter(ArrayList<Trip> trips, Context c) {
        this.trips = trips;
        this.c = c;
    }

    public void setTrips(ArrayList<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public TripHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_layout_trip, null);
        TripHolder viewHolder = new TripHolder(itemLayoutView, c);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TripHolder holder, int position) {
        holder.departureTime.setText(trips.get(position).getDepartureTime());
        holder.startingPointAndDestionation.setText(trips.get(position).getStartingPoint() + " - " + trips.get(position).getDestionation());
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    public static class TripHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context c;

        @BindView(R.id.departure_time)
        TextView departureTime;
        @BindView(R.id.starting_point_and_destination)
        TextView startingPointAndDestionation;


        public TripHolder(View view, Context c) {
            super(view);
            view.setOnClickListener(this);
            this.c = c;
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View v) {
        }
    }

}
