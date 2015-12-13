package au.com.ibm.expresshelp.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import au.com.ibm.expresshelp.R;
import au.com.ibm.expresshelp.views.apptasks.NeedHelpView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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

    }

    private void navigateToNeedHelpUI(){

        Intent intent = new Intent( MainActivity.this, NeedHelpView.class );
        startActivity(intent);
    }
}
