package JavaEE7.CurrencyConverter.interceptor;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

/**
 * Created by alex on 10/29/15.
 */
@ExceptionalAmountsLogged
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class ExceptionalAmountLogger implements Serializable{

    // TODO why you no method scope

    @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext)
            throws Exception {
        try{
            double amount = (Double)invocationContext.getParameters()[0];
            if(amount > 10000) {
                System.out.println("Amount over limit: " + amount);
            }
        }catch (ArrayIndexOutOfBoundsException | ClassCastException e) {
            // no matter
        }


        return invocationContext.proceed();
    }
}
