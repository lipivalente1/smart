package com.example.smartnutri;

import static com.example.smartnutri.MainActivity.Categoria.carne;
import static com.example.smartnutri.MainActivity.Categoria.cereal;
import static com.example.smartnutri.MainActivity.Categoria.bebida;
import static com.example.smartnutri.MainActivity.Categoria.outros;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Produto extends LinearLayout {

    public ImageView rec;
    public TextView nomeProduto;
    private ImageView fotoProduto;
    private Button buttonAdd;
    private LinearLayout fundoProduto;
    private LinearLayout contPreco;
    private LinearLayout contNutri;
    private LinearLayout contNutri2;
    private TextView textPreco;
    private TextView textNutri;
    private TextView textNutri2;

    private int ID;
    private MainActivity.Categoria cat;
    private boolean buttonSelecionado = false;
    private Datas.ProdutosMercados produto;


    public int getID_P() {
        return ID;
    }
    public void setID_P(int ID) {
        this.ID = ID;
    }
    private LinearLayout produtoContainer;
    private String urlImg;
    public int getValor_nutricional() {
        return valor_nutricional;
    }
    public String categoria;
    private float preco;
    private LinearLayout rec2;
    private LinearLayout rec3;
    public void setValor_nutricional(int valor_nutricional) {
        this.valor_nutricional = valor_nutricional;
    }

    private int valor_nutricional;

    private void initFields()
    {
        setID_P(produto.id);
        setValor_nutricional(MainActivity.listaProdutos.get(produto.id_nutri).Valor_Nutricional);
    }

    public Datas.ProdutosMercados getProduto()
    {
        return this.produto;
    }

    public Produto(Context context, Datas.ProdutosMercados produto, MainActivity.Categoria cat)
    {
        super(context);
        this.produto = produto;
        this.categoria = MainActivity.listaProdutos.get(produto.id_nutri).Categoria;
        this.preco = produto.preco;
        urlImg = this.produto.urlImagem;
        initFields();
        this.cat = cat;
        init();
    }

    public Produto(Context context, int id, float valor_nutricional, MainActivity.Categoria cat)
    {
        super(context);
        initFields();
        this.cat = cat;
        init();
    }

    public Produto(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public Produto(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init()
    {
        LayoutInflater.from(getContext()).inflate(R.layout.produto_layout, this, true);

        rec2 = findViewById(R.id.contNutri1);
        rec3 = findViewById(R.id.contNutri2);

            rec = (ImageView) findViewById(R.id.rec);
            if(getValor_nutricional() >= 50)
            {
                rec2.setBackgroundColor(Color.parseColor("#87CF03"));
                rec3.setBackgroundColor(Color.parseColor("#87CF03"));
            }
            else
            {
                rec2.setBackgroundColor(Color.RED);
                rec3.setBackgroundColor(Color.RED);
            }

        nomeProduto = (TextView) findViewById(R.id.nomeProduto);
        nomeProduto.setText(produto.nomeProduto);

        fotoProduto = (ImageView) findViewById(R.id.fotoProduto);
        trocaImg();

        fundoProduto =  findViewById(R.id.fundoProduto);
        produtoContainer = findViewById(R.id.produto1);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonSelecionado)
                {
                    buttonSelecionado = false;
                    fundoProduto.setBackgroundColor(Color.parseColor("#FFFAF7F6"));
                    switch(cat) {
                        case cereal:
                            MainActivity.getProdutosEscolhidosCereal().remove(produto);
                            break;
                        case bebida:
                            MainActivity.getProdutosEscolhidosBebidas().remove(produto);
                            break;
                        case carne:
                            MainActivity.getProdutosEscolhidosCarne().remove(produto);
                            break;
                        case outros:
                            MainActivity.getProdutosEscolhidosOutros().remove(produto);
                            break;
                    }

                    MainActivity.itensSelecionados--;
                    MainActivity2.exibeButtonFinalizar();
                }
                else {
                    buttonSelecionado = true;
                    fundoProduto.setBackgroundColor(Color.parseColor("#FFDDFFBB"));
                    switch(cat) {
                        case cereal:
                            MainActivity.setProdutosEscolhidosCereal(produto);
                            break;
                        case bebida:
                            MainActivity.setProdutosEscolhidosBebidas(produto);
                            break;
                        case carne:
                            MainActivity.setProdutosEscolhidosCarne(produto);
                            break;
                        case outros:
                            MainActivity.setProdutosEscolhidosOutros(produto);
                            break;
                    }
                    MainActivity.itensSelecionados++;
                    MainActivity2.exibeButtonFinalizar();
                }
            }
        });

        contNutri = findViewById(R.id.contNutri1);
        contNutri2 = findViewById(R.id.contNutri2);
        contPreco = findViewById(R.id.contPreco);
        textNutri = findViewById(R.id.textNutri);
        textNutri2 = findViewById(R.id.textNutri2);
        textPreco = findViewById(R.id.textPre);


        setPreco();
        setValorNutri();
        setImg();
    }

    public void ocultaButton()
    {
        buttonAdd.setVisibility(View.INVISIBLE);

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) produtoContainer.getLayoutParams();

        // Define a margem top para 6 pixels
        layoutParams.topMargin = 6;
        //layoutParams.bottomMargin = 5;

        // Aplica os novos parâmetros de layout à View
        produtoContainer.setLayoutParams(layoutParams);

    }

    public void modoProd2()
    {
        //rec.setVisibility(View.GONE);
        buttonAdd.setVisibility(View.GONE);
        contPreco.setVisibility(View.VISIBLE);
        contNutri.setVisibility(View.GONE);
        contNutri2.setVisibility(View.VISIBLE);
    }
    private void trocaImg()
    {
        switch (cat)
        {
            case cereal:
                fotoProduto.setImageResource(R.drawable.cereal);
                break;
            case bebida:
                break;
            case carne:
                break;
            case outros:
                break;
        }
    }

    public void setImg()
    {
        Picasso.get()
                .load(urlImg)
                .into(fotoProduto);
    }

    public void setPreco()
    {
        String p = String.format("R$ %.2f", preco);
        textPreco.setText(String.valueOf(p));
    }

    public void setValorNutri()
    {
        textNutri.setText(String.valueOf(getValor_nutricional()));
        textNutri2.setText(String.valueOf(getValor_nutricional()));
    }

}
