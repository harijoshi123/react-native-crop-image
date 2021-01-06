package com.reactlibrary;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;
import com.facebook.react.bridge.Promise;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

public class CropImageModule extends ReactContextBaseJavaModule {

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";

    private final ReactApplicationContext reactContext;
    public CropImageModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "CropImage";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, "test");
        constants.put(DURATION_LONG_KEY, "test");
        return constants;
    }

    @ReactMethod
    public void callbackMethod(String stringArgument, int numberArgument, Callback callback) {
        WritableMap evt = Arguments.createMap();
        evt.putString("event", "callbackMethod");
        sendEvent(reactContext, "EventReminder", evt);
        callback.invoke(
                "Callback Received numberArgumentss: " + numberArgument +
                        " stringArgumentss: " + stringArgument
        );
    }

    @ReactMethod
    public void promiseMethod(String stringArgument, int numberArgument, Promise promise) {
        WritableMap evt = Arguments.createMap();
        evt.putString("event", "promiseMethod");
        sendEvent(reactContext, "EventReminder", evt);
        try {
            promise.resolve("Promise Received numberArgumentss: " + numberArgument + " stringArgumentss: " + stringArgument);
        } catch(Exception e) {
            promise.reject("Create Event Error", e);
        }
    }

    @ReactMethod
    public void threeDifferentTypesMethod(String stringArgument, int numberArgument, Boolean status, Promise promise) {
        WritableMap evt = Arguments.createMap();
        evt.putString("event", "threeDifferentTypesMethod");
        sendEvent(reactContext, "EventReminder", evt);
        if (status == true) {
            try {
                JSONObject object = new JSONObject();
                object.put("string", stringArgument);
                object.put("int", numberArgument);
                object.put("status", status);
                promise.resolve(object.toString());
            } catch(Exception e) {
                promise.reject("Error", e);
            }
        } else {
            promise.reject("Status: false");
        }
    }

    private void sendEvent(ReactApplicationContext reactContext, String eventName, @Nullable WritableMap params) {
        reactContext
        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
        .emit(eventName, params);
    }
}
