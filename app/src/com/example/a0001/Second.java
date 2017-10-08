package com.example.a0001;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Second extends Activity {
	private String name="",sensor="";
	private Float range=0f,K=0f;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);
        Button btnHome=(Button)findViewById(R.id.btnHome);
        Button button1=(Button)findViewById(R.id.button1);
        btnHome.setOnClickListener(myListner);
        button1.setOnClickListener(myListner2);
    }
    private Button.OnClickListener myListner2=new Button.OnClickListener(){
	  public void onClick(View v){
	      Intent intent = new Intent("com.google.zxing.client.android.SCAN");
	      if(getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size()==0){
	      }else{
	          intent.putExtra("SCAN_MODE", "SCAN_MODE");
	          startActivityForResult(intent, 1);
	      }
	  }
    };
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
	    if(requestCode==1){
	        if(resultCode==RESULT_OK){
	                // ZXing回傳的內容
	                String contents = intent.getStringExtra("SCAN_RESULT");
	                TextView textView1 = (TextView) findViewById(R.id.textView1);
	                if(contents.contains("NUUCSIE")){
	                  int start=0,x=0;
                      for(int i=0;i<4;++i){
                    	x=contents.indexOf(',', start);
                    	switch(i){
                    	case 0:
                    		name=contents.substring(start, x);
                    		textView1.setText("運動名稱:"+name);
                    		break;
                    	case 1:
                    		sensor=contents.substring(start, x);
                    		break;
                    	case 2:
                    		range=Float.valueOf(contents.substring(start, x));
                    		break;
                    	case 3:
                    		K=Float.valueOf(contents.substring(start, x));
                    		textView1.setText(textView1.getText()+"  每次動作消耗熱量:"+K+"K");
                    		break;
                    	}
                    	start=x+1;
                      }
	                }else{
	                	textView1.setText(contents.toString()+"(非可用條碼)");
	                }
	            }
	        else if(resultCode==RESULT_CANCELED){
	                Toast.makeText(this, "取消掃描", Toast.LENGTH_LONG).show();
	        }
	    }
	}
    private Button.OnClickListener myListner=new Button.OnClickListener(){
    	public void onClick(View v){
            Intent intent = getIntent();
            Bundle bundle = new Bundle();
            bundle.putString("name",name);
            bundle.putString("sensor",sensor);
            bundle.putFloat("range",range);
            bundle.putFloat("K",K);
            intent.putExtras(bundle); 
            setResult(123456, intent);
    		finish();
    	}
    }; 
}