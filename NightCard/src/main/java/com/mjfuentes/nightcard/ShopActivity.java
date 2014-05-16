package com.mjfuentes.nightcard;

import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mjfuentes.nightcard.Adapters.BeersAdapter;
import com.mjfuentes.nightcard.Adapters.ClientAdapter;
import com.mjfuentes.nightcard.Adapters.DrinksAdapter;
import com.mjfuentes.nightcard.Controller.DrinksController;
import com.mjfuentes.nightcard.Model.BasicFragment;
import com.mjfuentes.nightcard.Model.Trago;


public class ShopActivity extends Activity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    BasicFragment[] fragments = new BasicFragment[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrinksController.fillDrinks();
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                BasicFragment fragment = fragments[position];
                if (fragment != null) {
                    fragment.updateFragment();
                }
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    fragments[0] = ClienteFragment.newInstance();
                    return fragments[0];
                case 1:
                    fragments[1] = CervezasFragment.newInstance();
                    return fragments[1];
                case 2:
                    fragments[2] = TragosFragment.newInstance();
                    return fragments[2];
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    public static class ClienteFragment extends BasicFragment {
        private TextView saldoCliente;
        private TextView gastoCliente;
        private TextView finalCliente;
        private ClientAdapter adapter;
        public static ClienteFragment newInstance() {
            ClienteFragment fragment = new ClienteFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        public ClienteFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_cliente, container, false);
            adapter = new ClientAdapter(inflater,container,this);
            saldoCliente = (TextView) rootView.findViewById(R.id.saldoCliente);
            gastoCliente = (TextView) rootView.findViewById(R.id.gastoCliente);
            finalCliente = (TextView) rootView.findViewById(R.id.finalCliente);
            ListView list = (ListView) rootView.findViewById(R.id.selectedItems);
            list.setAdapter(adapter);
            this.refreshData();
            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();
        }

        public void refreshData(){
            saldoCliente.setText(String.valueOf(DrinksController.getUserAmount()));
            gastoCliente.setText(String.valueOf(DrinksController.getTotalAmount()));
            finalCliente.setText(String.valueOf(DrinksController.getUserAmount() - DrinksController.getTotalAmount()));
        }

        @Override
        public void updateFragment() {
            refreshData();
            adapter.notifyDataSetChanged();
        }
    }

    public static class CervezasFragment extends BasicFragment {
        private TextView saldo;
        private BeersAdapter adapter;
        public static CervezasFragment newInstance() {
            CervezasFragment fragment = new CervezasFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        public CervezasFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_alcohol, container, false);
            saldo = (TextView) rootView.findViewById(R.id.saldoCliente);
            adapter = new BeersAdapter(inflater,container,this);
            ListView list = (ListView) rootView.findViewById(R.id.drinks);
            list.setAdapter(adapter);
            this.refreshData();
            return rootView;
        }

        public void refreshData(){
            saldo.setText("Saldo: " + String.valueOf(DrinksController.getUserAmount() - DrinksController.getTotalAmount()));
        }

        @Override
        public void onResume() {
            super.onResume();
        }

        @Override
        public void updateFragment() {
            adapter.notifyDataSetChanged();
            this.refreshData();
        }
    }

    public static class TragosFragment extends BasicFragment {
        private DrinksAdapter adapter;
        private TextView saldo;
        public static TragosFragment newInstance() {
            TragosFragment fragment = new TragosFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        public TragosFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_alcohol, container, false);
            saldo = (TextView) rootView.findViewById(R.id.saldoCliente);
            saldo.setText("Saldo: " + String.valueOf(DrinksController.getUserAmount() - DrinksController.getTotalAmount()));
            adapter = new DrinksAdapter(inflater,container,this);
            ListView list = (ListView) rootView.findViewById(R.id.drinks);
            list.setAdapter(adapter);
            this.refreshData();
            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();
        }

        public void refreshData(){
            saldo.setText("Saldo: " + String.valueOf(DrinksController.getUserAmount() - DrinksController.getTotalAmount()));
        }

        @Override
        public void updateFragment() {
            adapter.notifyDataSetChanged();
            this.refreshData();
        }
    }
}
