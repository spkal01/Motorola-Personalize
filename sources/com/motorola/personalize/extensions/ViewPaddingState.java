package com.motorola.personalize.extensions;

import kotlin.Metadata;

@Metadata(mo15461d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003JE\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001e"}, mo15462d2 = {"Lcom/motorola/personalize/extensions/ViewPaddingState;", "", "left", "", "top", "right", "bottom", "start", "end", "(IIIIII)V", "getBottom", "()I", "getEnd", "getLeft", "getRight", "getStart", "getTop", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: ViewPaddingState.kt */
public final class ViewPaddingState {
    private final int bottom;
    private final int end;
    private final int left;
    private final int right;
    private final int start;
    private final int top;

    public static /* synthetic */ ViewPaddingState copy$default(ViewPaddingState viewPaddingState, int i, int i2, int i3, int i4, int i5, int i6, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i = viewPaddingState.left;
        }
        if ((i7 & 2) != 0) {
            i2 = viewPaddingState.top;
        }
        int i8 = i2;
        if ((i7 & 4) != 0) {
            i3 = viewPaddingState.right;
        }
        int i9 = i3;
        if ((i7 & 8) != 0) {
            i4 = viewPaddingState.bottom;
        }
        int i10 = i4;
        if ((i7 & 16) != 0) {
            i5 = viewPaddingState.start;
        }
        int i11 = i5;
        if ((i7 & 32) != 0) {
            i6 = viewPaddingState.end;
        }
        return viewPaddingState.copy(i, i8, i9, i10, i11, i6);
    }

    public final int component1() {
        return this.left;
    }

    public final int component2() {
        return this.top;
    }

    public final int component3() {
        return this.right;
    }

    public final int component4() {
        return this.bottom;
    }

    public final int component5() {
        return this.start;
    }

    public final int component6() {
        return this.end;
    }

    public final ViewPaddingState copy(int i, int i2, int i3, int i4, int i5, int i6) {
        return new ViewPaddingState(i, i2, i3, i4, i5, i6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ViewPaddingState)) {
            return false;
        }
        ViewPaddingState viewPaddingState = (ViewPaddingState) obj;
        return this.left == viewPaddingState.left && this.top == viewPaddingState.top && this.right == viewPaddingState.right && this.bottom == viewPaddingState.bottom && this.start == viewPaddingState.start && this.end == viewPaddingState.end;
    }

    public int hashCode() {
        return (((((((((Integer.hashCode(this.left) * 31) + Integer.hashCode(this.top)) * 31) + Integer.hashCode(this.right)) * 31) + Integer.hashCode(this.bottom)) * 31) + Integer.hashCode(this.start)) * 31) + Integer.hashCode(this.end);
    }

    public String toString() {
        return "ViewPaddingState(left=" + this.left + ", top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + ", start=" + this.start + ", end=" + this.end + ')';
    }

    public ViewPaddingState(int i, int i2, int i3, int i4, int i5, int i6) {
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
        this.start = i5;
        this.end = i6;
    }

    public final int getLeft() {
        return this.left;
    }

    public final int getTop() {
        return this.top;
    }

    public final int getRight() {
        return this.right;
    }

    public final int getBottom() {
        return this.bottom;
    }

    public final int getStart() {
        return this.start;
    }

    public final int getEnd() {
        return this.end;
    }
}
