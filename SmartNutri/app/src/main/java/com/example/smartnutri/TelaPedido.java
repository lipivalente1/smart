package com.example.smartnutri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.smartnutri.componentes.lista_produtos;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
//import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaPedido extends AppCompatActivity {

    private LinearLayout telaPedido;
    private AlertDialog dialog;
    public Integer[] ids;
    ServicesAPI conAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pedido);
        telaPedido = findViewById(R.id.telaPedido);


        if(MainActivity.getProdutosEscolhidosBebidas().size() != 0)
        {
            lista_produtos lista = new lista_produtos(this);
            lista.setNomeCategoria(MainActivity.categorias[0]);
            for(Datas.ProdutosMercados prod : MainActivity.getProdutosEscolhidosBebidas())
            {
                Produto p = new Produto(this, prod, MainActivity.catAtual);
                lista.addProduto(p);
                p.ocultaButton();
            }
            telaPedido.addView(lista);
        }

        if(MainActivity.getProdutosEscolhidosCereal().size() != 0)
        {
            lista_produtos lista = new lista_produtos(this);
            lista.setNomeCategoria(MainActivity.categorias[1]);
            for(Datas.ProdutosMercados prod : MainActivity.getProdutosEscolhidosCereal())
            {
                Produto p = new Produto(this, prod, MainActivity.catAtual);
                lista.addProduto(p);
                p.ocultaButton();
            }
            telaPedido.addView(lista);
        }

        if(MainActivity.getProdutosEscolhidosCarne().size() != 0)
        {

            lista_produtos lista = new lista_produtos(this);
            lista.setNomeCategoria(MainActivity.categorias[2]);
            for(Datas.ProdutosMercados prod : MainActivity.getProdutosEscolhidosCarne())
            {
                Produto p = new Produto(this, prod, MainActivity.catAtual);
                lista.addProduto(p);
                p.ocultaButton();
            }
            telaPedido.addView(lista);
        }

        if(MainActivity.getProdutosEscolhidosOutros().size() != 0)
        {
            lista_produtos lista = new lista_produtos(this);
            lista.setNomeCategoria(MainActivity.categorias[3]);
            for(Datas.ProdutosMercados prod : MainActivity.getProdutosEscolhidosOutros())
            {
                Produto p = new Produto(this, prod, MainActivity.catAtual);
                lista.addProduto(p);
                p.ocultaButton();
            }
            telaPedido.addView(lista);
        }


    }

    public void obterListaOtimizada(View view) {
        List<Map<String, Object>> dadosList = new ArrayList<>();

        TelaListaOtimizada.valorOrcamento = 0;

        if(MainActivity.getProdutosEscolhidosCereal().size() != 0)
        {
            for(Datas.ProdutosMercados produto : MainActivity.getProdutosEscolhidosCereal())
            {
                TelaListaOtimizada.valorOrcamento += produto.preco;
                TelaListaOtimizada.nomeMercado = produto.nomeMercado;

                Map<String, Object> dados = new HashMap<>();
                // Crie objetos JSON individuais
                dados.put("id", produto.id);
                dados.put("Valor_Nutricional", MainActivity.listaProdutos.get(produto.id_nutri).Valor_Nutricional);
                dados.put("preco", produto.preco);
                dados.put("Subcategoria", MainActivity.listaProdutos.get(produto.id_nutri).Subcategoria);
                dadosList.add(dados);

            }
        }

        if(MainActivity.getProdutosEscolhidosBebidas().size() != 0)
        {
            for(Datas.ProdutosMercados produto : MainActivity.getProdutosEscolhidosBebidas())
            {
                TelaListaOtimizada.valorOrcamento += produto.preco;
                TelaListaOtimizada.nomeMercado = produto.nomeMercado;
                Map<String, Object> dados = new HashMap<>();
                // Crie objetos JSON individuais
                dados.put("id", produto.id);
                dados.put("Valor_Nutricional", MainActivity.listaProdutos.get(produto.id_nutri).Valor_Nutricional);
                dados.put("preco", produto.preco);
                dados.put("Subcategoria", MainActivity.listaProdutos.get(produto.id_nutri).Subcategoria);
                dadosList.add(dados);

            }
        }

        if(MainActivity.getProdutosEscolhidosCarne().size() != 0)
        {

            for(Datas.ProdutosMercados produto : MainActivity.getProdutosEscolhidosCarne())
            {
                TelaListaOtimizada.valorOrcamento += produto.preco;
                TelaListaOtimizada.nomeMercado = produto.nomeMercado;
                Map<String, Object> dados = new HashMap<>();
                // Crie objetos JSON individuais
                dados.put("id", produto.id);
                dados.put("Valor_Nutricional", MainActivity.listaProdutos.get(produto.id_nutri).Valor_Nutricional);
                dados.put("preco", produto.preco);
                dados.put("Subcategoria", MainActivity.listaProdutos.get(produto.id_nutri).Subcategoria);
                dadosList.add(dados);

            }
        }

        if(MainActivity.getProdutosEscolhidosOutros().size() != 0)
        {

            for(Datas.ProdutosMercados produto : MainActivity.getProdutosEscolhidosOutros())
            {
                TelaListaOtimizada.valorOrcamento += produto.preco;
                TelaListaOtimizada.nomeMercado = produto.nomeMercado;
                Map<String, Object> dados = new HashMap<>();
                // Crie objetos JSON individuais
                dados.put("id", produto.id);
                dados.put("Valor_Nutricional", MainActivity.listaProdutos.get(produto.id_nutri).Valor_Nutricional);
                dados.put("preco", produto.preco);
                dados.put("Subcategoria", MainActivity.listaProdutos.get(produto.id_nutri).Subcategoria);
                dadosList.add(dados);

            }
        }

        Map<String, Object> dadosResult = new HashMap<>();
        dadosResult.put("lista_app", dadosList);

        SharedPreferences sharedPreferences = getSharedPreferences("testeID", Context.MODE_PRIVATE);

        Log.d("JA", (new JSONObject(dadosResult)).toString());

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(dadosResult)).toString());
        conAPI = NetworkUtils.getClient().create(ServicesAPI.class);
          Call<Datas.IDS> call = conAPI.getIds(body);

            call.enqueue(new Callback<Datas.IDS>() {
                @Override
                public void onResponse(Call<Datas.IDS> call, Response<Datas.IDS> response) {

                    MainActivity.ids = response.body().ids;

                    Intent intent = new Intent(TelaPedido.this, TelaListaOtimizada.class);
                    startActivity(intent);

                }

                @Override
                public void onFailure(Call<Datas.IDS> call, Throwable t) {
                }
            });

        }
    }





