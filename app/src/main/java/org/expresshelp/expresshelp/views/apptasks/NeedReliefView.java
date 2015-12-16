package org.expresshelp.expresshelp.views.apptasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.expresshelp.asynctask.TaskReceiver;
import org.expresshelp.expresshelp.R;
import org.expresshelp.expresshelp.app.ExpressHelpApp;
import org.expresshelp.expresshelp.model.NeedHelpData;
import org.expresshelp.tasks.ReliefRequest;

/**
 * Created by rajib on 13/12/15.
 */
public class NeedReliefView extends BaseView implements TaskReceiver {


    private NeedHelpData _needHelpData = null;

    private ProgressDialog _pDialogue = null;

    public static String _responseKey       = "relief_response";
    public static String _response          = null;
    public static String _defaultResponse   = "Request is received.";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.need_relief);

        _response = readResponseProperty();

        String baseInfo = getIntent().getStringExtra( ExpressHelpApp._locationData );

        _needHelpData =  new NeedHelpData( baseInfo );


        setupUI();

    }

    public void setupUI(){

        Button reliefRequest = ( Button ) findViewById( R.id.btn_need_relief_step2 );

        reliefRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestRelief();
            }
        });
    }

    public void requestRelief(){

        prepareReliefData();

        JSONObject data = _needHelpData.serializeData();

        if( data == null ){


            Toast.makeText(getApplicationContext(), _prepareDataError, Toast.LENGTH_LONG).show();
            return;
        }


        Button btnRelief = (Button) findViewById(R.id.btn_need_relief_step2 );
        btnRelief.setEnabled( false );


        _pDialogue = new ProgressDialog( this );

        _pDialogue.setCancelable(false);
        _pDialogue.setTitle("Relief request");
        _pDialogue.setMessage("Sending Data");
        _pDialogue.show();

        ReliefRequest rrTask = new ReliefRequest( ExpressHelpApp._reliefURL );
        rrTask.setReceiver( this );
        rrTask.execute( data.toString() );

    }

    public void prepareReliefData(){

        CheckBox _isFoodNeeded = ( CheckBox ) findViewById( R.id.chk_food );
        _needHelpData.setFoodNeeded(_isFoodNeeded.isChecked()) ;

        CheckBox _isWaterNeeded = ( CheckBox ) findViewById( R.id.chk_water );
        _needHelpData.setWaterNeeded(_isWaterNeeded.isChecked()) ;

        CheckBox _isClothesNeeded = ( CheckBox ) findViewById( R.id.chk_clothes );
        _needHelpData.setClothNeeded(_isClothesNeeded.isChecked()) ;

        CheckBox _isMilkNeeded = ( CheckBox ) findViewById( R.id.chk_milk );
        _needHelpData.setMilkNeeded(_isMilkNeeded.isChecked()) ;

        CheckBox _isBabyFoodNeeded = ( CheckBox ) findViewById( R.id.chk_baby_food );
        _needHelpData.setBabyFoodNeeded(_isBabyFoodNeeded.isChecked()) ;

        CheckBox _isFemaleClothNeeded = ( CheckBox ) findViewById( R.id.chk_clothes_female );
        _needHelpData.setFemaleClothNeeded(_isFemaleClothNeeded.isChecked()) ;

        CheckBox _isMaleClothNeeded = ( CheckBox ) findViewById( R.id.chk_clothes_male );
        _needHelpData.setMaleClothNeeded(_isMaleClothNeeded.isChecked()) ;

        CheckBox _isSanitaryNeeded = ( CheckBox ) findViewById( R.id.chk_sanitary );
        _needHelpData.setSanitaryNeeded(_isSanitaryNeeded.isChecked()) ;

        CheckBox _isDiaperNeeded = ( CheckBox ) findViewById( R.id.chk_diapers );
        _needHelpData.setDiaperNeeded(_isDiaperNeeded.isChecked()) ;

        CheckBox _isSoapNeeded = ( CheckBox ) findViewById( R.id.chk_soaps );
        _needHelpData.setSoapNeeded(_isSoapNeeded.isChecked()) ;

        CheckBox _isHandWashNeeded = ( CheckBox ) findViewById( R.id.chk_hand_wash );
        _needHelpData.setHandWashNeeded(_isHandWashNeeded.isChecked()) ;

        CheckBox _isPhenylNeeded = ( CheckBox ) findViewById( R.id.chk_phenyl );
        _needHelpData.setPhenylNeeded(_isPhenylNeeded.isChecked());

        EditText _otherSpecials = ( EditText ) findViewById( R.id.detail_special_needs );
        _needHelpData.setOtherSpecials(_otherSpecials.getText().toString());

        EditText _medicineDetails = ( EditText ) findViewById( R.id.detail_medicine );
        _needHelpData.setMedicineDetails(_medicineDetails.getText().toString());

        EditText _extra = ( EditText ) findViewById( R.id.extra_needs );
        _needHelpData.setOtherDetails( _extra.getText().toString() );

        return;

    }
    @Override
    public void receiveResult(String response, String source) {

        Button btnRelief = (Button) findViewById(R.id.btn_need_relief_step2 );

        btnRelief.setEnabled(true);

        _pDialogue.dismiss();
        _pDialogue = null;

        if( source.equals( ReliefRequest._reliefTaskID ) ){

            //check if the response is fine or not
            //take actions accordingly

            _needHelpData.reset();

            Toast.makeText( this, _response,  Toast.LENGTH_LONG ).show();
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
