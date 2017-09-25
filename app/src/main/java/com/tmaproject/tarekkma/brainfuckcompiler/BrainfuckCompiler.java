package com.tmaproject.tarekkma.brainfuckcompiler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarekkma on 9/25/17.
 */

public class BrainfuckCompiler {

  public interface InterpreterEventsListener {
    void onOutput(char output);
    void onRequestInput();
    void onFinished();
  }

  private List<Integer> memory = new ArrayList<>();
  private char[] tokens;
  private String result = "";
  private int curruntMemPos;
  private int savedPos;
  private String code;
  private InterpreterEventsListener listener;

  public BrainfuckCompiler(String code, InterpreterEventsListener listener) {
    this.code = code;
    this.listener = listener;
  }

  public void compile(){
    result = "";
    memory = new ArrayList<>();
    curruntMemPos = 0;
    tokens = code.toCharArray();
    compile(0);
  }

  public void resume(){
    compile(savedPos);
  }

  public void input(char a){
    setMemSolt(curruntMemPos,a);
  }

  private void compile(int pos){
    for (int i = pos;i<tokens.length;i++) {
      switch (tokens[i]){
        case '+':
          incMem(curruntMemPos);
          break;
        case '-':
          decMem(curruntMemPos);
          break;
        case '>':
          curruntMemPos++;
          break;
        case '<':
          curruntMemPos--;
          break;
        case '.':
          printMem(curruntMemPos);
          break;
        case '[':
          if(memory.get(curruntMemPos)==0)
            i = findClosingLoop(i);
          break;
        case ']':
          if(memory.get(curruntMemPos)!=0)
            i = findOpeningLoop(i);
          break;
        case ',':
          savedPos = i+1;
          listener.onRequestInput();
          return;
      }
    }
    listener.onFinished();
  }


  private void addMemSolts(int pos){
    while(pos >= memory.size())
      memory.add(0);
  }

  private void setMemSolt(int pos,int val){
    addMemSolts(pos);
    memory.set(pos,val);
  }

  private void incMem(int pos){
    addMemSolts(pos);
    memory.set(pos,memory.get(pos)+1);
  }

  private void decMem(int pos){
    addMemSolts(pos);
    memory.set(pos,memory.get(pos)-1);
  }

  private void printMem(int pos) {
    addMemSolts(pos);
    result += (char) memory.get(pos).intValue();
    listener.onOutput((char) memory.get(pos).intValue());
  }

  private int findClosingLoop(int pos){
    pos++;
    int skips = 0;
    char curruntToken;
    while (true){
      curruntToken = tokens[pos];
      if(curruntToken=='[')skips++;
      else if(curruntToken==']' && skips != 0)skips--;
      else if(curruntToken==']')break;
      pos++;
    }
    return pos;
  }

  private int findOpeningLoop(int pos){
    pos--;
    int skips = 0;
    char curruntToken;
    while (true){
      curruntToken = tokens[pos];
      if(curruntToken==']')skips++;
      else if(curruntToken=='[' && skips != 0)skips--;
      else if(curruntToken=='[')break;
      pos--;
    }
    return pos;
  }

  public String getResult() {
    return result;
  }
}
