package com.aloknath.totango;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickEvent(View view){

        Intent intent;

        switch (view.getId()) {

            case R.id.button:
                intent = new Intent(this, TotangoActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent = new Intent(this, UserProfileActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
