package vercors.sif.unverifedcode.examples.implicit;


import vercors.sif.unverifedcode.examples.dummy.UnverifiedClass;
import vercors.sif.unverifedcode.examples.dummy.UnverifiedClassFactory;
import vercors.sif.unverifedcode.examples.dummy.UnverifiedSubclass;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

public class HighUnverifiedFunctionCalls {
    //insecure
    public static void highUnverifiedCall(int secret) {
        if (secret > 0) {
            unverifiedFunction(0);
        }
    }

    //insecure
    public static void differentArgs(int secret) {
        if (secret > 0) {
            unverifiedFunction(0);
        } else {
            unverifiedFunction(1);
        }
    }

    //secure -> disallow incompleteness
    public static void sameArgs(int secret) {
        if (secret > 0) {
            unverifiedFunction(0);
        } else {
            unverifiedFunction(0);
        }
    }

    // insecure? -> attacker model? can we see the name of a class w/out reflection? memory allocation?
    public static void subclassArg(int secret) {
        DemoClass obj1 = new DemoClass(0);
        DemoClass obj2 = new DemoClass(0) {
        };
        DemoClass argForCall = secret > 0 ? obj1 : obj2;

        unverifiedFunction(argForCall);
    }

    //  insecure? memory allocation? -> first disallow it
    public static void argsOnlyDiffInPrivateFields(int secret) {
        DemoClass obj1 = new DemoClass(0);
        DemoClass obj2 = new DemoClass(0);
        obj1.packagePrivate = 1;
        obj2.packagePrivate = 2;
        DemoClass argForCall = secret > 0 ? obj1 : obj2;

        unverifiedFunction(argForCall);
    }

    //  insecure!
    public static void argsOnlyDiffInPublicFields(int secret) {
        DemoClass obj1 = new DemoClass(0);
        DemoClass obj2 = new DemoClass(2);
        obj1.f = 1;
        obj2.f = 2;
        DemoClass argForCall = secret > 0 ? obj1 : obj2;

        unverifiedFunction(argForCall);
    }

    // insecure? memory allocation?
    public static void differentObjects(int secret) {
        DemoClass obj1 = new DemoClass(0);
        DemoClass obj2 = new DemoClass(0);
        DemoClass argForCall = secret > 0 ? obj1 : obj2;

        unverifiedFunction(argForCall);
    }


}
