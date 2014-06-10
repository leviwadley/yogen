package com.strategy.yogen;
import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import android.annotation.SuppressLint;
import android.util.Log;




public class Server_Connect {

	
		private JSONArray JSON_Deck = new JSONArray();
		private JSONArray JSON_Vars = new JSONArray();
		private JSONArray JSON_Names = new JSONArray(); 

public void receive_JSON_deck()throws JSONException
{}
public void receive_JSON_names()throws JSONException
{}
public void receive_JSON_vars()throws JSONException
{}
public void send_JSON_deck()throws JSONException
{}
public void send_JSON_names()throws JSONException
{}
public void send_JSON_vars()throws JSONException
{}

public JSONArray get_JSON_deck()throws JSONException
{return JSON_Deck;}
public JSONArray get_JSON_names()throws JSONException
{return JSON_Vars;}
public JSONArray get_JSON_vars()throws JSONException
{return JSON_Names;}

public static void sendPost(JSONObject post, String email, String password)
{
    DefaultHttpClient client = new DefaultHttpClient();
    client.getCredentialsProvider().setCredentials(new AuthScope(null,-1), new UsernamePasswordCredentials(email,password));
    HttpPost httpPost = new HttpPost("http://mysite.com/posts");
    JSONObject holder = new JSONObject();


    try {   
        holder.put("post", post);

        StringEntity se = new StringEntity(holder.toString());
        Log.d("SendPostHTTP", holder.toString());
        httpPost.setEntity(se);
        httpPost.setHeader("Content-Type","application/json");


    } catch (UnsupportedEncodingException e) {
        Log.e("Error",""+e);
        e.printStackTrace();
    } catch (JSONException js) {
        js.printStackTrace();
    }

    HttpResponse response = null;

    try {
        response = client.execute(httpPost);
    } catch (ClientProtocolException e) {
        e.printStackTrace();
        Log.e("ClientProtocol",""+e);
    } catch (IOException e) {
        e.printStackTrace();
        Log.e("IO",""+e);
    }

    HttpEntity entity = response.getEntity();

    if (entity != null) {
        try {
            entity.consumeContent();
        } catch (IOException e) {
            Log.e("IO E",""+e);
            e.printStackTrace();
        }
    }

}

}
		/*This is how the game gets player ID's*/

		/*This will be used to update every 5 seconds*/
	
