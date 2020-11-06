package cn.krisez.behavior

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Toast
import androidx.preference.*

class MySettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting_pre, rootKey)
        val edit = findPreference<EditTextPreference>("edit_data")
        edit?.summaryProvider = Preference.SummaryProvider<EditTextPreference> { preference ->
            preference.text
        }
        edit?.setOnBindEditTextListener {
            it.inputType = InputType.TYPE_CLASS_NUMBER
        }
        val feedback = findPreference<Preference>("feedback")
        //对某个进行点击事件 true代表已经处理了，就intent设置无效，false会触发intent
        feedback?.setOnPreferenceClickListener {
            Toast.makeText(context, "feedback", Toast.LENGTH_SHORT).show()
            false
        }
        //发送一个intent事件
        feedback?.intent = Intent(context, SettingActivity::class.java)

        //全局检索对应的值
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context /* Activity context */)
        val name = sharedPreferences.getString("edit_data", "")
        Log.d("MySettingsFragment.kt", "onCreatePreferences: $name")

        val again = findPreference<EditTextPreference>("edit_data_again")
        again?.preferenceDataStore = DataStore()
        again?.summaryProvider = Preference.SummaryProvider<EditTextPreference> { preference ->
            preference.text
        }
    }

    /**
     * 以下是将某个preference替换成自定义（传递数据至服务器）
     */
    class DataStore : PreferenceDataStore() {
        override fun putString(key: String, value: String?) {
            // Save the value somewhere
            Log.d("DataStore", "putString: 存储数据至服务器")
        }

        override fun getString(key: String, defValue: String?): String? {
            // Retrieve the value
            Log.d("DataStore", "getString: 服务器返回数据")
            return "service back"
        }
    }
}