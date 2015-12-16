package org.expresshelp.expresshelp.views.apptasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.expresshelp.expresshelp.R;

public class MainActivity extends BaseView {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ehelp);

        setupUI();
    }


    private void setupUI(){

        Button needHelp = ( Button ) findViewById( R.id.btn_need_help );

        needHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigateToNeedHelpUI();


            }
        });

        Button provideHelp = ( Button ) findViewById( R.id.btn_provide_help );

        provideHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigateToProvideHelpUI();


            }
        });

    }

    private void navigateToNeedHelpUI(){

        Intent intent = new Intent( MainActivity.this, NeedHelpView.class );
        startActivity(intent);
    }

    private void navigateToProvideHelpUI(){

        Intent intent = new Intent( MainActivity.this, ProvideHelp.class );
        startActivity(intent);
    }
}
