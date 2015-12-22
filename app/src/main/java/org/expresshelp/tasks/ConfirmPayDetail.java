package org.expresshelp.tasks;

import android.util.Log;

import org.expresshelp.asynctask.AsyncServiceTask;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by rchakrav on 22/12/2015.
 */
public class ConfirmPayDetail extends AsyncServiceTask {

    public static String _confirmFinanceHelpTaskID = "Confirm Financial Help";

    private String _url;

    public ConfirmPayDetail( String url ){

        super( _confirmFinanceHelpTaskID );
        _url = url;
    }


    @Override
    protected String performTask(String... params) throws IOException, JSONException, URISyntaxException {

        String data = params[0];
        Log.i("ConfirmFinancialHelp", data);
        //String result = performPost( _url , data.toString(), null, null );
        return performPost( _url , data, null, null );
    }
}
