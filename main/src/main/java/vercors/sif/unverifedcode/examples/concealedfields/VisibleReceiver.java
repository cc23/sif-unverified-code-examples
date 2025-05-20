package vercors.sif.unverifedcode.examples.concealedfields;

import vercors.sif.unverifedcode.examples.dummy.UnverifiedClass;
import vercors.sif.unverifedcode.examples.dummy.UnverifiedContainer;

import java.util.concurrent.atomic.AtomicBoolean;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

public class VisibleReceiver {
    // conc = {unverifiedClass}
    // invariant = low(unverifiedClass);
    private UnverifiedClass unverifiedClass;

    //insecure!
    public void implicitLeaker() {
        unverifiedClass.unverifiedCall();
    }

    //insecure! Also insecure if UnverifiedClass is verified!
    public void implicitLeakerAssignment() {
        unverifiedClass.field = 3;
    }

    //secure!
    public static void main(int secret, UnverifiedClass unverifiedClass1, UnverifiedClass unverifiedClass2) {
        VisibleReceiver visibleReceiver = new VisibleReceiver();
        unverifiedFunction(visibleReceiver);
        if (secret == 0) {
            visibleReceiver.unverifiedClass = unverifiedClass1;
        } else {
            visibleReceiver.unverifiedClass = unverifiedClass2;
        }

    }

}
