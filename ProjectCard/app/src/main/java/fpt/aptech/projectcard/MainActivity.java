package fpt.aptech.projectcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import fpt.aptech.projectcard.databinding.ActivityMainBinding;
import fpt.aptech.projectcard.ui.home.HomeFragment;
import fpt.aptech.projectcard.ui.login.LoginFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //navigation
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private NavController navController;
    //change fragment
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
//        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //binding data as same as findViewById
        drawer = binding.drawerLayout;
        navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_buycard, R.id.nav_login)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    //change action bar for each fragment
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    //============================NAVIGATION MENU EVENT CLICK===================================================
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.nav_login: {
                if (item.getTitle().equals("Logout")) {
                    //if logout, change title menu item Logout to Login
                    Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_LONG).show();
                    item.setTitle("Login");
                    //set email to default after logout
                    TextView txtEmail = findViewById(R.id.textViewMail);
                    txtEmail.setText(R.string.nav_header_subtitle);
                    //change to fragment_login
                    fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
                    LoginFragment loginFragment = new LoginFragment();
                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main,loginFragment);
                    fragmentTransaction.commit();
                }
                break;
            }
        }
        //close navigation menu drawer after click item to transfer to other activity
        NavigationUI.onNavDestinationSelected(item, navController);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //==============================END NAVIGATION MENU ================================================
}