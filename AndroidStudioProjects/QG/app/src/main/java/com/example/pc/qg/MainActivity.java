 package com.example.pc.qg;

 import android.os.Bundle;
 import android.support.annotation.NonNull;
 import android.support.design.widget.NavigationView;
 import android.support.design.widget.TabLayout;
 import android.support.v4.view.GravityCompat;
 import android.support.v4.view.ViewPager;
 import android.support.v4.widget.DrawerLayout;
 import android.support.v7.app.ActionBarDrawerToggle;
 import android.support.v7.app.AppCompatActivity;
 import android.support.v7.widget.Toolbar;
 import android.view.Menu;
 import android.view.MenuInflater;
 import android.view.MenuItem;
 import android.view.View;

 public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private ViewPagerAdapter mViewPagerAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        mToolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        View view=getLayoutInflater().inflate(R.layout.custom_toolbar,null);
        setViewPager();
        mToolbar.addView(view);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void setViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);
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
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.menu, menu);
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

     @Override
     public boolean onNavigationItemSelected(@NonNull MenuItem item) {
         // Handle navigation view item clicks here.
         int id = item.getItemId();

         if (id == R.id.nav_camera) {
             // Handle the camera action
         } else if (id == R.id.nav_gallery) {

         } else if (id == R.id.nav_slideshow) {

         } else if (id == R.id.nav_manage) {

         } else if (id == R.id.nav_share) {

         } else if (id == R.id.nav_send) {

         }

         DrawerLayout drawer =  findViewById(R.id.drawer_layout);
         drawer.closeDrawer(GravityCompat.START);
         return true;     }
 }
//     setSupportActionBar(toolbar);
//     View logo = getLayoutInflater().inflate(R.layout.view_logo, null);
//    toolbar.addView(logo);
