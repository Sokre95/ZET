package fer.ruazosa.ruazosa16_zet.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import fer.ruazosa.ruazosa16_zet.R;
import fer.ruazosa.ruazosa16_zet.bus.BusFragment;
import fer.ruazosa.ruazosa16_zet.home.HomeFragment;
import fer.ruazosa.ruazosa16_zet.tram.TramFragment;

public class MainActivity extends AppCompatActivity {

    public static final String HOME_FRAGMENT = "home";
    public static final String BUS_FRAGMENT = "bus";
    public static final String TRAM_FRAGMENT = "tram";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view) NavigationView navigationView;

    ActionBarDrawerToggle actionBarDrawerToggle;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new HomeFragment(), HOME_FRAGMENT);
        fragmentTransaction.commit();

        if(savedInstanceState != null) {
            getSupportActionBar().setTitle(savedInstanceState.getCharSequence("title"));
            Fragment currentFragment = getSupportFragmentManager().getFragment(savedInstanceState, "fragment");
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, currentFragment);
            fragmentTransaction.commit();
        } else {
            getSupportActionBar().setTitle("Home");
        }

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.home_item:
                        getSupportActionBar().setTitle("Home");
                        fragmentTransaction.replace(R.id.main_container, new HomeFragment(), HOME_FRAGMENT);
                        break;
                    case R.id.bus_item:
                        getSupportActionBar().setTitle("Bus");
                        fragmentTransaction.replace(R.id.main_container, new BusFragment(), BUS_FRAGMENT);
                        break;
                    case R.id.tram_item:
                        getSupportActionBar().setTitle("Tram");
                        fragmentTransaction.replace(R.id.main_container, new TramFragment(), TRAM_FRAGMENT);
                        break;
                    default:
                        break;
                }
                fragmentTransaction.commit();
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });

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
        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT);
        if(currentFragment == null) {
            currentFragment = getSupportFragmentManager().findFragmentByTag(BUS_FRAGMENT);
            if(currentFragment == null) {
                currentFragment = getSupportFragmentManager().findFragmentByTag(TRAM_FRAGMENT);
            }
        }
        getSupportFragmentManager().putFragment(outState, "fragment", currentFragment);
    }
}
