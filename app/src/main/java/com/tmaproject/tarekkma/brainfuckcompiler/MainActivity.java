package com.tmaproject.tarekkma.brainfuckcompiler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  TextView resultTV;
  EditText codeET;
  Button runBTN;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    resultTV = (TextView) findViewById(R.id.result_view);
    codeET = (EditText) findViewById(R.id.code_view);
    runBTN = (Button) findViewById(R.id.run_button);
  }

  public void runButtonClikced(View view) {
    BrainfuckCompiler compiler = new BrainfuckCompiler();
    compiler.compile(codeET.getText().toString());
    resultTV.setText(compiler.getResult());
  }
}


