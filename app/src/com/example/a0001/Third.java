package com.example.a0001;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class Third extends Activity {
	private EditText editText1,editText2;
	private TextView textView3;
	private CheckBox checkBox1;
	private String account,password,A="",URL="http://192.168.1.113/ts/";
	protected static final int REFRESH_DATA = 0x00000001;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page3);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        Button button1=(Button)findViewById(R.id.button1);
        Button button2=(Button)findViewById(R.id.button2);
        Button button3=(Button)findViewById(R.id.button3);
        textView3 = (TextView) findViewById(R.id.textView3);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        button1.setOnClickListener(myListner1);
        button2.setOnClickListener(myListner2);
        button3.setOnClickListener(myListner3);
        
        
        try{
        	FileReader fr = new FileReader("/sdcard/password.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String str;
            str=bfr.readLine();
            if(str.equals("T")){
              checkBox1.setChecked(true);
              str=bfr.readLine();
              editText1.setText(str);
              str=bfr.readLine();
              editText2.setText(str);
            }
            
            
            fr.close();
	    }catch(IOException e){
	       e.printStackTrace();
	    }
    }
    private Runnable mutiThread = new Runnable() {
		public void run() {
			Looper.prepare();
			send();
			Looper.loop();
		}
	};
	private void send() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(URL+"login_android.php");
		// Post Data
		account = editText1.getText().toString();
		password = editText2.getText().toString();
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
		nameValuePair.add(new BasicNameValuePair("account", account));
		nameValuePair.add(new BasicNameValuePair("password", password));
		// Encoding POST data
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		} catch (UnsupportedEncodingException e) {
			// log exception
			e.printStackTrace();
		}
		// making POST request.
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String htmlResponse = EntityUtils.toString(entity);
			A=htmlResponse.toString();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (response != null){
				Log.i("Response : ", "response " + response.getStatusLine().toString());
			}
		} finally {

		}
	}
    private Button.OnClickListener myListner1=new Button.OnClickListener(){
    	public void onClick(View v){
    		A="";
    		Thread thread = new Thread(mutiThread);
			thread.start();
			do{}while(A.equals(""));
			
			if(A.endsWith("T")){
				Toast.makeText(Third.this,"登入成功",Toast.LENGTH_LONG).show();
				if(checkBox1.isChecked()){
					try{
				        FileWriter fw = new FileWriter("/sdcard/password.txt", false);
				        BufferedWriter bw = new BufferedWriter(fw); //將BufferedWeiter與FileWrite物件做連結
				        bw.write("T");
				        bw.newLine();
				        bw.write(account);
				        bw.newLine();
				        bw.write(password);
				        bw.close();
				    }catch(IOException e){
				       e.printStackTrace();
				    }
					
				}else{
					try{
				        FileWriter fw = new FileWriter("/sdcard/password.txt", false);
				        BufferedWriter bw = new BufferedWriter(fw); //將BufferedWeiter與FileWrite物件做連結
				        bw.write("F");
				        bw.close();
				    }catch(IOException e){
				       e.printStackTrace();
				    }
				}
				Intent intent = getIntent();
	            Bundle bundle = new Bundle();
	            bundle.putString("account",account);
	            bundle.putString("password",password);
	            intent.putExtras(bundle); 
	            setResult(654321, intent);
				finish();
			}else{
				Toast.makeText(Third.this,"登入失敗",Toast.LENGTH_LONG).show();
			}
			
    	}
    };
    private Button.OnClickListener myListner2=new Button.OnClickListener(){
	  public void onClick(View v){
		  Intent ie = new Intent(Intent.ACTION_VIEW,Uri.parse(URL+"member_join_form.php"));
          startActivity(ie);
	  }
    };
    private Button.OnClickListener myListner3=new Button.OnClickListener(){
    	public void onClick(View v){
    		finish();
    	}
    }; 
}