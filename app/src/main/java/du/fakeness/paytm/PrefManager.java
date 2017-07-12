package du.fakeness.paytm;

import android.util.Log;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by admin on 21-03-2017.
 */

public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "apl_pref";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    private static final String MERCHANT_NAME = "merchantName";

    private static final String KEY_IS_MER_NO = "isMerOrNo";

    private static final String KEY_AMOUNT = "amount";

    private static final String KEY_NAME="name";

    private static final String KEY_OTHER_MERCHANT="isOtherMerchant";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return
                pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setMerchant(String merchantName) {

        editor.putString(MERCHANT_NAME, merchantName);
        // commit changes
        editor.commit();

    }

    public String getMerchant(){
        return pref.getString(MERCHANT_NAME, "");
    }

    public void setMerNo(boolean isMerOrNo) {

        editor.putBoolean(KEY_IS_MER_NO, isMerOrNo);

        // commit changes
        editor.commit();

    }

    public boolean getMerNo(){
        return pref.getBoolean(KEY_IS_MER_NO, true);
    }


    public void setAmount(String amount) {

        editor.putString(KEY_AMOUNT, amount);

        // commit changes
        editor.commit();

    }

    public String getAmount(){
        return pref.getString(KEY_AMOUNT, "100");
    }
    public void setName(String name) {

        editor.putString(KEY_NAME, name);

        // commit changes
        editor.commit();

    }

    public String getName(){
        return pref.getString(KEY_NAME, "");
    }

    public void setOtherMerchant(boolean otherMerchanct) {

        editor.putBoolean(KEY_OTHER_MERCHANT, otherMerchanct);

        // commit changes
        editor.commit();

    }

    public boolean isOtherMerchnat(){
        return pref.getBoolean(KEY_OTHER_MERCHANT, true);
    }

}