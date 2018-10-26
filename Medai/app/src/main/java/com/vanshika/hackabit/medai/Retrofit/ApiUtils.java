package com.vanshika.hackabit.medai.Retrofit;

public class ApiUtils {
    private ApiUtils() {}
    public static final String BASE_URL = "http://192.168.43.145:8000/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
