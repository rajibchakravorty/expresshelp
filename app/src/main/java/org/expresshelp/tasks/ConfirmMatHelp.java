package org.expresshelp.tasks;

import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

import org.expresshelp.asynctask.AsyncServiceTask;

/**
 * Created by rajib on 16/12/15.
 */
public class ConfirmMatHelp  extends AsyncServiceTask {

    public static String _confirmMaterialHelpTaskID = "Confirm Material Help";

    private String _url;

    public ConfirmMatHelp( String url ){

        super( _confirmMaterialHelpTaskID );
        _url = url;
    }


    @Override
    protected String performTask(String... params) throws IOException, JSONException, URISyntaxException {

        String data = params[0];
        Log.i("ConfirmMaterialHelp", data);
        //String result = performPost( _url , data.toString(), null, null );
        return performPost( _url , data, null, null );
    }
}