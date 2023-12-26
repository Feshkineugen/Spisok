package com.example.spisok;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditTaskActivity extends AppCompatActivity {

    private EditText taskDescriptionEditText;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private int position;

    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_task);

    // Инициализация элементов пользовательского интерфейса
    taskDescriptionEditText = findViewById(R.id.taskDescriptionEditText);
    datePicker = findViewById(R.id.datePicker);
    timePicker = findViewById(R.id.timePicker);

    // Получение выбранной задачи и позиции из предыдущей активности
    String selectedTask = getIntent().getStringExtra("selectedTask");
    position = getIntent().getIntExtra("position", -1);

    // Установка текста описания задачи
    taskDescriptionEditText.setText(selectedTask);

    // Нахождение и инициализация кнопки сохранения
    Button saveButton = findViewById(R.id.saveButton);
    // Установка слушателя нажатия на кнопку сохранения
    saveButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            saveTask();
        }
    });
}

  private void saveTask() {
    // Получение обновленного описания задачи из текстового поля
    String updatedTaskDescription = taskDescriptionEditText.getText().toString();

    // Получение выбранной пользователем даты из элемента DatePicker
    int year = datePicker.getYear();
    int month = datePicker.getMonth();
    int dayOfMonth = datePicker.getDayOfMonth();

    // Получение выбранного пользователем времени из элемента TimePicker
    int hour = timePicker.getHour();
    int minute = timePicker.getMinute();

    // Создание экземпляра Calendar и установка выбранной пользователем даты и времени
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month, dayOfMonth, hour, minute);
    // Дальнейшие действия с полученным объектом Calendar


        // Форматирование даты для получения дня недели
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        String dayOfWeek = dayFormat.format(calendar.getTime());

        // Форматирование времени
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String time = timeFormat.format(calendar.getTime());

        // Составление описания задачи с днем недели
        String updatedTaskWithDay = "Задача: " + updatedTaskDescription + " (" + dayOfWeek + ") - " + time;

        Intent resultIntent = new Intent();
// Устанавливаем обновленную задачу в качестве результата
resultIntent.putExtra("updatedTask", updatedTaskWithDay);
// Передаем позицию элемента для обновления в качестве результата
resultIntent.putExtra("position", position);
// Устанавливаем результат операции как "RESULT_OK" и передаем resultIntent
setResult(RESULT_OK, resultIntent);
// Завершаем текущую активность
finish();
    }
}
