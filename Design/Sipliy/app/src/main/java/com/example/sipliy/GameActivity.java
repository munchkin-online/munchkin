package com.example.sipliy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    private ImageView player_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player_icon = findViewById(R.id.player_icon);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.player_icon:
                        //search.setText("play");
                        startActivity(new Intent(GameActivity.this, InventoryActivity.class));
                        break;
                }
            }
        };

        player_icon.setOnClickListener(clickListener);

    }
}
