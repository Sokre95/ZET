package fer.ruazosa.ruazosa16_zet.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.R;
import fer.ruazosa.ruazosa16_zet.adapters.RouteAdapter;
import fer.ruazosa.ruazosa16_zet.model.Line;

public class FavouritesFragment extends Fragment {

    private static final String FAVOURITES = "FAVOURITES";

    ArrayList<Line> favouriteLines = new ArrayList<>();

    RouteAdapter favouritesAdapter;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    public FavouritesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        updateFavourites();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        favouritesAdapter = new RouteAdapter(favouriteLines, getContext());
        recyclerView.setAdapter(favouritesAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateFavourites();
        favouritesAdapter.notifyDataSetChanged();
    }

    private void updateFavourites(){
        favouriteLines.clear();
        Map<String, String> favouriteLinesMap = (Map<String, String>) this.getActivity().getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE).getAll();
        for (String lineId : favouriteLinesMap.keySet()) {
            favouriteLines.add(new Line(Integer.parseInt(lineId), favouriteLinesMap.get(lineId)));
        }
    }
}
