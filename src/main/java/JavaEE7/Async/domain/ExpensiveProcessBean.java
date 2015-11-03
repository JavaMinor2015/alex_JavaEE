package JavaEE7.Async.domain;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

/**
 * Created by alex on 11/3/15.
 */
@Stateful
public class ExpensiveProcessBean {


    @Asynchronous
    public Future<String> heavyMethod() {
        List<Integer> intList = new ArrayList<>();

        for (int i = 0; i < 500000; i++) {
            intList.add(new Random().nextInt());
        }

        for (int i = 0; i < 10; i++) {
            for (Integer integer : intList) {
                integer = new Random().nextInt();
            }
        }
        return new AsyncResult<>("*alarm*");
    }
}
