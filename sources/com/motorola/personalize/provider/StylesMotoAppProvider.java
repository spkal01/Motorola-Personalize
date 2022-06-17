package com.motorola.personalize.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.motorola.personalize.provider.data.ProviderUri;
import com.motorola.personalize.provider.data.provider.FamilyProvider;
import com.motorola.personalize.provider.data.provider.FamilySectionProvider;
import com.motorola.personalize.provider.data.provider.FeatureProvider;

public class StylesMotoAppProvider extends ContentProvider {
    private static final String AUTHORITY = "com.motorola.personalize.provider";
    private static final int FAMILY_CODE = 0;
    private static final int FAMILY_SECTION_CODE = 2;
    private static final int FEATURE_CODE = 1;
    private final FamilyProvider familyProvider = new FamilyProvider();
    private final FeatureProvider featureProvider = new FeatureProvider();
    private final FamilySectionProvider sectionProvider = new FamilySectionProvider();
    private final UriMatcher uriMatcher = new UriMatcher(-1);

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public boolean onCreate() {
        this.uriMatcher.addURI(AUTHORITY, ProviderUri.FAMILIES.getPath(), 0);
        this.uriMatcher.addURI(AUTHORITY, ProviderUri.FEATURES.getPath(), 1);
        this.uriMatcher.addURI(AUTHORITY, ProviderUri.FAMILY_SECTIONS.getPath(), 2);
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String packageName = getContext().getPackageName();
        int match = this.uriMatcher.match(uri);
        if (match == 0) {
            return this.familyProvider.getFamilies();
        }
        if (match == 1) {
            return this.featureProvider.getFeatures(packageName);
        }
        if (match != 2) {
            return null;
        }
        return this.sectionProvider.getSections();
    }
}
