package net.japan.kana.hakana.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import net.japan.kana.hakana.R;
import net.japan.kana.hakana.core.BaseActivity;
import net.japan.kana.hakana.fragment.FastQuizFragment;
import net.japan.kana.hakana.widgets.DrawerArrowDrawable;

import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity{
    @InjectView(R.id.layout_drawer)
    DrawerLayout mDrawer;
    @InjectView(R.id.layout_drawer_menu)
    LinearLayout mDrawerMenu;
    @InjectView(R.id.drawer_menu_kana_btn)
    Button mMenuKanaBtn;
    @InjectView(R.id.drawer_menu_settings_btn)
    Button mMenuSettingsBtn;
    @InjectView(R.id.drawer_menu_about_btn)
    Button mMenuAboutBtn;
    @InjectView(R.id.drawer_menu_test_btn)
    Button mMenuQuizBtn;

    private Button[] mMenuButtons = new Button[4];

    private MenuDrawerListener mDrawerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing of array of menu items
        mMenuButtons[0] = mMenuKanaBtn;
        mMenuButtons[1] = mMenuSettingsBtn;
        mMenuButtons[2] = mMenuAboutBtn;
        mMenuButtons[3] = mMenuQuizBtn;

        initDrawer();
        showFastQuiz();
    }

    private void showFastQuiz(){
        getFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new FastQuizFragment()).commit();
    }

    private void showKana(){
        getFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new FastQuizFragment()).commit();
    }

    private void showSettings(){
        getFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new FastQuizFragment()).commit();
    }

    private void showAbout(){
        getFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new FastQuizFragment()).commit();
    }

    private void showQuiz(){
        getFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new FastQuizFragment()).commit();
    }

    //Called when user clicks on menu in action bar
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

    //Initializing of drawer and arrow on the action bar
    private void initDrawer(){
        DrawerArrowDrawable arrow = new DrawerArrowDrawable(getResources());
        arrow.setStrokeColor(getResources().getColor(android.R.color.white));
        getActionBar().setIcon(arrow);
        mDrawerListener = new MenuDrawerListener(arrow, getString(R.string.app_name), getString(R.string.fast_quiz_title));
        mDrawer.setDrawerListener(mDrawerListener);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if(!getPreference().isUserKnowAboutDrawer()){
            mDrawer.openDrawer(mDrawerMenu);
            getPreference().setUserKnowAboutDrawer(true);
        }else {
            getActionBar().setTitle(getString(R.string.fast_quiz_title));
        }
    }

    @OnClick({R.id.drawer_menu_kana_btn, R.id.drawer_menu_settings_btn, R.id.drawer_menu_test_btn, R.id.drawer_menu_about_btn})
    public void onDrawerMenuClicked(View v){
        mDrawer.closeDrawer(mDrawerMenu);
        if(v.isSelected()){ //Menu already chosen
            return;
        }

        //deselecting all menu items
        for (Button b: mMenuButtons){
            b.setSelected(false);
        }

        //set id of clicked item for listener. It will changed fragment
        mDrawerListener.setLastBtnClicked(v.getId());

        //selecting current clicked item
        v.setSelected(true);
    }

    //Response for rotating of arrow and changing of fragment when menu item was clicked
    private class MenuDrawerListener extends DrawerLayout.SimpleDrawerListener{
        private DrawerArrowDrawable arrow;
        private final String titleForOpened;
        private final String titleForClosed;
        private int lastBtnClicked = -1;

        private MenuDrawerListener(DrawerArrowDrawable arrow, String titleForOpened, String titleForClosed){
            this.arrow = arrow;
            this.titleForOpened = titleForOpened;
            this.titleForClosed = titleForClosed;
        }

        @Override
        public void onDrawerSlide(View view, float slideOffset){
            // Sometimes slideOffset ends up so close to but not quite 1 or 0.
            if (slideOffset >= .995) {
                arrow.setFlip(true);
            } else if (slideOffset <= .005) {
                arrow.setFlip(false);
            }

            //set up new arrow position
            arrow.setParameter(slideOffset);
        }

        @Override
        public void onDrawerClosed(View drawerView){
            //drawer was closed, check is it was a menu item
            switch(lastBtnClicked){
                case R.id.drawer_menu_about_btn:
                    showAbout();
                    break;
                case R.id.drawer_menu_settings_btn:
                    showSettings();
                    break;
                case R.id.drawer_menu_kana_btn:
                    showKana();
                    break;
                case R.id.drawer_menu_test_btn:
                    showQuiz();
                    break;
            }
            lastBtnClicked = -1;
            getActionBar().setTitle(titleForClosed);
        }

        @Override
        public void onDrawerOpened(View drawerView){
            getActionBar().setTitle(titleForOpened);
        }

        public void setLastBtnClicked(int lastBtnClicked){
            this.lastBtnClicked = lastBtnClicked;
        }
    }
}
