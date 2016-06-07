package fer.ruazosa.ruazosa16_zet.tram;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fer.ruazosa.ruazosa16_zet.data.ExampleData;
import fer.ruazosa.ruazosa16_zet.wrappers.Linija;
import fer.ruazosa.ruazosa16_zet.R;
import fer.ruazosa.ruazosa16_zet.adapters.TrasaAdapter;

public class TramDnevni extends Fragment {


    private List<Linija> linije;
    private RecyclerView rv;

    public TramDnevni() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tram_dnevni, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linije = ExampleData.getTramDnevni();
        rv = (RecyclerView) view.findViewById(R.id.list);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        rv.setLayoutManager(llm);
        TrasaAdapter pa = new TrasaAdapter(linije, getContext());
        rv.setAdapter(pa);
    }

}
