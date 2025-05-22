package vercors.sif.unverifedcode.examples.concealedfields;

public class ConcealedReferenceFields {

    //secure
    public static void main2(int secret) {
        A a = new A();
        A a1 = new A();
        a1.setPublicInt(1);
        A a2 = new A();
        a1.setPublicInt(2);
        if(secret > 0) {
            a.setA(a2);
        }
    }

    //insecure
    public static void main(int secret) {
        B b = new B();
        B b1 = new B();
        b1.setPublicInt(1);
        B b2 = new B();
        b1.setPublicInt(2);
        if(secret > 0) {
            b.setB(b2);
        }
    }
}
class A {
    //conc = {a, concInt}
    //mod = {a, concInt}
    private A a;
    private int concInt;
    public int publicInt;

    // not secure
    public int getAPublicInt() {
        return a.publicInt;
    }
    //not secure
    public int getAConcInt() {
        return a.concInt;
    }

    //setters, all secure
    public void setA(A a) {
        this.a = a;
    }

    public void setConcInt(int concInt) {
        this.concInt = concInt;
    }

    //requires lowEvent
    //requires low(publicInt)
    public void setPublicInt(int publicInt) {
        this.publicInt = publicInt;
    }
}

class B {
    // conc = {concInt}
    // mod = {b, concInt, publicInt}

    //inv: low(b) && leakable(b)
    private B b;
    private int concInt;
    public int publicInt;

    //secure
    public void setConcInt(int concInt) {
        this.concInt = concInt;
    }

    // secure
    // requires lowEvent
    // requires low(publicInt)
    public void setPublicInt(int publicInt) {
        this.publicInt = publicInt;
    }

    // secure
    // requires lowEvent
    // requires low(b)
    public void setB(B b) {
        this.b = b;
    }

    // secure
    public int getBPublicInt() {
        return b.publicInt;
    }

    //not secure
    public int getBConcInt() {
        return b.concInt;
    }

    // secure
    public int getBBPublicInt() {
        return b.b.publicInt;
    }

    //not secure
    public int getBBConcInt() {
        return b.b.concInt;
    }
}
