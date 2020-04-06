package apch.marvelbasket;

import android.content.Intent;
import android.graphics.Typeface;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import apch.marvelbasket.bean.User;

public class login extends AppCompatActivity {
    EditText email, password;
    TextView sup, lin;
    private String Tag="login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lin = (TextView) findViewById(R.id.lin);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pwd);
        sup = (TextView) findViewById(R.id.sup);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");
        lin.setTypeface(custom_font1);
        sup.setTypeface(custom_font);
        email.setTypeface(custom_font);
        password.setTypeface(custom_font);
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),signup.class);
                startActivity(it);
            }
        });

        lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int f1=0;
                int f2=0;
                String eid = email.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                Log.e(Tag, "onClick: eid="+eid);
                Log.e(Tag, "onClick: pwd="+pwd);

                if(!eid.equals("")){
                    f1=1;
                }
                if(!pwd.equals("")){
                    f2=1;
                }

                Log.e(Tag, "onClick: f1 = "+f1 + " f2 = "+f2);

                if(f1==1&&f2==1){
                    /*String URL = "http://192.168.43.248:9191/user/auth";

                    //step-1
                    RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
                    JSONObject jsonBody = new JSONObject();
                    try {
                        jsonBody.put("email",email.getText().toString());
                        jsonBody.put("password",password.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //step-2
                    JsonObjectRequest objectRequest = new JsonObjectRequest(
                            Request.Method.POST,
                            URL,
                            jsonBody,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    Log.e("Rest Response",response.toString());
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    int statusCode = error.networkResponse.statusCode;
                                    if(statusCode == 401) {
                                        Toast.makeText(getApplicationContext(), "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                                    }
                                    Log.e("Rest Error",error.toString());
                                }
                            }
                    );
                    //step-3
                    requestQueue.add(objectRequest);*/
                    Intent it = new Intent(login.this, HomeActivity.class);
                    startActivity(it);
                }
                else if(f1==0&&f2==1){
                    email.setError("Please Enter Email");
                }
                else if(f1==1&&f2==0){
                    password.setError("Please Enter Password");
                }
                else{
                    email.setError("Please Enter Email");
                }
            }
        });
    }
}