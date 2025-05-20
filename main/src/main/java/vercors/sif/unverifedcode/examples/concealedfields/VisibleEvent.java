package vercors.sif.unverifedcode.examples.concealedfields;

import java.util.concurrent.atomic.AtomicBoolean;

import static vercors.sif.unverifedcode.examples.dummy.UnverifiedClass.unverifiedFunction;

public class VisibleEvent {
    // conc = {value}
    private int value;
    //insecure!
    public void implicitLeaker(){
        if (value == 0){
            unverifiedFunction(1);
        }
    }

    //secure!
    public static void main(int secret){
        VisibleEvent event = new VisibleEvent();
        unverifiedFunction(event);
        event.value = 1;

        if(secret == 1){
            event.value = 0;
        }
    }

    //but if we would allow the function implicitLeaker function the following adv. code could get information about secret
    public static AtomicBoolean unverifiedFunctionCalled = new AtomicBoolean(false);

    public static void adversarialCode(VisibleEvent event){
        new Thread(() -> {
            event.implicitLeaker();
            if(unverifiedFunctionCalled.get()){
                System.out.println("secret == 1");
            }
        })
                .start();
    }
    public static void unverifiedFunctionImpl(int i){
        if(i == 1){
            unverifiedFunctionCalled.getAndSet(true);
        }
    }

}
