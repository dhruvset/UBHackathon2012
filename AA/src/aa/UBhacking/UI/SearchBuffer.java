package aa.UBhacking.UI;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class SearchBuffer implements Parcelable{

	public String category="search packets";
	public ArrayList<Packet> packets;



	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(category);
		Bundle b = new Bundle();
//		b.putParcelableArray("packets", packets);
		dest.writeBundle(b);
	}


}