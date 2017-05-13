package com.example.ishiiaya.homeaccount;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    static EditText mDateFrom;
    static EditText mDateTo;
    Spinner mIssueKind;
    ListView mListView;
    ArrayList<HomeIssueEntity> list;
    HomeIssueAdapter myAdapter;
    static FragmentManager fragmanager;

    Runnable netWork = new Runnable() {
        @Override
        public void run() {
            HashMap<String, String> param = new HashMap<>();
            param.put(StringUtil.DATE_FROM, mDateFrom.getText().toString());
            param.put(StringUtil.DATE_TO, mDateTo.getText().toString());
            param.put(StringUtil.KIND, (String) mIssueKind.getSelectedItem());
            String responseData = StringUtil.EMPTY;
            try {
                responseData = HttpRequestUtil.getIssueDataByGet(ServerUtil.SERVER_URL, param);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString(ServerUtil.HTTP_RESPONSE, responseData);
            msg.setData(data);
            mHandler.sendMessage(msg);
        }
    };

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String responseData = data.getString(ServerUtil.HTTP_RESPONSE);
            JSONObject responseJsonObject;
            try {
                responseJsonObject = new JSONObject(responseData);
                JSONArray responseDataList =
                        responseJsonObject.getJSONArray(ServerUtil.JSON_DATA_KEY);
                list.clear();
                for (int i = 0; i < responseDataList.length() - 1; i++) {
                    JSONObject responseJsonDataItem = responseDataList.getJSONObject(i);
                    String id = responseJsonDataItem.getString(ServerUtil.DB_COLUMN_ID);
                    String date = responseJsonDataItem.getString(ServerUtil.DB_COLUMN_DATE);
                    String title = responseJsonDataItem.getString(ServerUtil.DB_COLUMN_TITLE);
                    String amount = responseJsonDataItem.getString(ServerUtil.DB_COLUMN_AMOUNT);
                    HomeIssueEntity issueEntity = new HomeIssueEntity();
                    issueEntity.setId(id);
                    issueEntity.setDate(date);
                    issueEntity.setTitle(title);
                    issueEntity.setAmount(amount);
                    list.add(issueEntity);
                }

                myAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDateFrom = (EditText) findViewById(R.id.dateFrom);
        mDateTo = (EditText) findViewById(R.id.dateTo);
        fragmanager = getSupportFragmentManager();
        mIssueKind = (Spinner) findViewById(R.id.issueKind);
        mListView = (ListView) findViewById(R.id.issues);
        list = new ArrayList<>();
        myAdapter = new HomeIssueAdapter(MainActivity.this);
        myAdapter.setIssueList(list);
        mListView.setAdapter(myAdapter);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add(StringUtil.EMPTY);
        adapter.add(StringUtil.TRAVEL);
        adapter.add(StringUtil.FOOD);
        adapter.add(StringUtil.TRAFFIC);
        Spinner spinner = (Spinner) findViewById(R.id.issueKind);
        spinner.setAdapter(adapter);
    }

    public void showIssueList(View view) {
        new Thread(netWork).start();
    }

    public void showFromDatePicker(View view) {
        DatePickerDialogFragmentInnerClass datePicker = new DatePickerDialogFragmentInnerClass();
        datePicker.show(getSupportFragmentManager(), "datePickerFrom");
    }

    public void showToDatePicker(View view) {
        DatePickerDialogFragmentInnerClass datePicker = new DatePickerDialogFragmentInnerClass();
        datePicker.show(getSupportFragmentManager(), "datePickerTo");
    }

    public static class DatePickerDialogFragmentInnerClass extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(getActivity(), this, year, month, dayOfMonth);

            return datePickerDialog;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            if (fragmanager.findFragmentByTag("datePickerFrom") != null) {
                mDateFrom.setText(String.valueOf(year) +
                        convertToStandardDateString(String.valueOf(month + 1)) +
                        convertToStandardDateString(String.valueOf(dayOfMonth)));
            }
            if (fragmanager.findFragmentByTag("datePickerTo") != null) {
                mDateTo.setText(String.valueOf(year) +
                        convertToStandardDateString(String.valueOf(month + 1)) +
                        convertToStandardDateString(String.valueOf(dayOfMonth)));
            }
        }
    }

    private static String convertToStandardDateString(String notStandardString) {
        if (1 == notStandardString.length()) {
            notStandardString = "0" + notStandardString;
        }
        return notStandardString;
    }
}