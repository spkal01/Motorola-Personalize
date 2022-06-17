package com.motorola.personalize.provider.data;

public enum ProviderUri {
    FAMILIES("/families"),
    FEATURES("/features"),
    FAMILY_SECTIONS("/sections");
    
    private final String uri;

    private ProviderUri(String str) {
        this.uri = str;
    }

    public String getPath() {
        return this.uri;
    }
}
