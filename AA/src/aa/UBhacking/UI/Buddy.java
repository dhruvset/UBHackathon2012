package aa.UBhacking.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Buddy extends Activity{
    /** Called when the activity is first created. */
	
	Button chat,addfriend,exit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buddy);
       
        chat = (Button)findViewById(R.id.bchat);
        addfriend = (Button)findViewById(R.id.bmakefriend);        
        exit = (Button)findViewById(R.id.bexit);

        chat.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent openChat = new Intent("aa.UBhacking.UI.CHAT");
				startActivity(openChat);
			}
		});
        
        addfriend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
        
        exit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.exit(0);
			}
		});
        
        
    }

}
