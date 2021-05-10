package sg.edu.rp.c346.id19029863.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText pax;
    EditText phone;
    DatePicker dp;
    TimePicker tp;
    Button btnConfirm;
    Button btnReset;
    CheckBox smoking;
    RadioGroup rgGender;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        pax = findViewById(R.id.pax);
        phone = findViewById(R.id.phone);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        btnConfirm = findViewById(R.id.confirm);
        btnReset = findViewById(R.id.reset);
        smoking = findViewById(R.id.smoking);
        rgGender = findViewById(R.id.rgGender);
        display = findViewById(R.id.display);


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().length()!= 0 && pax.getText().toString().trim().length()!= 0 &&
                        phone.getText().toString().trim().length()!= 0){
                    int checkedRadioId = rgGender.getCheckedRadioButtonId();
                    int intResponse = dp.getDayOfMonth();
                    int intResponse2 = dp.getMonth() + 1;
                    int intResponse3 = dp.getYear();
                    String stringResponse = tp.getCurrentHour().toString();
                    String stringResponse2 = tp.getCurrentMinute().toString();
                    if (smoking.isChecked()){
                        if (checkedRadioId == R.id.radioButtonMale) {
                            display.setText("Mr " + name.getText() + ", phone number " + phone.getText() + " ,has reserved " + pax.getText() + " person  in smoking area on " +
                                    intResponse+ "/" + intResponse2 + "/" + intResponse3 + " " + stringResponse + ":" + stringResponse2);
                        }else {
                            display.setText("Ms " + name.getText() + ", phone number " + phone.getText() + " ,has reserved " + pax.getText() + " person table in smoking area on " +
                                    intResponse + "/" + intResponse2 + "/" + intResponse3 + " " + stringResponse + ":" + stringResponse2);
                        }
                    }else{
                        if (checkedRadioId == R.id.radioButtonMale) {
                            display.setText("Mr " + name.getText() + ", phone number " + phone.getText() + " ,has reserved " + pax.getText() + " person table in Non smoking area on " +
                                    intResponse+ "/" + intResponse2 + "/" + intResponse3 + " " + stringResponse + ":" + stringResponse2);
                        }else {
                            display.setText("Ms " + name.getText() + ", phone number " + phone.getText() + " ,has reserved " + pax.getText() + " person table in Non smoking area on " +
                                    intResponse + "/" + intResponse2 + "/" + intResponse3 + " " + stringResponse + ":" + stringResponse2);
                        }

                    }
                    String stringSize = pax.getText().toString();
                    int iSize = Integer.parseInt(stringSize);
                    if (iSize > 5){
                        Toast.makeText(MainActivity.this, "Max 5 allowed", Toast.LENGTH_SHORT).show();
                        pax.setText("");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                phone.setText("");
                pax.setText("");
                smoking.setChecked(false);
                dp.updateDate(2020,5,1);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);

            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay < 8) {
                    tp.setCurrentHour(8);
                    tp.setCurrentMinute(0);
                }
                else if(hourOfDay > 20) {
                    tp.setCurrentHour(20);
                    tp.setCurrentMinute(59);
                }
            }
        });


    }
}