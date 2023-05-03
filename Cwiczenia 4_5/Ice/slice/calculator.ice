
#ifndef CALC_ICE
#define CALC_ICE

module Demo
{
  enum operation { MIN, MAX, AVG };

  exception NoInput {};
  exception EmptySequence {
    string reason = "Empty sequence";
  };

  sequence<long> NumberSequence;

  struct A
  {
    short a;
    long b;
    float c;
    string d;
  }

  interface Calc
  {
    long add(int a, int b);
    long subtract(int a, int b);
    void op(A a1, short b1); //załóżmy, że to też jest operacja arytmetyczna ;)
    double avg(NumberSequence s) throws EmptySequence;
  };

};

#endif
