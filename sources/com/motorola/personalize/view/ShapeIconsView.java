package com.motorola.personalize.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.motorola.personalize.C1057R;
import com.motorola.styles.model.ShapeOption;
import java.util.ArrayList;
import java.util.List;

public class ShapeIconsView extends RelativeLayout {
    private List<View> mViewList;
    private ShapeOption option;

    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public ShapeIconsView(Context context) {
        super(context);
        init(context);
    }

    public ShapeIconsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ShapeIconsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        inflate(context, C1057R.layout.shape_icons_view, this);
        ArrayList arrayList = new ArrayList();
        this.mViewList = arrayList;
        arrayList.add(findViewById(C1057R.C1060id.shape_icon1));
        this.mViewList.add(findViewById(C1057R.C1060id.shape_icon2));
        this.mViewList.add(findViewById(C1057R.C1060id.shape_icon3));
        this.mViewList.add(findViewById(C1057R.C1060id.shape_icon4));
    }

    public void showAppIcons() {
        LayerDrawable shape = this.option.getShape();
        ((ShapeDrawable) shape.getDrawable(1)).setTint(-1);
        TypedArray obtainTypedArray = getResources().obtainTypedArray(C1057R.array.shape_icons);
        for (int i = 0; i < this.mViewList.size(); i++) {
            View view = this.mViewList.get(i);
            ((ImageView) view.findViewById(C1057R.C1060id.preview_setting_icon_bg)).setImageDrawable(shape);
            ((ImageView) view.findViewById(C1057R.C1060id.preview_setting_icon)).setImageResource(obtainTypedArray.getResourceId(i, 0));
        }
        obtainTypedArray.recycle();
    }

    public void setShapeOption(ShapeOption shapeOption) {
        this.option = shapeOption;
    }
}
