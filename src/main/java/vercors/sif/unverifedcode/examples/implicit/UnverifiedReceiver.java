package vercors.sif.unverifedcode.examples.implicit;

import vercors.sif.unverifedcode.examples.dummy.UnverifiedClass;
import vercors.sif.unverifedcode.examples.dummy.UnverifiedClassFactory;
import vercors.sif.unverifedcode.examples.dummy.UnverifiedSubclass;

public class UnverifiedReceiver {

    //insecure
    public static void subClassReceiver(int secret) {
        UnverifiedClass unverifiedObj1 = new UnverifiedClass();
        UnverifiedClass unverifiedObj2 = new UnverifiedSubclass();
        UnverifiedClass receiver = secret > 0 ? unverifiedObj1 : unverifiedObj2;

        receiver.unverifiedCall();
    }

    //insecure
    public static void diffReceiver(int secret) {
        UnverifiedClass unverifiedObj1 = new UnverifiedClass();
        UnverifiedClass unverifiedObj2 = new UnverifiedClass();
        if (unverifiedObj1.equals(unverifiedObj2)) {
            UnverifiedClass receiver = secret > 0 ? unverifiedObj1 : unverifiedObj2;
            receiver.unverifiedCall();

        }
    }

    //secure -> check if possible
    public static void sameReceiver(int secret) {
        UnverifiedClass unverifiedObj1 = UnverifiedClassFactory.getUnverifiedObject();
        UnverifiedClass unverifiedObj2 = UnverifiedClassFactory.getUnverifiedObject();
        if (unverifiedObj1 == unverifiedObj2) {
            UnverifiedClass receiver = secret > 0 ? unverifiedObj1 : unverifiedObj2;
            receiver.unverifiedCall();
        }
    }

    //secure
    public static void setFieldSecure(int secret) {
        UnverifiedClass unverifiedObj = new UnverifiedClass();
        unverifiedObj.field = 3;
    }

    //insecure
    public static void setField(int secret) {
        UnverifiedClass unverifiedObj = new UnverifiedClass();
        if (secret > 0) {
            unverifiedObj.field = 3;
        }
    }

    //insecure
    public static void potentialUnverifiedSubclass(DemoClass demoClass, int secret) {
        demoClass.demoMethod(secret);
    }

    // secure
    // requires demoClass.getClass() == DemoClass.class && hidden(demoClass)
    // is first part necessary? hidden(demoClass) should imply demoClass.getClass() is verified Class
    // how would demoClass be hidden, if it was not directly created with a verified Constructor call of verified Class?
    // or put differently: if demoClass is an instance of an unverified subclass, then hidden(demoClass) cannot hold

    //2nd observation: hidden(demoClass) is sufficient but not necessary.
    // demoClass.getClass() == DemoClass.class is already sufficient
    public static void verifiedSubclass(DemoClass demoClass, int secret) {
        demoClass.demoMethod(secret);
    }


    //insecure
    public static void potentialUnverifiedSubclassHighEvent(DemoClass demoClass, int secret) {
        if (secret == 42) {
            demoClass.demoMethod(0);
        }
    }

    //secure
    // requires demoClass.getClass() == DemoClass.class
    //assuming demoClass.demoMethod is not a lowEvent
    public static void verifiedSubclassHighEvent(DemoClass demoClass, int secret) {
        if (secret == 42) {
            demoClass.demoMethod(0);
        }
    }
}
