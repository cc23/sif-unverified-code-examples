package vercors.sif.unverifedcode.examples.necker.design.questions;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

public class ReturnLeak {
    // secure
    public static void main(int secret) {
        Storage storage = new Storage();
        storage.readable = secret;

        Storage storage2 = new Storage();

        storage2.readable = storage.getReadable();
    }

    // secure
    public static void mainLow(int secret) {
        Storage storage = new Storage();
        storage.readable = secret;
        storage.readable = 0;

        Storage storage2 = new Storage();
        unverifiedFunction(storage2);
        storage2.readable = storage.getReadable();
    }

    // insecure
    public static void mainInsecure(int secret) {
        Storage storage = new Storage();
        storage.readable = secret;

        Storage storage2 = new Storage();
        unverifiedFunction(storage2);
        storage2.readable = storage.getReadable();
    }
}

class Storage {
    int readable;

    public int getReadable() {
        return readable;
    }
}