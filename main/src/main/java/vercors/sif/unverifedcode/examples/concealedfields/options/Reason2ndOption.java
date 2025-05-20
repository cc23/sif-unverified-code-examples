package vercors.sif.unverifedcode.examples.concealedfields.options;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

public class Reason2ndOption {
    // conc = {concealedField}
    // mod = {concealedField}
    // inv = low(concealedField)
    private int concealedField;


    //not secure! does not preserve the invariant
    //requires acc(this.concealedField, write)
    //ensures acc(this.concealedField, write)
    //ensures input ==> concealedField == 1
    private void assignConcealedField(boolean input) {
        if (input) {
            concealedField = 1;
        }
    }

    // REMARK: if the invariant states that a field is low,
    //      it follows that almost all assignments to that field need to be lowEvent
    //requires acc(this.concealedField, write)
    //requires low(concealedField)
    //ensures acc(this.concealedField, write)
    //ensures this.concealedField == concealedField
    public void setConcealedField(int concealedField) {
        this.concealedField = concealedField;
    }

    //not secure! invariant is not established after call to setConcealedField
    public static void main(int secret) {
        Reason2ndOption reason2NdOption = new Reason2ndOption();
        if (secret == 0) {
            reason2NdOption.setConcealedField(0);
        }
        unverifiedFunction(reason2NdOption.concealedField);
    }

    //REMARK: invariants containing low expression do not work as those without
    // -> this is only a problem if the low expr in the inv contains a CONCEALED FIELD, as an assignments to non-concealed fields always need to be lowEvent
    // solutions:
    // 1. disallow low expressions in invariant for concealed fields
    // 2. if an invariant contains a low expression of a field, we need to assure that all assignments to that field are lowEvents
}
