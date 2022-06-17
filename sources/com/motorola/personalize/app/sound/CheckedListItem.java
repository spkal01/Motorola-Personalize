package com.motorola.personalize.app.sound;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import com.motorola.personalize.C1057R;

public class CheckedListItem extends LinearLayout implements Checkable {
    /* access modifiers changed from: protected */
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    /* access modifiers changed from: protected */
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.generateLayoutParams(layoutParams);
    }

    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public CheckedListItem(Context context) {
        super(context);
    }

    public CheckedListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CheckedListItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CheckedListItem(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setChecked(boolean z) {
        getCheckedTextView().setChecked(z);
    }

    public boolean isChecked() {
        return getCheckedTextView().isChecked();
    }

    public void toggle() {
        getCheckedTextView().toggle();
    }

    private CheckedTextView getCheckedTextView() {
        return (CheckedTextView) findViewById(C1057R.C1060id.checked_text_view);
    }
}
