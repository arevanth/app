package coms514.smartwindow;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Toggle extends AppCompatActivity {

    public String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle);
        ip = getIntent().getStringExtra("ip");
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    protected void open(View view)
    {
        System.out.println("Going inside the function");
        //sendGetHttpRequest();
        //sendPostHttpRequest();
        sendOpenRequest();
    }

    protected void close(View view)
    {
        System.out.println("Going inside the function");
        //sendGetHttpRequest();
        sendCloseRequest();
        //sendPostHttpRequest();
    }

    protected String sendOpenRequest()
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

    protected String sendCloseRequest()
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

    protected void sendGetHttpRequest()
    {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://192.168.43.2/?cmd=TURN_ON_LED";

        StringRequest request= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println(response.toString());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.getStackTrace();
            }
        });

        queue.add(request);
    }

    protected void sendPostHttpRequest(Map<String, String> params)
    {
        String url = "http://google.com";

        JSONObject requestBody = new JSONObject(params);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, requestBody, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}
