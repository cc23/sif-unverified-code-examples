package vercors.sif.unverifedcode.examples.concealedfields;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

public class PrivateWriteToConcealedReferenceField {
    public static void main(String[] args) {
        int secret = 119;
        D d = new D();
        d.field = secret;
        D leakedD = new D();
        leakedD.d = d;
        unverifiedFunction(leakedD);
    }
}

class D {
    // conc = {dConcealed}
    // inv = low(dConcealed)
    public int field;
    public D d;
    private D dConcealed;

    public int getAField() {
        return d.field;
    }

    // secure
    // requires lowEvent
    // requires low(dConcealed)
    private void concealedToNonConcealed() {
        d = dConcealed;
    }

    //insecure
    public void concealedToNonConcealedPublic() {
        concealedToNonConcealed();
    }
}