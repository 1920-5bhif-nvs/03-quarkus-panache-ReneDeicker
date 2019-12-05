package at.htl.cardealerManagement.business;

import at.htl.cardealerManagement.model.CarExemplar;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class CarExemplarRepository implements PanacheRepository<CarExemplar> {
    @Transactional
    public CarExemplar save(CarExemplar entity) {
        this.persistAndFlush(entity);
        return findById(entity.getId());
    }

    @Transactional
    public CarExemplar update(CarExemplar entity) {
        this.save(entity);
        return findById(entity.getId());
    }
}
