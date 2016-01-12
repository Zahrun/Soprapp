package gei.soprapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import gei.soprapp.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Thread miseAJourThread;

    public SectionsPagerAdapter getmSectionsPagerAdapter() {
        return mSectionsPagerAdapter;
    }

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public ViewPager getmViewPager() {
        return mViewPager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setLogo(R.mipmap.ic_launcher);
        ab.setDisplayUseLogoEnabled(true);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "onResume");
        miseAJourThread = new ThreadKeepUpdated(getmViewPager().getRootView());
        miseAJourThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause","onPause");
        if (miseAJourThread!=null && !miseAJourThread.isInterrupted()) {
            synchronized (miseAJourThread) {
                miseAJourThread.interrupt();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private Fragment fragmentSearch;
        private Fragment fragmentFavorites;
        private final FragmentManager mFragmentManager;
        
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragmentManager = fm;
        }

        public void switchSearchFragment(){
            int position = fragmentSearch.getArguments().getInt(FragmentAbstract.ARG_SECTION_NUMBER);
            boolean results = false;
            if (fragmentSearch instanceof FragmentSearch) {
                results = true;
            }
            mFragmentManager.beginTransaction().remove(fragmentSearch).commit();
            if (results) {
                fragmentSearch = FragmentSearchResults.newInstance(position);
            } else {
                fragmentSearch = FragmentSearch.newInstance(position);
            }
            notifyDataSetChanged();
        }

        public void switchFavoritesFragment(){
            int position = fragmentFavorites.getArguments().getInt(FragmentAbstract.ARG_SECTION_NUMBER);
            boolean reservations = false;
            if (fragmentFavorites instanceof FragmentFavorites) {
                reservations = true;
            }
            mFragmentManager.beginTransaction().remove(fragmentFavorites).commit();
            if (reservations) {
                fragmentFavorites = FragmentFavoritesReservations.newInstance(position);
                Log.e("Nouveau", "FragmentFavoritesReservations");
            } else {
                fragmentFavorites = FragmentFavorites.newInstance(position);
                Log.e("Nouveau", "FragmentFavorites");
            }
            if (fragmentFavorites==null) {
                Log.e("ERREUR ICI", "");
            }
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(final int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a FragmentAbstract (defined as a static inner class below).
            Fragment fragment;
            switch(position){
                case 0:
                    if (fragmentFavorites == null)
                    {
                        fragmentFavorites = FragmentFavorites.newInstance(position);
                    }
                    fragment = fragmentFavorites;
                    break;
                case 1:
                    if (fragmentSearch == null)
                    {
                        fragmentSearch = FragmentSearch.newInstance(position);
                    }
                    fragment = fragmentSearch;
                    break;
                case 2:
                    fragment = FragmentReservations.newInstance(position);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid section number");
            }
            return fragment;
        }

        @Override
        public int getItemPosition(Object object)
        {
            if (object instanceof FragmentSearch && fragmentSearch instanceof FragmentSearchResults)
                return POSITION_NONE;
            if (object instanceof FragmentSearchResults && fragmentSearch instanceof FragmentSearch)
                return POSITION_NONE;
            if (object instanceof FragmentFavorites && fragmentFavorites instanceof FragmentFavoritesReservations)
                return POSITION_NONE;
            if (object instanceof FragmentFavoritesReservations && fragmentFavorites instanceof FragmentReservations)
                return POSITION_NONE;
            return POSITION_UNCHANGED;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Salles favorites";
                case 1:
                    return "Recherche";
                case 2:
                    return "Reservations";
            }
            return null;
        }
    }
}
