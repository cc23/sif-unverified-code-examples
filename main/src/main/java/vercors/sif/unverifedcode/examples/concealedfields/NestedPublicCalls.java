package vercors.sif.unverifedcode.examples.concealedfields;

public class NestedPublicCalls {
    // conc = {concealedField}
    private int concealedField;
    public int readableField;

    public int method() {
        return identity(concealedField);
    }

    public int method2(){
        leakingWrite(concealedField);
        return 1;
    }

    public int identity(int myArg) {
        return myArg;
    }

    public void leakingWrite(int myArg) {
        readableField = myArg;
    }

}
