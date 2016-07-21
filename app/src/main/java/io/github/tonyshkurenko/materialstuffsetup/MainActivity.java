package io.github.tonyshkurenko.materialstuffsetup;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

  static final Demo[] DEMOS = new Demo[] {
      new Demo("Nav drawer activity", NavigationDrawerActivity.class),
      new Demo("Text input layout activity", TextInputLayoutActivity.class)
  };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final ListView listView = (ListView) findViewById(R.id.list_view);

    if (listView != null) {
      listView.setAdapter(
          new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, DEMOS));

      listView.setOnItemClickListener(this);
    }
  }

  @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    startActivity(new Intent(this, DEMOS[position].activity));
  }

  static class Demo {
    final String name;
    final Class<? extends Activity> activity;

    public Demo(String name, Class<? extends Activity> activity) {
      this.name = name;
      this.activity = activity;
    }

    @Override public String toString() {
      return name;
    }
  }
}
