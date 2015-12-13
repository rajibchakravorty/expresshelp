package au.com.ibm.tasks;

import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

import au.com.ibm.asynctask.AsyncServiceTask;

/**
 * Created by rajib on 12/12/15.
 */
public class RescueRequest extends AsyncServiceTask{

    public static String _rescueTaskID = "Rescue Task";

    private String _url;

    public RescueRequest( String url ){

        super( _rescueTaskID );
        _url = url;
    }


    @Override
    protected String performTask(String... params) throws IOException, JSONException, URISyntaxException {

        String data = params[0];
        //String result = performPost( _url , data.toString(), null, null );
        return performPost( _url , data, null, null );
    }
}
