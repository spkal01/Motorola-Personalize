package com.motorola.styles;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtils {
    public static final String MSG_EXCEPTION_GET_FIELD = "Exception on get field";
    public static final String MSG_EXCEPTION_GET_FIELD_VALUE = "Exception on get field value";
    public static final String MSG_EXCEPTION_INVOKE_METHOD = "Exception on invoke method";
    public static final String MSG_EXCEPTION_SET_FIELD_VALUE = "Exception on set field value";
    public static final String TAG = "ReflectUtils";

    public static Object getFieldValue(Object obj, String str) {
        return getFieldValue(obj.getClass(), obj, str);
    }

    public static Object getFieldValue(Class<?> cls, Object obj, String str) {
        try {
            return getFieldValueOrThrow(obj, cls.getDeclaredField(str));
        } catch (Exception e) {
            Log.e("ReflectUtils", "Exception on get field value", e);
            return null;
        }
    }

    public static Object getFieldValue(Object obj, Field field) {
        try {
            return getFieldValueOrThrow(obj, field);
        } catch (Exception e) {
            Log.e("ReflectUtils", "Exception on get field value", e);
            return null;
        }
    }

    public static Object getFieldValue(String str, String str2) {
        try {
            return getFieldValueOrThrow((Object) null, Class.forName(str).getDeclaredField(str2));
        } catch (Exception e) {
            Log.e("ReflectUtils", "Exception on get field value", e);
            return null;
        }
    }

    public static Object getFieldValueOrThrow(Object obj, String str) {
        return getFieldValueOrThrow(obj.getClass(), obj, str);
    }

    public static Object getFieldValueOrThrow(Class<?> cls, Object obj, String str) {
        try {
            return getFieldValueOrThrow(obj, cls.getDeclaredField(str));
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new RuntimeException("Exception on get field value", e2);
        }
    }

    public static Object getFieldValueOrThrow(Object obj, Field field) {
        if (field == null) {
            return null;
        }
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException("Exception on get field value", e);
        }
    }

    public static Object getFieldValueOrThrow(String str, String str2) {
        try {
            return getFieldValueOrThrow((Object) null, Class.forName(str).getDeclaredField(str2));
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new RuntimeException("Exception on get field value", e2);
        }
    }

    public static Field getField(Object obj, String str) {
        return getField(obj.getClass(), str);
    }

    public static Field getField(Class<?> cls, String str) {
        try {
            return getFieldOrThrow(cls, str);
        } catch (Exception e) {
            Log.e("ReflectUtils", "Exception on get field", e);
            return null;
        }
    }

    public static Field getField(String str, String str2) {
        try {
            return getFieldOrThrow(Class.forName(str), str2);
        } catch (Exception e) {
            Log.e("ReflectUtils", "Exception on get field", e);
            return null;
        }
    }

    public static Field getFieldOrThrow(Object obj, String str) {
        return getFieldOrThrow(obj.getClass(), str);
    }

    public static Field getFieldOrThrow(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            if (declaredField != null && !declaredField.isAccessible()) {
                declaredField.setAccessible(true);
            }
            return declaredField;
        } catch (Exception e) {
            throw new RuntimeException("Exception on get field", e);
        }
    }

    public static Field getFieldOrThrow(String str, String str2) {
        try {
            return getFieldOrThrow(Class.forName(str), str2);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new RuntimeException("Exception on get field", e2);
        }
    }

    public static void setField(Object obj, String str, Object obj2) {
        setField(obj, getField(obj, str), obj2);
    }

    public static void setField(Object obj, String str, String str2, Object obj2) {
        Field field;
        if (str == null) {
            field = getField(obj, str2);
        } else {
            field = getField(str, str2);
        }
        setField(obj, field, obj2);
    }

    public static void setField(Object obj, Class<?> cls, String str, Object obj2) {
        setField(obj, cls == null ? getField(obj, str) : getField(cls, str), obj2);
    }

    public static void setField(Object obj, Field field, Object obj2) {
        if (field != null) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            try {
                field.set(obj, obj2);
            } catch (IllegalAccessException e) {
                Log.e("ReflectUtils", "Exception on set field value", e);
            }
        }
    }

    public static Object invokeMethod(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        return invokeMethod(obj.getClass(), obj, str, clsArr, objArr);
    }

    public static Object invokeMethod(Class<?> cls, Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            return invokeMethodOrThrow(cls, obj, str, clsArr, objArr);
        } catch (Exception e) {
            Log.e("ReflectUtils", "Exception on invoke method", e);
            return null;
        }
    }

    public static Object invokeMethod(String str, String str2, Class<?>[] clsArr, Object[] objArr) {
        try {
            return invokeMethodOrThrow(Class.forName(str), (Object) null, str2, clsArr, objArr);
        } catch (Exception e) {
            Log.e("ReflectUtils", "Exception on invoke method", e);
            return null;
        }
    }

    public static Object invokeMethodOrThrow(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        return invokeMethodOrThrow(obj.getClass(), obj, str, clsArr, objArr);
    }

    public static Object invokeMethodOrThrow(Class<?> cls, Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            if (declaredMethod == null) {
                return null;
            }
            if (!declaredMethod.isAccessible()) {
                declaredMethod.setAccessible(true);
            }
            return declaredMethod.invoke(obj, objArr);
        } catch (Exception e) {
            throw new RuntimeException("Exception on invoke method", e);
        }
    }

    public static Object invokeMethodOrThrow(String str, String str2, Class<?>[] clsArr, Object[] objArr) {
        try {
            return invokeMethodOrThrow(Class.forName(str), (Object) null, str2, clsArr, objArr);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new RuntimeException("Exception on invoke method", e2);
        }
    }
}
