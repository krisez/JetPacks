package cn.krisez.behavior

import android.view.View
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import cn.krisez.common.CommonBaseActivity

class SettingActivity : CommonBaseActivity(), PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
    override fun init() {
        setTitle("设置")
        addRightHelp("https://developer.android.google.cn/guide/topics/ui/settings", "")
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, MySettingsFragment(), "setting").commit()
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_setting, null)

    override fun onPreferenceStartFragment(caller: PreferenceFragmentCompat?, pref: Preference): Boolean {
        // Instantiate the new Fragment
        val args = pref.extras
        val fragment = supportFragmentManager.fragmentFactory.instantiate(
                classLoader,
                pref.fragment)
        fragment.arguments = args
        fragment.setTargetFragment(caller, 0)
        // Replace the existing Fragment with the new Fragment
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit()
        return true
    }

}
