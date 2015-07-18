package com.customprint.sweetzyl.simplecalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;



import com.customprint.sweetzyl.simplecalculator.Presenter.Main_presenter;
import com.customprint.sweetzyl.simplecalculator.Presenter.operation_presenter;
import com.customprint.sweetzyl.simplecalculator.Model.Model;
import com.customprint.sweetzyl.simplecalculator.Presenter.operation_presenter_impl;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class MainActivity extends AppCompatActivity implements Main_presenter {

    @Bind(R.id.firstnum)
    EditText edtfirstNum;

    @Bind(R.id.secondnum)
    EditText edtsecondNum;

    operation_presenter operationPresenter;
    Model model;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        operationPresenter = new operation_presenter_impl();
        awesomeValidation = new AwesomeValidation(ValidationStyle.COLORATION);
        awesomeValidation.setColor(Color.RED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @OnClick({R.id.btnAdd, R.id.btnMinus, R.id.btnDivide, R.id.btnMultiply})
    public void computeOperation(Button view) {
        String fNum = this.edtfirstNum.getText().toString();
        String sNum = this.edtsecondNum.getText().toString();
        String optr = view.getText().toString();
        if(!operationPresenter.isFieldEmpty(fNum) && !operationPresenter.isFieldEmpty(sNum)){
            operationPresenter.onOperatorClick(fNum, sNum, optr);
            startResultActivity(operationPresenter.getModel());
        }else{
            if(operationPresenter.isFieldEmpty(fNum)){
                setFirstFieldError();
            }
            if (operationPresenter.isFieldEmpty(sNum)) {
                setSecondFieldError();
            }
        }
    }

    @OnClick(R.id.btnCancel)
    public void clearEditText() {
        this.edtfirstNum.setText("");
        this.edtsecondNum.setText("");
        this.edtfirstNum.setError(null);
        this.edtsecondNum.setError(null);
        this.edtfirstNum.requestFocus();
    }

    @OnClick(R.id.btnExit)
    public void exitApp() {
        this.finish();
    }

    @Override
    public void setFirstFieldError() {
        awesomeValidation.addValidation(edtfirstNum, "Error", "Please Input");
        awesomeValidation.validate();
    }

    @Override
    public void setSecondFieldError() {
        awesomeValidation.addValidation(edtsecondNum, "Error", "Please Input");
        awesomeValidation.validate();
    }

    public void startResultActivity(Model model) {
        Intent intent = new Intent(getBaseContext(),ResultActivity.class);
        intent.putExtra(KEY_INPUT, model);
        startActivity(intent);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
