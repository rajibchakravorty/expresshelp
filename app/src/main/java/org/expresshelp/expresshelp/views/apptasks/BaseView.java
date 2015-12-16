package org.expresshelp.expresshelp.views.apptasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.expresshelp.expresshelp.R;


public class BaseView extends AppCompatActivity {

    public static String _dataMissingError = "Please provide full location detail";
    public static String _prepareDataError = "Error preparing the data for remote server";


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ){


        switch( item.getItemId() ){

            case R.id.action_about:

                navigateToAbout();
                return true;

            case R.id.action_main:

                navigateToMain();
                return true;

            case R.id.action_support:
                navigateToSupport();
                return true;

            /*
            case R.id.action_updateprofile:

                navigateToUpdate();
                return true;



            case R.id.action_search:

                searchTips();
                return true;
            */

            default:
                return super.onOptionsItemSelected( item );

        }

    }

    private void navigateToAbout(){

        Intent newIntent = new Intent( getApplicationContext(), AboutView.class );
        startActivity( newIntent );

    }

    private void navigateToMain(){

        Intent newIntent = new Intent( getApplicationContext(), MainActivity.class );
        startActivity( newIntent );

    }

    private void navigateToSupport(){

        Intent newIntent = new Intent( getApplicationContext(), SupportView.class );
        startActivity( newIntent );

    }


    /*
    private void navigateToUpdate(){

        Intent newIntent = new Intent( getApplicationContext(), UpdateProfileUI.class );
        startActivity( newIntent );

    }

    private void navigateToLogin(){

        if( !BlueMobileApp.isLoggedIn() ){
            Intent intent = new Intent( getApplicationContext(),LoginUI.class );
            intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity(intent);
            finish();
        }
    }
    */

}

