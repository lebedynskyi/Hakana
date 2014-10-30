package net.japan.kana.hakana.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.LinearLayout;

import net.japan.kana.hakana.R;
import net.japan.kana.hakana.core.BaseActivity;
import net.japan.kana.hakana.widgets.DrawerArrowDrawable;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity{
    @InjectView(R.id.layout_drawer)
    DrawerLayout mDrawer;
    @InjectView(R.id.layout_drawer_menu)
    LinearLayout mDrawerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initDrawer();
    }

    private void initDrawer(){
        //Initializing of drawer and arrow on the action abr
        DrawerArrowDrawable arrow = new DrawerArrowDrawable(getResources());
        arrow.setStrokeColor(getResources().getColor(android.R.color.white));
        getActionBar().setIcon(arrow);
        DrawerArrowListener arrowListener = new DrawerArrowListener(arrow);
        mDrawer.setDrawerListener(arrowListener);
    }

    private class DrawerArrowListener extends DrawerLayout.SimpleDrawerListener{
        private DrawerArrowDrawable arrow;

        private DrawerArrowListener(DrawerArrowDrawable arrow){
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
    }
}
