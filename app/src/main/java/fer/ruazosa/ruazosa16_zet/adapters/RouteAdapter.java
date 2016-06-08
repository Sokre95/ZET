package fer.ruazosa.ruazosa16_zet.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.activities.DetailsActivity;
import fer.ruazosa.ruazosa16_zet.wrappers.Line;
import fer.ruazosa.ruazosa16_zet.R;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.TrasaViewHolder> {

    private List<Line> lines = new ArrayList<>();
    private Context c;

    public RouteAdapter(Context c) {
        this.c = c;
    }

    public RouteAdapter(List<Line> lines, Context c) {
        this.lines = lines;
        this.c = c;
    }

    @Override
    public TrasaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_layout_route, null);
        TrasaViewHolder viewHolder = new TrasaViewHolder(itemLayoutView, c);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrasaViewHolder holder, int position) {
        holder.broj_linije.setText(lines.get(position).getBrojLinije());
        holder.naziv_linije.setText(lines.get(position).getNazivLinije());
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    @Override
    public int getItemCount() {
        return lines.size();
    }

    public static class TrasaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context c;

        @BindView(R.id.broj_linije) TextView broj_linije;
        @BindView(R.id.naziv_linije) TextView naziv_linije;

        public TrasaViewHolder(View view, Context c) {
            super(view);
            this.c = c;
            view.setOnClickListener(this);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(c, DetailsActivity.class);
            this.c.startActivity(i);
        }
    }

}
