package fer.ruazosa.ruazosa16_zet;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.DisplayHolder> {

    List<InfoDisplay> displays;

    public DisplayAdapter(List<InfoDisplay> displays) {
        this.displays = displays;
    }


    @Override
    public DisplayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_layout_info, null);
        DisplayHolder viewHolder = new DisplayHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DisplayHolder holder, int position) {
        holder.timeView.setText(displays.get(position).getTime());
        holder.stationView.setText(displays.get(position).getStation());
        holder.routeView.setText(displays.get(position).getRouteName());
    }

    @Override
    public int getItemCount() {
        return displays.size();
    }

    public static class DisplayHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView timeView;
        public TextView stationView;
        public TextView routeView;

        public DisplayHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            timeView = (TextView) itemView.findViewById(R.id.vrijeme_dolazak);
            stationView = (TextView) itemView.findViewById(R.id.naziv_stanice);
            routeView = (TextView) itemView.findViewById(R.id.naziv_linije);
        }

        @Override
        public void onClick(View v) {
            // TODO : create activity
        }
    }

}
