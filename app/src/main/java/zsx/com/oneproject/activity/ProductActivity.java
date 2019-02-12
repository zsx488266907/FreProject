package zsx.com.oneproject.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import zsx.com.oneproject.R;
import zsx.com.oneproject.fragment.Fragment01;
import zsx.com.oneproject.fragment.Fragment02;

public class ProductActivity extends AppCompatActivity {
    private BottomNavigationView navigation;
    private ViewPager viewPager;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.but1:

                  viewPager.setCurrentItem(0);
                case  R.id.but2:
                    viewPager.setCurrentItem(1);

            }
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        viewPager = findViewById(R.id.viewpager);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return  new Fragment01();
                         default:
                            return  new Fragment02();

                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
            switch (i){
                case 0:
                    navigation.setSelectedItemId(R.id.but1);
                break;
                default:
                    navigation.setSelectedItemId(R.id.but2);
                    break;

            }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

}
