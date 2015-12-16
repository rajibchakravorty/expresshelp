package org.expresshelp.expresshelp.views.apptasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.expresshelp.expresshelp.R;
import org.expresshelp.expresshelp.model.ProvideMaterialHelp;

/**
 * Created by rajib on 13/12/15.
 */
public class ProvideHelp extends BaseView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.provide_help);

        setupUI();

    }

    public void setupUI(){

        Button financeHelp = ( Button ) findViewById( R.id.btn_financial_help );

        financeHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigateToFinancialHelpActivity();

            }
        });

        Button materialHelp = ( Button ) findViewById( R.id.btn_material_help );

        materialHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigateToMaterialHelpActivity();

            }
        });

        Button volunteerHelp = ( Button ) findViewById( R.id.btn_be_volunteer );

        volunteerHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigateToVolunteerActivity();

            }
        });
    }

    public void navigateToFinancialHelpActivity(){
        Toast.makeText( this, "Not implemented yet", Toast.LENGTH_LONG );
    }

    public void navigateToMaterialHelpActivity(){
        Intent intent = new Intent( ProvideHelp.this, ProvideMaterialView.class );
        startActivity(intent);
    }

    public void navigateToVolunteerActivity(){
        Intent intent = new Intent( ProvideHelp.this, RegisterVolunteer.class );
        startActivity( intent );
    }
}
