package fer.ruazosa.ruazosa16_zet.bus;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.R;
import fer.ruazosa.ruazosa16_zet.adapters.ViewPagerAdapter;
import fer.ruazosa.ruazosa16_zet.model.Line;

public class BusFragment extends Fragment {

    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.viewPager) ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    public static final String DAILY_BUS = "Daily bus";
    public static final String NIGHT_BUS = "Night bus";
    public static final String DAILY_BUS_DATA = "Daily bus data";
    public static final String NIGHT_BUS_DATA = "Night bus data";

    public BusFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        return inflater.inflate(R.layout.fragment_bus, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        if(savedInstanceState != null) {
            ArrayList<Line> dailyBusData = (ArrayList<Line>)
                    savedInstanceState.getSerializable(DAILY_BUS_DATA);
            ArrayList<Line> nightBusData = (ArrayList<Line>)
                    savedInstanceState.getSerializable(NIGHT_BUS_DATA);
            Bundle daily = new Bundle();
            Bundle night = new Bundle();
            daily.putSerializable("DATA", dailyBusData);
            night.putSerializable("DATA", nightBusData);
            BusDnevni busDnevni = new BusDnevni();
            BusNocni busNocni = new BusNocni();
            busDnevni.setArguments(daily);
            busNocni.setArguments(night);
            viewPagerAdapter.addFragments(busDnevni, DAILY_BUS);
            viewPagerAdapter.addFragments(busNocni, NIGHT_BUS);
        } else {
            viewPagerAdapter.addFragments(new BusDnevni(), DAILY_BUS);
            viewPagerAdapter.addFragments(new BusNocni(), NIGHT_BUS);
        }

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BusDnevni busDnevni = (BusDnevni) viewPagerAdapter.getItem(0);
        BusNocni busNocni = (BusNocni) viewPagerAdapter.getItem(1);
        outState.putSerializable(DAILY_BUS_DATA, busDnevni.getData());
        outState.putSerializable(NIGHT_BUS_DATA, busNocni.getData());
    }
}
