package coms514.smartwindow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Operate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate);
    }

    protected void operate(View view)
    {
        Intent i = new Intent(Operate.this,Toggle.class);
        startActivity(i);
    }
}
