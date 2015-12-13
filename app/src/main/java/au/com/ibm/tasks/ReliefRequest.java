package au.com.ibm.tasks;

import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

import au.com.ibm.asynctask.AsyncServiceTask;

/**
 * Created by rajib on 13/12/15.
 */
public class ReliefRequest extends AsyncServiceTask {

    public static String _reliefTaskID = "Relief Task";

    private String _url;

    public ReliefRequest( String url ){

        super( _reliefTaskID );
        _url = url;
    }


    @Override
    protected String performTask(String... params) throws IOException, JSONException, URISyntaxException {

        String data = params[0];
        //String result = performPost( _url , data.toString(), null, null );
        return performPost( _url , data, null, null );
    }
}