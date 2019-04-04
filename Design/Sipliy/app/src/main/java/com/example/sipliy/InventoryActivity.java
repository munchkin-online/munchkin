package com.example.sipliy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class InventoryActivity extends AppCompatActivity {

    private ImageView exitFromInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        exitFromInventory = findViewById(R.id.exitFromInventory);

        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch (v.getId())
                {
                    case R.id.exitFromInventory:
                        finish();
                        break;
                }
            }
        };

        exitFromInventory.setOnClickListener(clickListener);
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
