package com.HelloAlien.helloaliens;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.ClipData.Item;

public class RssHandler extends DefaultHandler {
	private List<RssItem> list =
				new ArrayList<RssItem>();
	private RssItem current;
	private StringBuffer content;
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (localName.equalsIgnoreCase("item")){
			current = new RssItem();
			list.add(current);
		} else{
			if (current != null){
				if (localName.equalsIgnoreCase("enclosure")){
					current.setImageUrl(attributes.getValue("url"));
				}
			}
		}
		content = new StringBuffer();		
	}
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
	}
	
}
