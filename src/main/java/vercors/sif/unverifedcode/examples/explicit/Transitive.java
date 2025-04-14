package vercors.sif.unverifedcode.examples.explicit;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

public class Transitive {

    //insecure
    public static void leakSecretObject(int secret) {
        DemoClass myObject = new DemoClass();
        DemoClass my2ndLayerObject = new DemoClass();
        myObject.object = my2ndLayerObject;
        unverifiedFunction(myObject);
        //insecure
        my2ndLayerObject.f = secret;
    }

}
