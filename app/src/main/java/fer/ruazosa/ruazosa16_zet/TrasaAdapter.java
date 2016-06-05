package fer.ruazosa.ruazosa16_zet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TrasaAdapter extends RecyclerView.Adapter<TrasaAdapter.TrasaViewHolder> {

    private List<Linija> linije;
    private TrasaViewHolder tva;
    private int position;
    private Context c;

    public TrasaAdapter(List<Linija> linije, Context c) {
        this.linije = linije;
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
        holder.broj_linije.setText(linije.get(position).getBrojLinije());
        holder.naziv_linije.setText(linije.get(position).getNazivLinije());
    }

    @Override
    public int getItemCount() {
        return linije.size();
    }

    public static class TrasaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CardView cv;
        public TextView broj_linije;
        public TextView naziv_linije;
        private Context c;

        public TrasaViewHolder(View view, Context c) {
            super(view);
            view.setOnClickListener(this);
            broj_linije = (TextView) view.findViewById(R.id.broj_linije);
            naziv_linije = (TextView) view.findViewById(R.id.naziv_linije);
            this.c = c;
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(c, DetailsActivity.class);
            this.c.startActivity(i);
        }
    }

}
