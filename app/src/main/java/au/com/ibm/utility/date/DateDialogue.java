package au.com.ibm.utility.date;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.widget.DatePicker;

import java.util.GregorianCalendar;

/**
 * Created by rajib on 13/12/15.
 */
public class DateDialogue{

    private String _dateString = null;

    public DateDialogue( Context c, String message ) {
        DatePickerDialog dateDlg = new DatePickerDialog(c,
                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        GregorianCalendar chosenDate = new GregorianCalendar();
                        chosenDate.set(dayOfMonth, monthOfYear, year);
                        long dtDob = chosenDate.getTimeInMillis();
                        setDateString( DateFormat.format("MMMM dd, yyyy", dtDob).toString() );
                    }
                }, 2011, 0, 1);

        dateDlg.setMessage(message);
        dateDlg.show();
    }

    public void setDateString( String newDate ){
        _dateString = newDate;
    }

    public String getDateString(){
        return _dateString;
    }
}
