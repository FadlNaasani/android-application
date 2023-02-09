package oslomet.no;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;



public class setting extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        // bytte mellom mellom setting layout og prefernse.xml
       getSupportFragmentManager().beginTransaction().replace(R.id.setting, new Prefranserfragemnt()).commit();


    }


}
