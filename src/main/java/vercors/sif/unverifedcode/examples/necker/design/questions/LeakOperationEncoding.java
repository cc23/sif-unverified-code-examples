package vercors.sif.unverifedcode.examples.necker.design.questions;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

class A {
    public int i;
    private A a;

    public A() {
    }
    public A(A a){
        this.a = a;
    }
    public A getA() {
        return a;
    }
}

public class LeakOperationEncoding {

    public static void main(String[] args){
        A aHidden = new A();
        A a = new A(aHidden);
        aHidden.i = 3;
        // leak(a)
        adversaryCode(a);
        assert aHidden.i == 3;
    }

    public static void adversaryCode(A a){
        a.getA().i = 5;
    }
}
