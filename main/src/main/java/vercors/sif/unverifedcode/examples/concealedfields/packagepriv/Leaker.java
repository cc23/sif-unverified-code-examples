package vercors.sif.unverifedcode.examples.concealedfields.packagepriv;

public class Leaker {
    // secure
    public int leakReadable(Storage storage) {
        return storage.readable;
    }

    // insecure!
    public int leakNotReadable(Storage storage) {
        return storage.notReadable;
    }
}
