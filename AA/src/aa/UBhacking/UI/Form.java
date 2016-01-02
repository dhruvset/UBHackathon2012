package aa.UBhacking.UI;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.json.simple.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Form extends Activity {
	Button submit;
	EditText username,password,age,area,level,time;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form);

		submit = (Button)findViewById(R.id.bsubmit);
		username = (EditText)findViewById(R.id.tname);
		password = (EditText)findViewById(R.id.tpassword);
		age = (EditText)findViewById(R.id.tage);
		area = (EditText)findViewById(R.id.tarea);
		level = (EditText)findViewById(R.id.tlevel);
		time = (EditText)findViewById(R.id.ttime);



		submit.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {
				username.setInputType(InputType.TYPE_CLASS_TEXT);
				String sname = username.getText().toString();

				password.setInputType(InputType.TYPE_CLASS_TEXT);
				String spw = password.getText().toString();

				age.setInputType(InputType.TYPE_CLASS_TEXT);
				String sage = age.getText().toString();

				area.setInputType(InputType.TYPE_CLASS_TEXT);
				String sarea = area.getText().toString();

				level.setInputType(InputType.TYPE_CLASS_TEXT);
				String slevel = level.getText().toString();

				time.setInputType(InputType.TYPE_CLASS_TEXT);
				String stime = time.getText().toString();

				JSONObject jsonobject = new JSONObject();
				jsonobject.put("type", "newuser");
				jsonobject.put("username", sname);
				jsonobject.put("password", spw);
				jsonobject.put("age", Integer.parseInt(sage));
				jsonobject.put("area", sarea);
				jsonobject.put("level", Integer.parseInt(slevel));
				jsonobject.put("time", stime);
				jsonobject.put("ip","fe80::5054:ff:fe12:3456");
				jsonobject.put("port","5554");
				jsonobject.put("score", "50");
				jsonobject.put("status", "Available");
				jsonobject.put("buddy_name", "No_buddy");

				Log.i("hii","seent1");

				final JSONObject temp = jsonobject;
				new Thread(){
					public void run(){
						Log.i("hii","seent2");

						HttpParams params = new BasicHttpParams();
						params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
						HttpClient httpclient = new DefaultHttpClient(params);

						HttpPost post = new HttpPost(Constants.SERVER);
						StringEntity s_ent;
						Log.i("hii","seent3");

						try {
							Log.i("hii","seent4");

							s_ent = new StringEntity(temp.toString());
							Log.i("hii","seent5");

							s_ent.setContentEncoding(new BasicHeader("a",temp.toString()));
							Log.i("hii","seent6");

							post.setEntity(s_ent);
							Log.i("hii","seent7");
							
							HttpResponse response=httpclient.execute(post);
							String result="";
							Header[] headers = response.getAllHeaders();
							for(Header h : headers){
								if(h.getName().equals("Result")){
									result = h.getValue();
								}
							}
							Log.i("StartBuffering","seent5 " + result);
							
							if(!result.equals("SERVER_OK")){
								System.exit(1);
							}
							
							
							Intent openProfile = new Intent("aa.UBhacking.UI.PROFILE");
							int activityID = 0x100;
							openProfile.putExtra("username",temp.get("username").toString());
							openProfile.putExtra("age",temp.get("age").toString());
							openProfile.putExtra("score",temp.get("score").toString());
							openProfile.putExtra("area",temp.get("area").toString());
							openProfile.putExtra("level",temp.get("level").toString());
							openProfile.putExtra("time",temp.get("time").toString());
							openProfile.putExtra("status",temp.get("status").toString());
							openProfile.putExtra("buddy",temp.get("buddy_name").toString());

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
	}

	//		public String getLocalIpAddress() {
	//			final String ip;
	//			new Thread(){
	//				public void run(){
	//					try {
	//						for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	//							NetworkInterface intf = en.nextElement();
	//							for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	//								InetAddress inetAddress = enumIpAddr.nextElement();
	//								if (!inetAddress.isLoopbackAddress()) {
	//									return inetAddress.getHostAddress().toString();
	//								}
	//							}
	//						}
	//					} catch (SocketException ex) {
	//						Log.i("AAAA", ex.toString());
	//					}
	//				}
	//			}.start();			
	//		}



}
