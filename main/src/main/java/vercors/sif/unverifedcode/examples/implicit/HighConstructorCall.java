package vercors.sif.unverifedcode.examples.implicit;

class A {
    int i;
    A a;
}

public class HighConstructorCall {
    public static void main(boolean secret) {
        A a;
        if (secret) {
            a = new A();
        } else {
            a = new A();
        }
    }
}
