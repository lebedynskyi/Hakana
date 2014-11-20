package net.japan.kana.hakana.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import net.japan.kana.hakana.R;
import net.japan.kana.hakana.core.BaseActivity;
import net.japan.kana.hakana.core.BaseFragment;
import net.japan.kana.hakana.fragment.AboutFragment;
import net.japan.kana.hakana.fragment.FastQuizFragment;
import net.japan.kana.hakana.fragment.KanaFragment;
import net.japan.kana.hakana.fragment.QuizFragment;
import net.japan.kana.hakana.fragment.SettingsFragment;
import net.japan.kana.hakana.fragment.VocabListFragment;
import net.japan.kana.hakana.tools.Logging;
import net.japan.kana.hakana.widgets.DrawerArrowDrawable;

import java.util.List;

import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @InjectView(R.id.layout_drawer)
    DrawerLayout mDrawer;
    @InjectView(R.id.layout_drawer_menu)
    LinearLayout mDrawerMenu;

    @InjectViews({R.id.drawer_menu_kana_btn, R.id.drawer_menu_settings_btn, R.id.drawer_menu_about_btn,
            R.id.drawer_menu_test_btn, R.id.drawer_menu_vocab_btn})
    List<Button> mMenuButtons;

    private MenuDrawerListener mDrawerListener;

    //Just need save it for
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDrawer();
        showFastQuiz();
    }

    private void showFastQuiz() {
        showFragment(FastQuizFragment.class);
    }

    private void showKana() {
        showFragment(KanaFragment.class);
    }

    private void showSettings() {
        showFragment(SettingsFragment.class);
    }

    private void showAbout() {
        showFragment(AboutFragment.class);
    }

    private void showQuiz() {
        showFragment(QuizFragment.class);
    }

    private void showVocab() {
        showFragment(VocabListFragment.class);
    }

    private void showFragment(Class<? extends Fragment> clazz) {
        String fragmentTag = clazz.getSimpleName();
        Fragment fr = getFragmentManager().findFragmentByTag(fragmentTag);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();

        if (currentFragment != null) {
            tr.hide(currentFragment);
        }

        if (fr == null) {
            try {
                //Creating and adding new fragment
                fr = clazz.newInstance();
                Logging.d("Created new instance of " + fragmentTag + "/" + fr.toString());
                tr.add(R.id.main_fragment_container, fr, fragmentTag);
            } catch (Exception e) {
                e.printStackTrace();
                Logging.e("Cannot instantiate fragment for class -> " + fragmentTag);
                return;
            }
        } else {
            Logging.d("Reusing fragment for -> " + fragmentTag);
            //showing existing fragment
            tr.show(fr);
        }

        this.currentFragment = fr;
        //Set title for current fragment
        tr.commit();
    }

    //Called when user clicks on menu in action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawer.isDrawerOpen(mDrawerMenu)) {
                mDrawer.closeDrawer(mDrawerMenu);
            } else {
                mDrawer.openDrawer(mDrawerMenu);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Initializing of drawer and arrow on the action bar
    private void initDrawer() {
        DrawerArrowDrawable arrow = new DrawerArrowDrawable(getResources());
        arrow.setStrokeColor(getResources().getColor(android.R.color.white));
        getActionBar().setIcon(arrow);
        mDrawerListener = new MenuDrawerListener(arrow);
        mDrawer.setDrawerListener(mDrawerListener);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (!getPreference().isUserKnowAboutDrawer()) {
            mDrawer.openDrawer(mDrawerMenu);
            getPreference().setUserKnowAboutDrawer(true);
        }
    }

    @Override
    public void onBackPressed() {
        if (!(currentFragment instanceof BaseFragment) || !((BaseFragment) currentFragment).consumeBackEvent()) {
            super.onBackPressed();
        }
    }

    @OnClick({R.id.drawer_menu_kana_btn, R.id.drawer_menu_settings_btn, R.id.drawer_menu_test_btn, R.id.drawer_menu_about_btn, R.id.drawer_menu_vocab_btn})
    public void onDrawerMenuClicked(View v) {
        mDrawer.closeDrawer(mDrawerMenu);
        if (v.isSelected()) { //Menu already chosen
            return;
        }

        //deselecting all menu items
        for (Button b : mMenuButtons) {
            b.setSelected(false);
        }

        //set id of clicked item for listener. It will changed fragment
        mDrawerListener.setLastBtnClicked(v.getId());

        //selecting current clicked item
        v.setSelected(true);
    }

    //Response for rotating of arrow and changing of fragment when menu item was clicked
    private class MenuDrawerListener extends DrawerLayout.SimpleDrawerListener {
        private DrawerArrowDrawable arrow;
        private int lastBtnClicked = -1;

        private MenuDrawerListener(DrawerArrowDrawable arrow) {
            this.arrow = arrow;
        }

        @Override
        public void onDrawerSlide(View view, float slideOffset) {
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
        public void onDrawerClosed(View drawerView) {
            //drawer was closed, check is it was a menu item
            switch (lastBtnClicked) {
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
                case R.id.drawer_menu_vocab_btn:
                    showVocab();
                    break;
            }
            lastBtnClicked = -1;
        }

        @Override
        public void onDrawerOpened(View drawerView) {
        }

        public void setLastBtnClicked(int lastBtnClicked) {
            this.lastBtnClicked = lastBtnClicked;
        }
    }
}
