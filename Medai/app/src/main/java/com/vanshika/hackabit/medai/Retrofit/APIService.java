package com.vanshika.hackabit.medai.Retrofit;

import com.vanshika.hackabit.medai.Models.MedicineDb;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @POST("/medData/")
    Call<MedicineDb> getMedicineInfo();
//    @FormUrlEncoded
//    @POST("/medData")
//    Call <Void> sendMedName(@Field("name") String medName);
    /*
    @FormUrlEncoded
    @POST("/send/")*/
    //Call <Void> sendMedName(@Field("name") String medName);

}
