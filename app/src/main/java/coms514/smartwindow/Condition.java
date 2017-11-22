package coms514.smartwindow;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Condition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        List<String> ip = getAllIp(MainActivity.email);

        for(int i=0; i<ip.size();i++)
        {
            System.out.println(ip.get(i));
        }

        System.out.println("Now updating the list");

        final ListView listView = (ListView) findViewById(R.id.ip_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.activity_listview,ip);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) listView.getItemAtPosition(position);
                System.out.print(selected);
                toggle(selected);
            }
        });
    }

    protected static List<String> getAllIp(String request)
    {
        System.out.print("Starting to send Request");
        List<String> results = new ArrayList<String>();


        try
        {
            URL url = new URL("http://" + Util.ip + "/getip");

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
            httpURLConnection.connect();

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(request);
            wr.flush();
            wr.close();

            BufferedReader serverAnswer = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = serverAnswer.readLine()) != null) {
                sb.append(line);
                //System.out.println("LINE: " + line);
            }

            //System.out.println(sb.toString());
            JSONArray repsonse = new JSONArray(sb.toString());

            for(int i=0; i<repsonse.length();i++)
            {
                results.add(repsonse.getString(i));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return results;

    }

    protected void toggle(String ip)
    {
        Intent i = new Intent(this,Toggle.class).putExtra("ip",ip);
        startActivity(i);
    }
}
