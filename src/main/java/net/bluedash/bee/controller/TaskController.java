package net.bluedash.bee.controller;

import net.bluedash.bee.data.MemberRepository;
import net.bluedash.bee.model.*;
import net.bluedash.bee.model.Package;
import net.bluedash.bee.model.Task_;
import net.bluedash.bee.model.Package_;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 12 22 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Named
@RequestScoped
public class TaskController implements Serializable {

    @Inject
    @MemberRepository
    private EntityManager em;

    @Named
    @Produces
    private List<Package> packages = new ArrayList<Package>();

    @Named
    @Produces
    private Task currentProduct;

    private String taskName;

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Task getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Task currentProduct) {
        this.currentProduct = currentProduct;
    }

    public void packagesOfTask() {
        if (taskName == null) return;

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Task> taskC = builder.createQuery(Task.class);
        Root<Task> taskRoot = taskC.from(Task.class);
        taskC.select(taskRoot).where(builder.equal(taskRoot.get(Task_.name), taskName));
        Task task;
        try {
            task = em.createQuery(taskC).getSingleResult();
            setCurrentProduct(task);
        } catch (javax.persistence.NoResultException e) {
            return;
        }

        CriteriaQuery<Package> packageC = builder.createQuery(Package.class);
        Root<Package> packageRoot = packageC.from(Package.class);
        packageC.select(packageRoot).where(builder.equal(packageRoot.get(Package_.task), task));

        packages = em.createQuery(packageC).getResultList();
    }
}
