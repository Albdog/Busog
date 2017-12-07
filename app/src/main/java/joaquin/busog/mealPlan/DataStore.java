package joaquin.busog.mealPlan;

import android.content.Context;
import android.content.SharedPreferences;

public class DataStore {

    private static final String PREFS_FILE = "com.doublel.sharedpreferencesapp.preferences";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Context mContext;

    public DataStore(Context context) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public void setString(String key, String budget) {
        mEditor.putString(key, budget);
        mEditor.apply();
    }

    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }
}
