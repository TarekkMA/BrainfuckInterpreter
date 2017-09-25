package com.tmaproject.tarekkma.brainfuckcompiler;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tarekkma on 9/25/17.
 */
public class BrainfuckCompilerTest {
  BrainfuckCompiler compiler;

  @Test
  public void test_HelloWorld(){
    compiler = new BrainfuckCompiler(
        "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.",
        new BrainfuckCompiler.InterpreterEventsListener() {
          @Override public void onOutput(char output) {

          }

          @Override public void onRequestInput() {

          }

          @Override public void onFinished() {
            assertEquals("Hello World!\n",compiler.getResult());
          }
        });
    compiler.compile();
  }
}