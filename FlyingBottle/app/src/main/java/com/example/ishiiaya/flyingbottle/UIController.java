package com.example.ishiiaya.flyingbottle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ishiiaya on 2017/05/01.
 */

public class UIController {

    private static UIController mUIController;
    private AppCompatActivity mAppCompatActivity;
    Button mThrowButton;
    Button mPickButton;
    Button mMakeButton;
    View mBottleView;
    EditText mEditText;

    private UIController(AppCompatActivity appCompatActivity) {
        mAppCompatActivity = appCompatActivity;
    }

    public static UIController getInstance(AppCompatActivity appCompatActivity) {
        if (null == mUIController) {
            return new UIController(appCompatActivity);
        }
        return mUIController;
    }

    public void initialize() {
        mThrowButton = (Button) mAppCompatActivity.findViewById(R.id.throwBottle);
        mPickButton = (Button) mAppCompatActivity.findViewById(R.id.pickBottle);
        mMakeButton = (Button) mAppCompatActivity.findViewById(R.id.makeNewBottle);
        mEditText = (EditText) mAppCompatActivity.findViewById(R.id.countText);
        mBottleView = mAppCompatActivity.findViewById(R.id.pic);
    }

    public void showIndicationDialog(int dialogName) {
        if (Constants.DIALOG_NODATA_SESSION == dialogName) {
            new AlertDialog.Builder(mAppCompatActivity).setTitle(UIConstants.APP_NAME).
                    setIcon(android.R.drawable.sym_def_app_icon).
                    setMessage(UIConstants.PICK_INDICATION).show();
        } else if (Constants.DIALOG_NODATA_DB == dialogName) {
            new AlertDialog.Builder(mAppCompatActivity).setTitle(UIConstants.APP_NAME).
                    setIcon(android.R.drawable.sym_def_app_icon).
                    setMessage(UIConstants.NO_BOTTLE_WARNING).show();
        }
    }

    public void doAnimate(int animateAction) {
        if (Constants.THROW == animateAction) {
            mBottleView.animate().rotationX(300).translationX(500).scaleX(0.1f).alpha(0).
                    setDuration(3000l).translationY(-200);
        } else if (Constants.PICK == animateAction) {
            mBottleView.animate().rotationX(-360).translationX(0).scaleX(1f).alpha(1).
                    setDuration(3000l);
        } else if (Constants.DESTROY == animateAction) {
            mBottleView.animate().alpha(0).setDuration(2000l);
        }
    }

    public void setAnimateInit() {
        mBottleView.setRotationX(300);
        mBottleView.setTranslationX(500);
        mBottleView.setScaleX(0.1f);
        mBottleView.setAlpha(0);
    }

    public void switchButtonLock(int activeButtonName) {

        if (Constants.ACTIVE_PICK == activeButtonName) {
            mThrowButton.setClickable(false);
            mPickButton.setClickable(true);
            mMakeButton.setClickable(true);
        } else if (Constants.ACTIVE_THROW == activeButtonName) {
            mPickButton.setClickable(false);
            mThrowButton.setClickable(true);
            mMakeButton.setClickable(false);
        }
    }


    public void freshBottleCount(int count) {
        mEditText.setText(String.valueOf(count) + " bottles in the sea");
    }
}
