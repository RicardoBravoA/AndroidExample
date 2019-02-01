package com.rba.androidexample.shake;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ShakeManager {

    private Context context;
    private int interval = 2;
    private static final int FORCE = 30;
    private static final int TIME = 20;

    private SensorManager sensorManager;
    private ShakeListener shakeListener;
    private Boolean supported;
    private boolean running = false;

    public ShakeManager(Context context) {
        this.context = context;
    }

    public boolean isListening() {
        return running;
    }

    public void stopListening() {
        running = false;
        try {
            if (sensorManager != null && sensorEventListener != null) {
                sensorManager.unregisterListener(sensorEventListener);
            }
        } catch (Exception e) {
            Log.d("exception: ", e.toString());
        }
    }

    public boolean isSupported() {
        if (supported == null) {
            if (context != null) {
                sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
                List<Sensor> sensors = getSensorList();
                supported = !sensors.isEmpty();
            } else {
                supported = Boolean.FALSE;
            }
        }
        return supported;
    }

    private void configure(int interval) {
        this.interval = interval;
    }

    private List<Sensor> getSensorList() {
        List<Sensor> sensors = new ArrayList<>();

        if (sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER) != null) {
            sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        }
        return sensors;
    }

    public void startListening(ShakeListener shakeListener) {

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensors = getSensorList();

        if (sensors != null && !sensors.isEmpty()) {
            running = sensorManager.registerListener(
                    sensorEventListener, sensors.get(0),
                    SensorManager.SENSOR_DELAY_FASTEST);
            this.shakeListener = shakeListener;
        }
    }

    public void startListening(ShakeListener shakeListener, int interval) {
        configure(interval);
        startListening(shakeListener);
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {

        private long lastUpdate = 0;
        private long lastShake = 0;

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //Do nothing
        }

        public void onSensorChanged(SensorEvent event) {

            long now = event.timestamp;

            if (now > 9999) {
                now = Long.parseLong(String.valueOf(now).substring(0, 8));
            }

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            if (lastUpdate == 0) {
                lastUpdate = now - TIME;
                lastShake = now - TIME;
            } else {
                long timeDiff = now - lastUpdate;
                float force = Math.abs(x + y + z);

                if (force > FORCE && timeDiff > TIME && (now - lastShake >= interval)) {
                    lastUpdate = now;
                    shakeListener.onShake(force);
                    lastShake = now;
                    stopListening();
                }
            }
        }
    };
}
