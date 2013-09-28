package com.HelloAlien.helloaliens;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RssHandler extends DefaultHandler {
	private List<RssItem> list =
				new ArrayList<RssItem>();
	private RssItem current;
	private StringBuffer content;
	
	public RssItem getItem(){
		return list.get(0);
	}
	
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
		content.append(new String(ch,start,length));
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (current != null){
			if (localName.equalsIgnoreCase("item")){
				current = null;
			} 
			else if (localName.equalsIgnoreCase("title")){
				current.setTitle(content.toString());
			}
			else if (localName.equalsIgnoreCase("description")){
				current.setDescription(content.toString());
			}
			else if (localName.equalsIgnoreCase("pubdate")){
				current.setDate(content.toString());
			}
			else if (localName.equalsIgnoreCase("link")){
				current.setLink(content.toString());
			}
		}
	}
	
}
