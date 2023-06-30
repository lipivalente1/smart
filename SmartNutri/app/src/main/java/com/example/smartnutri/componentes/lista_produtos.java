package com.example.smartnutri.componentes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartnutri.Produto;
import com.example.smartnutri.R;

public class lista_produtos extends LinearLayout {
    private LinearLayout containerPedido;
    private TextView nomeCategoria;

    public int count = 0;

    public lista_produtos(Context context) {
        super(context);
        init();
    }

    public lista_produtos(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public lista_produtos(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.produtos_escolhidos_cat, this, true);
        containerPedido = findViewById(R.id.containerPedido);
        nomeCategoria = findViewById(R.id.nomeCategoria);
    }

    public void addProduto(Produto newProduto)
    {
        count++;
        containerPedido.addView(newProduto);
    }
    public void setNomeCategoria(String nomeCategoria)
    {
        this.nomeCategoria.setText(nomeCategoria);
    }

}
