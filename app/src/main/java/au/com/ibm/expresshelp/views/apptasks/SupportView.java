package au.com.ibm.expresshelp.views.apptasks;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import au.com.ibm.expresshelp.R;
import au.com.ibm.expresshelp.app.ExpressHelpApp;

/**
 * Created by rajib on 13/12/15.
 */
public class SupportView extends BaseView{

    public static String _emailKey = "email";
    public static String _phoneKey = "phone";


    private String _email = "";
    private String _phone = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.support );

        getSupportInfo();

        setupUI();

    }

    private void setupUI(){

        EditText emailText = ( EditText ) findViewById( R.id.txt_email );
        EditText phoneText = ( EditText ) findViewById( R.id.txt_phone );

        emailText.setText( _email );
        phoneText.setText( _phone );

        emailText.setKeyListener( null );
        phoneText.setKeyListener( null );
    }


    private void getSupportInfo(){

        try{
            Context c = getApplicationContext();

            InputStream is = c.getAssets().open( ExpressHelpApp._aboutPropertiesFile );
            Properties p = new Properties();
            p.load( is );
            _email = p.getProperty( _emailKey );
            _phone = p.getProperty( _phoneKey );

        }
        catch( IOException ioe ){
            _email = "No email found";
            _phone = "No phone info found";
        }
    }

}
