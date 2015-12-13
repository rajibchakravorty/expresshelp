package au.com.ibm.expresshelp.views.apptasks;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import au.com.ibm.asynctask.TaskReceiver;
import au.com.ibm.expresshelp.R;
import au.com.ibm.expresshelp.app.ExpressHelpApp;
import au.com.ibm.expresshelp.model.NeedHelpData;
import au.com.ibm.tasks.RescueRequest;

/**
 * Created by rajib on 12/12/15.
 */
public class NeedHelpView extends BaseView implements TaskReceiver   {

    public static String _dataMissingError = "Please provide full location detail";
    public static String _prepareDataError = "Error preparing the JSON for rescue request";

    public static String _responseKey       = "rescue_response";
    public static String _response          = null;
    public static String _defaultResponse   = "Request is received.";

    private NeedHelpData _needHelpData = new NeedHelpData();

    private ProgressDialog _pDialogue = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.need_help);

        _response = readResponseProperty();

        setupUI();

    }

    private void setupUI(){

        Button btnRescue = (Button) findViewById( R.id.btn_need_rescue );

        btnRescue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendRescueRequest();
            }
        });

        Button btnRelief = (Button) findViewById( R.id.btn_need_relief_step1 );

        btnRelief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                collectReliefRequest();
            }
        });
    }

    private void collectReliefRequest(){

        prepareRescueData();

        JSONObject data = _needHelpData.serializeData();

        if( data == null ){


            Toast.makeText( getApplicationContext(), _prepareDataError, Toast.LENGTH_LONG );
            return;
        }

        Intent intent = new Intent( NeedHelpView.this, NeedReliefView.class );
        intent.putExtra( ExpressHelpApp._locationData, data.toString() );
        startActivity(intent);
    }

    private void sendRescueRequest(){

        //collect the data
        prepareRescueData();

        JSONObject data = _needHelpData.serializeData();

        if( data == null ){


            Toast.makeText( getApplicationContext(), _prepareDataError, Toast.LENGTH_LONG );
            return;
        }

        Button btnRescue = (Button) findViewById( R.id.btn_need_rescue );
        Button btnRelief = (Button) findViewById(R.id.btn_need_relief_step1 );

        btnRescue.setEnabled( false );
        btnRelief.setEnabled( false );


        _pDialogue = new ProgressDialog( this );

        _pDialogue.setCancelable(false);
        _pDialogue.setTitle("Rescue request");
        _pDialogue.setMessage("Sending Data");
        _pDialogue.show();

        RescueRequest rrTask = new RescueRequest( ExpressHelpApp._rescueURL );
        rrTask.setReceiver(this);
        rrTask.execute(data.toString());
    }


    private void prepareRescueData(){

        //collect the data
        EditText homeNumber = ( EditText ) findViewById( R.id.home_number );
        EditText streetName = ( EditText ) findViewById( R.id.street_name );
        EditText division   = ( EditText ) findViewById( R.id.division    );
        EditText city       = ( EditText ) findViewById( R.id.city        );


        if( homeNumber.equals( NeedHelpData._defaultHome ) ||
                streetName.equals(NeedHelpData._defaultStreet) ||
                division.equals( NeedHelpData._defaultDivision ) ||
                city.equals( NeedHelpData._defaultCity ) ){

            Toast.makeText( this, _dataMissingError, Toast.LENGTH_LONG );

            return;
        }



        EditText totalPeople = ( EditText ) findViewById( R.id.num_total_people );
        EditText totalOld    = ( EditText ) findViewById( R.id.num_old_people   );
        EditText totalChild  = ( EditText ) findViewById( R.id.num_child        );



        _needHelpData.setHome(homeNumber.getText().toString());
        _needHelpData.setStreet(streetName.getText().toString());
        _needHelpData.setDivision(division.getText().toString());
        _needHelpData.setCity(city.getText().toString());

        _needHelpData.setNumTotalPeople(totalPeople.getText().toString());
        _needHelpData.setNumOldPeople(totalOld.getText().toString());
        _needHelpData.setNumChildren(totalChild.getText().toString());

        return;

    }

    @Override
    public void receiveResult(String response, String source) {

        Button btnRescue = (Button) findViewById( R.id.btn_need_rescue );
        Button btnRelief = (Button) findViewById(R.id.btn_need_relief_step1 );

        btnRescue.setEnabled(true);
        btnRelief.setEnabled(true);

        _pDialogue.dismiss();
        _pDialogue = null;

        if( source.equals( RescueRequest._rescueTaskID ) ){

            //check if the response is fine or not
            //take actions accordingly

            _needHelpData.reset();

            Toast.makeText( this, _response,  Toast.LENGTH_LONG );
        }

    }

    private String readResponseProperty( ) {

        if( _response != null ){
            return _response;
        }

        try

        {
            Context c = getApplicationContext();

            InputStream is = c.getAssets().open(ExpressHelpApp._responsePropertiesFile);
            Properties p = new Properties();
            p.load(is);
            return p.getProperty( _responseKey );

        }

        catch( IOException ioe ){
            _response = null;
            return _defaultResponse;
        }
    }
}
