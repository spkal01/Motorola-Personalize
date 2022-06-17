package com.motorola.styles.model;

import java.util.HashMap;

public class Result extends HashMap<String, Object> {
    public static final String KEY_ERROR = "error";

    public boolean containsError() {
        return containsKey(KEY_ERROR);
    }

    public Object putError(Object obj) {
        return put(KEY_ERROR, obj);
    }

    public Object getError() {
        return getOrDefault(KEY_ERROR, (Object) null);
    }

    public String getErrorMsg() {
        return getOrDefault(KEY_ERROR, "").toString();
    }
}
