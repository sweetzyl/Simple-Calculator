package com.customprint.sweetzyl.simplecalculator.Presenter;

import android.text.TextUtils;
import com.customprint.sweetzyl.simplecalculator.Model.Model;

/**
 * Created by Sweetzyl on 7/18/2015.
 */
public class operation_presenter_impl implements operation_presenter {
    Model model;
    @Override
    public boolean isFieldEmpty(String field) {
        if(TextUtils.isEmpty(field)){
            return true;
        }
        return false;
    }

    @Override
    public void onOperatorClick(String fNum, String sNum, String operator) {
        this.model = new Model(fNum,sNum,operator);
    }

    @Override
    public Model getModel() {
        return model;
    }
}


