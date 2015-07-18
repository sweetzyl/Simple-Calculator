package com.customprint.sweetzyl.simplecalculator.Presenter;

import com.customprint.sweetzyl.simplecalculator.Model.Model;

/**
 * Created by Sweetzyl on 7/18/2015.
 */
public  interface operation_presenter {

    public boolean isFieldEmpty(String field);
    public void onOperatorClick(String fNum, String sNum, String operator);
    public Model getModel();

}
