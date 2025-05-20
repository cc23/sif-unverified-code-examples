package vercors.sif.unverifedcode.examples.concealedfields.options;

public class PasswordChecker {
    // conc = {password}

    private String password;

    // requires acc(this.password, write)
    // ensures password == this.password
    public PasswordChecker(String password, boolean toLowerCase, boolean toUpperCase) {
        if (toLowerCase) {
            this.password = password.toLowerCase();
        } else if (toUpperCase) {
            this.password = password.toUpperCase();
        } else {
            this.password = password;
        }
    }

    //requires acc(this.password, read)
    public boolean checkPassword(String password) {
        //declassify(this.password.equals(password))
        return this.password.equals(password);
    }

}
