package com.example.smartnutri.componentes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.smartnutri.MainActivity;
import com.example.smartnutri.MainActivity3;
import com.example.smartnutri.R;
import com.squareup.picasso.Picasso;


public class infoProduto extends LinearLayout {
    private String nome;
    private int valor_nutricional;
    private TableLayout tableLayout;
    private LinearLayout containerTable;
    //private Drawable foto;
   // MainActivity.Categoria catAtual;
    private TextView textNome;
    private ImageView imgProduto;
    private String urlImg;
    private int id;

    public infoProduto(Context context, int id)
    {
        super(context);
        this.id = id-1;
        this.nome = MainActivity.listaMercados.get(this.id).nomeProduto;
        this.valor_nutricional = MainActivity.listaProdutos.get(MainActivity.listaMercados.get(this.id).id_nutri).Valor_Nutricional;
        urlImg = MainActivity.listaMercados.get(this.id).urlImagem;
        init();
    }

    public infoProduto(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public infoProduto(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    private void init()
    {
        LayoutInflater.from(getContext()).inflate(R.layout.infoproduto, this, true);

        textNome = findViewById(R.id.textNome);
        textNome.setText(this.nome);

        TextView textValor = findViewById(R.id.textValor);
        textValor.setText(String.format("%d", this.valor_nutricional));

        LinearLayout backValor = findViewById(R.id.backValor);

        if(this.valor_nutricional >= 50)
        {
            backValor.setBackgroundColor(Color.parseColor("#65EC56"));
        }
        else{
            backValor.setBackgroundColor(Color.parseColor("#FF5722"));
        }

        containerTable  = findViewById(R.id.containerTable);
        tableLayout = findViewById(R.id.tableNutri);
        imgProduto = findViewById(R.id.imgProduto);
        setImg();
        setTable();
    }


    private void setTable()
    {
        TableRow headerRow = (TableRow) tableLayout.getChildAt(0);
        TextView header = (TextView) headerRow.getChildAt(1);
        header.setText(String.format("%.1f", MainActivity.listaProdutos.get(MainActivity.listaMercados.get(this.id).id_nutri).Energia));

        headerRow = (TableRow) tableLayout.getChildAt(1);
        header = (TextView) headerRow.getChildAt(1);
        header.setText(String.format("%.1f", MainActivity.listaProdutos.get(MainActivity.listaMercados.get(this.id).id_nutri).Proteina));

        headerRow = (TableRow) tableLayout.getChildAt(2);
        header = (TextView) headerRow.getChildAt(1);
        header.setText(String.format("%.1f", MainActivity.listaProdutos.get(MainActivity.listaMercados.get(this.id).id_nutri).Lipideos));

        headerRow = (TableRow) tableLayout.getChildAt(3);
        header = (TextView) headerRow.getChildAt(1);
        header.setText(String.format("%.1f", MainActivity.listaProdutos.get(MainActivity.listaMercados.get(this.id).id_nutri).Carboidrato));

        headerRow = (TableRow) tableLayout.getChildAt(4);
        header = (TextView) headerRow.getChildAt(1);
        header.setText(String.format("%.1f", MainActivity.listaProdutos.get(MainActivity.listaMercados.get(this.id).id_nutri).FibraAlimentar));

        headerRow = (TableRow) tableLayout.getChildAt(5);
        header = (TextView) headerRow.getChildAt(1);
        header.setText(String.format("%.1f", MainActivity.listaProdutos.get(MainActivity.listaMercados.get(this.id).id_nutri).Sodio));

    }

    public void setImg()
    {
        Picasso.get()
                .load(urlImg)
                .into(imgProduto);
    }
}
