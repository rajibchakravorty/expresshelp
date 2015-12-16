package org.expresshelp.utility.date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by rajib on 13/12/15.
 */
public class DateDialogue extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private static int _initYear = 2015;
    private static int _initMonth = 11;
    private static int _initDay = 15;

    private String _dateString = null;

    private Context _c;
    DatePickerDialog.OnDateSetListener _l;
    public void setConext( Context c ){
        _c = c;
    }

    public void setListener( DatePickerDialog.OnDateSetListener l){
        _l = l;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog( _c, _l, year, month, day);
    }


    public void setDateString( String newDate ){
        _dateString = newDate;
    }

    public String getDateString(){
        return _dateString;
    }

    public void showDateDialogue( Context c, String message ){
        DatePickerDialog dateDlg = new DatePickerDialog(c,
                this, _initYear, _initMonth, _initDay);

        dateDlg.setMessage(message);
        dateDlg.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        GregorianCalendar chosenDate = new GregorianCalendar();
        chosenDate.set(dayOfMonth, monthOfYear, year);
        long dtDob = chosenDate.getTimeInMillis();
        setDateString(DateFormat.format("MMMM dd, yyyy", dtDob).toString());
    }
}
