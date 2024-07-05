package com.example.attendence;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class Mydialog extends DialogFragment {
    public static final String CLASS_ADD_DIALOG="addClass";
    public static final String CLASS_UPDATE_DIALOG="updateClass";
    public static final String STUDENT_ADD_DIALOG="addStudent";
    public static final String  STUDENT_UPDATE_DIALOG = "updateStudent" ;
    private OnClickListner listner;
    private int roll;
    private String name;

    public Mydialog(int roll, String name) {

        this.roll = roll;
        this.name = name;
    }

    public Mydialog() {

    }

    public interface OnClickListner{
        void onclck(String text1,String text2);
    }

    public void setListner(OnClickListner listner) {
        this.listner = listner;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = null;
        if (getTag().equals(CLASS_ADD_DIALOG))dialog=getAddClassDialog();
        if(getTag().equals(STUDENT_ADD_DIALOG))dialog=getAddStudentDialog();
        if(getTag().equals(CLASS_UPDATE_DIALOG))dialog=getUpdateClassDialog();
        if(getTag().equals(STUDENT_UPDATE_DIALOG))dialog=getUpdateStudentDialog();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return dialog;
    }

    private Dialog getUpdateStudentDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);

        TextView title = view.findViewById(R.id.title);
        title.setText("Update Student");


        EditText roll_edit = view.findViewById(R.id.edit01);
        EditText name_edit = view.findViewById(R.id.edit02);

        roll_edit.setHint("Roll");
        name_edit.setHint("Name");


        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add = view.findViewById(R.id.add_btn);
        add.setText("Update");
        roll_edit.setText(roll+"");
        roll_edit.setEnabled(false);
        name_edit.setText(name);


        cancel.setOnClickListener(v-> dismiss());
        add.setOnClickListener(v-> {
            String roll = roll_edit.getText().toString();
            String name = name_edit.getText().toString();

            listner.onclck(roll,name);
            dismiss();

        });

        return builder.create();
    }

    private Dialog getUpdateClassDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);

        TextView title = view.findViewById(R.id.title);
        title.setText("Update Class");


        EditText class_edit = view.findViewById(R.id.edit01);
        EditText subject_edit = view.findViewById(R.id.edit02);

        class_edit.setHint("Class Name");
        subject_edit.setHint("Subject Name");

        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add = view.findViewById(R.id.add_btn);
        add.setText("update");


        cancel.setOnClickListener(v-> dismiss());
        add.setOnClickListener(v-> {
            String className = class_edit.getText().toString();
            String subName = subject_edit.getText().toString();
            listner.onclck(className,subName);
            dismiss();
        });

        return builder.create();


    }

    private Dialog getAddStudentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);

        TextView title = view.findViewById(R.id.title);
        title.setText("Add New Student");


        EditText roll_edit = view.findViewById(R.id.edit01);
        EditText name_edit = view.findViewById(R.id.edit02);

        roll_edit.setHint("Roll");
        name_edit.setHint("Name");

        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add = view.findViewById(R.id.add_btn);


        cancel.setOnClickListener(v-> dismiss());
        add.setOnClickListener(v-> {
            String roll = roll_edit.getText().toString();
            String name = name_edit.getText().toString();
            roll_edit.setText(String.valueOf(Integer.parseInt(roll)+1));
            name_edit.setText("");
            listner.onclck(roll,name);

        });

        return builder.create();

    }

    private Dialog getAddClassDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);

        TextView title = view.findViewById(R.id.title);
        title.setText("Add New Class");


        EditText class_edit = view.findViewById(R.id.edit01);
        EditText subject_edit = view.findViewById(R.id.edit02);

        class_edit.setHint("Class Name");
        subject_edit.setHint("Subject Name");

        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add = view.findViewById(R.id.add_btn);


        cancel.setOnClickListener(v-> dismiss());
        add.setOnClickListener(v-> {
            String className = class_edit.getText().toString();
            String subName = subject_edit.getText().toString();
            listner.onclck(className,subName);
            dismiss();
        });

        return builder.create();

    }
}
