package com.HelloAlien.helloaliens;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.os.AsyncTask;

public class RssService extends AsyncTask<RssHandler, Void, RssItem> {
	
	private MainActivity activity;
	
	public RssService(MainActivity activity){
		this.activity = activity;
	}

	@Override
	protected RssItem doInBackground(RssHandler... arg0) {
			RssHandler handler = arg0[0];
			RssItem item = null;
			
			try{
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();
				XMLReader xr = sp.getXMLReader();
				xr.setContentHandler(handler);
				
				xr.parse(new InputSource(activity.getAssets().open("image_of_the_day.xml")));
				
				item = handler.getItem();
				
				}catch (Exception e){
				e.printStackTrace();
			}
			return item;
		}

}
