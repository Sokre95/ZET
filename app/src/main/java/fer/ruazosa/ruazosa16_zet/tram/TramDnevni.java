package fer.ruazosa.ruazosa16_zet.tram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import java.io.IOException;
import java.util.ArrayList;

import fer.ruazosa.ruazosa16_zet.LineView;
import fer.ruazosa.ruazosa16_zet.R;
import fer.ruazosa.ruazosa16_zet.activities.SearchActivity;
import fer.ruazosa.ruazosa16_zet.model.Line;
import fer.ruazosa.ruazosa16_zet.presenters.DailyTramPresenter;
import fer.ruazosa.ruazosa16_zet.LineFragment;
import fer.ruazosa.ruazosa16_zet.presenters.MvpLceRxPresenter;
import fer.ruazosa.ruazosa16_zet.ZetWebService;

public class TramDnevni extends LineFragment {

    public TramDnevni() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_tram_dnevni, container, false);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        try {
            presenter.subscribe(ZetWebService.getInstance().getDailyTramRoutes(), pullToRefresh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MvpLceRxPresenter createPresenter() {
        return new DailyTramPresenter();
    }

    @Override
    protected void setQueryHandler() {
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.setAction(Intent.ACTION_SEARCH);
                Bundle bundle = new Bundle();
                bundle.putString("QUERY", query);
                bundle.putSerializable("DATA", routeAdapter.getLines());
                intent.putExtra("Bundle", bundle);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        };

        searchView.setOnQueryTextListener(queryTextListener);
    }
}
