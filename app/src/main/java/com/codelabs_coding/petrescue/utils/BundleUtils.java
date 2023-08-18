package com.codelabs_coding.petrescue.utils;

import android.os.Bundle;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class BundleUtils {
    public static Bundle getBundleString(String key, String value) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        return bundle;
    }

    public static Bundle getBundleInt(String key, int value) {
        Bundle bundle = new Bundle();
        bundle.putInt(key, value);
        return bundle;
    }

    public static Bundle getBundleSerializable(String key, Object value) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(key, (Serializable) value);
        return bundle;
    }

    public static Bundle getBundleObject(String key, Object value) {
        Bundle bundle = new Bundle();
        Gson gson =  new Gson();
        bundle.putString(key,  gson.toJson(value));
        return bundle;
    }

    public static Bundle getBundleSerializableBoolean(String key, Object value, String key1, Boolean value1) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(key, (Serializable) value);
        bundle.putBoolean(key1, value1);
        return bundle;
    }

    public static Bundle getBundleSerializableString(String key, String value, String key1, Object value1) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        bundle.putSerializable(key1, (Serializable) value1);
        return bundle;
    }

    public static Bundle getBundleSerStringSer(String key, Object value, String key1, String value1, String key2, Object value2) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(key, (Serializable) value);
        bundle.putSerializable(key2, (Serializable) value2);
        bundle.putString(key1, value1);
        return bundle;
    }

    public static Bundle getBundle2String1Ser(String key, String value, String key1, String value1, String key2, Object value2) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        bundle.putString(key1, value1);
        bundle.putSerializable(key2, (Serializable) value2);
        return bundle;
    }

    public static Bundle getBundle2String(String key1, String value1, String key2, String value2) {
        Bundle bundle = new Bundle();
        bundle.putString(key1, value1);
        bundle.putString(key2, value2);
        return bundle;
    }

    public static Bundle getBundle2S1Ser(String key1, String value1, String key2, String value2, String key3, Serializable value3) {
        Bundle bundle = new Bundle();
        bundle.putString(key1, value1);
        bundle.putString(key2, value2);
        bundle.putSerializable(key3, value3);
        return bundle;
    }

    public static Bundle getBundle3String(String key1, String value1, String key2, String value2, String key3, String value3) {
        Bundle bundle = new Bundle();
        bundle.putString(key1, value1);
        bundle.putString(key2, value2);
        bundle.putString(key3, value3);
        return bundle;
    }

    public static Bundle getBundleLong1String3(String key1, Long value1, String key2, String value2, String key3, String value3, String key4, String value4) {
        Bundle bundle = new Bundle();
        bundle.putLong(key1, value1);
        bundle.putString(key2, value2);
        bundle.putString(key3, value3);
        bundle.putString(key4, value4);
        return bundle;
    }

    public static Bundle getBundleBoolean1String2(String key1, Boolean value1, String key2, String value2, String key3, String value3) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(key1, value1);
        bundle.putString(key2, value2);
        bundle.putString(key3, value3);
        return bundle;
    }

    public static Bundle getBundleLong1String4(String key1, Long value1, String key2, String value2, String key3, String value3, String key4, String value4, String key5, String value5) {
        Bundle bundle = new Bundle();
        bundle.putLong(key1, value1);
        bundle.putString(key2, value2);
        bundle.putString(key3, value3);
        bundle.putString(key4, value4);
        bundle.putString(key5, value5);
        return bundle;
    }

    public static Bundle getBundle4String(String key1, String value1, String key2, String value2, String key3, String value3, String key4, String value4) {
        Bundle bundle = new Bundle();
        bundle.putString(key1, value1);
        bundle.putString(key2, value2);
        bundle.putString(key3, value3);
        bundle.putString(key4, value4);
        return bundle;
    }

    public static Bundle getBundle5String(String key1, String value1, String key2, String value2, String key3, String value3, String key4, String value4, String key5, String value5) {
        Bundle bundle = new Bundle();
        bundle.putString(key1, value1);
        bundle.putString(key2, value2);
        bundle.putString(key3, value3);
        bundle.putString(key4, value4);
        bundle.putString(key5, value5);
        return bundle;
    }

    public static Bundle getBundle3String2Long(String key1, String value1, String key2, String value2, String key3, String value3, String key4, long value4, String key5, long value5) {
        Bundle bundle = new Bundle();
        bundle.putString(key1, value1);
        bundle.putString(key2, value2);
        bundle.putString(key3, value3);
        bundle.putLong(key4, value4);
        bundle.putLong(key5, value5);
        return bundle;
    }

    public static Bundle getBundle3StringLong(String key1, String value1, String key2, String value2, String key3, String value3, String key4, long value4) {
        Bundle bundle = new Bundle();
        bundle.putString(key1, value1);
        bundle.putString(key2, value2);
        bundle.putString(key3, value3);
        bundle.putLong(key4, value4);
        return bundle;
    }

    public static Bundle getBundle4String2Long(String key, String value, String key1, String value1, String key2, String value2, String key3, String value3, String key4, long value4, String key5, long value5) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        bundle.putString(key1, value1);
        bundle.putString(key2, value2);
        bundle.putString(key3, value3);
        bundle.putLong(key4, value4);
        bundle.putLong(key5, value5);
        return bundle;
    }

    public static Bundle getBundleStringBoolean(String key, String value, String key2, Boolean value2) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        bundle.putBoolean(key2, value2);
        return bundle;
    }

    public static Bundle getBundleBoolean(String key, Boolean value) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(key, value);
        return bundle;
    }

    public static Bundle getBundleBooleanThird(String key, Boolean value, String key1, Boolean value1, String key2, Boolean value2) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(key, value);
        bundle.putBoolean(key1, value1);
        bundle.putBoolean(key2, value2);
        return bundle;
    }


    public static Bundle getBundle2Boolean1String(String key, String value, String key1, Boolean value1, String key2, Boolean value2) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        bundle.putBoolean(key1, value1);
        bundle.putBoolean(key2, value2);
        return bundle;
    }

    public static Bundle getBundleBooleanFour(String key, Boolean value, String key1, Boolean value1, String key2, Boolean value2, String key3, Boolean value3) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(key, value);
        bundle.putBoolean(key1, value1);
        bundle.putBoolean(key2, value2);
        bundle.putBoolean(key3, value3);
        return bundle;
    }

    public static Bundle getBundleBooleanFive(String key, Boolean value, String key1, Boolean value1, String key2, Boolean value2, String key3, Boolean value3, String key4, Boolean value4) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(key, value);
        bundle.putBoolean(key1, value1);
        bundle.putBoolean(key2, value2);
        bundle.putBoolean(key3, value3);
        bundle.putBoolean(key4, value4);
        return bundle;
    }

    public static Bundle getBundleDouble(String key, Double value) {
        Bundle bundle = new Bundle();
        bundle.putDouble(key, value);
        return bundle;
    }

    public static Bundle getBundleDoubleString(String key, Double value, String key2, String value2) {
        Bundle bundle = new Bundle();
        bundle.putDouble(key, value);
        bundle.putString(key2, value2);
        return bundle;
    }

    public static Bundle getBundleLong(String key, Long value) {
        Bundle bundle = new Bundle();
        bundle.putLong(key, value);
        return bundle;
    }
}
