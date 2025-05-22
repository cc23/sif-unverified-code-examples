package vercors.sif.unverifedcode.examples.concealedfields.options;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

public class ConditionallyLowAssignFail {
    // conc = {concealedField, flag}
    // mod = {concealedField, flag}
    // inv = low(flag) && flag ==> low(concealedField)
    private int concealedField;
    private boolean flag;

    //requires low(this.concealedField)
    //requires low(flag)
    //requires lowEvent
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    //requires acc(this.concealedField, write)
    //requires low(concealedField)
    //requires lowEvent
    //ensures acc(this.concealedField, write)
    //ensures this.concealedField == concealedField
    //ensures this.flag
    public void setConcealedFieldLow(int concealedField) {
        this.flag = true;
        this.concealedField = concealedField;
    }

    //requires acc(this.concealedField, write)
    //requires lowEvent
    //ensures acc(this.concealedField, write)
    //ensures this.concealedField == concealedField
    //ensures !this.flag
    public void setConcealedFieldHigh(int concealedField) {
        this.flag = false;
        this.concealedField = concealedField;
    }

    // not secure!
    public static void main(int secret) {
        ConditionallyLowAssignFail conditionallyLowAssignFail = new ConditionallyLowAssignFail();
        if(secret > 0){
            conditionallyLowAssignFail.concealedField = 3;
        }
        //passes, as conditionallyLowAssign is hidden -> inv doesnt need to hold
        conditionallyLowAssignFail.flag = true;
        //fails, since conditionallyLowAssingFail.concealedField is not low
        unverifiedFunction(conditionallyLowAssignFail.concealedField);
    }
}
