package vercors.sif.unverifedcode.examples.explicit;

public class AdversaryCreatedSubclass {
    public static void main(String[] args) {
        int secret = 119;
        VerifiedClass unverifiedClass = new UnverifiedClass();
        unverifiedClass.storeSecret(unverifiedClass, secret);
        System.out.println(((UnverifiedClass) unverifiedClass).concealedField);

        unverifiedClass.methodOverridenByAdversary(secret);
        System.out.println(((UnverifiedClass) unverifiedClass).concealedField);
    }
}

class VerifiedClass {
    private int concealedField;

    public void storeSecret(VerifiedClass verifiedClass, int secret){
        verifiedClass.concealedField = secret;
    }
    public void methodOverridenByAdversary(int secret){
        //do nothing
    }
}

class UnverifiedClass extends VerifiedClass {
    int concealedField;
    @Override
    public void methodOverridenByAdversary(int secret){
        concealedField = secret;
    }
}
