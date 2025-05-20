package vercors.sif.unverifedcode.examples.necker.design.questions;

class B {
    // mod = {i}

    private int i;

    public B(int i) {
        this.i = i;
    }

    // requires Perm(this.a, write);
    // ensures Perm(this.a, write);
    public void setI(int i) {
        this.i = i;
    }
}

public class ModAndConcealedField {
    public static void main(String[] args) {
        B b = new B(5);

    }
}
