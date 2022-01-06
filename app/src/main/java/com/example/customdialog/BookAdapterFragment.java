package com.example.customdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customdialog.databinding.LayoutOfRecyclerViewBinding;
import com.example.customdialog.roomDatabase.TextDao;
import com.example.customdialog.roomDatabase.TextDatabase;
import com.example.customdialog.roomDatabase.TextEntity;
import com.example.customdialog.roomDatabase.UtilityHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookAdapterFragment extends RecyclerView.Adapter<BookAdapterFragment.BookViewHolder> {

    private List<TextEntity> list;
    private OnFavIconClickListener listener;

    LayoutOfRecyclerViewBinding binding;

    Context context;

    Random rnd = new Random();
    int currentColor = Color.argb(255, rnd.nextInt(156), rnd.nextInt(256), rnd.nextInt(256));

    public void setList(List<TextEntity> list) {
        this.list = list;
    }


    /*OnFavIconClickListener listener,*/

    BookAdapterFragment(OnFavIconClickListener listener) {

        this.listener=listener;
        //this.listener=listener;  // listener is for our click
//        this.listener1 = listener1; // listener1 is our heart.
    }

    public interface OnFavIconClickListener{

        void clickFav(int position,CompoundButton buttonView,boolean isChecked);
    }
    // click event for item click
//    public interface onItemClickListener{
//      void onClick(int position);
//    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       // View dialogView = inflater.inflate(R.layout.activity_list_view,null);
        binding = LayoutOfRecyclerViewBinding.inflate(inflater, parent, false);
        BookViewHolder viewHolder = new BookViewHolder(binding);
        context= parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        TextEntity textEntity = list.get(position);

        // in BindViewHolder we are getting position thats why we have kept if position in this method.

        if(textEntity.getFavorate()){   // if isFav is 1 that means it is checked and if condition will be true. so it will enter

            binding.fav.setBackgroundResource(R.drawable.ic_fav_red);

        }else{
            binding.fav.setBackgroundResource(R.drawable.ic_fav_white);
        }
        holder.binding.setData(textEntity);

        TextDatabase database = UtilityHelper.getDatabase(context);

        TextDao dao = database.getTextDao();


        // for random background color
        holder.itemView.setBackgroundColor(currentColor);

        // position for toast
//        holder.itemView.setOnClickListener(v -> {
//            listener1.onClick(position);
//        });

        holder.itemView.setOnClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("choose one")
                    .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           // System.out.println("which::" + which);
                            TextEntity tEntity = list.get(holder.getAdapterPosition());
                            dao.deleteText(tEntity);  // deleting from table
                            list = dao.displayText();
                            notifyDataSetChanged();
                        }
                    }).show();

        });

        holder.binding.fav.setOnCheckedChangeListener((buttonView, isChecked) -> {

            listener.clickFav(position,buttonView,isChecked);

        });
    }

    @Override
    public int getItemCount() {
        if(list!=null){ // if list is not null
            return list.size();
        }
        return 0;

    }


    public static class BookViewHolder extends RecyclerView.ViewHolder {
        private LayoutOfRecyclerViewBinding binding;

        public BookViewHolder(@NonNull LayoutOfRecyclerViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }
}

