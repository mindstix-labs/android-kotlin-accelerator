package com.mindstix.core.sharedpref

import android.content.Context
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.mindstix.core.models.SharedPrefType

/**
 *
 *
 * Author by Nirav Patel,
 * on 14,December
 **/
class SharedPref(context: Context, type: SharedPrefType, private val fileName: String) {
    private val TAG = SharedPref::class.java.simpleName

    companion object {
        private const val FileNameEncrypted = "BaseLineEncrypted"
        private const val FileNameNormal = "BaseLineNormal"
        private var instance: SharedPref? = null
        private val STRING_DEFAULT_VALUE = null
        private const val INT_DEFAULT_VALUE = -999
        private const val BOOLEAN_DEFAULT_VALUE = false
        private const val FLOAT_DEFAULT_VALUE = -999.99f
        private const val LONG_DEFAULT_VALUE = -9999L

        @JvmStatic
        @Synchronized
        fun getInstanceOfSharePref(
            context: Context,
            type: SharedPrefType,
            fileName: String?,
        ): SharedPref {
            val preferFileName = if (fileName.isNullOrEmpty()) {
                if (type == SharedPrefType.NormalSharedPref) {
                    FileNameNormal
                } else {
                    FileNameEncrypted
                }
            } else {
                fileName
            }
            if (instance == null) {
                instance = SharedPref(context, type, preferFileName)
            }
            return requireNotNull(instance)
        }
    }

