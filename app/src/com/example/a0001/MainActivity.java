package com.example.a0001;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener,OnClickListener{
	private TextView textView1,textView2,textView13;
	private float Px,Py,Pz,range,K,x,y,z;
	private int countx = 0,county = 0,countz = 0;
	private String name="",sensor="",account="",password="";
	private boolean aa=false,bb=false,cc=false,dd=false;
	private SensorManager sensorManager;
	Intent intent = getIntent();
	Intent intent2 = getIntent();
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button1 = (Button) findViewById(R.id.button1);
		Button button2 = (Button) findViewById(R.id.button2);
		Button button3 = (Button) findViewById(R.id.button3);
		Button button4 = (Button) findViewById(R.id.button4);
		Button button5 = (Button) findViewById(R.id.button5);
		Button button6 = (Button) findViewById(R.id.button6);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		button5.setOnClickListener(this);
		button6.setOnClickListener(this);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		textView13 = (TextView) findViewById(R.id.textView13);
	    Intent intent=new Intent(MainActivity.this,Second.class);
	    startActivityForResult(intent,123456);
	}
	
    @Override 
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(resultCode){  
        case 123456:
            name = data.getExtras().getString("name");
            if(name.equals("")){
            	textView1.setText("失敗");
				textView13.setText("請重新掃描QRcode");
            }else{
              sensor = data.getExtras().getString("sensor");
              range = data.getExtras().getFloat("range");
              K = data.getExtras().getFloat("K");
            }
            break;
        case 654321:
        	account = data.getExtras().getString("account");
            if(account.equals("")){
              textView2.setText("尚未登入，將無法紀錄");
            }else{
              password = data.getExtras().getString("password");
              textView2.setText(account+"使用中");
            }
            break;
        }
    }
	
	public void onClick(View view){
		switch (view.getId()){
			case R.id.button1:
				sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
				sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),300000);		
				aa=true;
				break;
			case R.id.button2:
				countx = 0;
				county = 0;
				countz = 0;
				textView1.setText("0");
				textView13.setText("熱量");
				break;
			case R.id.button3:
				if(aa)sensorManager.unregisterListener(this);
				aa=false;
				break;
			case R.id.button4:	// 呼叫另一個自建的 Activity    			
				if(aa)sensorManager.unregisterListener(this);
				aa=false;
				Intent intent=new Intent(MainActivity.this,Second.class);
			    startActivityForResult(intent,123456);
			    countx = 0;
				county = 0;
				countz = 0;
				textView1.setText("0");
				textView13.setText("熱量");
				break;
			case R.id.button5:	// 呼叫另一個自建的 Activity    		
				Intent intent2=new Intent(MainActivity.this,Third.class);
			    startActivityForResult(intent2,654321);
				break;
		}
	}
	
	public void onAccuracyChanged(Sensor sensor, int accuracy){

	}
	
	public void onSensorChanged(SensorEvent event){

		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
			
    		if(sensor.equals("X")){
    			x=event.values[0];
                if (!bb && x - Px > range){
        			bb=!bb;
        		}else if (bb && x - Px < -range){
        			++countx;
        			bb=!bb;
        		}
                Px=x;
    			textView1.setText(String.valueOf(countx));
				textView13.setText(String.valueOf(Float.valueOf(((int)(countx*K*100)))/100)+"卡");
				
    		}else if(sensor.equals("Y")){
    			y=event.values[1];
    			if (!cc && y - Py > range){
        			cc=!cc;
        		}else if (cc && y - Py < -range){
        			++county;
        			cc=!cc;
        		}
    			Py=y;
    			textView1.setText(String.valueOf(county));
				textView13.setText(String.valueOf(Float.valueOf(((int)(county*K*100)))/100)+"卡");
				
    		}else if(sensor.equals("Z")){
    			z=event.values[2];
    			if (!dd && z - Pz > range){
        			dd=!dd;
        		}else if (dd && z - Pz < -range){
        			++countz;
        			dd=!dd;
        		}
    			Pz=z;
    			textView1.setText(String.valueOf(countz));
				textView13.setText(String.valueOf(Float.valueOf(((int)(countz*K*100)))/100)+"卡");
    		}else{
    			textView1.setText("錯誤");
				textView13.setText("座標錯誤");
				sensorManager.unregisterListener(this);
    		}
        }
		
	}
}