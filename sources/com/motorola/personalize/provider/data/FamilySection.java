package com.motorola.personalize.provider.data;

public enum FamilySection {
    MAKE_ADJUSTMENTS("make_adjustments"),
    APPLY_CHANGES("apply_changes");
    

    /* renamed from: id */
    private final String f166id;

    private FamilySection(String str) {
        this.f166id = str;
    }

    public String getId() {
        return this.f166id;
    }
}
