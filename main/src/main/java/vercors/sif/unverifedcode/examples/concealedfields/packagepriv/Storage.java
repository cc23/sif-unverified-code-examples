package vercors.sif.unverifedcode.examples.concealedfields.packagepriv;
// Falls der Adv. Subklassen unseren Verifizierten Klassen erstellen kann, müssen protected Fields wie public Fields behandelt werden.
// Falls nicht, können sie wie packagePrivate mit zusätzlichen Checks behandelt werden.
public class Storage {
    //concealed_fields = {notReadable}

    protected int readable;
    int notReadable;
    protected int protectedNotReadable;

    public int getReadable() {
        return readable;
    }
}
