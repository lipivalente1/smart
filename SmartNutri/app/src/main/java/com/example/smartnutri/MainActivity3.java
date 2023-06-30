package com.example.smartnutri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartnutri.componentes.infoProduto;

public class MainActivity3 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        LinearLayout paiLayout =  findViewById(R.id.layoutInfo);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);

        infoProduto info = new infoProduto(this, id);
        paiLayout.addView(info);
    }
}