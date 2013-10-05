package com.HelloAlien.helloaliens;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;


public class RssService extends AsyncTask<RssHandler, Void, RssItem> {
	
	private MainActivity activity;
	private ProgressDialog progress;
	
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
				
				/*xr.parse(new InputSource(activity.getAssets().open("image_of_the_day.xml")));*/
				
				//exemplo para usar atualização internet
				xr.parse(new InputSource(new URL("http://asd.rss").openStream()));
				
				item = handler.getItem();
				item.setImage(BitmapFactory.decodeResource(activity.getResources(),R.drawable.hwb_oil_visualization_larc_2013));
				
				}catch (Exception e){
				e.printStackTrace();
			}
			return item;
		}
	
	protected void onPostExecute(RssItem result) {
		activity.displayData(result);		
		progress.dismiss();
	}
	protected void onPreExecute() {
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB){
			progress = new ProgressDialog(activity, ProgressDialog.THEME_HOLO_DARK);
		}
		else{
		progress = new ProgressDialog(activity);
		}
		progress.setIndeterminate(true);
		progress.setMessage("Por Favor Aguarde(fazer uma string)");
		progress.show();
	}
}
