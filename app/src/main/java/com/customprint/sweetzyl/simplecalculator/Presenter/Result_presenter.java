package com.customprint.sweetzyl.simplecalculator.Presenter;

import java.text.ParseException;

/**
 * Created by Sweetzyl on 7/18/2015.
 */
public interface Result_presenter {

    static final String KEY_INPUT = "data_domain_object";
    public void setSuccessColor();
    public void setErrorColor();
    public void validateResult(String result) throws ParseException;
}
