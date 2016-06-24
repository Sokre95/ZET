package fer.ruazosa.ruazosa16_zet.home;


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
import fer.ruazosa.ruazosa16_zet.adapters.DisplayAdapter;
import fer.ruazosa.ruazosa16_zet.R;


public class CloseFragment extends Fragment {

    @BindView(R.id.list) RecyclerView recyclerView;

    public CloseFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_close, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //List<InfoDisplay> displays = ExampleData.getRouteInfo();
        //ButterKnife.bind(this, view);
        //recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        //DisplayAdapter displayAdapter = new DisplayAdapter(displays);
        //recyclerView.setAdapter(displayAdapter);
    }
}
