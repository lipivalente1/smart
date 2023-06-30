package com.example.smartnutri;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServicesAPI {
        @GET("/nutri/produtos")
        Call<Datas> getListProdutos();
        @GET("/mercados/produtos")
        Call<Datas> getListMercados();

        //@POST("/lista")
        //Call<ResponseBody> getIds(@Body RequestBody dadosLista);

        @POST("/lista")
        Call<Datas.IDS> getIds(@Body RequestBody dadosLista);
}
