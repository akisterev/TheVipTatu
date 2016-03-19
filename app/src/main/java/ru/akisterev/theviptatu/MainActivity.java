package ru.akisterev.theviptatu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ru.akisterev.theviptatu.MainFragments.FragmentMainEnrollMaster;
import ru.akisterev.theviptatu.MainFragments.FragmentMainFeedback;
import ru.akisterev.theviptatu.MainFragments.FragmentMainGallery;
import ru.akisterev.theviptatu.MainFragments.FragmentMainMain;
import ru.akisterev.theviptatu.MainFragments.FragmentMainNews;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentMainMain        fragmentMainMain;
    FragmentMainNews        fragmentMainNews;
    FragmentMainFeedback    fragmentMainFeedback;
    FragmentMainEnrollMaster fragmentMainEnrollMaster;
    FragmentMainGallery     fragmentMainGallery;

    FragmentManager         fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentMainMain            = new FragmentMainMain();
        fragmentMainNews            = new FragmentMainNews();
        fragmentMainFeedback        = new FragmentMainFeedback();
        fragmentMainEnrollMaster    = new FragmentMainEnrollMaster();
        fragmentMainGallery         = new FragmentMainGallery();

        fragmentManager             = getFragmentManager();

        Bundle bundle = new Bundle();
        bundle.putString("aaa", "fggg");
        fragmentMainFeedback.setArguments(bundle);

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragmentMainMain)
                .commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragmentMainFeedback)
                        .commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Fragment    fragment    = null;
        int         id          = item.getItemId();

        switch(id){
            case R.id.nav_main:
                setTitle(getString(R.string.app_name));
                fragment = fragmentMainMain;
                break;
            case R.id.nav_news:
                setTitle(getString(R.string.label_news));
                fragment = fragmentMainNews;
                break;
            case R.id.nav_feedback:
                setTitle(getString(R.string.label_feedback));
                fragment = fragmentMainFeedback;
                break;
            case R.id.nav_enroll_master:
                setTitle(getString(R.string.label_enroll_master));
                fragment = fragmentMainEnrollMaster;
                break;
            case R.id.nav_gallery:
                setTitle(getString(R.string.label_gallery));
                fragment = fragmentMainGallery;
                break;
        }

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
