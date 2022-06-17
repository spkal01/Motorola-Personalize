package com.motorola.styles.model.partner;

public class InvariantDeviceProfile {
    public static final String TAG = "IDP";
    String displayName;
    float minHeightDps;
    float minWidthDps;
    public int numColumns;
    public int numRows;

    public InvariantDeviceProfile() {
    }

    private InvariantDeviceProfile(InvariantDeviceProfile invariantDeviceProfile) {
        this.numRows = invariantDeviceProfile.numRows;
        this.numColumns = invariantDeviceProfile.numColumns;
    }
}
