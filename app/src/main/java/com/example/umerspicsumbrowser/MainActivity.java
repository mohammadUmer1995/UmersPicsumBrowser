package com.example.umerspicsumbrowser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.umerspicsumbrowser.adapter.RecyclerAdapter;
import com.example.umerspicsumbrowser.datamodel.AutharPojo;
import com.example.umerspicsumbrowser.singletonclass.VolleySingleton;
import com.example.umerspicsumbrowser.url.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<AutharPojo> mArrayList;
    private RecyclerView rc_view;
    private RecyclerAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mArrayList = new ArrayList<>();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rc_view = findViewById(R.id.rc_view);
        rc_view.setLayoutManager(gridLayoutManager);
        rc_view.setItemAnimator(new DefaultItemAnimator());
        networkRequestList();
        recyclerAdapter = new RecyclerAdapter(mArrayList, this);
        rc_view.setAdapter(recyclerAdapter);
    }


    private void networkRequestList() {

        String url = URLs.ROOT_URL;
        final ProgressDialog progressDialog0 = new ProgressDialog(MainActivity.this);
        progressDialog0.setIndeterminate(true);
        progressDialog0.setCancelable(false);
        progressDialog0.setMessage("please Wait...");
        progressDialog0.show();

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog0.dismiss();
                        try {


                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                AutharPojo autharPojo = new AutharPojo();

                                autharPojo.setId(String.valueOf(obj.getInt("id")));
                                autharPojo.setAuthor(obj.getString("author"));
                                mArrayList.add(autharPojo);
                            }
                        } catch (JSONException e) {
                            Log.d("dnndnndn", "onResponse: " + e.getMessage());
                            e.printStackTrace();
                        }

                        recyclerAdapter.notifyDataSetChanged();

                        Log.d("IMAGE_RESPONSE ", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );

        VolleySingleton.getInstance(this).addToRequestQueue(postRequest);
    }


}