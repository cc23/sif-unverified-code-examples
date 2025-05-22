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

    //insecure
    public static void leakSecretObjectAfter(int secret) {
        DemoClass myObject = new DemoClass();
        unverifiedFunction(myObject);
        DemoClass my2ndLayerObject = new DemoClass();
        my2ndLayerObject.f = secret;
        //insecure
        myObject.object = my2ndLayerObject;
    }



}

class GetterTransitive {
    // inv: low(object) && leakable(object)
    public int f;
    private GetterTransitive object;

    public GetterTransitive getObject() {
        return object;
    }

    //insecure
    public static void leakSecretObject(int secret) {
        GetterTransitive myObject = new GetterTransitive();
        GetterTransitive my2ndLayerObject = new GetterTransitive();
        myObject.object = my2ndLayerObject;
        unverifiedFunction(myObject);
        //insecure
        my2ndLayerObject.f = secret;
    }

    //insecure
    public static void leakSecretObjectAfter(int secret) {
        GetterTransitive myObject = new GetterTransitive();
        unverifiedFunction(myObject);
        GetterTransitive my2ndLayerObject = new GetterTransitive();
        my2ndLayerObject.f = secret;
        //insecure
        myObject.object = my2ndLayerObject;
    }


}