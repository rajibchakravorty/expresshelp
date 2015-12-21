package org.expresshelp.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import org.expresshelp.asynctask.TaskReceiver;
import org.expresshelp.R;
import org.expresshelp.app.ExpressHelpApp;
import org.expresshelp.model.ProvideMaterialHelp;
import org.expresshelp.tasks.ConfirmMatHelp;
import org.expresshelp.tasks.ConfirmVolunteer;

/**
 * Created by rajib on 16/12/15.
 */
public class ProvideMaterialView extends BaseView implements TaskReceiver  {

    private ProgressDialog _pDialogue = null;


    private ProvideMaterialHelp _materialHelpInfo = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.provide_material_help);

        setupUI();

    }

    public void setupUI(){


        String emailPhone = ExpressHelpApp.getEmailPhone();

        EditText emailPhoneInput = (EditText) findViewById( R.id.email_phone );
        emailPhoneInput.setText(emailPhone);

        Button confirm = (Button) findViewById( R.id.btn_confirm_mat_help );

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmMaterialHelp();
            }
        });


    }

    public void confirmMaterialHelp(){

        boolean isValid = prepareJSONString();

        if( !isValid ){
            return;
        }

        JSONObject data = _materialHelpInfo.serializeData();

        if( data == null ){
            Toast.makeText( this, _prepareDataError, Toast.LENGTH_LONG );
            return;
        }

        _pDialogue = new ProgressDialog( this );
        _pDialogue.setCancelable(false);
        _pDialogue.setTitle("Volunteer Registration");
        _pDialogue.setMessage("Sending Data");
        _pDialogue.show();

        ConfirmMatHelp _cmTask = new ConfirmMatHelp( ExpressHelpApp._materialHelpURL);
        _cmTask.setReceiver( this );
        _cmTask.execute( data.toString() );


    }

    private boolean prepareJSONString(){

        EditText emailPhoneInput = (EditText) findViewById( R.id.email_phone );

        if( emailPhoneInput.getText().toString().equals( "" ) ){
            Toast.makeText( this, "Please provide a valid email address or phone number", Toast.LENGTH_LONG );
            return false;
        }

        ExpressHelpApp.setEmailPhone( emailPhoneInput.getText().toString() );

         CheckBox  _canProvideFood          = (CheckBox) findViewById( R.id.chk_provide_food );
         CheckBox  _canProvideBabyFood      = (CheckBox) findViewById( R.id.chk_provide_baby_food );
         CheckBox  _canProvideFemaleClothes = (CheckBox) findViewById( R.id.chk_provide_female_clothes );
         CheckBox  _canProvideClothes       = (CheckBox) findViewById( R.id.chk_provide_clothes );
         CheckBox  _canProvideMaleClothes   = (CheckBox) findViewById( R.id.chk_provide_male_clothes );
         CheckBox  _canProvideMedicine      = (CheckBox) findViewById( R.id.chk_provide_medicine );
         CheckBox  _canProvideMilk          = (CheckBox) findViewById( R.id.chk_provide_milk );
         CheckBox  _canProvideToiletries    = (CheckBox) findViewById( R.id.chk_provide_toiletries );
         CheckBox  _canProvideWater         = (CheckBox) findViewById( R.id.chk_provide_water );
         EditText  _detail                  = (EditText) findViewById( R.id.provide_txt_detail );


        _materialHelpInfo = new ProvideMaterialHelp( ExpressHelpApp.getEmailPhone(),
                                                     _canProvideFood.isChecked(),
                                                     _canProvideMedicine.isChecked(),
                                                     _canProvideWater.isChecked(),
                                                     _canProvideBabyFood.isChecked(),
                                                     _canProvideToiletries.isChecked(),
                                                     _canProvideClothes.isChecked(),
                                                     _canProvideMilk.isChecked(),
                                                     _canProvideFemaleClothes.isChecked(),
                                                     _canProvideMaleClothes.isChecked(),
                                                     _detail.getText().toString()  );

        return true;
    }

    @Override
    public void receiveResult(String response, String source) {

        _pDialogue.dismiss();
        _pDialogue = null;

        if( source.equals( ConfirmVolunteer._confirmVolunteerTaskID ) ){

            //check if the response is fine or not
            //take actions accordingly

            Toast.makeText(this, "Confirmation sent. Thank YOU", Toast.LENGTH_LONG).show();
        }

    }
}
