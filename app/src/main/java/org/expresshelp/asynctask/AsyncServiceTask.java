

package org.expresshelp.asynctask;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.params.HttpParams;
import org.json.JSONException;

import org.expresshelp.utility.http.GetService;
import org.expresshelp.utility.http.PostService;

import android.os.AsyncTask;

public abstract class AsyncServiceTask  extends AsyncTask< String, Void, String >{


	
	private TaskReceiver _tr = null;
	
	private String _response;
	private String _serviceID;
	
	protected abstract String performTask( String... params ) throws IOException, JSONException, URISyntaxException;
	
	public AsyncServiceTask( String serviceID ){
		_serviceID = serviceID;
	}
	
	
	@Override
	protected String doInBackground( String... params ) {

		
		try {
			return performTask( params );
		} catch (IOException e) {

			return null;
		} catch (JSONException e) {

			return null;
		} catch (URISyntaxException e) {
			return null;
		}

	}
	
	@Override
	protected void onPostExecute( String result ){
		_response = result;
		if( _tr != null ){
			_tr.receiveResult( _response, _serviceID );
		}
	}
	
	public void setReceiver( TaskReceiver tr ){
		_tr = tr;
	}
	
	public String performPost( String url, String data, HttpParams params, String authString ) 
			                   throws ClientProtocolException, URISyntaxException, IOException{
		
		PostService ps = new PostService( params );
		return ps.performPost( url,  data );
	}
	
	public String performGet( String url, String data, HttpParams params, String authString ) 
            throws ClientProtocolException, URISyntaxException, IOException{

		GetService gs = new GetService( null );
		
		if( authString == null ){
			return gs.performGet( url );
		}
		else{
			return gs.performGet( url, authString );
		}
	}
	

}
