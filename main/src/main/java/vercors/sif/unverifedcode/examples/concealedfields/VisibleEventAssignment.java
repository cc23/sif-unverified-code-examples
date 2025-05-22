package vercors.sif.unverifedcode.examples.concealedfields;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

class MyContainer {
    public int content;
}

public class VisibleEventAssignment {
    // conc = {value}
    private int value;
    public MyContainer container;

    //insecure!
    //requires leakable(this.container) ==> low(this.value)
    // fails 2nd verification, as assignments to container.content need to be lowEvent, but low(this.value) doesn't hold
    public void implicitLeakerEvent() {
        if (value == 0) {
            container.content = 0;
        } else {
            container.content = 1;
        }
    }

    //secure!
    public static void main(int secret) {
        VisibleEventAssignment myObj = new VisibleEventAssignment();
        unverifiedFunction(myObj);
        myObj.value = 1;

        if (secret == 1) {
            myObj.value = 0;
        }
    }
}
