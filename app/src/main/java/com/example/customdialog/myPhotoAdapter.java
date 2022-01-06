package com.example.customdialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customdialog.databinding.RetrofitRawItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class myPhotoAdapter extends RecyclerView.Adapter<myPhotoAdapter.MyPhotoViewHolder> {


    List<MyPhotos> mylist;
    public void setList(List<MyPhotos> list) {
        this.mylist = list;
    }


    @NonNull
    @Override
    public MyPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RetrofitRawItemBinding binding = RetrofitRawItemBinding.inflate(inflater,parent,false);
        MyPhotoViewHolder viewHolder = new MyPhotoViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPhotoViewHolder holder, int position) {
        MyPhotos photos = mylist.get(position);
        holder.binding.setData(photos);

        Picasso.get()
                .load(photos.getUrl())
                .resize(50, 50)
                .centerCrop()
                .into(holder.binding.imageView2);

        Picasso.get()
                .load(photos.getThumbnailUrl())
                .resize(50, 50)
                .centerCrop()
                .into(holder.binding.imageView3);
    }

    @Override
    public int getItemCount() {

        if(mylist!= null){
           return mylist.size();
        }
      // System.out.println("size:::" + mylist.size());
        return 0;
    }

    public class MyPhotoViewHolder extends RecyclerView.ViewHolder {
        RetrofitRawItemBinding binding;
        public MyPhotoViewHolder(@NonNull RetrofitRawItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
