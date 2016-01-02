package aa.UBhacking.UI;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

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
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity{

	Button submit,newUser;
	EditText username,password;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		submit = (Button)findViewById(R.id.bsubmit);
		newUser = (Button)findViewById(R.id.bnewuser);
		username = (EditText)findViewById(R.id.eusername);
		password = (EditText)findViewById(R.id.epassword);


		submit.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {
				username.setInputType(InputType.TYPE_CLASS_TEXT);
				String sname = username.getText().toString();

				password.setInputType(InputType.TYPE_CLASS_TEXT);
				String spw = password.getText().toString();

				JSONObject jsonobject = new JSONObject();
				jsonobject.put("type","login");
				jsonobject.put("username", sname);
				jsonobject.put("password", spw);

				final JSONObject temp = jsonobject;
				new Thread(){
					public void run(){

						Log.i("hii","seent2");

						HttpClient httpclient = new DefaultHttpClient();
						HttpPost post = new HttpPost(Constants.SERVER);
						StringEntity s_ent;
						Log.i("hii","seent3");

						try {
							Log.i("hii","seent4");

							s_ent = new StringEntity(temp.toString());
							s_ent.setContentEncoding(new BasicHeader("a",temp.toString()));
							post.setEntity(s_ent);

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

							if(jsonobj.get("result").toString().equals("false")){
								return;
							}

							Intent openProfile = new Intent("aa.UBhacking.UI.PROFILE");
							int activityID = 0x100;
							openProfile.putExtra("username",jsonobj.get("username").toString());
							openProfile.putExtra("area",jsonobj.get("area").toString());
							openProfile.putExtra("time",jsonobj.get("available_time").toString());
							openProfile.putExtra("status",jsonobj.get("relationship_status").toString());
							openProfile.putExtra("score",jsonobj.get("health_score").toString());
							openProfile.putExtra("level",jsonobj.get("addiction_level").toString());
							openProfile.putExtra("buddy",jsonobj.get("buddy_name").toString());

							startActivityForResult(openProfile, activityID);	

						} catch (Exception e) {
							// TODO Auto-generated catch block
							Log.i("error",e.getMessage());
							e.printStackTrace();
						} 
					}
				}.start();


			}
		});

		newUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent openForm = new Intent("aa.UBhacking.UI.FORM");
				startActivity(openForm);
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	public String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.i("AAAA", ex.toString());
		}
		return null;
	}
}
