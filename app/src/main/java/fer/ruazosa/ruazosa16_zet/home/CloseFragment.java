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

import fer.ruazosa.ruazosa16_zet.adapters.DisplayAdapter;
import fer.ruazosa.ruazosa16_zet.data.ExampleData;
import fer.ruazosa.ruazosa16_zet.wrappers.InfoDisplay;
import fer.ruazosa.ruazosa16_zet.R;


public class CloseFragment extends Fragment {

    private RecyclerView rv;

    public CloseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_close, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<InfoDisplay> displays = ExampleData.getRouteInfo();
        rv = (RecyclerView) view.findViewById(R.id.list);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        rv.setLayoutManager(llm);
        DisplayAdapter displayAdapter = new DisplayAdapter(displays);
        rv.setAdapter(displayAdapter);
    }
}
