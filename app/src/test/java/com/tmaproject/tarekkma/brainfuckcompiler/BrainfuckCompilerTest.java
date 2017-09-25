package com.tmaproject.tarekkma.brainfuckcompiler;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tarekkma on 9/25/17.
 */
public class BrainfuckCompilerTest {
  BrainfuckCompiler compiler = new BrainfuckCompiler();

  @Test
  public void test_HelloWorld(){
    compiler.compile("++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.");
    assertEquals("Hello World!\n",compiler.getResult());
  }
}