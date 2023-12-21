package com.example.spisok;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EditTaskActivity extends AppCompatActivity {

    private EditText taskDescriptionEditText;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        taskDescriptionEditText = findViewById(R.id.taskDescriptionEditText);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);

        String selectedTask = getIntent().getStringExtra("selectedTask");
        position = getIntent().getIntExtra("position", -1);

        taskDescriptionEditText.setText(selectedTask);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        String updatedTaskDescription = taskDescriptionEditText.getText().toString();

        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int dayOfMonth = datePicker.getDayOfMonth();

        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth, hour, minute);
        String dateTime = calendar.getTime().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("updatedTask", updatedTaskDescription);
        resultIntent.putExtra("position", position);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}