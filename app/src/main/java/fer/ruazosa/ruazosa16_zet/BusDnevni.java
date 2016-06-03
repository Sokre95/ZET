package fer.ruazosa.ruazosa16_zet;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BusDnevni extends Fragment {

    private List<Linija> linije = new ArrayList<>();
    private RecyclerView rv;

    public BusDnevni() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bus_dnevni, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        rv = (RecyclerView) view.findViewById(R.id.list);
        GridLayoutManager llm = new GridLayoutManager(view.getContext(), 2);
        rv.setLayoutManager(llm);
        TrasaAdapter pa = new TrasaAdapter(linije, getContext());
        rv.setAdapter(pa);
    }

    private void initData() {
        linije = new ArrayList<>();
        linije.add(new Linija("113", "LJUBLJANICA-JARUN"));
        linije.add(new Linija("215", "TRNAVA-KVATERNIKOV TRG"));
        linije.add(new Linija("231", "BORONGAJ-DUBEC"));
        linije.add(new Linija("269", "BORONGAJ-SESVETSKI KRALJEVEC"));
    }

}
