package coms514.smartwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class user_register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        set_spinner();
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

        System.out.println(Name + "-" + Age + "-" + type);

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
}
