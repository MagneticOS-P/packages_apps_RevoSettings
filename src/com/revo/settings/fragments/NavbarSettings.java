/*
 * Copyright (C) 2014 TeamEos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.revo.settings.fragments;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.support.v7.preference.ListPreference;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.provider.Settings;

import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.nano.MetricsProto;
import com.android.settings.R;
import com.android.internal.util.custom.NavbarUtils;

public class NavbarSettings extends SettingsPreferenceFragment implements OnPreferenceChangeListener {

    private static final String KEYS_SHOW_NAVBAR_KEY = "navigation_bar_show";
    private SwitchPreference mEnableNavBar;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.revo_settings_navigation);

        final PreferenceScreen prefScreen = getPreferenceScreen();
        ContentResolver resolver = getContentResolver();

        mEnableNavBar = (SwitchPreference) prefScreen.findPreference(KEYS_SHOW_NAVBAR_KEY);

        boolean showNavBarDefault = NavbarUtils.hasNavbarByDefault(getActivity());

        boolean showNavBar = Settings.System.getInt(resolver,
                Settings.System.NAVIGATION_BAR_SHOW, showNavBarDefault ? 1 : 0) == 1;
        mEnableNavBar.setChecked(showNavBar);

    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference == mEnableNavBar) {
            boolean checked = ((SwitchPreference)preference).isChecked();
            Settings.System.putInt(getContentResolver(),
                    Settings.System.NAVIGATION_BAR_SHOW, checked ? 1:0);
            return true;
        }
        return super.onPreferenceTreeClick(preference);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        return false;
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.REVO_SETTINGS;
    }
}
