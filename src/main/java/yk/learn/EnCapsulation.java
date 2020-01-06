package yk.learn;

import yk.learn.sub.Parent;

public class EnCapsulation extends Parent{
  public static void main(String[] args) {
    
    Parent p = new Parent();
    //p.priv = 10;
    //p.defa = 10;
    //p.prot = 10;
    p.publ = 10;
    
    EnCapsulation en = new EnCapsulation();
    //en.priv = 10;
    //en.defa = 10;
    en.prot = 10;
    en.publ = 10;
    
  }
}
