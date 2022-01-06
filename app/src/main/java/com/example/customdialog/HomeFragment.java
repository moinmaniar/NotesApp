package com.example.customdialog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.customdialog.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    List<MyPhotos> myList;


    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myList = new ArrayList<>(); // initialization

        myPhotoAdapter adapter1 = new myPhotoAdapter();

        binding.recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.recyclerViewHome.setAdapter(adapter1);


        RetrofitService service = RetrofitClient.getRetroService();

        Call<List<MyPhotos>> call = service.getAllPhotos();

        call.enqueue(new Callback<List<MyPhotos>>() {
            @Override
            public void onResponse(Call<List<MyPhotos>> call, Response<List<MyPhotos>> response) {
                myList =response.body();
                System.out.println("mylist:::"+myList);
                adapter1.setList(myList);

                binding.recyclerViewHome.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call<List<MyPhotos>> call, Throwable t) {
                Log.i("callerror", t.toString());
            }
        });
    }

    public interface DialogAddListener {
        void applyText(String name);
    }
}