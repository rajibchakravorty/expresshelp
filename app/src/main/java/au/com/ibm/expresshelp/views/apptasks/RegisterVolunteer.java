package au.com.ibm.expresshelp.views.apptasks;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.GregorianCalendar;

import au.com.ibm.asynctask.TaskReceiver;
import au.com.ibm.expresshelp.R;
import au.com.ibm.utility.date.DateDialogue;

/**
 * Created by rajib on 13/12/15.
 */
public class RegisterVolunteer extends BaseView implements TaskReceiver {

    public static String _startDateMessage = "Arrival/Start Date?";
    public static String _endDateMessage = "Departure/End Date";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_volunteer);

        setupUI();

    }

    private void setupUI(){

        Button arrival = (Button) findViewById( R.id.btn_volun_start_date );

        arrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setStartDate();
            }
        });

        Button finish = (Button) findViewById( R.id.btn_volun_end_date );

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setEndDate();
            }
        });
    }


    private void setStartDate(){

        String startDate = pickDate( _startDateMessage );

        TextView tv = ( TextView ) findViewById( R.id.txt_start_date );
        tv.setText(startDate);
    }

    private void setEndDate(){

        String endDate = pickDate( _endDateMessage );

        TextView tv = ( TextView ) findViewById( R.id.txt_end_date );
        tv.setText( endDate );
    }


    private String pickDate( String message ){

        DateDialogue dd = new DateDialogue( this, message );
        return dd.getDateString();

    }
    @Override
    public void receiveResult(String response, String source) {

    }
}
