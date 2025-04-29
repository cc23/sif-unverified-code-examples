package vercors.sif.unverifedcode.examples.concealedfields;

// Wir brauchen eine zusätzliche Specification, um zu sagen, dass ein Field nicht exposed wird -> tatsächlich "private" ist.
// Es ist secure secrets in solchen Feldern zu speichern, auch wenn das Objekt Leakable ist.
// es muss also gelten, leakable(this) ==> \forall f : fields, !(f in concealed_fields) ==> low(f)

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

public class Complex {

    //modifiable_fields=(notReadable, readable)
    //concealed_fields=(notReadable)

    private int notReadable;
    private int readable;

    // secure
    public int getReadable() {
        return readable;
    }

    public void setNotReadable(int i){
        this.notReadable = i;
    }
    public void setReadable(int i){
        this.readable = i;
    }

    // insecure
    public int getNotReadable(){
        return readable;
    }

    public static void main(int secret) {
        Complex myObj = new Complex();
        myObj.notReadable = secret;
        myObj.readable = secret;
        myObj.notReadable = 0;
        myObj.readable = 0;
        unverifiedFunction(myObj);
        //secure
        myObj.notReadable = secret;
        //insecure!
        myObj.readable = secret;
    }
}
