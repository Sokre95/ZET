package fer.ruazosa.ruazosa16_zet.bus;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.adapters.RouteAdapter;
import fer.ruazosa.ruazosa16_zet.data.ExampleData;
import fer.ruazosa.ruazosa16_zet.wrappers.Line;
import fer.ruazosa.ruazosa16_zet.R;


public class BusNocni extends Fragment {

    private List<Line> linije;
    @BindView(R.id.list) RecyclerView recyclerView;
    RouteAdapter routeAdapter;

    public BusNocni() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bus_nocni, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linije = ExampleData.getBusNocni();
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        routeAdapter = new RouteAdapter(getContext());
        recyclerView.setAdapter(routeAdapter);
    }
}
