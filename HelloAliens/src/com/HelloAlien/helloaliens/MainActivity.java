package com.HelloAlien.helloaliens;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refresh();
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void displayData(RssItem item){
    	TextView title = (TextView)findViewById(R.id.titulo);
    	TextView date = (TextView)findViewById(R.id.data);
    	ImageView image = (ImageView)findViewById(R.id.imagem);
    	TextView description = (TextView)findViewById(R.id.texto);
    	
    	if (item != null){
    			title.setText(item.getTitle());
    			date.setText(item.getDate());
    			image.setImageBitmap(item.getImage());
    			description.setText(item.getDescription());
    	}
    }  
    public void refresh(){
    	displayData(null);
    	RssService service = new RssService(this);
    	service.execute(new RssHandler());
    }
}
