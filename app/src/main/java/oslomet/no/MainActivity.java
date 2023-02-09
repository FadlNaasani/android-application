package oslomet.no;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    private SharedPreferences shareantall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shareantall= PreferenceManager.getDefaultSharedPreferences(this);


        ImageButton play = findViewById(R.id.play_imag);

        play.setOnClickListener(view -> {
            Intent k = new Intent(MainActivity.this,play.class);
            k.putExtra("valg",shareantall.getString("valg","først_valg"));
            startActivity(k);
        });
        ImageButton setting = findViewById(R.id.setting_img);
        setting.setOnClickListener(view -> {
            Intent d = new Intent(MainActivity.this,setting.class);
            startActivity(d);
        });
        ImageButton statistic = findViewById(R.id.static_imag);
        statistic.setOnClickListener(view -> {
            Intent m = new Intent(MainActivity.this,statistic.class);
            startActivity(m);
        });

    }

   @Override
   public void onBackPressed(){
       new AlertDialog.Builder(this)
               .setMessage("vil du ha slutte appen?")
               .setCancelable(false)
               .setPositiveButton("ja", (dialog, id) -> MainActivity.super.onBackPressed())
               .setNegativeButton("Nei", null)
               .show();
   }


}