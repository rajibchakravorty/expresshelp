package org.expresshelp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rajib on 15/12/15.
 */
public class Volunteer {

    public static String  _arrivalDateKey   = "arrival_date";
    public static String  _departureDateKey = "departure_date";
    public static String  _requireAccomKey  = "require_accommodation";
    public static String  _workAloneKey     = "work_alone";
    public static String _emailPhoneKey     = "email_phone";

    private String _arrivalDate;
    private String _departureDate;
    private boolean _requireAccom;
    private boolean _workAlone;
    private boolean _workTeam;
    private String _emailPhone;

    public Volunteer( String emailPhone,
                      String arrivalDate,
                      String departureDate,
                      boolean requireAccom,
                      boolean workAlone,
                      boolean workTeam ){

        _emailPhone    = emailPhone;
        _arrivalDate   = arrivalDate;
        _departureDate =  departureDate;
        _requireAccom  = requireAccom;
        _workAlone     = workAlone;
        _workTeam      = workTeam;
    }

    public JSONObject serializeData(){

        try {
            JSONObject data = new JSONObject();

            data.put( _arrivalDateKey  , _arrivalDate   );
            data.put( _departureDateKey, _departureDate );
            data.put( _requireAccomKey , _requireAccom  );
            data.put( _workAloneKey    , _workAlone     );
            data.put( _emailPhoneKey   , _emailPhone    );

            return data;
        }
        catch( JSONException jse ){
            return null;
        }
    }


}
