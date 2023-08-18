package com.codelabs_coding.petrescue.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class CommonUtils {

    public static Double formatDouble(Object object) {
        if (isEmpty(object)) {
            return 0.0;
        } else {
            String stringValue = object.toString().replace(",", ".");
            return Double.parseDouble(stringValue);
        }
    }

    public static String formatNull(Object object) {
        if (isEmpty(object)) {
            return "";
        } else {
            return object.toString().trim();
        }
    }

    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        if (obj instanceof String) {
            return "NULL".equalsIgnoreCase(obj.toString().trim())
                    || "".equals(obj.toString().trim());
        } else if (obj instanceof Collection) {
            return ((Collection<?>) obj).size() == 0;
        } else if (obj instanceof Map) {
            return ((Map<?, ?>) obj).size() == 0;
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        } else {
            try {
                return isEmpty(obj.toString());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static String getStrEditView(EditText editText) {
        return CommonUtils.formatNull(editText.getText()).trim();
    }

    public static void clearStrEditView(EditText editText) {
        editText.getText().clear();
    }

    public static void startActivity(Context mContext, Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(mContext, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);
    }

    public static void jumpToActivityWithClearStack(Context mContext, Class<?> clz) {
        Intent intent = new Intent(mContext, clz);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    public static void startActivity(Context mContext, Class<?> clz) {
        mContext.startActivity(new Intent(mContext, clz));
    }

    public static String formatTimeStamp(long timestampInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date(timestampInMillis);
        return sdf.format(date);
    }
}
