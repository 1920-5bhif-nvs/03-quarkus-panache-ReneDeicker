package at.htl.cardealerManagement.business;

import at.htl.cardealerManagement.model.Employee;
import at.htl.cardealerManagement.model.StockMovement;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class StockMovementRepository implements PanacheRepository<StockMovement> {
    @Transactional
    public StockMovement save(StockMovement entity) {
        this.persistAndFlush(entity);
        return findById(entity.getId());
    }

    @Transactional
    public StockMovement update(StockMovement entity) {
        this.save(entity);
        return findById(entity.getId());
    }
}
