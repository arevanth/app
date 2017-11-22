package coms514.smartwindow;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Timer;

public class BackgroundService extends Service {

    private Timer timer = new Timer();

    public BackgroundService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*@Override
    public void onCreate()
    {
        super.onCreate();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String email = MainActivity.email;
                List<String> ip = Condition.getAllIp(email);
                int length = ip.size();
                int i = 0;
                while (i<length)
                {
                    if(sendRequestToServer(ip.get(i)) < ip.get(i))
                    {
                        Toggle.sendOpenRequest();
                    }
                    i++;
                }
            }
        });
    }*/

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    protected int  sendRequestToServer(String ip)
    {
        int photocellValue = 0;

        return photocellValue;
    }


}
