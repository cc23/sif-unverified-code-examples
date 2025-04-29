package vercors.sif.unverifedcode.examples.concealedfields.packagepriv;

public class Leaker {
    // package private needs to be treated like public
    public int leakPackagePrivate(Storage storage){
        return storage.notReadable;
    }
    // protected needs to be treated like public
    public int leakProtected(Storage storage){
        return storage.protectedNotReadable;
    }
}
