

package org.expresshelp.utility.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class GetService {
	
	private HttpClient _httpClient;
	
	public GetService( HttpParams params ){
		
		if( params == null ){
			
			_httpClient = new DefaultHttpClient();
		}
		else{
			
			_httpClient = new DefaultHttpClient( params );
		}
	}
	
	public String performGet( String url ) throws URISyntaxException, ClientProtocolException, IOException{
		
		URI uri     = new URI( url ).normalize();
		HttpGet httpGet = new HttpGet( uri );
		
		
		HttpResponse response = _httpClient.execute( httpGet );
		
		return EntityUtils.toString( response.getEntity() );
	}
	
	public String performGet( String url, String authString ) throws URISyntaxException, ClientProtocolException, IOException{
		
		URI uri     = new URI( url ).normalize();
		HttpGet httpGet = new HttpGet( uri );
		
		if( authString != null ){
			httpGet.setHeader( "Authorization", authString );
		}
		
		
		
		HttpResponse response = _httpClient.execute( httpGet );
		
		return EntityUtils.toString( response.getEntity() );
	}
}
