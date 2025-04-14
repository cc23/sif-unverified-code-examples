//from F.Neckers Master Thesis p.17-18
package vercors.sif.unverifedcode.examples.implicit;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

public class EvenHolderInsecure {
    private int f;

    public void setF(int i) {
        this.f = i;
    }

    public static void main(int secret) {
        EvenHolderInsecure ph = new EvenHolderInsecure();
        unverifiedFunction(ph);

        if (ph.f % 2 != 0) { // could be true
            unverifiedFunction(secret);
        }
    }
}
