package org.expresshelp.expresshelp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rajib on 12/12/15.
 */
public class NeedHelpData {

    public static String _deviceLocationKey = "device_location";
    public static String _homeKey           = "home_number";
    public static String _streetKey         = "street";
    public static String _divisionKey       = "division";
    public static String _cityKey           = "city";
    public static String _numTotalKey       = "num_total_people";
    public static String _numOldKey         = "num_old_people";
    public static String _numChildKey       = "num_child";

    public static String _defaultHome     = "home number";
    public static String _defaultStreet   = "street name";
    public static String _defaultDivision = "division/locality";
    public static String _defaultCity     = "city";

    public static String _foodKey         = "food";
    public static String _waterKey        = "water";
    public static String _clothKey        = "cloth";
    public static String _milkKey         = "milk";
    public static String _femaleClothKey  = "female_cloth";
    public static String _maleClothKey    = "male_cloth";
    public static String _babyFoodKey     = "baby_food";
    public static String _sanitaryKey     = "sanitary";
    public static String _diaperKey       = "diaper_food";
    public static String _soapKey         = "soap";
    public static String _handwashKey     = "handwash";
    public static String _phenylKey       = "phenyl";

    public static String _otherSpecialKey   = "other_special";
    public static String _medicineDetailKey = "medicicne_detail";
    public static String _extraKey          = "extra";



    private String _deviceLocation = "";
    private String _home           = _defaultHome;
    private String _street         = _defaultStreet;
    private String _division       = _defaultDivision;
    private String _city           = _defaultCity;
    private String _numTotalPeople = "number of total people";
    private String _numOldPeople   = "number of old aged people";
    private String _numChildren    = "number of child";

    private boolean _foodNeeded  = true;
    private boolean _waterNeeded = true;
    private boolean _clothNeeded = true;

    private boolean _milkNeeded        = false;
    private boolean _femaleClothNeeded = false;
    private boolean _maleClothNeeded   = false;
    private boolean _babyFoodNeeded    = false;

    private boolean _sanitaryNeeded        = false;
    private boolean _diaperNeeded          = false;
    private boolean _soapNeeded            = false;
    private boolean _handWashNeeded        = false;
    private boolean _phenylNeeded          = false;

    private String _otherSpecials   = "";
    private String _medicineDetails = "";
    private String _otherDetails    = "";

    public void reset(){

        _deviceLocation     = "";
        _home               = _defaultHome;
        _street             = _defaultStreet;
        _division           = _defaultDivision;
        _city               = _defaultCity;
        _numTotalPeople     = "number of total people";
        _numOldPeople       = "number of old aged people";
        _numChildren        = "number of child";
    }

    public NeedHelpData(){

    }

    public NeedHelpData( String jsonData ){

        try{
            JSONObject data = new JSONObject( jsonData );

            setHome     ( data.getString( _homeKey      ) );
            setStreet   ( data.getString( _streetKey    ) );
            setDivision ( data.getString( _divisionKey  ) );
            setCity     ( data.getString( _cityKey      ) );

            setNumTotalPeople ( data.getString( _numTotalKey ));
            setNumOldPeople   ( data.getString( _numOldKey   ));
            setNumChildren    ( data.getString( _numChildKey ));

        }

        catch( JSONException je ){
            reset();
        }

    }

    public JSONObject serializeData(){

        try {
            JSONObject data = new JSONObject();

            data.put( _homeKey    , _home     );
            data.put( _streetKey  , _street   );
            data.put( _divisionKey, _division );
            data.put( _cityKey    , _city     );

            data.put( _numTotalKey, _numTotalPeople );
            data.put( _numOldKey  , _numOldPeople   );
            data.put( _numChildKey, _numChildren    );



            data.put( _foodKey       , _foodNeeded );
            data.put( _waterKey      , _waterNeeded );
            data.put( _clothKey      , _clothNeeded );
            data.put( _milkKey       , _milkNeeded );
            data.put( _femaleClothKey, _femaleClothNeeded );
            data.put( _maleClothKey  , _maleClothNeeded );
            data.put( _babyFoodKey   , _babyFoodNeeded );
            data.put( _sanitaryKey   , _sanitaryNeeded );
            data.put( _diaperKey     , _diaperNeeded );
            data.put( _soapKey       , _soapNeeded );

            data.put( _handwashKey   , _handWashNeeded );
            data.put( _phenylKey     , _phenylNeeded );

            data.put( _otherSpecialKey  , _otherSpecials );
            data.put( _medicineDetailKey, _medicineDetails );
            data.put( _extraKey         , _otherDetails );


            return data;
        }
        catch( JSONException jse ){

            return null;
        }

    }



