package com.revo.settings.fragments;

import com.android.internal.logging.nano.MetricsProto;

import android.os.Bundle;
import com.android.settings.R;
import android.provider.Settings;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v14.preference.SwitchPreference;


import com.android.settings.SettingsPreferenceFragment;

public class NotificationSettings extends SettingsPreferenceFragment 
    implements Preference.OnPreferenceChangeListener {

private static final String FORCE_EXPANDED_NOTIFICATIONS = "force_expanded_notifications";

private SwitchPreference mForceExpanded;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        addPreferencesFromResource(R.xml.revo_settings_notifications);

	    mForceExpanded = (SwitchPreference) findPreference(FORCE_EXPANDED_NOTIFICATIONS);
        mForceExpanded.setChecked((Settings.System.getInt(getContentResolver(),
		        Settings.System.FORCE_EXPANDED_NOTIFICATIONS, 0) == 1));
    }

     @Override
     public boolean onPreferenceChange(Preference preference, Object objValue) {
        if (preference == mForceExpanded) {
            boolean checked = ((SwitchPreference)preference).isChecked();
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.FORCE_EXPANDED_NOTIFICATIONS, checked ? 1:0);
            return true;
        }
        return false;
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.REVO_SETTINGS;
    }
}
