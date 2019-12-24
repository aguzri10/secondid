package io.github.aguzri10.second.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import io.github.aguzri10.second.R;
import io.github.aguzri10.second.fragment.HeadlineFragment;
import io.github.aguzri10.second.fragment.HomeFragment;
import io.github.aguzri10.second.fragment.OthersFragment;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // set default ke HomeFragment
        loadFragment(new HomeFragment());

        // Inisialisasi bottomnavigationview
        bottomNavigationView = findViewById(R.id.bottom_nav);

        // beri listener pada saat item menu bottomnav terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setElevation(0);
    }

    // method untuk load fragment
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.page_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.home:
                fragment = new HomeFragment();
                break;

            case R.id.headline:
                fragment = new HeadlineFragment();
                break;

            case R.id.others:
                fragment = new OthersFragment();
                break;
        }

        return loadFragment(fragment);
    }
}
