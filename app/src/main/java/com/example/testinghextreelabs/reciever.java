package com.example.testinghextreelabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class reciever extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Intent intent = getIntent();
            intent.putExtra("token", 1094795585);
            setResult(-1, intent);
            finish();
        }

}
