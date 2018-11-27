package com.example.rplrus11.midsemester12rpl;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {
    @GET("upcoming?api_key=d63e33e85fff1cc246603deae467eb33")
    Call<jsonRespond>getJsonUpcoming();
}
