package com.example.smartnutri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartnutri.componentes.lista_produtos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaListaOtimizada extends AppCompatActivity {

    private List<Datas.Substituiveis> substituiveis;
    TextView text;
    LinearLayout contProdutos;
    TextView valorOrc;
    TextView valorTot;
    TextView nomeMer;
   // public static int[] ids = {2, 3, 4, 5};
    public static Integer[] ids;
    public static float valorOrcamento;
    public static String nomeMercado;
    public static float valorTotal;

    public static List<Datas.ProdutosMercados> listaMercados = MainActivity.listaMercados;
    public static List<Datas.AllProdutos> listaProdutos = MainActivity.listaProdutos;
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_otimizada);

        valorTotal = 0;

        TelaListaOtimizada.ids = MainActivity.ids;
        //Log.d("YEAH2", "NO");

        valorOrc = findViewById(R.id.valorOrcamento);
        valorTot = findViewById(R.id.valorTotal);
        nomeMer = findViewById(R.id.nomeMercado);
        contProdutos = (LinearLayout) findViewById(R.id.contProdutos);


        nomeMer.setText(nomeMercado);
        valorOrc.setText(String.format("%.2f", valorOrcamento));

       // ids = TelaPedido.ids;

        lista_produtos listaCereais = new lista_produtos(this);
        listaCereais.setNomeCategoria(MainActivity.categorias[1]);
        lista_produtos listaCarne = new lista_produtos(this);
        listaCarne.setNomeCategoria(MainActivity.categorias[2]);
        lista_produtos listaBebidas = new lista_produtos(this);
        listaBebidas.setNomeCategoria(MainActivity.categorias[0]);
        lista_produtos listaOutros = new lista_produtos(this);
        listaOutros.setNomeCategoria(MainActivity.categorias[3]);


        if((ids != null) && (ids.length >= 0) && (listaMercados != null))
        {
            for(int i = 0; i < ids.length; i++ )
            {
                int idP = ids[i]-1;
                Produto p = new Produto(this, listaMercados.get(idP), MainActivity.catAtual);
                p.modoProd2();



                if(listaProdutos.get(listaMercados.get(idP).id_nutri).Categoria.equals("Cereais"))
                {
                    valorTotal += listaMercados.get(idP).preco;
                    listaCereais.addProduto(p);
                }
                if(listaProdutos.get(listaMercados.get(idP).id_nutri).Categoria.equals("Carnes"))
                {
                    valorTotal += listaMercados.get(idP).preco;
                    listaCarne.addProduto(p);
                }if(listaProdutos.get(listaMercados.get(idP).id_nutri).Categoria.equals("Bebidas"))
                {
                    valorTotal += listaMercados.get(idP).preco;
                    listaBebidas.addProduto(p);
                }if(listaProdutos.get(listaMercados.get(idP).id_nutri).Categoria.equals("Outros"))
                {
                    valorTotal += listaMercados.get(idP).preco;
                    listaOutros.addProduto(p);
                }

            }

                if(listaBebidas.count > 0)
                    contProdutos.addView(listaBebidas);
                if(listaCereais.count > 0)
                    contProdutos.addView(listaCereais);
                if(listaCarne.count > 0)
                    contProdutos.addView(listaCarne);
                if(listaOutros.count > 0)
                    contProdutos.addView(listaOutros);


                String valorT = String.format("%.2f", valorTotal);
            valorTot.setText(valorT);
        }
    }
}