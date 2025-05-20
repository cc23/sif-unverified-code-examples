package vercors.sif.unverifedcode.examples.concealedfields;

public class SetConcealedAndLeak {
    private int concealedField;

    //insecure -> race condition with adversarial code
    public int setConcealedField(int concealedField) {
        this.concealedField = concealedField;
        return this.concealedField;
    }
    //insecure -> race condition with adversarial code
    private int setConcealedFieldPriv(int concealedField) {
        this.concealedField = concealedField;
        return this.concealedField;
    }

    //insecure
    public int setConcealedFieldViaMethodCall(int concealedField){
        setConcealedFieldPriv(concealedField);
        return this.concealedField;
    }
}
