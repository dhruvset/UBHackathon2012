package aa.UBhacking.UI;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends Activity {

	Button chat,search,unfriend,edit,ratep,ratem,exit;
	TextView name,age,status,city,level,time,score;
	public Handler handler;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);

		chat = (Button)findViewById(R.id.bchat);
		search = (Button)findViewById(R.id.bsearch);
		unfriend = (Button)findViewById(R.id.bunfriend);
		edit = (Button)findViewById(R.id.beditprofile);
		ratep = (Button)findViewById(R.id.bratep);
		ratem = (Button)findViewById(R.id.bratem);
		exit = (Button)findViewById(R.id.bexit);

		name =  (TextView)findViewById(R.id.tname);
		age =  (TextView)findViewById(R.id.tage);
		status =  (TextView)findViewById(R.id.tstatus);
		city =  (TextView)findViewById(R.id.tarea);
		level =  (TextView)findViewById(R.id.tlevel);
		time =  (TextView)findViewById(R.id.ttime);
		score =  (TextView)findViewById(R.id.tscore);

		Bundle extras = getIntent().getExtras();
		name.setText("Anonymous Name - "+extras.getString("username"));
		age.setText("Age - "+extras.getString("age"));
		status.setText("Relationship Status - "+extras.getString("buddy"));
		city.setText("City - " + extras.getString("area"));
		level.setText("Addiction level - "+extras.getString("level"));
		time.setText("Time - "+extras.getString("time"));
		score.setText("Score - "+extras.getString("score"));


		ratep.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		ratem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		chat.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent openChat = new Intent("aa.UBhacking.UI.CHAT");
				startActivity(openChat);
			}
		});

		search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//creates a new thread and starts listening and 
				StartBuffering();

			}
		});

		edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//				Intent openProfile = new Intent("aa.UBhacking.UI.PROFILE");
				//				startActivity(openProfile);
			}
		});

		exit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.exit(0);
			}
		});



	}

	public void StartBuffering(){
		new Thread(){
			@SuppressWarnings("unchecked")
			public void run(){
				JSONObject jsonobject = new JSONObject();
				jsonobject.put("type", "search");
				jsonobject.put("username", "ashwin");

				HttpClient httpclient = new DefaultHttpClient();
				HttpPost post = new HttpPost(Constants.SERVER);
				StringEntity s_ent;
				Log.i("StartBuffering","seent3");
				try {
					Log.i("StartBuffering","seent4");
					s_ent = new StringEntity(jsonobject.toString());
					s_ent.setContentEncoding(new BasicHeader("a",jsonobject.toString()));
					post.setEntity(s_ent);
					//StartListening();
					HttpResponse response=httpclient.execute(post);
					String result="";
					Header[] headers = response.getAllHeaders();
					for(Header h : headers){
						if(h.getName().equals("Result")){
							result = h.getValue();
						}
					}
					JSONObject jsonobj= (JSONObject) new JSONParser().parse(result);
					Log.i("StartBuffering","seent5 " + result);

					Intent openSearch = new Intent("aa.UBhacking.UI.SEARCH");
					int activityID = 0x100;
					openSearch.putExtra("value1",jsonobj.get("value1").toString());
					openSearch.putExtra("value2",jsonobj.get("value2").toString());
					openSearch.putExtra("value3",jsonobj.get("value3").toString());
					openSearch.putExtra("value4",jsonobj.get("value4").toString());
					openSearch.putExtra("value5",jsonobj.get("value5").toString());

					startActivityForResult(openSearch, activityID);	

				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.i("error",e.getMessage());
					e.printStackTrace();
				} 

			}
		}.start();
	}
}