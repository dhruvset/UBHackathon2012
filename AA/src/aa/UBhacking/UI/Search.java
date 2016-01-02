package aa.UBhacking.UI;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
import android.view.View;


public class Search extends ListActivity{

	
	String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
			"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
			"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };
	static String[] VAL = new String[5];
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
 
		Bundle extras = getIntent().getExtras();
        VAL[0] = extras.getString("value1");
        VAL[1] = extras.getString("value2");
        VAL[2] = extras.getString("value3");
        VAL[3] = extras.getString("value4");
        VAL[4] = extras.getString("value5");

        
 
		setListAdapter(new ArrayAdapter<String>(this, R.layout.search,VAL));
 
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
 
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent openBuddy = new Intent("aa.UBhacking.UI.BUDDY");
				
				startActivity(openBuddy);
						
//			    // When clicked, show a toast with the TextView text
//			    Toast.makeText(getApplicationContext(),
//				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
 
	}
}
