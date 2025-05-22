package vercors.sif.unverifedcode.examples.concealedfields;

public class NestedPublicCalls {
    // conc = {concealedField}
    private int concealedField;
    public int readableField;

    // insecure
    // without inv: low(concealedField) 2nd verification fails
    public int method() {
        return identity(concealedField);
    }

    // insecure
    // without inv: low(concealedField) 2nd verification fails, because precondition of leakingWrite doesn't hold
    public int method2(){
        leakingWrite(concealedField);
        return 1;
    }

    //secure
    //ensures low(myArg) ==> low(\result)
    public int identity(int myArg) {
        return myArg;
    }

    // secure
    // requires low(myArg)
    public void leakingWrite(int myArg) {
        readableField = myArg;
    }

}
