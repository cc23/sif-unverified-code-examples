package vercors.sif.unverifedcode.examples.concealedfields.options;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

public class Incompleteness2ndOption {
    // conc = {concealedField, flag}
    // mod = {concealedField, flag}
    // inv = low(flag) && flag ==> low(concealedField)
    private int concealedField;
    private boolean flag;

    //not secure!
    // 2nd verification fails. invariant is not strong enough to prove low(\result)
    public int getConcealedField() {
        if(!flag){
            return -1;
        }
        return concealedField;
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


    //not secure!
    public static void main(int secret) {
        Incompleteness2ndOption incompleteness2ndOption = new Incompleteness2ndOption();
        incompleteness2ndOption.flag = false;
        if (secret == 3) {
            incompleteness2ndOption.concealedField = 15;
        }
        unverifiedFunction(incompleteness2ndOption);
        if (incompleteness2ndOption.flag && incompleteness2ndOption.concealedField > 0) {
            unverifiedFunction(1);
        }
    }
}
