package mrh.framtest20160801.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mrh.framtest20160801.R;
import mrh.framtest20160801.configs.AppManager;
import mrh.framtest20160801.configs.utils.ListUtils;
import mrh.framtest20160801.ui.base.BaseAty;
import mrh.framtest20160801.ui.frg.FragFirst;
import mrh.framtest20160801.ui.frg.FragFour;
import mrh.framtest20160801.ui.frg.FragSecond;
import mrh.framtest20160801.ui.frg.FragThird;
import xutils.view.annotation.Event;
import xutils.view.annotation.ViewInject;


public class MainActivity extends BaseAty {
    @ViewInject(R.id.viewpager_vp)
    protected ViewPager viewPager;
    private FragmentManager myFm;
    @ViewInject(R.id.main2_index)
    private TextView tv_index;
    @ViewInject(R.id.main2_order)
    private TextView tv_order;
    @ViewInject(R.id.main2_water)
    private TextView tv_station;
    @ViewInject(R.id.main2_person)
    private TextView tvPerson;
    protected List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionBar.hide();
        if (ListUtils.isEmpty(fragmentList)) {
            fragmentList = new ArrayList<>();
        }
        fragmentList.add(new FragFirst());
        fragmentList.add(new FragSecond());
        fragmentList.add(new FragThird());
        fragmentList.add(new FragFour());
        myFm = getSupportFragmentManager();
        viewPager.setAdapter(new MyFragAdapter(myFm));
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        onTabChanged(tv_index);
                        break;
                    case 1:
                        onTabChanged(tv_order);
                        break;
                    case 2:
                        onTabChanged(tv_station);
                        break;
                    case 3:
                        onTabChanged(tvPerson);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tv_index.setSelected(true);
        tv_index.setTextColor(Color.parseColor("#2c82df"));

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initialized() {

    }

    @Override
    protected void requestData() {

    }

    @Event({R.id.main2_index, R.id.main2_order, R.id.main2_water, R.id.main2_person})
    private void onTabClick(View v) {
        switch (v.getId()) {
            case R.id.main2_index:
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.main2_order:
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.main2_water:
                viewPager.setCurrentItem(2, false);
                break;
            case R.id.main2_person:
                viewPager.setCurrentItem(3, false);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("moruhui", AppManager.mActivityStack.toString());
    }


    private void onTabChanged(TextView v) {
        tv_index.setSelected(tv_index.getId() == v.getId() ? true : false);
        tv_order.setSelected(tv_order.getId() == v.getId() ? true : false);
        tv_station.setSelected(tv_station.getId() == v.getId() ? true : false);
        tvPerson.setSelected(tvPerson.getId() == v.getId() ? true : false);
        tv_index.setTextColor(tv_index.getId() == v.getId() ? Color.parseColor("#2c82df") : Color.parseColor("#282828"));
        tv_order.setTextColor(tv_order.getId() == v.getId() ? Color.parseColor("#2c82df") : Color.parseColor("#282828"));
        tv_station.setTextColor(tv_station.getId() == v.getId() ? Color.parseColor("#2c82df") : Color.parseColor("#282828"));
        tvPerson.setTextColor(tvPerson.getId() == v.getId() ? Color.parseColor("#2c82df") : Color.parseColor("#282828"));

    }


    protected class MyFragAdapter extends FragmentPagerAdapter {
        public MyFragAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return ListUtils.getSize(fragmentList);
        }


    }

}
