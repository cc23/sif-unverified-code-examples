package vercors.sif.unverifedcode.examples.concealedfields;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

public class WrongConcealedFields {
    // concealed = {notReallyConcealed}
    // inv = low(notReallyConcealed) -> disallow low expressions in invariants
    private int notReallyConcealed;

    // requires perm(this.notReallyConcealed, read);
    // ensures perm(this.notReallyConcealed, read);
    // ensures \result == this.notReallyConcealed;
    public int getNotReallyConcealed() {
        return notReallyConcealed;
    }

    private static void main(boolean secret){
        WrongConcealedFields wrongConcealedFields = new WrongConcealedFields();
        unverifiedFunction(wrongConcealedFields);
        if(secret){
            wrongConcealedFields.notReallyConcealed = 4;
        }
    }
}

// ==> transformed after new viper encoding into:

class WrongConcealedFieldsEncoded {
    // concealed = {notReallyConcealed}
    // inv = low(notReallyConcealed)

    private int notReallyConcealed;

    // requires perm(this.notReallyConcealed, read);
    // ensures perm(this.notReallyConcealed, read);
    // ensures \result == this.notReallyConcealed;
    public int getNotReallyConcealed_h() {
        return notReallyConcealed;
    }

    // ensures low(\result);
    // ensures perm(leakable(\result)) > 0;
    public int getNotReallyConcealed_l() {
        //assume low(this)
        //inhale (leakable(this), write)
        return notReallyConcealed;
    }
}