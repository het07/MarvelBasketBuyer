package apch.marvelbasket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

import apch.marvelbasket.bean.User;

public class EventActivity extends AppCompatActivity  {

    private static final String TAG = "EventActivity";
    private LinearLayout parentLinearLayout;
    private DatePicker datePicker;
    private TextView dateView;
    private Button signup;
    private int year, month, day;
    private EditText editText;
    private List<String> eventNames = new ArrayList<>();
    private List<String> eventDates = new ArrayList<>();
    private String eName;
    private String eDate;
    private int c = 0;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor myData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevents);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

        signup=(Button)findViewById(R.id.signup);

        Log.e(TAG, "onCreate: c : " + c);
        editText = findViewById(R.id.event_name_edit_text);


        eName = editText.getText().toString().trim();
        dateView = findViewById(R.id.date_tv);
        eDate = dateView.getText().toString().trim();

        Log.e(TAG, "onCreate: ename : " + eName + " edate : " + eDate);

        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        myData = sharedPreferences.edit();
        myData.remove("Name");
        myData.remove("Date");
        myData.apply();

        myData.putString("Name", editText.getText().toString().trim());
        myData.commit();

        Log.e(TAG, "onCreate:  Name " + sharedPreferences.getString("Name", "none"));
    }

    public void onAddField(View v) {

        if(c==1) {
            myData.putString("Name", editText.getText().toString().trim());
            myData.commit();
        }
        eDate = sharedPreferences.getString("Date", "");
        eName = sharedPreferences.getString("Name","");
        Log.e(TAG, "onAddField: con name?" + sharedPreferences.contains("Name") + sharedPreferences.contains("Date"));

        Log.e(TAG, "onAddField: ename : " + eName + " edate : " + eDate + c);
        if ((eName == null) || eName.equals("")) {
            Toast.makeText(getApplicationContext(), "Please Set Event Name", Toast.LENGTH_SHORT).show();
        }
        else if ((eDate==null) || (eDate.equals("")) || (eDate.equals("Set Date"))) {
            Toast.makeText(getApplicationContext(), "Please Set Event Date", Toast.LENGTH_SHORT).show();
        }
         else {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.field, parentLinearLayout, false);
            // Add the new row before the add field button.
            parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 2);
            c=0;

            eventNames.add(eName);
            eventDates.add(eDate);

            myData.remove("Name");
            myData.remove("Date");
            myData.apply();
        }
    }

    public void onNameClick(View v) {
        c=1;
        editText = (EditText) v;
        Log.e(TAG, "onNameClick: i am here");

    }

    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
    }


    //=================================================================================================

    public void onDateClick(View v) {
       // DialogFragment newFragment = new DatePickerFragment((TextView) v, sharedPreferences);
     //   newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void onSignUp(View v){
        Log.e(TAG, "onSignUp: hello " );

        Intent signUpIntent = getIntent();

        User user  = (User) signUpIntent.getSerializableExtra("user");

        Intent it = new Intent(this, HomeActivity.class);
        it.putExtra("user",user);
        startActivity(it);
    }
}