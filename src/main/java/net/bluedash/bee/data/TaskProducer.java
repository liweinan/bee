package net.bluedash.bee.data;

import net.bluedash.bee.data.MemberRepository;
import net.bluedash.bee.model.Task;
//import net.bluedash.bee.model.Product_;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 12 12 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@RequestScoped
public class TaskProducer {

    @Inject
    @MemberRepository
    private EntityManager em;

    private List<Task> tasks;

    @Produces
    @Named
    public List<Task> getTasks() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = cb.createQuery(Task.class);
        Root<Task> productRoot = criteria.from(Task.class);
//        criteria.select(productRoot).orderBy(cb.asc(productRoot.get(Product_.name)));
        tasks = em.createQuery(criteria).getResultList();
        return tasks;
    }

}
