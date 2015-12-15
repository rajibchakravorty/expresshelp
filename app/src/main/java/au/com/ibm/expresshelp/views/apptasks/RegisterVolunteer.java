package au.com.ibm.expresshelp.views.apptasks;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import au.com.ibm.asynctask.TaskReceiver;
import au.com.ibm.expresshelp.R;
import au.com.ibm.expresshelp.app.ExpressHelpApp;
import au.com.ibm.expresshelp.model.Volunteer;
import au.com.ibm.tasks.ConfirmVolunteer;
import au.com.ibm.tasks.ReliefRequest;
import au.com.ibm.utility.Utility;
import au.com.ibm.utility.date.DateDialogue;

/**
 * Created by rajib on 13/12/15.
 */
public class RegisterVolunteer extends BaseView implements TaskReceiver, DatePickerDialog.OnDateSetListener{

    public static String _startDateMessage = "Arrival/Start Date?";
    public static String _endDateMessage = "Departure/End Date";

    public static String _dateFormat = "dd-MM-yyyy";

    public static int _addDays = 7;

    private boolean _isStartDate;

    private ProgressDialog _pDialogue;

    DateDialogue newFragment = new DateDialogue();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_volunteer);

        setupUI();

    }

    private void setupUI(){

        setDefaultDates();

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

        Button confirm = ( Button ) findViewById( R.id.btn_confirm_volunt );
        confirm.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                confirmVolunteer();

            }
        });
    }

    public void confirmVolunteer(){

        TextView startDate = ( TextView ) findViewById( R.id.txt_start_date );
        TextView endDate   = ( TextView ) findViewById( R.id.txt_end_date   );

        CheckBox  requireAccom = (CheckBox ) findViewById( R.id.chk_need_accommodation );

        RadioButton _workAlone = ( RadioButton ) findViewById( R.id.radio_alone );



        Volunteer v = new Volunteer( startDate.getText().toString(),
                                     endDate.getText().toString(),
                                     requireAccom.isChecked(),
                                     _workAlone.isChecked(),
                                     !_workAlone.isChecked() );

        String volunteerData = v.serializeData();


        _pDialogue = new ProgressDialog( this );
        _pDialogue.setCancelable(false);
        _pDialogue.setTitle("Volunteer Registration");
        _pDialogue.setMessage("Sending Data");
        _pDialogue.show();

        ConfirmVolunteer _cvTask = new ConfirmVolunteer( ExpressHelpApp._confirmVolunteerURL);
        _cvTask.setReceiver( this );
        _cvTask.execute( volunteerData );

    }

    private void setDefaultDates(){

        Calendar c = Calendar.getInstance();

        Date dtCurrent = c.getTime();
        String currentDate = Utility.date2String(dtCurrent, _dateFormat);

        c.add( Calendar.DATE, _addDays );
        Date dtLater = c.getTime();
        String laterDate = Utility.date2String(dtLater, _dateFormat);


        TextView stv = ( TextView ) findViewById( R.id.txt_start_date );
        stv.setText( currentDate );

        TextView etv = ( TextView ) findViewById( R.id.txt_end_date );
        etv.setText(laterDate);
    }


    private void setStartDate(){

        pickDate( _startDateMessage );


        //newFragment.showDateDialogue(this, _startDateMessage);

    }


    private void setEndDate(){

        pickDate(_endDateMessage);


    }


    private void pickDate( String message ){

        if( message.equals( _startDateMessage ) ) {
            _isStartDate = true;
        }
        else{
            _isStartDate = false;
        }
        newFragment.setConext(this);
        newFragment.setListener(this);

        newFragment.show( getFragmentManager(), "pick a date" );


        return;

    }
    @Override
    public void receiveResult(String response, String source) {


        _pDialogue.dismiss();
        _pDialogue = null;

        if( source.equals( ReliefRequest._reliefTaskID ) ){

            //check if the response is fine or not
            //take actions accordingly

            Toast.makeText(this, "Confirmation sent. Thank YOU", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        GregorianCalendar chosenDate = new GregorianCalendar();
        chosenDate.set(year, monthOfYear, dayOfMonth );
        String _chosenDateString = Utility.date2String( chosenDate.getTime(),_dateFormat );

        if( _isStartDate ) {
            TextView tv = (TextView) findViewById(R.id.txt_start_date);
            tv.setText(_chosenDateString);
        }
        else{
            TextView tv = (TextView) findViewById(R.id.txt_end_date);
            tv.setText(_chosenDateString);

        }
    }


}
