package vercors.sif.unverifedcode.examples.concealedfields;

public class ConcealedWritingToFields {

}
class C {
    //conc = {a}
    private int a;
    private int b;

    // secure
    public int getB() {
        return b;
    }

    // secure
    public void copyA(C other) {
        other.a = a;
    }
    // secure
    public void copyOtherA(C other) {
        a = other.a;
    }

    // secure
    public void copyBToA(C other) {
         other.a = b;
    }
    // secure
    public void copyOtherBToA(C other) {
        a = other.b;
    }
    // not secure!
    public void copyOtherAToB(C other) {
        b = other.a;
    }
    // not secure!
    public void copyAToB(C other) {
        other.b = a;
    }

    //secure  (only considering concealed information flow problem)
    // requires low(a);
    private void concToNonConcealed() {
        this.b = a;
    }

}