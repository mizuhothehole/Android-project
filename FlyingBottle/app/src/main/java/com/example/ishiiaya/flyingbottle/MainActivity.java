package com.example.ishiiaya.flyingbottle;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.ViewAnimator;

import java.util.RandomAccess;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    private SessionData mSessionData;
    private BottleDbManager mBottleDbManager;
    private DialogInterface.OnClickListener mThrowAgainBtnListener;
    private DialogInterface.OnClickListener mThrowBtnListener;
    private DialogInterface.OnClickListener mDestroyListener;
    private UIController mUIController;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mBottleDbManager = BottleDbManager.getInstance();
        mBottleDbManager.initialize(this);
        mBottleDbManager.throwInitialData();

        mSessionData = SessionData.getSessionInstance();
        mSessionData.initialize();

        mUIController = UIController.getInstance(this);
        mUIController.initialize();

        initializeIf();
        mUIController.switchButtonLock(Constants.ACTIVE_PICK);
        mUIController.setAnimateInit();
        mUIController.freshBottleCount(mBottleDbManager.getBottleCount());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void makeEmptyBottle(View view) {
        mUIController.setAnimateInit();
        mSessionData.makeEmptyBottle();
        mUIController.switchButtonLock(Constants.ACTIVE_THROW);
        mUIController.doAnimate(Constants.PICK);
    }

    public void throwBottle(View view) {
        if (!StringUtil.isEmpty(mSessionData.getMessageToBeSent())) {
            throwBottleData();
            mSessionData.setMessageToBeSent(StringUtil.EMPTY);
        }
        mSessionData.setThrown(true);
        mUIController.switchButtonLock(Constants.ACTIVE_PICK);
        mUIController.doAnimate(Constants.THROW);
        mUIController.freshBottleCount(mBottleDbManager.getBottleCount());

    }

    public void pickBottle(View view) {
        mUIController.setAnimateInit();
        String bottleMsg = mBottleDbManager.getBottleData();
        if (StringUtil.isEmpty(bottleMsg)) {
            mUIController.showIndicationDialog(Constants.DIALOG_NODATA_DB);
        } else {
            mSessionData.fillBottleData(bottleMsg);
            mUIController.switchButtonLock(Constants.ACTIVE_THROW);
            mUIController.doAnimate(Constants.PICK);
            mUIController.freshBottleCount(mBottleDbManager.getBottleCount());
        }
    }

    public void doMsgProcess(View view) {
        if (mSessionData.isThrown()) {
            return;
        } else if (this.mSessionData.isGotActive()) {
            if (!doGotMsgProcess()) {
                mUIController.showIndicationDialog(Constants.DIALOG_NODATA_SESSION);
            }
        } else if (this.mSessionData.isSentActive()) {
            doSendMsgProcess();
        } else {
            mUIController.showIndicationDialog(Constants.DIALOG_NODATA_SESSION);
        }
    }

    private boolean doGotMsgProcess() {
        if (StringUtil.isEmpty(mSessionData.getMessageGot())) {
            return false;
        }
        mEditText = new EditText(this);
        mEditText.setText(StringUtil.nullToEmpty(mSessionData.getMessageGot()));
        new AlertDialog.Builder(this).setTitle(UIConstants.APP_NAME).
                setIcon(android.R.drawable.sym_def_app_icon).
                setView(mEditText).setPositiveButton(UIConstants.THROW_AGAIN, mThrowAgainBtnListener).
                setNegativeButton(UIConstants.DESTROY_BOTTLE, mDestroyListener).show();
        return true;
    }

    private void doSendMsgProcess() {
        mEditText = new EditText(this);
        new AlertDialog.Builder(this).setTitle(UIConstants.ENTER_INDICATION).
                setIcon(android.R.drawable.sym_def_app_icon).
                setView(mEditText).setPositiveButton(UIConstants.THROW_BOTTLE, mThrowBtnListener).
                setNegativeButton(UIConstants.DESTROY_BOTTLE, mDestroyListener).show();
    }

    private void throwBottleData() {
        if (StringUtil.isEmpty(mSessionData.getMessageToBeSent())) {
            return;
        }
        mBottleDbManager.throwBottleData(mSessionData.getMessageToBeSent());
        mSessionData.setMessageToBeSent(StringUtil.EMPTY);
    }

    private void initializeIf() {
        mThrowAgainBtnListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                throwBottleData();
                mSessionData.setThrown(true);
                mUIController.switchButtonLock(Constants.ACTIVE_PICK);
                mUIController.doAnimate(Constants.THROW);
                mUIController.freshBottleCount(mBottleDbManager.getBottleCount());
            }
        };
        mThrowBtnListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSessionData.setMessageToBeSent((mEditText.getText().toString()));
                throwBottleData();
                mSessionData.setMessageToBeSent(StringUtil.EMPTY);
                mSessionData.setThrown(true);
                mUIController.switchButtonLock(Constants.ACTIVE_PICK);
                mUIController.doAnimate(Constants.THROW);
                mUIController.freshBottleCount(mBottleDbManager.getBottleCount());
            }
        };
        mDestroyListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSessionData.initialize();
                mUIController.switchButtonLock(Constants.ACTIVE_PICK);
                mUIController.doAnimate(Constants.DESTROY);
                mUIController.freshBottleCount(mBottleDbManager.getBottleCount());
            }
        };
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
