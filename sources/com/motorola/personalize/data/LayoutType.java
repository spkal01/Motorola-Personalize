package com.motorola.personalize.data;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(mo15461d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 \u00072\u00020\u0001:\u0004\u0007\b\t\nB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0001\u0003\u000b\f\r¨\u0006\u000e"}, mo15462d2 = {"Lcom/motorola/personalize/data/LayoutType;", "", "layoutId", "", "(I)V", "getLayoutId", "()I", "Companion", "Icon", "Tile", "Title", "Lcom/motorola/personalize/data/LayoutType$Icon;", "Lcom/motorola/personalize/data/LayoutType$Tile;", "Lcom/motorola/personalize/data/LayoutType$Title;", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
/* compiled from: LayoutType.kt */
public abstract class LayoutType {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int LAYOUT_TYPE_ID_ICON = 1;
    private static final int LAYOUT_TYPE_ID_TILE = 2;
    private static final int LAYOUT_TYPE_ID_TITLE = 3;
    private final int layoutId;

    public /* synthetic */ LayoutType(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    private LayoutType(int i) {
        this.layoutId = i;
    }

    public final int getLayoutId() {
        return this.layoutId;
    }

    @Metadata(mo15461d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, mo15462d2 = {"Lcom/motorola/personalize/data/LayoutType$Icon;", "Lcom/motorola/personalize/data/LayoutType;", "()V", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
    /* compiled from: LayoutType.kt */
    public static final class Icon extends LayoutType {
        public static final Icon INSTANCE = new Icon();

        private Icon() {
            super(1, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(mo15461d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, mo15462d2 = {"Lcom/motorola/personalize/data/LayoutType$Tile;", "Lcom/motorola/personalize/data/LayoutType;", "()V", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
    /* compiled from: LayoutType.kt */
    public static final class Tile extends LayoutType {
        public static final Tile INSTANCE = new Tile();

        private Tile() {
            super(2, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(mo15461d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, mo15462d2 = {"Lcom/motorola/personalize/data/LayoutType$Title;", "Lcom/motorola/personalize/data/LayoutType;", "()V", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
    /* compiled from: LayoutType.kt */
    public static final class Title extends LayoutType {
        public static final Title INSTANCE = new Title();

        private Title() {
            super(3, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(mo15461d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, mo15462d2 = {"Lcom/motorola/personalize/data/LayoutType$Companion;", "", "()V", "LAYOUT_TYPE_ID_ICON", "", "LAYOUT_TYPE_ID_TILE", "LAYOUT_TYPE_ID_TITLE", "app_release"}, mo15463k = 1, mo15464mv = {1, 5, 1}, mo15466xi = 48)
    /* compiled from: LayoutType.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
