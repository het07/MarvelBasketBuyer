package apch.marvelbasket;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import apch.marvelbasket.bean.User;

public class signup extends AppCompatActivity
{
    private static final String TAG = "signup";
    EditText email,contact,pwd,firstName,lastName,address;
    TextView login,signup,birthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup = (TextView) findViewById(R.id.signup);
        login = (TextView) findViewById(R.id.login);
        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        pwd = (EditText) findViewById(R.id.pwd);
        email = (EditText) findViewById(R.id.email);
        contact = (EditText) findViewById(R.id.contact);
        birthdate = (TextView)findViewById(R.id.birthdate);
        address = (EditText) findViewById(R.id.address);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");
        contact.setTypeface(custom_font);
        signup.setTypeface(custom_font1);
        pwd.setTypeface(custom_font);
        login.setTypeface(custom_font);
        firstName.setTypeface(custom_font);
        lastName.setTypeface(custom_font);
        email.setTypeface(custom_font);
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(signup.this,login.class);
                startActivity(it);
            }
        });
        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int f=0;
                if(firstName.getText().toString().trim().length()==0) {
                    firstName.setError("Please Enter First Name");
                    f=1;
                }
                if(lastName.getText().toString().trim().length()==0) {
                    lastName.setError("Please Enter Last Name");
                    f=1;
                }
                if(address.getText().toString().trim().length()==0) {
                    address.setError("Please Enter Address");
                    f=1;
                }
                if(birthdate.getText().toString().trim()==null || (birthdate.getText().toString().trim()).equals("Birthdate")) {
                    Toast.makeText(getApplicationContext(),"Please Set your Birth Date",Toast.LENGTH_SHORT).show();
                    f=1;
                }
                if(email.getText().toString().trim().length()==0) {
                    email.setError("Please Enter Email");
                    f=1;
                }else if(email.getText().toString().trim().length()!=0) {
                    if (!emailValidator(email.getText().toString().trim())) {
                        Log.e(TAG, "onClick: validate" );
                        email.setError("Please Enter Valid Email(e.g xyz@abc.pqr)");
                        f = 1;
                    }
                }

                if(pwd.getText().toString().trim().length()==0) {
                    pwd.setError("Please Enter Password");
                    f=1;
                }
                if(contact.getText().toString().trim().length()==0) {
                    contact.setError("Please Enter Contact");
                    f=1;
                }else if(contact.getText().toString().trim().length()!=10){
                    contact.setError("Please Enter Valid Contact of Exact 10 digits");
                    f=1;
                }

                if(f==0) {
                    Log.e(TAG, "onClick: fn : "+ firstName.getText().toString());

                    String URL = "http://192.168.43.248:9191/user/register";

                    //step-1
                    RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());

                    JSONObject jsonObject = new JSONObject();

                    try {
                        Log.e(TAG, "onClick: fn try: "+ firstName.getText().toString());

                        JSONObject userJsonObject = new JSONObject();
                        userJsonObject.put("firstName",firstName.getText().toString());
                        userJsonObject.put("lastName",lastName.getText().toString());
                        userJsonObject.put("email",email.getText().toString());
                        userJsonObject.put("password",pwd.getText().toString());
                        userJsonObject.put("address",address.getText().toString());
                        userJsonObject.put("contact",contact.getText().toString());

                        JSONObject eventJsonObject = new JSONObject();
                        eventJsonObject.put("eventName","Birthdate");
                        eventJsonObject.put("eventDate",birthdate.getText().toString());

                        JSONArray eventJsonArray = new JSONArray();
                        eventJsonArray.put(eventJsonObject);

                        jsonObject.put("events",eventJsonArray);
                        jsonObject.put("user",userJsonObject);

                        Log.e(TAG,userJsonObject.toString());
                        Log.e(TAG,eventJsonObject.toString());
                        Log.e(TAG,jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //step-2
                    JsonObjectRequest objectRequest = new JsonObjectRequest(
                            Request.Method.POST,
                            URL,
                            jsonObject,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Intent it = new Intent(signup.this, HomeActivity.class);
                                    startActivity(it);
                                    Log.e("Rest Response",response.toString());
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    int statusCode = error.networkResponse.statusCode;
                                    if(statusCode == 409) {
                                        Toast.makeText(getApplicationContext(), "User email already exists", Toast.LENGTH_SHORT).show();
                                    }
                                    Log.e("Rest Error",error.toString());
                                }
                            }
                    );
                    //step-3
                    requestQueue.add(objectRequest);
                }
            }
        });

    }
    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void onDateClick(View v) {
        DialogFragment newFragment = new DatePickerFragment(birthdate);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
