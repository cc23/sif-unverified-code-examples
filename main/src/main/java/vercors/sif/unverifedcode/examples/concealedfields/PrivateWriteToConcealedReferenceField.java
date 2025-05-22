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
    // conc = {dConcealed} -> disallowed!
    // inv = low(dConcealed)
    public int field;
    public D d;
    private D dConcealed;

    // secure
    public int getDField() {
        return d.field;
    }

    // secure
    // requires lowEvent
    // requires low(this.dConcealed)
    private void concealedToNonConcealed() {
        d = dConcealed;
    }

    // secure
    // thanks to inv = low(dConcealed), passes 2nd verification
    public void concealedToNonConcealedPublic() {
        concealedToNonConcealed();
    }
}