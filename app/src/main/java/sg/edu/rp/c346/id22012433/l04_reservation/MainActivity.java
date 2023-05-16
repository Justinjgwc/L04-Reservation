package sg.edu.rp.c346.id22012433.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
TextView name;
TextView phone;
TextView pax;
RadioGroup rdp;
RadioButton smoke;
RadioButton Nsmoke;
DatePicker dp;
TimePicker tp;
Button submit;
Button reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.nameInput);
        phone = findViewById(R.id.phoneInput);
        pax = findViewById(R.id.paxInput);
        rdp = findViewById(R.id.rdp);
        smoke = findViewById(R.id.Smoke);
        Nsmoke = findViewById(R.id.Nsmoke);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        submit = findViewById(R.id.submit);
        reset = findViewById(R.id.Reset);

        int year = 2020;
        int month = Calendar.JUNE;
        int dayOfMonth = 1;
        dp.init(year, month, dayOfMonth, null);


        int hourOfDay = 19;
        int minute = 30;
        tp.setCurrentHour(hourOfDay);
        tp.setCurrentMinute(minute);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentDateTime = Calendar.getInstance();
                int selectedYear = dp.getYear();
                int selectedMonth = dp.getMonth();
                int selectedDay = dp.getDayOfMonth();
                int selectedHour = tp.getCurrentHour();
                int selectedMinute = tp.getCurrentMinute();
                Calendar selectedDateTime = Calendar.getInstance();
                selectedDateTime.set(selectedYear, selectedMonth, selectedDay, selectedHour, selectedMinute);



                if (name.getText().toString().isEmpty() ||
                        phone.getText().toString().isEmpty() ||
                        pax.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String seatingArea = "";
                int selectedRadioButtonId = rdp.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    seatingArea = selectedRadioButton.getText().toString();
                }
                int hour = tp.getCurrentHour();
                int minute = tp.getCurrentMinute();
                String amPm;
                if (hour >= 12) {
                    amPm = "PM";
                    if (hour > 12) {
                        hour -= 12;
                    }
                } else {
                    amPm = "AM";
                    if (hour == 0) {
                        hour = 12;
                    }
                }
                    String reservationDetails = "Your reservation is confirmed!\n"
                            + "Name: " + name.getText().toString() + "\n"
                            + "Phone: " + phone.getText().toString() + "\n"
                            + "Size of group: " + pax.getText().toString() + "\n"
                            + "Date for reservation: " + dp.getDayOfMonth() + "/" + (dp.getMonth() + 1) + "/" + dp.getYear() + "\n"
                            + "Time of reservation: " + hour + ":" + String.format("%02d",minute) + amPm + "\n"
                            + "Seating Area: " + seatingArea;

                    Toast.makeText(MainActivity.this, reservationDetails, Toast.LENGTH_LONG).show();

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                phone.setText("");
                pax.setText("");


                dp.updateDate(2020, Calendar.JUNE, 1);


                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
            }
        });
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (hourOfDay < 8 || hourOfDay > 20) {
                    tp.setCurrentHour(19);
                    tp.setCurrentMinute(30);
                    Toast.makeText(MainActivity.this, "Reservation time should be between 8 AM and 8:59 PM.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
