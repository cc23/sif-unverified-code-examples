package vercors.sif.unverifedcode.examples.necker.design;

import vercors.sif.unverifedcode.examples.dummy.UnverifiedClass;
import vercors.sif.unverifedcode.examples.dummy.UnverifiedContainer;
import vercors.sif.unverifedcode.examples.explicit.DemoClass;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

// 6 cases of passing objects to unverified code (leaking)
public class LeakSecretByCases {
    private static UnverifiedContainer<DemoClass> unverifiedContainer;
    private static DemoClass demoClass;

    //needed for case 1
    public static DemoClass getDemoClass() {
        return demoClass;
    }

    //insecure
    public static void case1(int secret) {
        //demoClass is leaked through the getDemoClass method
        demoClass.f = secret;
    }

    //insecure
    public static void case2(int secret) {
        UnverifiedClass unverifiedClass = new UnverifiedClass();
        DemoClass leakedObject = new DemoClass();
        //leak by assigning to public field of unverified object
        unverifiedClass.ref = leakedObject;
        leakedObject.f = secret;
    }

    //insecure
    public static void case3(int secret) {
        DemoClass leakedObject = new DemoClass();
        //leak by assigning to public field of a leaked (by case 1) object
        demoClass.object = leakedObject;
        leakedObject.f = secret;
    }

    //insecure
    public static void case4(int secret) {
        DemoClass leakedObject = new DemoClass();
        //leak by passing as argument to a method of unverified object
        unverifiedFunction(leakedObject);
        leakedObject.f = secret;
    }

    //insecure
    public static void case5(int secret) {
        DemoClass leakedObject = new DemoClass();
        DemoClass helper = new DemoClass();
        helper.object = leakedObject;
        //leakedObject indirectly leaked
        unverifiedFunction(helper);
        leakedObject.f = secret;
    }


    //Other cases how leaked references can be obtained (but no new objects are passed to unverified code).

    //insecure
    public static void case6(int secret) {
        //leakedObject returned by unverified Method
        DemoClass leakedObject = unverifiedContainer.getItem();
        leakedObject.f = secret;
    }

    //insecure
    public static void case7(int secret) {
        //leakedObject gotten from public field of an unverified object
        DemoClass leakedObject = unverifiedContainer.getItem();
        leakedObject.f = secret;
    }
}