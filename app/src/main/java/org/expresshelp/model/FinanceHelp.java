package org.expresshelp.model;

import org.apache.commons.codec.binary.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rchakrav on 22/12/2015.
 */
public class FinanceHelp {

    public static String _amountKey        = "donation_amount";
    public static String _emailPhoneKey    = "email_phone";
    public static String _accountNameKey   = "account_name";
    public static String _accountNumKey    = "account_number";
    public static String _bankNameKey      = "bank_name";
    public static String _bankBranchKey    = "bank_branch";
    public static String _branchAddressKey = "branch_address";
    public static String _branchCityKey    = "branch_city";
    public static String _branchStateKey   = "branch_state";



    private String _emailPhone;
    private String _amount;
    private String _accountName;
    private String _accountNum;
    private String _bankName;
    private String _bankBranch;
    private String _branchAddress;
    private String _branchCity;
    private String _branchState;

    public FinanceHelp( String emailPhone,
                        String amount,
                        String accName,
                        String accNum,
                        String bankName,
                        String branchName,
                        String branchAddress,
                        String branchCity,
                        String branchState ){

        _emailPhone          = emailPhone;
        _amount              = amount;
        _accountName         = accName;
        _accountNum          = accNum;
        _bankName            = bankName;
        _bankBranch          = branchName;
        _branchAddress       = branchAddress;
        _branchCity          = branchCity;
        _branchState         = branchState;
    }


    public JSONObject serializeData(){

        try {
            JSONObject data = new JSONObject();

            data.put( _amountKey          , _amount        );
            data.put( _accountNameKey     , _accountName   );
            data.put( _accountNumKey      , _accountNum    );
            data.put( _bankNameKey        , _bankName      );
            data.put( _bankBranchKey      , _bankBranch    );
            data.put( _branchAddressKey   , _branchAddress );
            data.put( _branchCity         , _branchCity    );
            data.put( _branchStateKey     , _branchState   );
            data.put( _emailPhoneKey      , _emailPhone    );
            return data;

        }
        catch( JSONException jse ){
            return null;
        }
    }



}
