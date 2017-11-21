package coms514.smartwindow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Operate extends AppCompatActivity {

    protected String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate);

    }

    protected void operation(View view)
    {
        System.out.println("Going Inside");
        EditText mEdit   = (EditText) findViewById(R.id.ipaddress);

        ip = mEdit.getText().toString();

        JSONObject request = new JSONObject();

        Intent i = new Intent(Operate.this, Toggle.class).putExtra("ip",ip);
        startActivity(i);

        /*try
        {
            request.put("email","");
            request.put("ip",ip);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        String response = sendHttpRequest(request);

        if(response.equals("true")) {

            Intent i = new Intent(Operate.this, Toggle.class).putExtra("ip",ip);
            startActivity(i);
        }*/
    }

    protected void sendGetHttpRequest() {
        System.out.print("Something");
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://172.20.10.8/?cmd=TURN_ON_LED";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

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

    protected String sendHttpRequest(JSONObject request)
    {
        System.out.print("Starting to send Request");
        StringBuilder sb = new StringBuilder();

        try
        {
            URL url = new URL("http://34.216.80.212:8080/saveip");

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.connect();

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(request.toString());
            wr.flush();
            wr.close();

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
