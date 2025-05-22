package vercors.sif.unverifedcode.examples.concealedfields;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

public class WrongConcealedFields {
    // concealed = {notReallyConcealed} --> disallowed
    // inv = low(notReallyConcealed)
    private int notReallyConcealed;

    //secure!
    // requires perm(this.notReallyConcealed, read);
    // ensures perm(this.notReallyConcealed, read);
    // ensures \result == this.notReallyConcealed;
    public int getNotReallyConcealed() {
        return notReallyConcealed;
    }

    // insecure!
    // wrongConcealedFields.notReallyConcealed is no longer concealed. Therefore, assignments need to be lowEvent
    private static void main(boolean secret) {
        WrongConcealedFields wrongConcealedFields = new WrongConcealedFields();
        unverifiedFunction(wrongConcealedFields);
        if (secret) {
            wrongConcealedFields.notReallyConcealed = 4;
        }
    }
}

// ==> transformed after new viper encoding into:

class WrongConcealedFieldsEncoded {
    // concealed = {notReallyConcealed} --> not allowed!
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
        //from invariant learn: low(notReallyConcealed) -> verifies
        return notReallyConcealed;
    }
}