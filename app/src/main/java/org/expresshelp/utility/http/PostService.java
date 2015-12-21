

package org.expresshelp.utility.http;



import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;



@SuppressWarnings("deprecation")
public class PostService {
	
	private HttpClient _httpClient;
	
	public PostService( HttpParams params ){
		
		if( params == null ){

			_httpClient = new DefaultHttpClient();
		}
		else{
			
			_httpClient = new DefaultHttpClient( params );
		}
	}
	
	public String performPost( String url, String data ) throws URISyntaxException, ClientProtocolException, IOException{
		
		URI uri     = new URI( url ).normalize();
		HttpPost httpPost = new HttpPost( uri );
		
		
		if( data != null ){
			StringEntity se = new StringEntity( data.toString(), HTTP.UTF_8  );
			se.setContentType( new BasicHeader( HTTP.CONTENT_TYPE, "application/json" ) );
			
			httpPost.setEntity( se  );
		}
		
		HttpResponse response = _httpClient.execute( httpPost );
		
		return EntityUtils.toString(response.getEntity());
	}
	
	public String performPost( String url, String data, String authString ) throws URISyntaxException, ClientProtocolException, IOException{
		
		URI uri     = new URI( url ).normalize();
		HttpPost httpPost = new HttpPost( uri );
		
		if( authString != null ){
			httpPost.setHeader( "Authorization", authString );
		}
		
		if( data != null ){
			StringEntity se = new StringEntity( data.toString(), HTTP.UTF_8  );
			se.setContentType( new BasicHeader( HTTP.CONTENT_TYPE, "application/json" ) );
			
			httpPost.setEntity( se  );
		}
		
		HttpResponse response = _httpClient.execute( httpPost );
		
		return EntityUtils.toString( response.getEntity() );
	}

}
