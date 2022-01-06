package com.example.customdialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

@GET("photos")
Call<List<MyPhotos>> getAllPhotos();

}