    private val sharedPreferencesObject = if (type == SharedPrefType.SecuredSharedPref) {
        // Initialize/open an instance of EncryptedSharedPreferences on below line.
        EncryptedSharedPreferences.create(
            context,
            fileName,
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    } else {
        context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }

    /**
     * Function to save String data in specified shared preferences file.
     *
     * @param fileName - Name of the shared preferences file.
     * @param keyName - Name of the key to be stored in shared preferences file.
     * @param value - String value to be stored against the keyName.
     */
    fun saveStringInPrefs(keyName: String, value: String) {
        Log.v(TAG, "saveStringInPrefs() Inside saveStringInPrefs")

        // If shared preferences key or value is not provided, return
        if (keyName.isEmpty() || value.isEmpty()) {
            Log.d(
                TAG,
                "saveStringInPrefs() Key or value to be stored in shared preferences is empty",
            )
            return
        }

        // Get reference to shared preferences file
//        val sharedPreferencesObject: SharedPreferences? = getSharedPrefsFile(fileName)

        // If shared preferences file reference is not found, return
        if (sharedPreferencesObject == null) {
            Log.d(TAG, "saveStringInPrefs() Shared preferences file not found - $fileName")
            return
        }

        // Save String data in shared preferences file
        with(sharedPreferencesObject.edit()) {
            putString(keyName, value)
            commit()
        }
    }

    /**
     * Function to save Integer data in specified shared preferences file.
     *
     * @param fileName - Name of the shared preferences file
     * @param keyName - Name of the key to be stored in shared preferences file
     * @param value - Int value to be stored against the keyName
     */
    fun saveIntInPrefs(keyName: String, value: Int) {
        Log.v(TAG, "saveIntInPrefs() Inside saveIntInPrefs")

        // If shared preferences key is not provided, return
        if (keyName.isEmpty()) {
            Log.d(TAG, "saveIntInPrefs() Key to be stored in shared preferences is empty")
            return
        }

        // If shared preferences file reference is not found, return
        if (sharedPreferencesObject == null) {
            Log.d(TAG, "saveIntInPrefs() Shared preferences file not found - $fileName")
            return
        }

        // Save Int data in shared preferences file
        with(sharedPreferencesObject.edit()) {
            putInt(keyName, value)
            commit()
        }
    }

    /**
     * Function to save Boolean data in specified shared preferences file.
     *
     * @param fileName - Name of the shared preferences file
     * @param keyName - Name of the key to be stored in shared preferences file
     * @param value - Boolean value to be stored against the keyName
     */
    fun saveBooleanInPrefs(keyName: String, value: Boolean) {
        Log.v(TAG, "saveBooleanInPrefs() Inside saveBooleanInPrefs")

        // If shared preferences key is not provided, return
        if (keyName.isEmpty()) {
            Log.d(TAG, "saveBooleanInPrefs() Key to be stored in shared preferences is empty")
            return
        }

        // If shared preferences file reference is not found, return
        if (sharedPreferencesObject == null) {
            Log.d(TAG, "saveBooleanInPrefs() Shared preferences file not found - $fileName")
            return
        }

        // Save Boolean data in shared preferences file
        with(sharedPreferencesObject.edit()) {
            putBoolean(keyName, value)
            commit()
        }
    }

    /**
     * Function to save Float data in specified shared preferences file.
     *
     * @param fileName - Name of the shared preferences file
     * @param keyName - Name of the key to be stored in shared preferences file
     * @param value - Float value to be stored against the keyName
     */
    fun saveFloatInPrefs(keyName: String, value: Float) {
        Log.v(TAG, "saveFloatInPrefs() Inside saveFloatInPrefs")

        // If shared preferences key is not provided, return
        if (keyName.isEmpty()) {
            Log.d(TAG, "saveFloatInPrefs() Key to be stored in shared preferences is empty")
            return
        }

        // If shared preferences file reference is not found, return
        if (sharedPreferencesObject == null) {
            Log.d(TAG, "saveFloatInPrefs() Shared preferences file not found - $fileName")
            return
        }

        // Save Float data in shared preferences file
        with(sharedPreferencesObject.edit()) {
            putFloat(keyName, value)
            commit()
        }
    }

    /**
     * Function to save Long data in specified shared preferences file.
     *
     * @param fileName - Name of the shared preferences file
     * @param keyName - Name of the key to be stored in shared preferences file
     * @param value - Long value to be stored against the keyName
     */
    fun saveLongInPrefs(keyName: String, value: Long) {
        Log.v(TAG, "saveLongInPrefs() Inside saveLongInPrefs")

        // If shared preferences key is not provided, return
        if (keyName.isEmpty()) {
            Log.d(TAG, "saveLongInPrefs() Key to be stored in shared preferences is empty")
            return
        }

        // If shared preferences file reference is not found, return
        if (sharedPreferencesObject == null) {
            Log.d(TAG, "saveLongInPrefs() Shared preferences file not found - $fileName")
            return
        }

        // Save Float data in shared preferences file
        with(sharedPreferencesObject.edit()) {
            putLong(keyName, value)
            commit()
        }
    }

    /**
     * Function to retrieve String data from the specified shared preferences file.
     *
     * @param fileName - Name of the shared preferences file
     * @param keyName - Name of the key to be retrieved from the shared preferences file
     * @return value - String value to be returned for the keyName
     */
    fun readStringFromPrefs(keyName: String): String? {
        Log.v(TAG, "readStringFromPrefs() Inside readStringFromPrefs")

        // If shared preferences key is not provided, return
        if (keyName.isEmpty()) {
            Log.d(TAG, "readStringFromPrefs() Key to be retrieved from shared preferences is empty")
            return null
        }

        // If shared preferences file reference is not found, return
        if (sharedPreferencesObject == null) {
            Log.d(TAG, "readStringFromPrefs() Shared preferences file not found - $fileName")
            return null
        }

        // Return String data from the shared preferences file
        return sharedPreferencesObject.getString(keyName, STRING_DEFAULT_VALUE)
    }

    /**
     * Function to retrieve Int data from the specified shared preferences file.
     *
     * @param fileName - Name of the shared preferences file
     * @param keyName - Name of the key to be retrieved from the shared preferences file
     * @return value - Int value to be returned for the keyName
     */
    fun readIntFromPrefs(keyName: String): Int {
        Log.v(TAG, "readIntFromPrefs() Inside readIntFromPrefs")

        // If shared preferences key is not provided, return
        if (keyName.isEmpty()) {
            Log.d(TAG, "readIntFromPrefs() Key to be retrieved from shared preferences is empty")
            return INT_DEFAULT_VALUE
        }

        // If shared preferences file reference is not found, return
        if (sharedPreferencesObject == null) {
            Log.d(TAG, "readIntFromPrefs() Shared preferences file not found - $fileName")
            return INT_DEFAULT_VALUE
        }

        // Return Int data from the shared preferences file
        return sharedPreferencesObject.getInt(keyName, INT_DEFAULT_VALUE)
    }

    /**
     * Function to retrieve Boolean data from the specified shared preferences file.
     *
     * @param fileName - Name of the shared preferences file
     * @param keyName - Name of the key to be retrieved from the shared preferences file
     * @return value - Boolean value to be returned for the keyName
     */
    fun readBooleanFromPrefs(

        keyName: String,
        defaultValue: Boolean = BOOLEAN_DEFAULT_VALUE,
    ): Boolean {
        Log.v(TAG, "readBooleanFromPrefs() Inside readBooleanFromPrefs")

        // If shared preferences key is not provided, return
        if (keyName.isEmpty()) {
            Log.d(
                TAG,
                "readBooleanFromPrefs() Key to be retrieved from shared preferences is empty",
            )
            return defaultValue
        }

        // If shared preferences file reference is not found, return
        if (sharedPreferencesObject == null) {
            Log.d(TAG, "readBooleanFromPrefs() Shared preferences file not found - $fileName")
            return defaultValue
        }

        // Return Boolean data from the shared preferences file
        return sharedPreferencesObject.getBoolean(keyName, defaultValue)
    }

    /**
     * Function to retrieve Float data from the specified shared preferences file.
     *
     * @param fileName - Name of the shared preferences file
     * @param keyName - Name of the key to be retrieved from the shared preferences file
     * @return value - Float value to be returned for the keyName
     */
    fun readFloatFromPrefs(keyName: String): Float {
        Log.v(TAG, "readFloatFromPrefs() Inside readFloatFromPrefs")

        // If shared preferences key is not provided, return
        if (keyName.isEmpty()) {
            Log.d(TAG, "readFloatFromPrefs() Key to be retrieved from shared preferences is empty")
            return FLOAT_DEFAULT_VALUE
        }

        // If shared preferences file reference is not found, return
        if (sharedPreferencesObject == null) {
            Log.d(TAG, "readFloatFromPrefs() Shared preferences file not found - $fileName")
            return FLOAT_DEFAULT_VALUE
        }

        // Return Float data from the shared preferences file
        return sharedPreferencesObject.getFloat(keyName, FLOAT_DEFAULT_VALUE)
    }

    /**
     * Function to retrieve Long data from the specified shared preferences file.
     *
     * @param fileName - Name of the shared preferences file
     * @param keyName - Name of the key to be retrieved from the shared preferences file
     * @return value - Long value to be returned for the keyName
     */
    fun readLongFromPrefs(keyName: String): Long {
        Log.v(TAG, "readLongFromPrefs() Inside readLongFromPrefs")

        // If shared preferences key is not provided, return
        if (keyName.isEmpty()) {
            Log.d(TAG, "readLongFromPrefs() Key to be retrieved from shared preferences is empty")
            return LONG_DEFAULT_VALUE
        }

        // If shared preferences file reference is not found, return
        if (sharedPreferencesObject == null) {
            Log.d(TAG, "readLongFromPrefs() Shared preferences file not found - $fileName")
            return LONG_DEFAULT_VALUE
        }

        // Return Long data from the shared preferences file
        return sharedPreferencesObject.getLong(keyName, LONG_DEFAULT_VALUE)
    }

    /**
     * Function to remove data from the specified shared preferences file.
     *
     * @param fileName - Name of the shared preferences file
     * @param keyName - Name of the key to be retrieved from the shared preferences file
     */
    fun removeFromPrefs(keyName: String) {
        Log.v(TAG, "removeFromPrefs() Inside removeFromPrefs")

        // If shared preferences key is not provided, return
        if (keyName.isEmpty()) {
            Log.d(TAG, "removeFromPrefs() Key to be removed from shared preferences is empty")
            return
        }

        // If shared preferences file reference is not found, return
        if (sharedPreferencesObject == null) {
            Log.d(TAG, "removeFromPrefs() Shared preferences file not found - $fileName")
            return
        }

        // Remove data from the shared preferences file
        with(sharedPreferencesObject.edit()) {
            remove(keyName)
            commit()
        }
    }

    /**
     * Function to clear entire data from the specified shared preferences file.
     *
     * @param fileName - Name of the shared preferences file
     */
    fun clearAll() {
        Log.v(TAG, "removeFromPrefs() Inside removeFromPrefs")

        // If shared preferences file reference is not found, return
        if (sharedPreferencesObject == null) {
            Log.d(TAG, "removeFromPrefs() Shared preferences file not found - $fileName")
            return
        }

        // Remove data from the shared preferences file
        with(sharedPreferencesObject.edit()) {
            clear()
            commit()
        }
    }
}
