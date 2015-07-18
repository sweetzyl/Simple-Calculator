package com.customprint.sweetzyl.simplecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;


import com.customprint.sweetzyl.simplecalculator.Presenter.Result_presenter;
import com.customprint.sweetzyl.simplecalculator.Model.Model;

import java.text.ParseException;


import butterknife.Bind;
import butterknife.ButterKnife;



/**
 * Created by Sweetzyl on 7/18/2015.
 */
public class ResultActivity extends AppCompatActivity implements Result_presenter {


    @Bind(R.id.FirstNumber)
    TextView firstNumber;

    @Bind(R.id.Operator)
    TextView operator;

    @Bind(R.id.secondnumber)
    TextView secondnumber;

    @Bind(R.id.Result)
    TextView tvresult;

    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        ButterKnife.bind(this);
        model = getIntent().getParcelableExtra(KEY_INPUT);
        if(model != null){
            firstNumber.setText(model.getFirstNumber());
            operator.setText(model.getOperator());
            secondnumber.setText(model.getSecondNumber());
            String result = model.getResult();
            try {
                validateResult(result);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setSuccessColor() {
        tvresult.setBackgroundColor(getResources().getColor(R.color.error));
    }

    @Override
    public void setErrorColor() {
        tvresult.setBackgroundColor(getResources().getColor(R.color.correct));
    }

    @Override
    public void validateResult(String result) throws ParseException {
        if(model.getNumFormat(result).doubleValue() == -1D){
            tvresult.setText("ERROR");
            setErrorColor();
        }else {
            tvresult.setText(result);
            setSuccessColor();
        }

        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
   }





}
