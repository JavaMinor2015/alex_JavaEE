package JavaEE7.Async;

import JavaEE7.Async.domain.ExpensiveProcessBean;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;

/**
 * Created by alex on 11/3/15.
 */
@Named
@ApplicationScoped
@Getter
@Setter
public class ExpensiveController implements Serializable{
    @EJB
    ExpensiveProcessBean bean;

    private String response = "no response yet";

    public void doHeavyTask(){
        try {
            response = bean.heavyMethod().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
