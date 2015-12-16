package org.expresshelp.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rajib on 15/12/15.
 */
public class Utility {

    public static String date2String( Date d, String format ){

        SimpleDateFormat timeFormat = new SimpleDateFormat( format );
        return timeFormat.format( d );

    }
}
