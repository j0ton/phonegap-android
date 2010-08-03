package com.phonegap;

import android.app.Activity;
import android.util.Log;
import android.webkit.WebView;
import org.json.*;

@SuppressWarnings("deprecation")
public class UIControls {
	
	private static final String LOG_TAG = "UIControls Query";
	DroidGap mApp;
	WebView mView;
	
	public class MenuItem {
		
		public String title;
		public Integer id;
		public String icon;
		
	}
	
	UIControls(WebView view, DroidGap app)
	{
		mApp = app;
		mView = view;
	}
	
	public void createMenu(String detailsJson) {
		
		// set app.menuItems to array of MenuItems
		try {
			
			JSONArray array = (JSONArray) new JSONTokener(detailsJson).nextValue();
			
			MenuItem[] items = new MenuItem[array.length()];
			for (int i=0; i<array.length(); i++) {
				items[i] = new MenuItem();
				items[i].title = array.optJSONObject(i).getString("title");
				items[i].id = Integer.parseInt( array.optJSONObject(i).getString("id") );
				items[i].icon = array.optJSONObject(i).getString("icon");
			}
			
			mApp.menuItems = items;
			
		} catch (JSONException e) {
			Log.d(LOG_TAG, e.toString());
			throw new RuntimeException(e);
		}
		
	}
	
}
