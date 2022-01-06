package com.example.customdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.customdialog.databinding.FragmentAddBinding;

import java.util.ArrayList;

public class DialogAdd extends AppCompatDialogFragment {
   private FragmentAddBinding binding;
    public ArrayList<Fun> list;
    private EditText add_name;
   // private FragmentAddBinding binding1;
    private DialogAdd dialogAdd;
    private HomeFragment.DialogAddListener listener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);


        try {
           listener = (HomeFragment.DialogAddListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

    }
//private MyInterface myInterface;


    //observer // shared preference

    // dialog


//    public DialogAdd(MyInterface myInterface) {
//        this.myInterface = myInterface;
//    }





        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);




            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.fragment_add,null);
            add_name = view.findViewById(R.id.add_name);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setView(view)
                    .setTitle("ADD")
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }

                    })
                    .setPositiveButton("add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String name =  add_name.getText().toString();
                            System.out.println(" myname::" + name);
//                        listener.applyText(name);
                            // DialogAddListener listener;
                            listener.applyText(name);

                        }

                    });


            builder.show();


        }


//        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String gotTxt = binding.tvAddInfo.getEditableText().toString();
//
//
//                Bundle bundle = new Bundle();
//                bundle.putString("key", gotTxt);
//
//                myInterface.passData(gotTxt);
//
//
//                dismiss();
//
//                //   BookAdapterFragment b = new BookAdapterFragment(list);
//
//
//            }
//
//
//        });

    }
//    public interface MyInterface {
//
//        void passData(String data);
//
//
//    }



//    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,temp).commit();

//    DialogAdd dialogAdd = new DialogAdd();
//                dialogAdd.show(getActivity().getSupportFragmentManager(), "add dialog is here");


//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//        LayoutInflater inflater  = getActivity().getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_add,null);
//
//        builder.setView(view)
//                .setTitle("login")
//                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//
//                })
//                .setPositiveButton("add", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//
//                });
//                return builder.create();
//   }