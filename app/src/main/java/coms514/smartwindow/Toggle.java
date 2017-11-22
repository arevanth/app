package coms514.smartwindow;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Toggle extends AppCompatActivity {

    public static String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle);
        ip = getIntent().getStringExtra("ip");
        System.out.println(ip);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    protected static void open(View view)
    {
        //sendGetHttpRequest();
        //sendPostHttpRequest();
        sendOpenRequest();
    }

    protected static void close(View view)
    {
        //sendGetHttpRequest();
        sendCloseRequest();
        //sendPostHttpRequest();
    }

    protected static String sendOpenRequest()
    {

        System.out.print("Starting to send Request");
        StringBuilder sb = new StringBuilder();

        try
        {
            URL url = new URL("http://" + ip + "/?cmd=TURN_ON_LED");

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            int responseCode = httpURLConnection.getResponseCode();

            BufferedReader serverAnswer = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;

            while ((line = serverAnswer.readLine()) != null) {
                sb.append(line);
                System.out.println("LINE: " + line);
            }

            System.out.println(sb.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();

    }

    protected static String sendCloseRequest()
    {
        System.out.print("Starting to send Request");
        StringBuilder sb = new StringBuilder();

        try
        {
            URL url = new URL("http://"+ ip +"/?cmd=TURN_OFF_LED");

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            int responseCode = httpURLConnection.getResponseCode();

            BufferedReader serverAnswer = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;

            while ((line = serverAnswer.readLine()) != null) {
                sb.append(line);
                System.out.println("LINE: " + line);
            }

            System.out.println(sb.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
