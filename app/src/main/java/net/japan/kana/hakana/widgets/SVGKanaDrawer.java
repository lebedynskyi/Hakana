package net.japan.kana.hakana.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/27/14
 */
public class SVGKanaDrawer extends View{
    private String mAssetFile;

    public SVGKanaDrawer(Context context){
        super(context);
        init(context, null);
    }

    public SVGKanaDrawer(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context, attrs);
    }

    public SVGKanaDrawer(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs){
        if(attrs != null){
            //TODO init asset file
        }
    }
}
