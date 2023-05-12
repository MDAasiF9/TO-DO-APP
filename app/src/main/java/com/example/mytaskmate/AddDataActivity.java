package com.example.mytaskmate;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import com.example.mytaskmate.databinding.ActivityAddDataBinding;
import com.google.android.material.textview.MaterialTextView;
import java.util.Calendar;
import java.util.Locale;

public class AddDataActivity extends AppCompatActivity {
    ActivityAddDataBinding binding;
    MaterialTextView editText;

    int year;
    int month;
    int day;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Time
        TextView timeTextView = findViewById(R.id.eTime);
        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }

            private void showTimePickerDialog () {
                //Get the current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // TimePickerDialog shown
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddDataActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Updated the textview with the selected time
                        String time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                        timeTextView.setText(time);

                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });

        // Date Time
        editText = findViewById(R.id.eDate);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddDataActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        editText.setText(selectedDate);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        String type = getIntent().getStringExtra("type");
        if (type.equals("update"))
        {
        setTitle("update");
        binding.title.setText(getIntent().getStringExtra("title"));
        binding.desc.setText(getIntent().getStringExtra("desc"));
        binding.eDate.setText(getIntent().getStringExtra("date"));
        int id = getIntent().getIntExtra("id", 0);
        binding.add.setText("update note");
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("title", binding.title.getText().toString());
                intent.putExtra("desc", binding.desc.getText().toString());
                intent.putExtra("date", binding.eDate.getText().toString());
                intent.putExtra("time", binding.eTime.getText().toString());
                intent.putExtra("id", id);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        }else {
            setTitle("Add Your Task");
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.title.getText().toString());
                    intent.putExtra("desc", binding.desc.getText().toString());
                    intent.putExtra("date", binding.eDate.getText().toString());
                    intent.putExtra("time", binding.eTime.getText().toString());

                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddDataActivity.this,MainActivity.class));
    }
}