package net.japan.kana.hakana.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import net.japan.kana.hakana.R;
import net.japan.kana.hakana.core.BaseActivity;
import net.japan.kana.hakana.widgets.DrawerArrowDrawable;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity{
    @InjectView(R.id.layout_drawer)
    DrawerLayout mDrawer;
    @InjectView(R.id.layout_drawer_menu)
    LinearLayout mDrawerMenu;

    @InjectView(R.id.drawer_menu_kana_btn)
    Button mKanaBtn;
    @InjectView(R.id.drawer_menu_settings_btn)
    Button mSettingsBtn;
    @InjectView(R.id.drawer_menu_about_btn)
    Button mAboutBtn;
    @InjectView(R.id.drawer_menu_test_btn)
    Button mTestbtn;
    private MenuDrawerListener mDrawerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            if(mDrawer.isDrawerOpen(mDrawerMenu)){
                mDrawer.closeDrawer(mDrawerMenu);
            }else{
                mDrawer.openDrawer(mDrawerMenu);
            }
        }
        return true;
    }

    private void initDrawer(){
        //Initializing of drawer and arrow on the action abr
        DrawerArrowDrawable arrow = new DrawerArrowDrawable(getResources());
        arrow.setStrokeColor(getResources().getColor(android.R.color.white));
        getActionBar().setIcon(arrow);
        mDrawerListener = new MenuDrawerListener(arrow);
        mDrawer.setDrawerListener(mDrawerListener);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick({R.id.drawer_menu_kana_btn, R.id.drawer_menu_settings_btn, R.id.drawer_menu_test_btn, R.id.drawer_menu_about_btn})
    public void onDrawerMenuClicked(View v){
        mDrawerListener.setLastBtnClicked(v.getId());
        mDrawer.closeDrawer(mDrawerMenu);
    }

    private class MenuDrawerListener extends DrawerLayout.SimpleDrawerListener{
        private DrawerArrowDrawable arrow;
        private int lastBtnClicked;

        private MenuDrawerListener(DrawerArrowDrawable arrow){
            this.arrow = arrow;
        }

        @Override
        public void onDrawerSlide(View view, float slideOffset){
            // Sometimes slideOffset ends up so close to but not quite 1 or 0.
            if (slideOffset >= .995) {
                arrow.setFlip(true);
            } else if (slideOffset <= .005) {
                arrow.setFlip(false);
            }

            arrow.setParameter(slideOffset);
        }

        @Override
        public void onDrawerClosed(View drawerView){
            if(lastBtnClicked != 0){ //Menu was clicked

            }
            lastBtnClicked = 0;
        }

        public void setLastBtnClicked(int lastBtnClicked){
            this.lastBtnClicked = lastBtnClicked;
        }
    }
}
