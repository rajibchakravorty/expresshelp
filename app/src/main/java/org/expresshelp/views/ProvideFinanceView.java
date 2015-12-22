package org.expresshelp.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.expresshelp.R;
import org.expresshelp.app.ExpressHelpApp;
import org.expresshelp.asynctask.TaskReceiver;
import org.expresshelp.model.FinanceHelp;
import org.expresshelp.model.MaterialHelp;
import org.expresshelp.tasks.ConfirmMatHelp;
import org.expresshelp.tasks.ConfirmPayDetail;
import org.expresshelp.tasks.ConfirmVolunteer;
import org.json.JSONObject;

import java.net.URI;

/**
 * Created by rchakrav on 22/12/2015.
 */
public class ProvideFinanceView extends BaseView  implements TaskReceiver {

    private ProgressDialog _pDialogue = null;


    private FinanceHelp _financeHelpInfo = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.provide_financial_help);

        setupUI();

    }

    public void setupUI(){


        String emailPhone = ExpressHelpApp.getEmailPhone();

        EditText emailPhoneInput = (EditText) findViewById( R.id.email_phone );
        emailPhoneInput.setText(emailPhone);

        Button payByGateway = ( Button ) findViewById( R.id.btn_pay_gateway );

        payByGateway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoPayGateway();
            }
        });

        Button confirm = (Button) findViewById( R.id.btn_confirm_pay );

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmFinancialHelp();
            }
        });


    }

    public void gotoPayGateway(){

        Uri gatewayURI = Uri.parse( ExpressHelpApp._payGatwareURL );
        Intent payIntent = new Intent( Intent.ACTION_VIEW, gatewayURI );
        startActivity( payIntent );

    }

    public void confirmFinancialHelp(){

        boolean isValid = prepareJSONString();

        if( !isValid ){
            return;
        }

        JSONObject data = _financeHelpInfo.serializeData();

        if( data == null ){
            Toast.makeText(this, _prepareDataError, Toast.LENGTH_LONG);
            return;
        }

        _pDialogue = new ProgressDialog( this );
        _pDialogue.setCancelable(false);
        _pDialogue.setTitle("Financial assistance");
        _pDialogue.setMessage("Sending Data");
        _pDialogue.show();

        ConfirmPayDetail _cmTask = new ConfirmPayDetail( ExpressHelpApp._financialHelpURL );
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

        EditText amount          = (EditText) findViewById( R.id.txt_amount         );
        EditText accountName     = (EditText) findViewById( R.id.txt_acc_name       );
        EditText accountNum      = (EditText) findViewById( R.id.txt_acc_num        );
        EditText bankName        = (EditText) findViewById( R.id.txt_bank_name      );
        EditText bankBranch      = (EditText) findViewById( R.id.txt_branch_name    );
        EditText branchAddress   = (EditText) findViewById( R.id.txt_branch_address );
        EditText branchCity      = (EditText) findViewById( R.id.txt_branch_city    );
        EditText branchState     = (EditText) findViewById( R.id.txt_branch_state   );

        if( amount.getText().toString().equals( "" ) ||
            accountName.getText().toString().equals( "" ) ||
            accountNum.getText().toString().equals( "" ) ||
            bankName.getText().toString().equals( "" ) ||
            bankBranch.getText().toString().equals( "" ) ||
            branchAddress.getText().toString().equals( "" ) ||
            branchCity.getText().toString().equals( "" ) ||
            branchState.getText().toString().equals( "" ) ){

            Toast.makeText( this, "Please supply valid input for each input. Thank you.", Toast.LENGTH_LONG );
            return false;

        }


        _financeHelpInfo = new FinanceHelp( ExpressHelpApp.getEmailPhone(),
                                            amount.getText().toString(),
                                            accountName.getText().toString(),
                                            accountNum.getText().toString(),
                                            bankName.getText().toString(),
                                            bankBranch.getText().toString(),
                                            branchAddress.getText().toString(),
                                            branchCity.getText().toString(),
                                            branchState.getText().toString() );

        return true;
    }

    @Override
    public void receiveResult(String response, String source) {

        _pDialogue.dismiss();
        _pDialogue = null;

        if( source.equals( ConfirmPayDetail._confirmFinanceHelpTaskID ) ){

            //check if the response is fine or not
            //take actions accordingly

            Toast.makeText(this, "Confirmation sent. Thank YOU", Toast.LENGTH_LONG).show();
        }

    }
}
