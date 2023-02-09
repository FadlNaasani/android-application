package oslomet.no;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class play extends AppCompatActivity {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnslett;
    private Button btn0;
    private Button btnok;
    private TextView sporsmal;
    private TextView dittsvar;
    private TextView svaret;
    private TextView rektig;
    private TextView feil;
    private int rektig_teller=0;
    private int feil_teller=0;
    private int randomindex;
    private String[] arryaysvarer;
    private final String [] total=new String[2];
    String resultat="";
    private ProgressBar enprogressBar;
    private int ferdig;
    private int tellersporsmal =0;
    String[] arryaysporsmaler;
    String[] melding;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
        Resources ress = getResources();
        melding= ress.getStringArray(R.array.melding);
        setverdier();
        hentarrays();
       String valgantll=getIntent().getStringExtra("valg");
       if(valgantll.equals("først_valg")){
           ferdig=5;
       }
       else if(valgantll.equals("andre_valg")){
           ferdig=10;
           Log.d("MM",valgantll);
       }
       else {
           ferdig=15;
       }

        Log.d("antallsporsmal",ferdig+"");
        btn1.setOnClickListener(view -> dittsvar.setText(dittsvar.getText().toString() + "1"));
        btn2.setOnClickListener(view -> dittsvar.setText(dittsvar.getText().toString() + "2"));
        btn3.setOnClickListener(view -> dittsvar.setText(dittsvar.getText().toString() + "3"));
        btn4.setOnClickListener(view -> dittsvar.setText(dittsvar.getText().toString() + "4"));
        btn5.setOnClickListener(view -> dittsvar.setText(dittsvar.getText().toString() + "5"));
        btn6.setOnClickListener(view -> dittsvar.setText(dittsvar.getText().toString() + "6"));
        btn7.setOnClickListener(view -> dittsvar.setText(dittsvar.getText().toString() + "7"));
        btn8.setOnClickListener(view -> dittsvar.setText(dittsvar.getText().toString() + "8"));
        btn9.setOnClickListener(view -> dittsvar.setText(dittsvar.getText().toString() + "9"));
        btn0.setOnClickListener(view -> dittsvar.setText(dittsvar.getText().toString() + "0"));

        btnslett.setOnClickListener(view -> {
            if (dittsvar.getText().length() > 0) {
                CharSequence charnumber = dittsvar.getText().toString();
                dittsvar.setText(charnumber.subSequence(0, charnumber.length() - 1));
            } else {
                Toast.makeText(play.this, melding[0], Toast.LENGTH_SHORT).show();
            }
        });

        btnok.setOnClickListener(view -> {
            if (dittsvar.getText().length()>0) {
                resultat+=arryaysporsmaler[randomindex]+"=";
                        if (arryaysvarer[randomindex].compareTo((dittsvar.getText().toString()))==0) {
                            resultat+=dittsvar.getText().toString()+"✔;";

                            rektig.setText(rektig_teller+1+"");
                           svaret.setText(arryaysvarer[randomindex]);
                            Toast.makeText(play.this, melding[1], Toast.LENGTH_SHORT).show();
                            rektig_teller++;

                        }
                        else{
                            feil.setText(feil_teller+1+"");
                            resultat+=dittsvar.getText().toString()+"❌;";//windos+.
                            svaret.setText(arryaysvarer[randomindex]);
                            Toast.makeText(play.this, melding[2], Toast.LENGTH_SHORT).show();
                            feil_teller++;
                        }
                        hentarrays();
                       tellersporsmal++;
                        dittsvar.setText("");
                        double prosent= (((double) tellersporsmal)/10)*100;
                Log.d("nytt",prosent+"");
                        enprogressBar.setProgress((int)prosent);
                    }
                    else {
                        Toast.makeText(play.this, melding[0], Toast.LENGTH_SHORT).show();
                    }
            if(ferdig== tellersporsmal) {

                total[0]=feil_teller+"";
                total[1]=rektig_teller+"";
                Bundle b=new Bundle();
                b.putStringArray("result", total);
                b.putString("Log", resultat);
                Intent i=new Intent(play.this, statistic.class);
                i.putExtras(b);
                startActivity(i);
                finish();
            }
                }

        );


    }

    private void setverdier(){
        btn1= findViewById(R.id.btn1);
        btn2= findViewById(R.id.btn2);
        btn3= findViewById(R.id.btn3);
        btn4= findViewById(R.id.btn4);
        btn5= findViewById(R.id.btn5);
        btn6= findViewById(R.id.btn6);
        btn7= findViewById(R.id.btn7);
        btn8= findViewById(R.id.btn8);
        btn9= findViewById(R.id.btn9);
        btnslett= findViewById(R.id.buttonslett);
        btn0= findViewById(R.id.btn0);
        btnok= findViewById(R.id.buttonok);
        sporsmal = findViewById(R.id.spørsmål);
        dittsvar= findViewById(R.id.input_verdi);
        svaret= findViewById(R.id.svar);
        rektig=findViewById(R.id.rektig);
        feil=findViewById(R.id.feil);
        enprogressBar=findViewById(R.id.progressBar2);

        //enprogressBar=findViewById(R.id.progressBar2);
    }
    @SuppressLint("SetTextI18n")
    private void hentarrays(){
        // hent spørsmåler
        Resources res = getResources();
        arryaysporsmaler = res.getStringArray(R.array.sporsmaler);
        randomindex = new Random().nextInt(arryaysporsmaler.length);

        sporsmal.setText(arryaysporsmaler[randomindex]+"=?");
        //hent svarer
        Resources res1 = getResources();
        arryaysvarer = res1.getStringArray(R.array.svarer);
    }
   @Override
   public void onBackPressed(){
       new AlertDialog.Builder(this)
               .setMessage(melding[3])
               .setCancelable(false)
               .setPositiveButton(melding[4], (dialog, id) -> play.super.onBackPressed())
               .setNegativeButton(melding[5], null)
               .show();
   }
}

