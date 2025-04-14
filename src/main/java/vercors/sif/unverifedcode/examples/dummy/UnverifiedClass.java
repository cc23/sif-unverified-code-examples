package vercors.sif.unverifedcode.examples.dummy;

import vercors.sif.unverifedcode.examples.explicit.DemoClass;

public class UnverifiedClass {
    public int field;
    public void unverifiedCall(){}
    public static void unverifiedFunction(Object arg){
        return;
    }

    public static void unverifiedFunction2(Object arg){
        return;
    }

    public static DemoClass unverifiedGetDemoClass(){
        return new DemoClass(0);
    }

}

