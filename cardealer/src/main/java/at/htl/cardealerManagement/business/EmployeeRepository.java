package at.htl.cardealerManagement.business;

import at.htl.cardealerManagement.model.Customer;
import at.htl.cardealerManagement.model.Employee;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class EmployeeRepository  implements PanacheRepository<Employee> {
    @Transactional
    public Employee save(Employee entity) {
        this.persistAndFlush(entity);
        return findById(entity.getId());
    }

    @Transactional
    public Employee update(Employee entity) {
        this.save(entity);
        return findById(entity.getId());
    }
}
