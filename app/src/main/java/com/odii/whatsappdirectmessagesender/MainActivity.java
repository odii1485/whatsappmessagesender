package com.odii.whatsappdirectmessagesender;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    Button  start;
    CountryCodePicker ccp;
    EditText editText,messtext;
    RadioGroup radioGroup;
    int chosen=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        ccp = findViewById(R.id.countryCodeHolder);
        editText = findViewById(R.id.phonenum);
        messtext = findViewById(R.id.messText);
        radioGroup = findViewById(R.id.radiok);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId ==  R.id.whatsapp)
            {
                chosen = 1;
            }
            else if(checkedId == R.id.whatsappb) {
                chosen = 2;
            }
            else if (checkedId == R.id.gwhatsapp)
            {
                chosen = 3;

            }
        });
        start.setOnClickListener(v -> {
            try {
                String mobile = ccp.getSelectedCountryCode() +  editText.getText();
                String msg = String.valueOf(messtext.getText());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + mobile + "&text=" + msg));
                switch (chosen)
                {
                    case 1:intent.setPackage("com.whatsapp");
                    break;
                    case 2:intent.setPackage("com.whatsapp.w4b");
                        break;
                    case 3:intent.setPackage("com.gbwhatsapp");
                        break;
                }
                startActivity(intent);
            }catch (Exception e){
                Toast.makeText(MainActivity.this, "WhatsApp not installed", Toast.LENGTH_SHORT).show();
            }

        });



    }

}