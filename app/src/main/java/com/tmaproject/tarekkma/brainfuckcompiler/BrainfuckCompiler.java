package com.tmaproject.tarekkma.brainfuckcompiler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarekkma on 9/25/17.
 */

public class BrainfuckCompiler {
  private List<Integer> memory = new ArrayList<>();
  private char[] tokens;
  private String result = "";


  public void compile(String code){
    result = "";
    memory = new ArrayList<>();
    int curruntPos = 0;
    tokens = code.toCharArray();
    for (int i = 0;i<tokens.length;i++) {
      switch (tokens[i]){
        case '+':
          incMem(curruntPos);
          break;
        case '-':
          decMem(curruntPos);
          break;
        case '>':
          curruntPos++;
          break;
        case '<':
          curruntPos--;
          break;
        case '.':
          printMem(curruntPos);
          break;
        case '[':
          if(memory.get(curruntPos)==0)
            i = findClosingLoop(i);
          break;
        case ']':
          if(memory.get(curruntPos)!=0)
            i = findOpeningLoop(i);
          break;
      }
    }
  }


  private void addMemSolts(int pos){
    while(pos >= memory.size())
      memory.add(0);
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
