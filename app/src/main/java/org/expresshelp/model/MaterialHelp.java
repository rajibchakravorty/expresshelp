package org.expresshelp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rajib on 16/12/15.
 */
public class MaterialHelp {

    public static String _foodKey          = "food";
    public static String _medicineKey      = "medicine";
    public static String _waterKey         = "water";
    public static String _babyFoodKey      = "baby_food";
    public static String _toiletriessKey   = "toiletries";
    public static String _clothesKey       = "clothes";
    public static String _milkKey          = "milk";
    public static String _femaleClothesKey = "female_clothes";
    public static String _maleClothesKey   = "male_clothes";

    public static String _detailKey        = "detail";
    public static String _emailPhoneKey    = "email_phone";


    private boolean _canProvideFood;
    private boolean _canProvideMedicine;
    private boolean _canProvideWater;
    private boolean _canProvideBabyFood;
    private boolean _canProvideToiltries;
    private boolean _canProvideClothes;
    private boolean _canProvideMilk;
    private boolean _canProvideFemaleClothes;
    private boolean _canProvideMaleClothes;

    private String _matHelpDetail;
    private String _emailPhone;

    public MaterialHelp(String emailPhone,
                        boolean canProvideFood,
                        boolean canProvideMedicine,
                        boolean canProvideWater,
                        boolean canProvideBabyFood,
                        boolean canProvideToiletries,
                        boolean canProvideClothes,
                        boolean canProvideMilk,
                        boolean canProvideFemaleClothes,
                        boolean canProvideMaleClothes,
                        String matHelpDetail){

        _emailPhone              = emailPhone;
        _canProvideFood          = canProvideFood ;
        _canProvideBabyFood      = canProvideBabyFood;
        _canProvideClothes       = canProvideClothes;
        _canProvideFemaleClothes = canProvideFemaleClothes;
        _canProvideMaleClothes   = canProvideMaleClothes;
        _canProvideWater         = canProvideWater;
        _canProvideToiltries     = canProvideToiletries;
        _canProvideMilk          = canProvideMilk;
        _canProvideMedicine      = canProvideMedicine;
        _matHelpDetail           = matHelpDetail;

    }


    public JSONObject serializeData(){

        try {
            JSONObject data = new JSONObject();

            data.put( _foodKey         , _canProvideFood          );
            data.put( _babyFoodKey     , _canProvideBabyFood      );
            data.put( _clothesKey      , _canProvideClothes       );
            data.put( _femaleClothesKey, _canProvideFemaleClothes );
            data.put( _maleClothesKey  , _canProvideMaleClothes   );
            data.put( _waterKey        , _canProvideWater         );
            data.put( _toiletriessKey  , _canProvideToiltries     );
            data.put( _milkKey         , _canProvideMilk          );
            data.put( _medicineKey     , _canProvideMedicine      );

            data.put( _detailKey       , _matHelpDetail           );

            data.put( _emailPhoneKey   , _emailPhone              );

            return data;

        }
        catch( JSONException jse ){
            return null;
        }


    }

}
