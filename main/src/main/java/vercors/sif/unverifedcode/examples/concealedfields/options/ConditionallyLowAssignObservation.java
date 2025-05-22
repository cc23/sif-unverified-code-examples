package vercors.sif.unverifedcode.examples.concealedfields.options;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

public class ConditionallyLowAssignObservation {
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
    //requires flag && concealedField != this.concealedField ==> lowEvent && low(this)
    //ensures acc(this.concealedField, write)
    //ensures this.concealedField == concealedField
    public void setConcealedFieldLow(int concealedField) {
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
    // OBSERVATION: assignments to fields that are only "conditionally low" still need to be lowEvent!
    public static void main(int secret) {
        ConditionallyLowAssignObservation conditionallyLowAssign = new ConditionallyLowAssignObservation();
        conditionallyLowAssign.setFlag(true);
        if(secret > 0){
            conditionallyLowAssign.setConcealedFieldLow(3);
        }
        unverifiedFunction(conditionallyLowAssign.concealedField);
    }

    // secure!
    // OBSERVATION: assignments to fields that are only "conditionally low" still need to be lowEvent!
    public static void mainSecure(int secret) {
        ConditionallyLowAssignObservation conditionallyLowAssign = new ConditionallyLowAssignObservation();
        unverifiedFunction(conditionallyLowAssign);
        conditionallyLowAssign.setFlag(false);
        if(secret > 0){
            conditionallyLowAssign.setConcealedFieldLow(3);
        }
        conditionallyLowAssign.setConcealedFieldLow(3);

        unverifiedFunction(conditionallyLowAssign.concealedField);
    }
}
