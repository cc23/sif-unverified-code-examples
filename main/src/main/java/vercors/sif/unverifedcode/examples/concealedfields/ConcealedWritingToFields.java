package vercors.sif.unverifedcode.examples.concealedfields;

public class ConcealedWritingToFields {

}
class C {
    // conc = {a}
    // mod = {a, b}
    // inv: low(b)
    private int a;
    private int b;

    // secure
    public int getB() {
        return b;
    }

    // secure
    // requires hidden(other) || leakable(other)
    // requires hidden(this) || leakable(this)
    // requires hidden(this) ==> perm(this.a, read)
    // requires hidden(other) ==> perm(other.a, write)
    public void copyA(C other) {
        other.a = a;
    }

    // secure
    // requires hidden(other) || leakable(other)
    // requires hidden(this) || leakable(this)
    // requires hidden(this) ==> perm(this.a, write)
    // requires hidden(other) ==> perm(other.a, read)
    public void copyOtherA(C other) {
        a = other.a;
    }

    // secure
    // requires hidden(other) || leakable(other)
    // requires hidden(this) || leakable(this)
    // requires hidden(this) ==> perm(this.b, read)
    // requires hidden(other) ==> perm(other.a, write)
    public void copyBToA(C other) {
         other.a = b;
    }

    // secure
    // requires hidden(other) || leakable(other)
    // requires hidden(this) || leakable(this)
    // requires hidden(other) ==> perm(other.b, read)
    // requires hidden(this) ==> perm(this.a, write)
    public void copyOtherBToA(C other) {
        a = other.b;
    }
    // not secure!
    public void copyOtherAToB(C other) {
        // assert inv <=> assert low(b) will fail, since low(other.a) doesn't need to hold
        b = other.a;
    }
    // not secure!
    public void copyAToB(C other) {
        // assert other.inv <=> assert low(other.b) will fail, since low(this.a) doesn't need to hold
        other.b = a;
    }

    //secure  (private methods are only verified once)
    // calls to this method are probably insecure
    // requires low(this.a);
    // QUESTION: preconditions like this are allowed, right?
    private void concToNonConcealed() {
        this.b = a;
    }

}