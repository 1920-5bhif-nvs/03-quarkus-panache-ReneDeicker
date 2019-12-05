package at.htl.cardealerManagement.business;

import at.htl.cardealerManagement.model.CarType;
import at.htl.cardealerManagement.model.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    @Transactional
    public Customer save(Customer entity) {
        this.persistAndFlush(entity);
        return findById(entity.getId());
    }

    @Transactional
    public Customer update(Customer entity) {
        this.save(entity);
        return findById(entity.getId());
    }
}