    public boolean isFoodNeeded() {
        return _foodNeeded;
    }

    public void setFoodNeeded(boolean foodNeeded) {
        this._foodNeeded = foodNeeded;
    }

    public boolean isWaterNeeded() {
        return _waterNeeded;
    }

    public void setWaterNeeded(boolean waterNeeded) {
        this._waterNeeded = waterNeeded;
    }

    public boolean isClothNeeded() {
        return _clothNeeded;
    }

    public void setClothNeeded(boolean clothNeeded) {
        this._clothNeeded = clothNeeded;
    }

    public boolean isMilkNeeded() {
        return _milkNeeded;
    }

    public void setMilkNeeded(boolean milkNeeded) {
        this._milkNeeded = milkNeeded;
    }

    public boolean isFemaleClothNeeded() {
        return _femaleClothNeeded;
    }

    public void setFemaleClothNeeded(boolean femaleClothNeeded) {
        this._femaleClothNeeded = femaleClothNeeded;
    }

    public boolean isMaleClothNeeded() {
        return _maleClothNeeded;
    }

    public void setMaleClothNeeded(boolean maleClothNeeded) {
        this._maleClothNeeded = maleClothNeeded;
    }

    public boolean isBabyFoodNeeded() {
        return _babyFoodNeeded;
    }

    public void setBabyFoodNeeded(boolean babyFoodNeeded) {
        this._babyFoodNeeded = _babyFoodNeeded;
    }

    public boolean isSanitaryNeeded() {
        return _sanitaryNeeded;
    }

    public void setSanitaryNeeded(boolean sanitaryNeeded) {
        this._sanitaryNeeded = sanitaryNeeded;
    }

    public boolean isDiaperNeeded() {
        return _diaperNeeded;
    }

    public void setDiaperNeeded(boolean diaperNeeded) {
        this._diaperNeeded = _diaperNeeded;
    }

    public boolean isSoapNeeded() {
        return _soapNeeded;
    }

    public void setSoapNeeded(boolean soapNeeded) {
        this._soapNeeded = soapNeeded;
    }

    public boolean isHandWashNeeded() {
        return _handWashNeeded;
    }

    public void setHandWashNeeded(boolean handWashNeeded) {
        this._handWashNeeded = handWashNeeded;
    }

    public boolean isPhenylNeeded() {
        return _phenylNeeded;
    }

    public void setPhenylNeeded(boolean phenylNeeded) {
        this._phenylNeeded = _phenylNeeded;
    }

    public String getOtherSpecials() {
        return _otherSpecials;
    }

    public void setOtherSpecials(String otherSpecials) {
        this._otherSpecials = otherSpecials;
    }

    public String getMedicineDetails() {
        return _medicineDetails;
    }

    public void setMedicineDetails(String medicineDetails) {
        this._medicineDetails = medicineDetails;
    }

    public String getOtherDetails() {
        return _otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this._otherDetails = otherDetails;
    }



    public String getDeviceLocation() {
        return _deviceLocation;
    }

    public void setDeviceLocation(String deviceLocation) {
        this._deviceLocation = deviceLocation;
    }


    public String getHome() {
        return _home;
    }

    public void setHome(String home) {
        this._home = home;
    }

    public String getStreet() {
        return _street;
    }

    public void setStreet(String street) {
        this._street = street;
    }

    public String getDivision() {
        return _division;
    }

    public void setDivision(String division) {
        this._division = division;
    }

    public String getCity() {
        return _city;
    }

    public void setCity(String city) {
        this._city = city;
    }

    public String getNumTotalPeople() {
        return _numTotalPeople;
    }

    public void setNumTotalPeople(String numTotalPeople) {
        this._numTotalPeople = numTotalPeople;
    }

    public String getNumOldPeople() {
        return _numOldPeople;
    }

    public void setNumOldPeople(String numOldPeople) {
        this._numOldPeople = numOldPeople;
    }

    public String getNumChildren() {
        return _numChildren;
    }

    public void setNumChildren(String numChildren) {
        this._numChildren = numChildren;
    }

}
