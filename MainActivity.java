package com.example.timer;

import com.example.timer.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.*;
import java.lang.Thread;


public class MainActivity extends Activity {
	
	private Button mStart;
	private Button mPause;
	private Button mReset;
	private TextView mTime;
	int ss,mm,hh;
    boolean running;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        mTime = (TextView) findViewById(R.id.display);
        mPause =(Button) findViewById(R.id.Pause);
        mStart =(Button) findViewById(R.id.Start);
        mReset =(Button) findViewById(R.id.Reset);
        
        ss = 0;
        mm = 0;
        hh = 0;
        running = false;
        
        mTime.setText(R.string.time);
        timeCount();   
        mStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				running = true;
				
			}
		});
        
        mPause.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				running = false;
			}
		});
        
        mReset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					FileWriter x = new FileWriter("timeList.txt",true);
					String displayText= (String) mTime.getText();
					x.write(displayText + "\r\n");
					x.close();
				}catch(IOException e){}
				ss = 0;
		        mm = 0;
		        hh = 0;
		        running = false;
		        mTime.setText(R.string.time);
			}
		});
                
	}

	public void timeCount()
	{
		while(running == true)
		{
			try{
				Thread.sleep(100);				
			}catch(Exception ex){}
			ss = ss + 1;
			if(ss == 60)
			{
				ss = 0;
				mm = mm + 1;
				if(mm == 60)
				{
					mm = 0;
					hh = hh + 1;
				}
			}
			
			String newText = hh + ":" + mm + ":" + ss;
			mTime.setText(newText);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
