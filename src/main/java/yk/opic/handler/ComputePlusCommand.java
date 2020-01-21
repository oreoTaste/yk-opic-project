package yk.opic.handler;

import yk.opic.util.Prompt;

public class ComputePlusCommand implements Command {
  Prompt prompt;

  public ComputePlusCommand(Prompt prompt) {
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    int num1 = prompt.inputInt("수1? ");
    int num2 = prompt.inputInt("수2? ");

    System.out.printf("계산결과는 %d입니다\n", num1 + num2);
  }
}
