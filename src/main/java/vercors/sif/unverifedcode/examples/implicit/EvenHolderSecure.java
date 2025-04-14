//from F.Neckers Master Thesis p.18-19
package vercors.sif.unverifedcode.examples.implicit;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

public class EvenHolderSecure {
    //invariant f%2 == 0
    private int f;

    public void setF(int i) {
        if (f % 2 == 0) {
            this.f = i;
        }
    }

    // secure
    public static void main(int secret) {
        EvenHolderSecure ph = new EvenHolderSecure();
        unverifiedFunction(ph);
        if (ph.f % 2 != 0) { //always false
            //leak secret
            unverifiedFunction(secret);
        }
    }

    // insecure
    public static void insecure(int secret) {
        EvenHolderSecure ph = new EvenHolderSecure();
        unverifiedFunction(ph);
        ph.setF(10);
        if (ph.f != 10) { // we don't know if true or not
            //leak secret
            unverifiedFunction(secret);
        }
    }
}
