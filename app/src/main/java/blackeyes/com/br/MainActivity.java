package blackeyes.com.br;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton fala;
    boolean falaCon = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fala = (ImageButton)findViewById(R.id.imageButton);

        fala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(falaCon){
                    fala.setImageResource(R.drawable.play);
                    falaCon = false;
                }else{
                    fala.setImageResource(R.drawable.stop);
                    falaCon = true;
                }

            }
        });


    }


}
