package com.example.emergencyapp;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

// Make sure to import your actual Fragment classes here
import com.example.emergencyapp.ui.home.HomeSOSFragment;
import com.example.emergencyapp.ui.maps.MapsFragment;
import com.example.emergencyapp.ui.alerts.NotificationsHubFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // Set the default screen on startup (Teammate B's Home SOS Dashboard)
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeSOSFragment()).commit();
        }

        // Handle navigation item selection clicks
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    selectedFragment = new HomeSOSFragment();
                } else if (itemId == R.id.nav_contacts) {
                    selectedFragment = new ContactsListFragment(); // Teammate A's fragment
                } else if (itemId == R.id.nav_map) {
                    selectedFragment = new MapsFragment();
                } else if (itemId == R.id.nav_alerts) {
                    selectedFragment = new NotificationsHubFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
                return false;
            }
        });
    }
}