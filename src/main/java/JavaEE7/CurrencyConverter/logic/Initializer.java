package JavaEE7.CurrencyConverter.logic;

import JavaEE7.CurrencyConverter.CurrencyRepository;
import JavaEE7.CurrencyConverter.domain.Currency;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.inject.Singleton;

/**
 * Created by alex on 10/26/15.
 */
@Singleton
@Startup
public class Initializer {

    @EJB
    private CurrencyRepository repository;

    // TODO still no worky, make worky

    @PostConstruct
    private void startup() {
        Currency[] cur = new Currency[]{
                new Currency("EUR", 0.88, 1.14),
                new Currency("USD", 1, 1),
                new Currency("GBP", 0.65, 1.55),
                new Currency("AUD", 0.72 , 1.3759)
        };
        for (Currency currency : cur) {
            CurrencyRepository.getCurrencies().add(currency);
        }
    }

    @PreDestroy
    private void shutdown() {

    }

}
