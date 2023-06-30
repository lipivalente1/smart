package com.example.smartnutri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.smartnutri.Datas.AllProdutos;

import org.json.JSONObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    public static final String categorias[] = {"Bebidas", "Grãos e Cereais",
            "Carnes", "Outros"};

    public TextView text;

    public static Integer[] ids;
    private static final String LOG_TAG = "";
    public static List<AllProdutos> listaProdutos;
    public static List<Datas.ProdutosMercados> listaMercados;
    public static ArrayList<Datas.ProdutosMercados> produtosEscolhidosCereal = new ArrayList<>();
    public static ArrayList<Datas.ProdutosMercados> produtosEscolhidosBebidas = new ArrayList<>();
    public static ArrayList<Datas.ProdutosMercados> produtosEscolhidosOutros = new ArrayList<>();
    public static ArrayList<Datas.ProdutosMercados> produtosEscolhidosCarne = new ArrayList<>();
    public static ArrayList<Produto> produtosCatCereal = new ArrayList<>();
    public static ArrayList<Produto> produtosCatBebidas = new ArrayList<>();
    public static ArrayList<Produto> produtosCatCarne = new ArrayList<>();
    public static ArrayList<Produto> produtosCatOutros = new ArrayList<>();
    public static int itensSelecionados; //qntd de itens selecionados pelo user
    ServicesAPI conAPI;
    public static Categoria catAtual;
    public static enum Categoria {
        bebida, cereal, carne, outros
    }

    public static void setProdutosCatCereal(Produto produto) {
        MainActivity.produtosCatCereal.add(produto);
    }

    public static List<Produto> getProdutosCatCereal() {
        return produtosCatCereal;
    }

    public static void setProdutosCatBebidas(Produto produto) {
        MainActivity.produtosCatBebidas.add(produto);
    }

    public static List<Produto> getProdutosCatBebidas() {
        return produtosCatBebidas;
    }

    public static void setProdutosCatCarne(Produto produto) {
        MainActivity.produtosCatCarne.add(produto);
    }

    public static List<Produto> getProdutosCatCarne() {
        return produtosCatCarne;
    }

    public static void setProdutosCatOutros(Produto produto) {
        MainActivity.produtosCatOutros.add(produto);
    }

    public static List<Produto> getProdutosCatOutros() {
        return produtosCatOutros;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StringBuilder infos = new StringBuilder();

        conAPI = NetworkUtils.getClient().create(ServicesAPI.class);

        Call<Datas> call = conAPI.getListMercados();
        Call<Datas> call2 = conAPI.getListProdutos();
        call.enqueue(new Callback<Datas>() {
            @Override
            public void onResponse(Call<Datas> call, Response<Datas> response) {
               /* infos.append(response.body().produtosList.get(0).NOME_PRODUTO+"\n").
                        append(response.body().produtosList.get(1).NOME_PRODUTO+"\n")
                        .append(response.body().produtosList.get(2).NOME_PRODUTO+"\n");
                */
                listaMercados = response.body().getListMercados();
                Log.d("MERCADO", listaMercados.toString());
            }

            @Override
            public void onFailure(Call<Datas> call, Throwable t) {
                //text.setText("Conecta na net ou liga a api na tomada, por obséquio ");
            }
        });
        call2.enqueue(new Callback<Datas>() {
            @Override
            public void onResponse(Call<Datas> call, Response<Datas> response) {
                //infos.append(response.body().getListProdutos().get(5).NOME_PRODUTO);
                // text.setText(infos.toString());
                listaProdutos = response.body().getListProdutos();
            }

            @Override
            public void onFailure(Call<Datas> call, Throwable t) {
            }
        });
    }

    public void openA2(View view) {
        Intent intent = new Intent(this, MainActivity2.class);


        if (view.getId() == R.id.id_bebida) {
            intent.putExtra("categoria", categorias[0]);
            catAtual = Categoria.bebida;
            startActivity(intent);
        }

        if (view.getId() == R.id.id_cereal) {
            intent.putExtra("categoria", categorias[1]);
            catAtual = Categoria.cereal;
            startActivity(intent);
        }

        if (view.getId() == R.id.id_carne) {
            intent.putExtra("categoria", categorias[2]);
            catAtual = Categoria.carne;
            startActivity(intent);
        }

        if (view.getId() == R.id.id_outros) {
            intent.putExtra("categoria", categorias[3]);
            catAtual = Categoria.outros;
            startActivity(intent);
        }
    }

    public static List<AllProdutos> getListaProdutos() {
        return listaProdutos;
    }

    public static void setListaProdutos(List<AllProdutos> listaProdutos) {
        MainActivity.listaProdutos = listaProdutos;
    }

    public static List<Datas.ProdutosMercados> getProdutosEscolhidosCereal() {
        return produtosEscolhidosCereal;
    }

    public static void setProdutosEscolhidosCereal(Datas.ProdutosMercados produtosEscolhidosCereal) {
        MainActivity.produtosEscolhidosCereal.add(produtosEscolhidosCereal);
    }

    public static List<Datas.ProdutosMercados> getProdutosEscolhidosBebidas() {
        return produtosEscolhidosBebidas;
    }

    public static void setProdutosEscolhidosBebidas(Datas.ProdutosMercados produtosEscolhidosBebidas) {
        MainActivity.produtosEscolhidosBebidas.add(produtosEscolhidosBebidas);
    }

    public static List<Datas.ProdutosMercados> getProdutosEscolhidosOutros() {
        return produtosEscolhidosOutros;
    }

    public static void setProdutosEscolhidosOutros(Datas.ProdutosMercados produtosEscolhidosOutros) {
        MainActivity.produtosEscolhidosOutros.add(produtosEscolhidosOutros);
    }

    public static List<Datas.ProdutosMercados> getProdutosEscolhidosCarne() {
        return produtosEscolhidosCarne;
    }

    public static void setProdutosEscolhidosCarne(Datas.ProdutosMercados produtosEscolhidosCarne) {
        MainActivity.produtosEscolhidosCarne.add(produtosEscolhidosCarne);
    }
}
