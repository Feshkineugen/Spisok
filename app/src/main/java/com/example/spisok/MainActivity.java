import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int EDIT_TASK_REQUEST_CODE = 1;
    private ArrayList<String> taskList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();
        taskList.add(" ");
        taskList.add("");
        taskList.add("");
        taskList.add(" ");
        taskList.add("");
        taskList.add(" ");
        taskList.add(" ");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedTask = taskList.get(position);
                Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
                intent.putExtra("selectedTask", selectedTask);
                intent.putExtra("position", position);
                startActivityForResult(intent, EDIT_TASK_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_TASK_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String updatedTask = data.getStringExtra("updatedTask");
            int position = data.getIntExtra("position", -1);
            if (position != -1 && updatedTask != null) {
                taskList.set(position, updatedTask);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
