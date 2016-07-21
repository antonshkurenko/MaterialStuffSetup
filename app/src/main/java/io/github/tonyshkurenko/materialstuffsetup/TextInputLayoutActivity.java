package io.github.tonyshkurenko.materialstuffsetup;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;

public class TextInputLayoutActivity extends AppCompatActivity {

  @SuppressWarnings("ConstantConditions") @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_text_input_layout);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    final TextInputLayout errorTextInputLayout =
        (TextInputLayout) findViewById(R.id.error_text_input_layout);

    errorTextInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override public void onTextChanged(CharSequence text, int start, int count, int after) {
        if (text.length() > 0 && text.length() <= 4) {
          errorTextInputLayout.setError("Example error");
        } else {
          errorTextInputLayout.setError(null);
        }
      }

      @Override public void afterTextChanged(Editable editable) {

      }
    });

    final TextInputLayout coolTextInputLayout =
        (TextInputLayout) findViewById(R.id.cool_text_input_layout);

    coolTextInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override public void onTextChanged(CharSequence text, int start, int count, int after) {
        if (text.length() > 0 && text.length() <= 4) {
          coolTextInputLayout.setError("Example error");
        } else {
          coolTextInputLayout.setError(null);
        }
      }

      @Override public void afterTextChanged(Editable editable) {

      }
    });
  }
}
