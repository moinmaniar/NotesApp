package com.example.customdialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customdialog.databinding.LayoutOfRecyclerViewBinding;
import com.example.customdialog.roomDatabase.TextEntity;

import java.util.List;

public class FavorateAdapter extends RecyclerView.Adapter<FavorateAdapter.ViewHolder> {

    private  LayoutOfRecyclerViewBinding binding;
    Context context;
    private List<FavorateModel> list;
    public FavorateAdapter(@NonNull Context context) {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutOfRecyclerViewBinding binding = LayoutOfRecyclerViewBinding.inflate(inflater, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      FavorateModel favorateModel= list.get(position);


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  LayoutOfRecyclerViewBinding binding;
        public ViewHolder(@NonNull LayoutOfRecyclerViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
