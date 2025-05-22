package vercors.sif.unverifedcode.examples.concealedfields;

public class PseudoConcealed {
    //inv: low(f) && f == g
    //conc = {g}
    // g is "pseudo-concealed" it is marked as concealed but still has a getter
    // this is fine, as it can only be assigned to at the same time as f (which requires lowEvent)
    // also inv enforces its content to be low all the time
    private int f;
    private int g;

    //secure
    public int getG() {
        return g;
    }

    //secure
    public int getF(){
        return f;
    }

    public void main(int secret){
        if(secret > 0){

        }
    }
}
