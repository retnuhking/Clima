package com.londonappbrewery.climapm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import static com.londonappbrewery.climapm.WeatherController.getCurrentCity;

public class SettingsController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        Intent myIntent = getIntent();
        Boolean currentToggleState = myIntent.getBooleanExtra("WindToggle", false);

        ImageButton backButton = findViewById(R.id.settingsBackButton);
        Switch windToggle = findViewById(R.id.windTS);
        final TextView mWindSpeedTV = findViewById(R.id.windSpeedTV);

        //Ensures that the toggle is in the state that reflects the current state
        //when the settings page is loaded
        if (currentToggleState) {
            windToggle.setChecked(true);
        } else {
            windToggle.setChecked(false);
        }

        final Intent toggleIntent = new Intent(SettingsController.this, WeatherController.class);

        windToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {
                    toggleIntent.putExtra("WindVis", true);
                } else {
                    toggleIntent.putExtra("WindVis", false);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentCity = getCurrentCity();
                toggleIntent.putExtra("City", currentCity);
                startActivity(toggleIntent);
            }
        });
    }
}
