package com.example.customdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Dao;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.customdialog.databinding.FragmentAddBinding;
import com.example.customdialog.databinding.FragmentBookBinding;
import com.example.customdialog.databinding.LayoutOfRecyclerViewBinding;
import com.example.customdialog.roomDatabase.TextDao;
import com.example.customdialog.roomDatabase.TextDatabase;
import com.example.customdialog.roomDatabase.TextEntity;
import com.example.customdialog.roomDatabase.UtilityHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;



public class BookFragment extends Fragment implements BookAdapterFragment.OnFavIconClickListener //implements HomeFragment.DialogAddListener //implements DialogAdd.MyInterface {
{
    @Override
    public void onPause() {
        super.onPause();
        Log.i("state","pause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("state","resume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("state","stop");
    }

    private FloatingActionButton fab1;
    private FragmentBookBinding binding;
    public List<TextEntity> list;
    private BookAdapterFragment adapterFragment;
    TextDao dao;
    private FragmentAddBinding binding1;
    private EditText add_name;
    TextDatabase textDatabase;

    Context context;



    public BookFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBookBinding.inflate(getLayoutInflater());
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ///////////////////////////////////////////////////////////////////////
        //list = new ArrayList();
        adapterFragment = new BookAdapterFragment( this);
        textDatabase = UtilityHelper.getDatabase(getContext());  // reverse process
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dao = textDatabase.getTextDao();   //  select * from tablename;
        refreshAdapter();

        /////////////////////////////////////////////////////////////////////////////////////////////




        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding1=FragmentAddBinding.inflate(getLayoutInflater());

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setView(binding1.getRoot())
                        .setTitle("ADD")
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }

                        })
                        .setPositiveButton("add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name =  binding1.addName.getText().toString();



                                if (!name.isEmpty()) {

                                    dao.insertText(new TextEntity(name,false));
                                    refreshAdapter();
                                }




                            }

                        });




                builder.show();

            }

        });



        }

    public void refreshAdapter() {


        list=dao.displayText();
        ///// for getting all the Previous Data in database onClick of Bookfragment
        // just refreshing the adapter fragment

        adapterFragment.setList(list); // avoiding .setList will result in no data on migrating from different fragment.
        binding.recyclerView.setAdapter(adapterFragment);  // whatever data is there in bookfragment(before) will
        // be set in recyclerView

    }

//
//    @Override
//    public void onItemClickFav(int position) {
//
////
////            Toast.makeText(getContext(), "Item is added " + position, Toast.LENGTH_SHORT).show();
////
////            //int gotId = textEntity.getId();
////
////            //System.out.println("gotId:::::::::::"+ gotId);
////            //textEntity.setFavorate(true);
////
////
////
////            System.out.println("if ::");
////            System.out.println("flag:::::::::" + flag);
////   //     }
//////        else if(flag){
//////            Toast.makeText(getContext(), "Item is removed " + position, Toast.LENGTH_SHORT).show();
//////            flag = true;
//////            System.out.println("else ::");
//////            System.out.println("flag:::::::::" + flag);
//        }
//
//
//
//    @Override
//    public void onUncheckFav(int position) {
////        Toast.makeText(getContext(), "Item is removed " + position, Toast.LENGTH_SHORT).show();
////        //Toast.makeText(getContext(), "IsFav" + position, Toast.LENGTH_SHORT).show();
//
//
//    }

//    @Override
//    public void onClick(int position) {
////        TextEntity  textEntity= list.get(position);
////        int gotId = textEntity.getId();
////        String idString = String.valueOf(gotId);
////        Toast.makeText(getContext(), textEntity.getText(),  Toast.LENGTH_LONG).show();
////
////        Handler handler= new Handler();
////        handler.postDelayed(new Runnable() {
////            @Override
////            public void run() {
////                Toast.makeText(getContext(), idString,  Toast.LENGTH_LONG).show();
////
////            }
////        },3500);  // first toast was not displaying thats why we used handler
//
//
//
//    }//for fragment we use getContext and not this or mainClass.this


    @Override
    public void clickFav(int position, CompoundButton buttonView,boolean isChecked) {

        if(isChecked){


            buttonView.setBackgroundResource(R.drawable.ic_fav_red);

            TextEntity e = list.get(position);

            e.setFavorate(true);  // setting up the value from 0 to 1 (false to true)


            dao.updateText(e); // updating the table  -> loading the data agin

//                Bundle bundle = new Bundle();
//                bundle.putBoolean(e);

            adapterFragment.notifyDataSetChanged();


                Toast.makeText(getContext(), "Added To Favourite", Toast.LENGTH_SHORT).show();


        }else {

            buttonView.setBackgroundResource(R.drawable.ic_fav_white);

            TextEntity e = list.get(position);

            e.setFavorate(false);


            dao.updateText(e);


            adapterFragment.notifyDataSetChanged();


                Toast.makeText(getContext(), "Removed from favorite", Toast.LENGTH_SHORT).show();

        }
    }
}


