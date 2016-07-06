package fer.ruazosa.ruazosa16_zet.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.R;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.DisplayHolder> {

    //List<InfoDisplay> displays;

    public DisplayAdapter() {
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
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class DisplayHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.vrijeme_dolazak) TextView timeView;
        @BindView(R.id.naziv_stanice) TextView stationView;
        @BindView(R.id.naziv_linije) TextView routeView;

        public DisplayHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            // TODO : create activity
        }
    }

}
