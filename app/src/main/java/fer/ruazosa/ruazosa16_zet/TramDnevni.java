package fer.ruazosa.ruazosa16_zet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TramDnevni extends Fragment {


    private List<Linija> linije = new ArrayList<>();
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
        initData();
        rv = (RecyclerView) view.findViewById(R.id.list);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        rv.setLayoutManager(llm);
        TrasaAdapter pa = new TrasaAdapter(linije, getContext());
        rv.setAdapter(pa);
    }

    private void initData() {
        linije = new ArrayList<>();
        linije.add(new Linija("11", "DUBEC-ČRNOMEREC"));
        linije.add(new Linija("12", "DUBRAVA-LJUBLJANICA"));
        linije.add(new Linija("7", "DUBRAVA-SAVSKI MOST"));
        linije.add(new Linija("17", "PREČKO-BORONGAJ"));
    }

}
