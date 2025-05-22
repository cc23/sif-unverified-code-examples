package vercors.sif.unverifedcode.examples.concealedfields;

public class SetConcealedAndLeak {
    // mod = {concealedField}
    private int concealedField;

    //insecure -> race condition with adversarial code
    // fails 2nd verification, as from this.concealedField = concealedField follows only inv = true and not low(this.concealedField)
    public int setConcealedField(int concealedField) {
        this.concealedField = concealedField;
        return this.concealedField;
    }

    //secure
    public int setConcealedFieldSecure(int concealedField) {
        this.concealedField = concealedField;
        return concealedField;
    }

    //secure
    //requires hidden(this) ==> perm(this.concealedField, write)
    //ensures hidden(this)  ==> \return == concealedField && this.concealedField == concealedField
    // ensures hidden(this) && low(concealedField) ==> low(\result) (-> implication of first postcondition)
    // ensures hidden(this) && low(concealedField) ==> low(this.concealedField) (-> implication of first postcondition)
    private int setConcealedFieldPriv(int concealedField) {
        this.concealedField = concealedField;
        return this.concealedField;
    }

    //insecure
    // 2nd verification fails, as postcondition of setConcealedFieldPriv is not strong enough to prove low(\result)
    public int setConcealedFieldViaMethodCall(int concealedField) {
        setConcealedFieldPriv(concealedField);
        return this.concealedField;
    }
}
