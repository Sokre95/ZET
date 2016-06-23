package fer.ruazosa.ruazosa16_zet.tram;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.R;
import fer.ruazosa.ruazosa16_zet.adapters.ViewPagerAdapter;
import fer.ruazosa.ruazosa16_zet.bus.BusDnevni;
import fer.ruazosa.ruazosa16_zet.bus.BusNocni;
import fer.ruazosa.ruazosa16_zet.model.Line;

public class TramFragment extends Fragment {

    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.viewPager) ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    public static final String DAILY_TRAM = "Daily tram";
    public static final String NIGHT_TRAM = "Night tram";
    public static final String DAILY_TRAM_DATA = "Daily tram data";
    public static final String NIGHT_TRAM_DATA = "Night tram data";

    public TramFragment() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu, menu);
        SearchManager searchManager =
                (SearchManager) ((AppCompatActivity)getActivity()).getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(((AppCompatActivity)getActivity()).getComponentName()));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_tram, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        if(savedInstanceState != null) {
            ArrayList<Line> dailyTramData = (ArrayList<Line>)
                    savedInstanceState.getSerializable(DAILY_TRAM_DATA);
            ArrayList<Line> nightTramData = (ArrayList<Line>)
                    savedInstanceState.getSerializable(NIGHT_TRAM_DATA);
            Bundle daily = new Bundle();
            Bundle night = new Bundle();
            daily.putSerializable("DATA", dailyTramData);
            night.putSerializable("DATA", nightTramData);
            TramDnevni tramDnevni = new TramDnevni();
            TramNocni tramNocni = new TramNocni();
            tramDnevni.setArguments(daily);
            tramNocni.setArguments(night);
            viewPagerAdapter.addFragments(tramDnevni, DAILY_TRAM);
            viewPagerAdapter.addFragments(tramNocni, NIGHT_TRAM);
        } else {
            viewPagerAdapter.addFragments(new TramDnevni(), DAILY_TRAM);
            viewPagerAdapter.addFragments(new TramNocni(), NIGHT_TRAM);
        }

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TramDnevni tramDnevni = (TramDnevni) viewPagerAdapter.getItem(0);
        TramNocni tramNocni = (TramNocni) viewPagerAdapter.getItem(1);
        outState.putSerializable(DAILY_TRAM_DATA, tramDnevni.getData());
        outState.putSerializable(NIGHT_TRAM_DATA, tramNocni.getData());
    }
}
