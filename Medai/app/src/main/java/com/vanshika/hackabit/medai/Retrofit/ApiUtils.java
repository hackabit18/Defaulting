package com.vanshika.hackabit.medai.Retrofit;

public class ApiUtils {
    private ApiUtils() {}
    public static final String BASE_URL = "https://med-ai.herokuapp.com/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
