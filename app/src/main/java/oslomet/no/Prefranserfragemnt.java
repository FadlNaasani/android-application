package oslomet.no;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;


import java.util.Locale;
import java.util.Objects;

public class Prefranserfragemnt extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        return false;
    }

    public void settland(String landskode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        cf.setLocale(new Locale(landskode));
        res.updateConfiguration(cf, dm);
        Objects.requireNonNull(getActivity()).recreate();
        Log.d("land", landskode);
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        //binding with xml preferanses
        addPreferencesFromResource(R.xml.prefrences);
        // hente checkboxes i xml via id(key)
        CheckBoxPreference sprak = (CheckBoxPreference) findPreference("check1");
        CheckBoxPreference sprak2 = (CheckBoxPreference) findPreference("check2");
        CheckBoxPreference valgen = (CheckBoxPreference) findPreference("først_valg");
        CheckBoxPreference valgto = (CheckBoxPreference) findPreference("andre_valg");
        CheckBoxPreference valgtre = (CheckBoxPreference) findPreference("tredje_valg");
        SharedPreferences share = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getContext()));
        if (share.getString("sprak", "NO").equals("NO")) {
            sprak.setChecked(true);
            sprak2.setChecked(false);

        } else {
            sprak2.setChecked(true);
            sprak.setChecked(false);
        }
        sprak.setOnPreferenceChangeListener((preference, o) -> {
            //lagre "NO" i key "sprak" og og bytte språke til norsk
            share.edit().putString("sprak", "NO").apply();
            settland("no");
            Objects.requireNonNull(getActivity()).recreate();
            return true;
        });
        sprak2.setOnPreferenceChangeListener((preference, o) -> {
            //lagre "DE" i key "sprak" og og bytte språke til tysk
            share.edit().putString("sprak", "DE").apply();
            settland("de");
            Objects.requireNonNull(getActivity()).recreate();
            return true;
        });
        if (share.getString("valg", "først_valg").equals("først_valg")) {
            valgen.setChecked(true);
            valgto.setChecked(false);
            valgtre.setChecked(false);
        } else if (share.getString("valg", "andre_valg").equals("andre_valg")) {
            valgen.setChecked(false);
            valgto.setChecked(true);
            valgtre.setChecked(false);
        } else {
            valgen.setChecked(false);
            valgto.setChecked(false);
            valgtre.setChecked(true);
        }
        valgen.setOnPreferenceChangeListener((preference, o) -> {
            share.edit().putString("valg","først_valg").apply();
            valgen.setChecked(true);
            valgto.setChecked(false);
            valgtre.setChecked(false);
            return true;
        });
        valgto.setOnPreferenceChangeListener((preference, o) -> {
            share.edit().putString("valg","andre_valg").apply();
            valgen.setChecked(false);
            valgto.setChecked(true);
            valgtre.setChecked(false);
            return true;
        });
        valgtre.setOnPreferenceChangeListener((preference, o) -> {
            share.edit().putString("valg","tredje_valg").apply();
            valgen.setChecked(false);
            valgto.setChecked(false);
            valgtre.setChecked(true);
            return true;
        });
    }
}