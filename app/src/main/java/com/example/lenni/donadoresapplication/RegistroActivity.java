package com.example.lenni.donadoresapplication;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener ,DatePickerDialog.OnDateSetListener{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txtFechaNacimiento)
    TextInputEditText txtNacimiento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ButterKnife.bind(this);
        txtNacimiento.setOnClickListener(this);
        toolbar.setTitle("Registro");
        setSupportActionBar(toolbar);
        ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtFechaNacimiento:
                    String a="";
                    openDateModal();
                break;
        }
    }

    private void openDateModal(){
        Calendar calendar=Calendar.getInstance();
        if(txtNacimiento.getText().toString().length()>0){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date d = sdf.parse(txtNacimiento.getText().toString());
                calendar.setTime(d);
            }catch (ParseException ex){

            }

        }
        else
                calendar=calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(RegistroActivity.this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String day="";
        String monthString="";
        if(dayOfMonth < 10)
            day="0"+String.valueOf(dayOfMonth);
        else
            day=String.valueOf(dayOfMonth);

        if(month+1 < 10)
            monthString="0"+String.valueOf(month+1);
        else
            monthString=String.valueOf(month);
        txtNacimiento.setText(year+"-"+monthString+"-"+day);
    }
}
