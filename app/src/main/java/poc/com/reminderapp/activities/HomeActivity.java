package poc.com.reminderapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import poc.com.reminderapp.R;
import poc.com.reminderapp.fragments.HomeFragment;

public class HomeActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loadFragment();
    }

    private void loadFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, HomeFragment.newInstance()).commit();
    }

}
