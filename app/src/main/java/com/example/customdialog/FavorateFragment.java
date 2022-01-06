package com.example.customdialog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.customdialog.R;
import com.example.customdialog.databinding.FragmentCartBinding;
import com.example.customdialog.roomDatabase.TextDao;
import com.example.customdialog.roomDatabase.TextEntity;

import java.util.List;


public class FavorateFragment extends Fragment implements BookAdapterFragment.OnFavIconClickListener {

    FragmentCartBinding binding;
    TextDao dao;
    public List<TextEntity> list;
    public List<TextEntity> fav;
    FavorateAdapter favorateAdapter;
    BookAdapterFragment bookAdapterFragment;

    public FavorateFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        fav = dao.isFav();
        bookAdapterFragment = new BookAdapterFragment(this);
        bookAdapterFragment.setList(list);
        binding.recyclerViewOfFavorate.setAdapter(bookAdapterFragment);


    }

    @Override
    public void clickFav(int position, CompoundButton buttonView, boolean isChecked) {

    }
}


