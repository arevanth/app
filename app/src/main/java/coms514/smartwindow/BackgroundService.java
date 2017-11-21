package coms514.smartwindow;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;

public class BackgroundService extends Service implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;

    public BackgroundService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        return START_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            float value = sensorEvent.values[0];
            //Call a function to check if its more than what we want.
        }
    }

    @Override
    public void onAccuracyChanged (Sensor sensor, int accuracy) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
