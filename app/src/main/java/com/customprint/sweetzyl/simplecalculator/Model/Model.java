package com.customprint.sweetzyl.simplecalculator.Model;

import android.os.Parcelable;
import android.os.Parcel;
import android.text.TextUtils;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by Sweetzyl on 7/18/2015.
 */
public class Model implements Parcelable {
    private String firstNum;
    private String secondNum;
    private String Operator;
    private double result;

    //Constructor
    public Model(String firstNumber, String secondNumber, String operator) {
        setOperand(firstNumber, secondNumber);
        setOperator(operator);
    }

    //Setter
    public Model setOperand(String firstOperand, String secondOperand) {
        try {
            this.firstNum = String.valueOf(getNumFormat(firstOperand));
            this.secondNum = String.valueOf(getNumFormat(secondOperand));
        } catch (ParseException e) {
            this.firstNum = firstOperand;
            this.secondNum = secondOperand;
        }
        return this;
    }

    public Model setOperator(String operator) {
        this.Operator = Operator;
        return this;
    }

    //Getters
    public String getFirstNumber() {
        return firstNum;
    }

    public String getSecondNumber() {
        return secondNum;
    }

    public String getOperator() {
        return Operator;
    }

    public String getResult() {
        return roundUpTwoDecimalPlaces(compute());
    }

    public Number getNumFormat(String number) throws ParseException {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        numberFormat.setGroupingUsed(true);
        return numberFormat.parse(number);
    }

    public double compute() {
        double firstNum = 0;
        double secondNum = 0;
        String operator = null;
        try {
            firstNum = getNumFormat(getFirstNumber()).doubleValue();
            secondNum = getNumFormat(getSecondNumber()).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        operator = String.valueOf(getOperator().charAt(0));
        double result = 0;

        if (!isComputable()) {
            return -1D;
        } else {
            switch (operator) {
                case "+":
                    result = firstNum + secondNum;
                    break;
                case "-":
                    result = firstNum - secondNum;
                    break;
                case "x":
                    result = firstNum * secondNum;
                    break;
                case "/":
                    result = firstNum / secondNum;
                    break;
            }
            return result;
        }
    }

    public boolean isComputable() {
        if (!TextUtils.isEmpty(getFirstNumber()) && !TextUtils.isEmpty(getSecondNumber()) && !TextUtils.isEmpty(getOperator())) {
            try {
                getNumFormat(getFirstNumber()).toString();
                getNumFormat(getSecondNumber()).toString();
            } catch (ParseException parseexception) {
                return false;
            }
            if (getOperator().length() == 1) {
                char c1 = getOperator().charAt(0);
                if (c1 == '+' || c1 == '-' || c1 == 'x' || c1 == '/') {
                    return true;
                }
            }
        }
        return false;
    }

    public String roundUpTwoDecimalPlaces(double result){
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        return decimalFormat.format(result);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstNum);
        dest.writeString(this.secondNum);
        dest.writeString(this.Operator);
        dest.writeDouble(this.result);
    }

    protected Model(Parcel in) {
        this.firstNum = in.readString();
        this.secondNum = in.readString();
        this.Operator = in.readString();
        this.result = in.readDouble();
    }


}
