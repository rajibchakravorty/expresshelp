package org.expresshelp.expresshelp.model;

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

        _emailPhone = emailPhone;
        setArrivalDate( arrivalDate );
        setDepartureDate( departureDate );
        setRequireAccom( requireAccom );
        setWorkAlone( workAlone );
        setWorkTeam(workTeam);
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

    public String getArrivalDate() {
        return _arrivalDate;
    }

    public void setArrivalDate(String _arrivalDate) {
        this._arrivalDate = _arrivalDate;
    }

    public String getDepartureDate() {
        return _departureDate;
    }

    public void setDepartureDate(String _departureDate) {
        this._departureDate = _departureDate;
    }

    public boolean isRequireAccom() {
        return _requireAccom;
    }

    public void setRequireAccom(boolean _requireAccom) {
        this._requireAccom = _requireAccom;
    }

    public boolean isWorkAlone() {
        return _workAlone;
    }

    public void setWorkAlone(boolean _workAlone) {
        this._workAlone = _workAlone;
    }

    public boolean isWorkTeam() {
        return _workTeam;
    }

    public void setWorkTeam(boolean _workTeam) {
        this._workTeam = _workTeam;
    }
}
