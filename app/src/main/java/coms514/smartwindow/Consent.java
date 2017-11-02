package coms514.smartwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Consent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consent);
    }

    protected void search(View v)
    {
        ListView listView = (ListView) findViewById(R.id.email_list);

        List<String> email_list = new ArrayList<String>();
        email_list.add("revanth@iastate.edu");
        email_list.add("revanth@blah.edu");
        email_list.add("revanth@blahblah.edu");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.activity_listview,email_list);
        listView.setAdapter(arrayAdapter);
    }

    protected void add_consent(View v)
    {
        System.out.print("Going in here");
        Button b = (Button)v;
        String email = b.getText().toString();
        System.out.print(email);
    }
}
