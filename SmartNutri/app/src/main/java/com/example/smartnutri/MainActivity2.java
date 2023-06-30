package com.example.smartnutri;

import static com.example.smartnutri.MainActivity.Categoria.bebida;
import static com.example.smartnutri.MainActivity.Categoria.carne;
import static com.example.smartnutri.MainActivity.Categoria.cereal;
import static com.example.smartnutri.MainActivity.Categoria.outros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.smartnutri.Datas.AllProdutos;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private MainActivity.Categoria cat;
    private String nomeCat;
    private List<AllProdutos> listaProdutos;
    private static Button botaoFinalizar;
    private LinearLayout layoutPai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listaProdutos = MainActivity.getListaProdutos();

        layoutPai = findViewById(R.id.linearPai);
        Intent intent = getIntent();
        cat = MainActivity.catAtual;

        botaoFinalizar = (Button) findViewById(R.id.button_Finalizar);

        //cat = intent.getStringExtra("categoria");
        // prod1.rec.setImageResource(R.drawable.verde);

        switch(cat)
        {
            case bebida:
                nomeCat = MainActivity.categorias[0];
                break;
            case cereal:
                nomeCat = MainActivity.categorias[1];
                break;
            case carne:
                nomeCat = MainActivity.categorias[2];
                break;
            case outros:
                nomeCat = MainActivity.categorias[3];
                break;
        }

        setTitle(nomeCat);

        if((cat != null) && (MainActivity.listaMercados != null) && (MainActivity.getProdutosCatCereal().size() == 0) && cat == cereal)
        {
            for(int i = 0; i < MainActivity.listaMercados.size(); i++)
            {
                if(listaProdutos.get(MainActivity.listaMercados.get(i).id_nutri).Categoria.equals("Cereais") && (MainActivity.listaMercados.get(i).nomeMercado.equals("Líder")))
                {
                    addProduto(MainActivity.listaMercados.get(i), cat);
                }
            }
        }
        else{
            if(MainActivity.getProdutosCatCereal().size() != 0 && cat == cereal)
            {
                for(Produto prod : MainActivity.getProdutosCatCereal())
                {
                    if(prod.categoria.equals("Cereais"))
                    {
                        exibeProduto(prod);
                    }
                }
            }
        }

        if((cat != null) && (MainActivity.listaMercados != null) && (MainActivity.getProdutosCatBebidas().size() == 0) && cat == bebida)
        {
            for(int i = 0; i < MainActivity.listaMercados.size(); i++)
            {
                if(listaProdutos.get(MainActivity.listaMercados.get(i).id_nutri).Categoria.equals("Bebidas") && (MainActivity.listaMercados.get(i).nomeMercado.equals("Líder")))
                {
                    addProduto(MainActivity.listaMercados.get(i), cat);
                }
            }
        }
        else{
            if(MainActivity.getProdutosCatBebidas().size() != 0 && cat == bebida)
            {
                for(Produto prod : MainActivity.getProdutosCatBebidas())
                {
                    if(prod.categoria.equals("Bebidas"))
                    {
                        exibeProduto(prod);
                    }
                }
            }
        }

        if((cat != null) && (MainActivity.listaMercados != null) && (MainActivity.getProdutosCatCarne().size() == 0) && (cat == carne) )
        {
            for(int i = 0; i < MainActivity.listaMercados.size(); i++)
            {
                if( listaProdutos.get(MainActivity.listaMercados.get(i).id_nutri).Categoria.equals("Carnes") && (MainActivity.listaMercados.get(i).nomeMercado.equals("Líder")))
                {
                    addProduto(MainActivity.listaMercados.get(i), cat);
                }
            }
        }
        else{
            if(MainActivity.getProdutosCatCarne().size() != 0 && cat == carne)
            {
                for(Produto prod : MainActivity.getProdutosCatCarne())
                {
                    if(prod.categoria.equals("Carnes"))
                    {
                        exibeProduto(prod);
                    }
                }
            }
        }

        if((cat != null) && (MainActivity.listaMercados != null) && (MainActivity.getProdutosCatOutros().size() == 0) && (cat == outros) )
        {
            for(int i = 0; i < MainActivity.listaMercados.size(); i++)
            {
                if(listaProdutos.get(MainActivity.listaMercados.get(i).id_nutri).Categoria.equals("Outros") && (MainActivity.listaMercados.get(i).nomeMercado.equals("Líder")))
                {
                    addProduto(MainActivity.listaMercados.get(i), cat);
                }
            }
        }
        else{
            if(MainActivity.getProdutosCatOutros().size() != 0 && cat == outros)
            {
                for(Produto prod : MainActivity.getProdutosCatOutros())
                {
                    if(prod.categoria.equals("Outros"))
                    {
                        exibeProduto(prod);
                    }
                }
            }
        }


        exibeButtonFinalizar();
    }

    public void openA3(View view) {
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Salvar os dados da instância no Bundle
        //outState.putString("categoria", cat);
        // Adicione outros dados da instância, se necessário
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restaurar os dados da instância do Bundle
       // cat = savedInstanceState.getString("categoria");
       // setTitle(cat);
        // Restaure outros dados da instância, se necessário
    }

    public static void exibeButtonFinalizar()
    {
        if(MainActivity.itensSelecionados > 0)
        {
            botaoFinalizar.setVisibility(View.VISIBLE);
        }
        else
        {
            botaoFinalizar.setVisibility(View.GONE);
        }
    }

    public void finalizarPedido(View view) {
        Intent telaPedido = new Intent(MainActivity2.this, TelaPedido.class);
        startActivity(telaPedido);
    }

    private void addProduto(Datas.ProdutosMercados p, MainActivity.Categoria cat)
    {

        //Produto prod = new Produto(this, listaProdutos.get(i).ID_PRODUTO, listaProdutos.get(i).Valor_Nutricional, cat);
        Produto prod = new Produto(this, p, cat);
        //prod.nomeProduto.setText(listaProdutos.get(i).NOME_PRODUTO);
        layoutPai.addView(prod);

        switch(cat){
            case cereal:
                MainActivity.setProdutosCatCereal(prod);
                break;
            case bebida:
                MainActivity.setProdutosCatBebidas(prod);
                break;
            case carne:
                MainActivity.setProdutosCatCarne(prod);
                break;
            case outros:
                MainActivity.setProdutosCatOutros(prod);
        }


        prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProd = new Intent(MainActivity2.this, MainActivity3.class);
                Produto vp = (Produto) v;

                intentProd.putExtra("id",  vp.getID_P() );
                startActivity(intentProd);

            }
        });
    }

    private void exibeProduto(Produto prod)
    {
        ViewGroup oldParent = (ViewGroup) prod.getParent();
        if (oldParent != null) {
            oldParent.removeView(prod);
        }
        layoutPai.addView(prod);
    }
}