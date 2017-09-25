package com.tmaproject.tarekkma.brainfuckcompiler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  TextView resultTV;
  EditText codeET;
  Button runBTN;
  ViewGroup inoutLayout;
  Button inputSubmitBTN;
  EditText inputeET;
  BrainfuckCompiler compiler;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    resultTV = (TextView) findViewById(R.id.result_view);
    codeET = (EditText) findViewById(R.id.code_view);
    runBTN = (Button) findViewById(R.id.run_button);
    inputeET = (EditText) findViewById(R.id.input);
    inoutLayout = (ViewGroup) findViewById(R.id.input_layout);
    inputSubmitBTN = (Button) findViewById(R.id.input_submit);
  }

  public void runButtonClikced(View view) {
    resultTV.setText("");
    compiler = new BrainfuckCompiler(codeET.getText().toString(), new BrainfuckCompiler.InterpreterEventsListener() {
      @Override public void onOutput(char output) {
        resultTV.append(output+"");
      }

      @Override public void onRequestInput() {
        inoutLayout.setVisibility(View.VISIBLE);
      }

      @Override public void onFinished() {
        Toast.makeText(MainActivity.this, "DONE", Toast.LENGTH_SHORT).show();
        resultTV.setText(compiler.getResult());
      }
    });
    compiler.compile();
  }

  public void onInputSubmit(View view){
    String s = inputeET.getText().toString();
    if(s.length() < 1){
      Toast.makeText(this, "Check Your Input", Toast.LENGTH_SHORT).show();
      return;
    }
    compiler.input(s.charAt(0));
    inputeET.setText("");
    inoutLayout.setVisibility(View.GONE);
    compiler.resume();
  }
}


