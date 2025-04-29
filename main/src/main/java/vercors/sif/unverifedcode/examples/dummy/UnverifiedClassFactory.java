package vercors.sif.unverifedcode.examples.dummy;

public class UnverifiedClassFactory {
    private int counter = 0;
    public static UnverifiedClass getUnverifiedObject(){
        return new UnverifiedClass();
    }
}
