package org.expresshelp.views;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.expresshelp.R;
import org.expresshelp.app.ExpressHelpApp;

/**
 * Created by rajib on 12/12/15.
 */
public class AboutView extends BaseView {

    private static String _aboutKey = "about";

    private String _about = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about );

        getAbout();

        setupUI();

    }

    private void setupUI(){

        EditText aboutText = ( EditText ) findViewById( R.id.txt_about );

        aboutText.setText( _about );
        aboutText.setKeyListener( null );


    }

    public void getAbout(){

        try{
            Context c = getApplicationContext();

            InputStream is = c.getAssets().open( ExpressHelpApp._aboutPropertiesFile );
            Properties p = new Properties();
            p.load( is );
            _about = p.getProperty(_aboutKey);
        }
        catch( IOException ioe ){
            _about = "About file could not be read";
        }
    }
}
