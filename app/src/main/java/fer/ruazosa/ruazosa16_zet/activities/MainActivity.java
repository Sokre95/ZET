package fer.ruazosa.ruazosa16_zet.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.R;
import fer.ruazosa.ruazosa16_zet.adapters.ViewPagerAdapter;
import fer.ruazosa.ruazosa16_zet.bus.BusDnevni;
import fer.ruazosa.ruazosa16_zet.bus.BusNocni;
import fer.ruazosa.ruazosa16_zet.home.CloseFragment;
import fer.ruazosa.ruazosa16_zet.home.FavouritesFragment;
import fer.ruazosa.ruazosa16_zet.tram.TramDnevni;
import fer.ruazosa.ruazosa16_zet.tram.TramNocni;

public class MainActivity extends AppCompatActivity {

    public static final int HOME = 0;
    public static final int BUS = 1;
    public static final int TRAM = 2;

    private int instance;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view) NavigationView navigationView;
    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.viewPager) ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    ActionBarDrawerToggle actionBarDrawerToggle;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        if(savedInstanceState != null) {
            getSupportActionBar().setTitle(savedInstanceState.getCharSequence("title"));
            getSupportActionBar().setTitle("Home");
            instance = savedInstanceState.getInt("STATE");
            switch (instance) {
                case HOME :
                    setHomeView();
                    break;
                case BUS :
                    setBusView();
                    break;
                case TRAM :
                    setTramView();
                    break;
                default:
                    break;
            }
        } else {
            setHomeView();
        }

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_item:
                        getSupportActionBar().setTitle("Home");
                        setHomeView();
                        break;
                    case R.id.bus_item:
                        getSupportActionBar().setTitle("Bus");
                        setBusView();
                        break;
                    case R.id.tram_item:
                        getSupportActionBar().setTitle("Tram");
                        setTramView();
                        break;
                    default:
                        break;
                }
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });

    }

    private void setHomeView() {
        getSupportActionBar().setTitle("Home");
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new CloseFragment(), "CLOSE");
        viewPagerAdapter.addFragments(new FavouritesFragment(), "FAVORITES");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        instance = HOME;
    }

    private void setBusView() {
        getSupportActionBar().setTitle("Bus");
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new BusDnevni(), "DAILY BUS");
        viewPagerAdapter.addFragments(new BusNocni(), "NIGHT BUS");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        instance = BUS;
    }

    private void setTramView() {
        getSupportActionBar().setTitle("Tram");
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new TramDnevni(), "DAILY TRAM");
        viewPagerAdapter.addFragments(new TramNocni(), "NIGHT TRAM");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        instance = TRAM;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        CharSequence title = getSupportActionBar().getTitle();
        outState.putCharSequence("title", title);
        outState.putInt("STATE", instance);
    }

}
