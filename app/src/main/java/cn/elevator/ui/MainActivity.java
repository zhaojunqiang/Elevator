package cn.elevator.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import cn.elevator.R;
import cn.elevator.helper.FragmentHelper;
import cn.elevator.ui.mvp.home.HomeFragment;
import cn.elevator.ui.mvp.search.SearchFragment;
import cn.elevator.ui.mvp.setting.SettingFrament;

/**
 * 主 Activity
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mBottomNavigationView;
    private List<Fragment> fragments = null;
    private FragmentHelper helper;
    private HomeFragment mHomeFragment= new HomeFragment();
    private SearchFragment mSearchFragment = new SearchFragment();
    private SettingFrament mSettingFrament = new SettingFrament();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QMUIStatusBarHelper.translucent(this);
        mBottomNavigationView = findViewById(R.id.bottomNavigationView);
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        fragments = new ArrayList<>();
        fragments.add(mHomeFragment);
        fragments.add(mSearchFragment);
        fragments.add(mSettingFrament);

        helper = new FragmentHelper(getSupportFragmentManager(), R.id.contentView, fragments);
        helper.addAndShowFragment(0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                helper.showCurrentFragment(0);
                break;
            case R.id.action_search:
                helper.showCurrentFragment(1);
                break;
            case R.id.action_setting:
                helper.showCurrentFragment(2);
            default:
                break;
        }
        return true;
    }
}
