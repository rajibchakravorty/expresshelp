package org.expresshelp.tasks;

import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

import org.expresshelp.asynctask.AsyncServiceTask;

/**
 * Created by rajib on 15/12/15.
 */
public class ConfirmVolunteer extends AsyncServiceTask {

    public static String _confirmVolunteerTaskID = "Confirm Volunteer";

    private String _url;

    public ConfirmVolunteer( String url ){

        super( _confirmVolunteerTaskID );
        _url = url;
    }


    @Override
    protected String performTask(String... params) throws IOException, JSONException, URISyntaxException {

        String data = params[0];
        Log.i("VolunteerRequest", data);
        //String result = performPost( _url , data.toString(), null, null );
        return performPost( _url , data, null, null );
    }
}