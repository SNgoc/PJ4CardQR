package fpt.aptech.projectcard;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import fpt.aptech.projectcard.databinding.ActivityMainBinding;
import fpt.aptech.projectcard.session.SessionManager;
import fpt.aptech.projectcard.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private TextView txtUsername;
    private ImageView imgAvatarNavHeader;
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
        //set text and avatar for nav_header_main
        View nav_header_view = navigationView.getHeaderView(0);
        txtUsername = nav_header_view.findViewById(R.id.textViewUsername);
        imgAvatarNavHeader = nav_header_view.findViewById(R.id.imgAvatarNavHeader);
        String username = SessionManager.getSaveUsername();
        String avatarUrl = SessionManager.getSaveLinkImage();
        txtUsername.setText(username + "'s Smart Card");

        try {
            //set view avatar nav header
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(avatarUrl).getContent());
            imgAvatarNavHeader.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //=========
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_buycard)
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

            case R.id.nav_logout: {
                txtUsername.setText(R.string.nav_header_subtitle);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;
            }
        }
        //close navigation menu drawer after click item to transfer to other activity
        NavigationUI.onNavDestinationSelected(item, navController);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //==============================END NAVIGATION MENU ================================================

    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStackImmediate();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }
}