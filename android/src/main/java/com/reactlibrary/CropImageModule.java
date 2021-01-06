package com.reactlibrary;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class CropImageModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public CropImageModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "CropImage";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        callback.invoke(
                "Received numberArgument: " + numberArgument +
                        " stringArgument: " + stringArgument
        );
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("CONSTANT_VALUE_ONE", 1024);
        constants.put("CONSTANT_VALUE_TWO", "A native constant string");
        return constants;
    }
}
