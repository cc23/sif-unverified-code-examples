package vercors.sif.unverifedcode.examples.explicit;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;
import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedGetDemoClass;

public class StoreSecretToLeaked {


    //insecure
    public static void storeSecretToLeakedObject(int secret) {
        DemoClass myObject = new DemoClass();
        unverifiedFunction(myObject);
        myObject.f = secret;
    }

    //secure under assumption, that packagePrivate field has no getters etc.
    public static void storeSecretToLeakedObjectPackagePriv(int secret) {
        DemoClass myObject = new DemoClass();
        unverifiedFunction(myObject);
        myObject.packagePrivate = secret;
    }

    //secure!
    public static void storeLowToLeakedObject(int secret) {
        DemoClass myObject = new DemoClass();
        unverifiedFunction(myObject);
        myObject.f = 0;
    }

    //insecure
    public static void unverifiedReturn(int secret) {
        DemoClass myObject = unverifiedGetDemoClass();
        myObject.f = secret;
    }

    public static DemoClass verifiedWrapperMethod(){
        return unverifiedGetDemoClass();
    }

    //insecure
    public static void verifiedLeakedReturn(int secret){
        DemoClass demoClass = verifiedWrapperMethod();
        demoClass.f = secret;
    }
}
