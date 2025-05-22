package vercors.sif.unverifedcode.examples.concealedfields;

import vercors.sif.unverifedcode.examples.dummy.UnverifiedClass;
import vercors.sif.unverifedcode.examples.dummy.UnverifiedContainer;

import java.util.concurrent.atomic.AtomicBoolean;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

public class VisibleReceiver {
    // conc = {unverifiedClass} --> disallowed!
    // invariant = low(unverifiedClass);
    private UnverifiedClass unverifiedClass;

    //secure!
    // passes 2nd verification since inv = low(unverifiedClass)
    //requires lowEvent
    //requires low(this.unverifiedClass)
    public void implicitLeaker() {
        unverifiedClass.unverifiedCall();
    }

    //secure!
    //passes 2nd verification since inv = low(unverifiedClass)
    // requires lowEvent
    // requires low(this.unverifiedClass)
    public void implicitLeakerAssignment() {
        unverifiedClass.field = 3;
    }

    //insecure!
    // visibleReceiver.unverifiedClass is no longer concealed. Therefore, assignments need to be lowEvent
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
