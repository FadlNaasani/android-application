package oslomet.no;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class statistic extends AppCompatActivity {
    private LinearLayout history;
    private SharedPreferences shareantall;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistic);
        TextView totalrektig = findViewById(R.id.totalrektig);
        TextView totalefeil = findViewById(R.id.totalfeil);
        history = findViewById(R.id.histo);
        Button tomhistory = findViewById(R.id.tomhisto);
        Button playagein = findViewById(R.id.spill);
        Bundle b = this.getIntent().getExtras();
        try {
            String log = b.getString("Log");
            String[] totalefromplay = b.getStringArray("result");
            totalrektig.setText(totalefromplay[1]);
            totalefeil.setText(totalefromplay[0]);
            // get verdier fra log i temp
            String temp = getSharedPreferences("statistic", MODE_PRIVATE).getString("Log", "");
            //lagre i log-file spørsmål og svaret
            getSharedPreferences("statistic", MODE_PRIVATE).edit().putString("Log", temp + log).apply();
                    // seprat text in log og set in i array histolog
                    String[] histolog = log.split(";");
            for (String s : histolog) {
                TextView Tb = new TextView(getApplicationContext());
                Tb.setText(s);
                //vise verdier i view til history
                history.addView(Tb);
            }
        } catch (Exception e) {

            String log = getSharedPreferences("statistic", MODE_PRIVATE).getString("Log", "");
            String feil = getSharedPreferences("statistic", MODE_PRIVATE).getString("feil", "0");
            String rektig = getSharedPreferences("statistic", MODE_PRIVATE).getString("rektig", "0");
            totalrektig.setText(rektig);
            totalefeil.setText(feil);
            Log.d("Test", log);
            String[] histolog = log.split(";");
            for (String s : histolog) {
                TextView Tb = new TextView(getApplicationContext());
                Tb.setText(s);
                history.addView(Tb);
            }
        }
        shareantall= PreferenceManager.getDefaultSharedPreferences(this);
            //spill på¨nytt
            playagein.setOnClickListener(view -> {

                Intent k = new Intent(statistic.this,play.class);
                k.putExtra("valg",shareantall.getString("valg","først_valg"));
                startActivity(k);
                finish();
            });
        // tom historikk
        tomhistory.setOnClickListener(view -> {
            getSharedPreferences("statistic", MODE_PRIVATE).edit().putString("Log", "").apply();
            history.removeAllViews();
            Log.d("tom",getSharedPreferences("statistic", MODE_PRIVATE).getString("Log","t"));
        });
        }
    }

