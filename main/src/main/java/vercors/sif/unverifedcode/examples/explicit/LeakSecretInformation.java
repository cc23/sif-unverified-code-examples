package vercors.sif.unverifedcode.examples.explicit;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;
import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedGetDemoClass;

public class LeakSecretInformation {

    //insecure
    public static void leakSecretObject(int secret) {
        DemoClass myObject = new DemoClass();
        myObject.f = secret;
        unverifiedFunction(myObject);
    }

    //secure
    public static void dontLeakSecretObject(int secret) {
        DemoClass myObject = new DemoClass();
        myObject.f = secret;
        myObject.f = 0;
        unverifiedFunction(myObject);
    }

    //insecure! package private must be treated like public.
    // -> adversary can create same package and access package private fields
    public static void packagePrivateField(int secret) {
        DemoClass myObject = new DemoClass();
        myObject.packagePrivate = secret;
        unverifiedFunction(myObject);
    }
}
