package net.japan.kana.hakana.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.LinearLayout;

/**
 * Author Vitalii Lebedynskyi
 * Date 11/3/14
 */
public class CheckableLinearLayout extends LinearLayout implements Checkable{
    private static final int[] CHECKED_STATE_SET = { android.R.attr.state_checked };
    private boolean isChecked;

    public CheckableLinearLayout(Context context){
        super(context);
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked())
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        return drawableState;
    }

    @Override
    public void setChecked(boolean checked){
        isChecked = checked;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++){
            View child = getChildAt(i);
            if(child instanceof Checkable){
                ((Checkable) child).setChecked(checked);
            }
        }

        refreshDrawableState();
    }

    @Override
    public boolean isChecked(){
        return isChecked;
    }

    @Override
    public void toggle(){
        setChecked(!isChecked());
    }
}
