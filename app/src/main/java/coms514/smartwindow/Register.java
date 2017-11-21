package coms514.smartwindow;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        set_spinner();
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    protected void register(View v)
    {
        EditText name = (EditText) findViewById(R.id.name);
        EditText age = (EditText) findViewById(R.id.age);
        Spinner spinner = (Spinner) findViewById(R.id.types_spinner);

        String Name = name.getText().toString().trim();

        if(Name.equals(""))
        {
            name.setError("Name is required.");
            name.setHint("Name is required.");
        }

        String Age = age.getText().toString().trim();

        if(Age.equals(""))
        {
            age.setError("Age is required.");
            age.setHint("Age is required.");
        }

        String type = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();

        JSONObject request = new JSONObject();

        try
        {
            request.put("name", Name);
            request.put("password","password");
            request.put("email", "revanth.a1994@gmail.com");
            request.put("type",1);
            //request.put("age",Age);
        }

        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        String response = sendRequest(request);

        if(response.equals("true"))
        {
            Intent i = new Intent(this,LoginActivity.class);
            startActivity(i);
        }
        else
        {

        }

    }

    private void set_spinner()
    {
        Spinner spinner = (Spinner) findViewById(R.id.types_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    protected String sendRequest(JSONObject request)
    {
        System.out.print("Starting to send Request");
        StringBuilder sb = new StringBuilder();

        try
        {
            URL url = new URL("http://34.216.80.212:8080/register");

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
