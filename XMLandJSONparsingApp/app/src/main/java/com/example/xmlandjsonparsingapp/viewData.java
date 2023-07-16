package com.example.xmlandjsonparsingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.InputStream;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class viewData extends AppCompatActivity {

    TextView header,body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        header = findViewById(R.id.text_head);
        body = findViewById(R.id.text_body);
        Intent intent = getIntent();

        String dataType = intent.getStringExtra("data");
        if(dataType.equals("xml")){
            header.setText("xml data");
            parseXML();
        }else if(dataType.equals("json")){
            header.setText("json data");
            parseJSON();
        }
    }

    public void parseXML(){
        try(InputStream istream = getAssets().open("weather.xml");) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(istream);
            doc.normalize();
            NodeList nodelist = doc.getElementsByTagName("weather");
            for(int i=0;i<nodelist.getLength();i++){
                NodeList nodes = nodelist.item(i).getChildNodes();
                for(int j=0;j<nodes.getLength();j++) {
                    if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        String data = (nodes.item(j).getNodeName()+" : "+nodes.item(j).getTextContent()+"\n");
                        body.append(data);
                    }
                }
                body.append("\n\n");
            }
        }
        catch (Exception e){e.printStackTrace();}
    }
    public void parseJSON(){
        try(InputStream istream = getAssets().open("weather.json")){
            byte[] data = new byte[istream.available()];
            istream.read(data);
            istream.close();
            String jsonString = new String(data);
            JSONObject jsonObj = new JSONObject(jsonString);
            JSONObject weatherObj = jsonObj.getJSONObject("weather");
            Iterator<String> keys = weatherObj.keys();
            while(keys.hasNext()){
                String key = keys.next();
                Object value = weatherObj.get(key);
                String dataToPut = (key+" : "+value+"\n");
                body.append(dataToPut);
            }
        }catch(Exception e){ e.printStackTrace();}
    }
}