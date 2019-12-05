package at.htl.cardealerManagement.business;

import at.htl.cardealerManagement.model.CarExemplar;
import at.htl.cardealerManagement.model.CarType;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class CareTypeRepository implements PanacheRepository<CarType> {
    @Transactional
    public CarType save(CarType entity) {
        this.persistAndFlush(entity);
        return findById(entity.getId());
    }

    @Transactional
    public CarType update(CarType entity) {
        this.save(entity);
        return findById(entity.getId());
    }
}
