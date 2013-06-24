package nl.tiemensit.youlesscollector.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.util.JSONBuilder;
import nl.tiemensit.youlesscollector.model.YoulessProperties;

public class ActualDataRetriever {
	
	public String youless_ipaddress;
	
	public ActualDataRetriever() {
		youless_ipaddress = YoulessProperties.getInstance().getValueByKey("ipaddress");
	}

	public JSONObject getActualData() {
		HttpClient client = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(getActualDataURL());
		getRequest.addHeader("accept", "application/json");

		JSONObject result = null;
		try {
			HttpResponse response = client.execute(getRequest);
			HttpEntity entity = response.getEntity();
			InputStreamReader in = new InputStreamReader(entity.getContent());
			BufferedReader reader = new BufferedReader(in);
			
			StringBuilder builder = new StringBuilder();
			String line = "";
			
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			
	        result = (JSONObject) JSONSerializer.toJSON( builder.toString() );        
			
			client.getConnectionManager().shutdown();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	private String getActualDataURL() {
		return "http://"+youless_ipaddress +"/a?f=j";
	}

}
