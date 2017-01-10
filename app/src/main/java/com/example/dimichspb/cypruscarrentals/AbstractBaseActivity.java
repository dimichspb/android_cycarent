package com.example.dimichspb.cypruscarrentals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public abstract class AbstractBaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_type_choose) {
            callTypeChooseActivity();
        }
        if (id == R.id.action_about_us) {
            callAboutUsActivity();
        }

        if (id == R.id.action_contact_us) {
            callContactUsActivity();
        }
        if (id == R.id.action_exit) {
            exitApplication();
        }

        return super.onOptionsItemSelected(item);
    }

    public void callTypeChooseActivity() {
        Intent intent = new Intent(this, TypeChooseActivity.class);
        startActivity(intent);
    }

    public void callAboutUsActivity() {
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
    }

    public void callContactUsActivity() {
        Intent intent = new Intent(this, ContactUsActivity.class);
        startActivity(intent);
    }

    public void exitApplication() {
        finish();
        System.exit(0);
    }
}
