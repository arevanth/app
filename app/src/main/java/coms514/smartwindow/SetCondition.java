package coms514.smartwindow;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SetCondition extends AppCompatActivity {

    public String ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ip = getIntent().getStringExtra("ip");

        setContentView(R.layout.activity_set_condition);

        TextView view = (TextView) findViewById(R.id.ip);

        view.setText(ip);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    protected void savecondition(View v)
    {
        EditText open = (EditText) findViewById(R.id.open);
        int opencondition = Integer.parseInt(open.getText().toString());

        EditText close = (EditText) findViewById(R.id.close);
        int closecondition = Integer.parseInt(close.getText().toString());

        JSONObject request = new JSONObject();

        try{
            request.put("email",MainActivity.email);
            request.put("ip",ip);
            request.put("open",open);
            request.put("close","close");

            if(sendHttpRequest(request).equals("true"))
            {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
        }

        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    protected String sendHttpRequest(JSONObject request)
    {
        System.out.print("Starting to send Request");
        StringBuilder sb = new StringBuilder();

        try
        {
            URL url = new URL("http://" + Util.ip + "/setcondition");

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
