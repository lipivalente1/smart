package com.example.smartnutri;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datas {
    public Produto getProdutos() {
        return produtos;
    }
    public void setProdutos(Produto produtos) {
        this.produtos = produtos;
    }

    @SerializedName("produto_nutri")
    Produto produtos;
    public class Produto{
        public int getID_PRODUTO() {
            return ID_PRODUTO;
        }

        public void setID_PRODUTO(String ID_PRODUTO) {
            //this.ID_PRODUTO = ID_PRODUTO;
        }

        public String getNOME_PRODUTO() {
            return NOME_PRODUTO;
        }

        public void setNOME_PRODUTO(String NOME_PRODUTO) {
            this.NOME_PRODUTO = NOME_PRODUTO;
        }

        public float getEnergia() {
            return Energia;
        }

        public void setEnergia(String energia) {
           // Energia = energia;
        }

        public float getProteina() {
            return Proteina;
        }

        public void setProteina(String proteina) {
            //Proteina = proteina;
        }

        public float getLipideos() {
            return Lipideos;
        }

        public void setLipideos(String lipideos) {
            //Lipideos = lipideos;
        }

        public float getCarboidrato() {
            return Carboidrato;
        }

        public void setCarboidrato(String carboidrato) {
            //Carboidrato = carboidrato;
        }

        public float getFibraAlimentar() {
            return FibraAlimentar;
        }

        public void setFibraAlimentar(String fibraAlimentar) {
            //FibraAlimentar = fibraAlimentar;
        }

        public float getSodio() {
            return Sodio;
        }

        public void setSodio(String sodio) {
            //Sodio = sodio;
        }

        public String getCategoria() {
            return Categoria;
        }

        public void setCategoria(String categoria) {
            Categoria = categoria;
        }

        public String getSubcategoria() {
            return Subcategoria;
        }

        public void setSubcategoria(String subcategoria) {
            Subcategoria = subcategoria;
        }

        public float getValor_Nutricional() {
            return Valor_Nutricional;
        }

        public void setValor_Nutricional(String valor_Nutricional) {
         //  Valor_Nutricional = valor_Nutricional;
        }

        /* ID_PRODUTO, NOME_PRODUTO, Energia, Proteina, Lipideos, Carboidrato,
                FibraAlimentar, Sodio, Categoria, Subcategoria, Valor_Nutricional;*/

        @SerializedName("ID_PRODUTO")
        public Integer ID_PRODUTO;
        @SerializedName("NOME_PRODUTO")
        public String NOME_PRODUTO;
        @SerializedName("Energia")
        public Float Energia;
        @SerializedName("Proteina")
        public Float Proteina;
        @SerializedName("Lipideos")
        public Float Lipideos;
        @SerializedName("Carboidrato")
        public Float Carboidrato;
        @SerializedName("FibraAlimentar")
        public Float FibraAlimentar;
        @SerializedName("Sodio")
        public Float Sodio;
        @SerializedName("Categoria")
        public String  Categoria;
        @SerializedName("Subcategoria")
        public String Subcategoria;
        @SerializedName("Valor_Nutricional")
        public Float Valor_Nutricional;
    }

    @SerializedName("produtos_nutri")
    public List<AllProdutos> produtosList = null;
    public List<AllProdutos> getListProdutos()
    {
        return produtosList;
    };
    @SerializedName("produtos_mercados")
    public List<ProdutosMercados> listaMercados;
    public List<ProdutosMercados> getListMercados(){return listaMercados;};

    public Perfil getPerfil(){return perfil;};
    public Perfil perfil;

    @SerializedName("id")
    public Integer[] ids;

    public Integer[] getId(){return ids;};


    public class AllProdutos {

        @SerializedName("ID_PRODUTO")
        public Integer ID_PRODUTO;
        @SerializedName("NOME_PRODUTO")
        public String NOME_PRODUTO;
        @SerializedName("Energia")
        public Float Energia;
        @SerializedName("Proteina")
        public Float Proteina;
        @SerializedName("Lipideos")
        public Float Lipideos;
        @SerializedName("Carboidrato")
        public Float Carboidrato;
        @SerializedName("FibraAlimentar")
        public Float FibraAlimentar;
        @SerializedName("Sodio")
        public Float Sodio;
        @SerializedName("Categoria")
        public String  Categoria;
        @SerializedName("Subcategoria")
        public String Subcategoria;
        @SerializedName("Valor_Nutricional")
        public int Valor_Nutricional;

    }

    public class ProdutosMercados{
        @SerializedName("id")
        public Integer id;
        @SerializedName("mercado")
        public String nomeMercado;
        @SerializedName("nome_produto")
        public String nomeProduto;
        @SerializedName("produto_nutri")
        public Integer id_nutri;
        @SerializedName("Endereco")
        public String endereco;
        @SerializedName("preco")
        public float preco;
        @SerializedName("imagem")
        public String urlImagem;
    }

    public class Substituiveis{
        //@SerializedName("Subcategoria")
        //public String subcategoria;
        //@SerializedName("Valor_Nutricional")
        //public float valor_nutricional;
        @SerializedName("id")
        public Integer id[];
        //@SerializedName("preco")
        //public float preco;
    }

    public class Perfil{
        @SerializedName("name")
        public String name;
        @SerializedName("job")
        public String job;
        @SerializedName("id")
        public Integer id;
        @SerializedName("createdAt")
        public String created;
    }

    public class IDS{
        @SerializedName("id")
        public Integer[] ids;
    }

}
