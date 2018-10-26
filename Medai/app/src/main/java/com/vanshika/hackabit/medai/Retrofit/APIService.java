package com.vanshika.hackabit.medai.Retrofit;

import com.vanshika.hackabit.medai.Models.MedicineDb;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("/data")
    Call<MedicineDb> getMedicineInfo();
}
