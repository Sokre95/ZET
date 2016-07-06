package fer.ruazosa.ruazosa16_zet.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.R;

public class RouteDetailsAdapter extends
        RecyclerView.Adapter<RouteDetailsAdapter.RouteDetailsHolder> {

    private ArrayList<String> details = new ArrayList<>();

    public RouteDetailsAdapter() {

    }

    public RouteDetailsAdapter(ArrayList<String> details) {
        this.details = details;
    }

    @Override
    public RouteDetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_layout_route_details, parent, false);
        RouteDetailsHolder viewHolder = new RouteDetailsHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RouteDetailsHolder holder, int position) {
        holder.text.setText(details.get(position));
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public void setDetails(ArrayList<String> details) {
        this.details = details;
    }

    public ArrayList<String> getDetails() {
        return this.details;
    }

    public static class RouteDetailsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.details)
        TextView text;

        public RouteDetailsHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }    }

}