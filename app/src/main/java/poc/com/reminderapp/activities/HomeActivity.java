package poc.com.reminderapp.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import poc.com.reminderapp.R;
import poc.com.reminderapp.fragments.CreateReminderFragment;
import poc.com.reminderapp.fragments.HomeFragment;

public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton createReminderFAB;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initListeners();
        loadHomeFragment();
    }

    private void initListeners() {
        createReminderFAB = findViewById(R.id.create_reminder_fab);
        createReminderFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCreateReminderFragment();
            }
        });
    }

    private void loadHomeFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, HomeFragment.newInstance()).commit();
    }

    private void loadCreateReminderFragment() {
        createReminderFAB.setVisibility(View.GONE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, CreateReminderFragment.newInstance()).addToBackStack("Create").commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!(getSupportFragmentManager().findFragmentById(R.id.container) instanceof HomeFragment)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            createReminderFAB.setVisibility(View.VISIBLE);
        }
        super.onBackPressed();

    }
}
