package net.bluedash.bee.controller;

import net.bluedash.bee.data.MemberRepository;
import net.bluedash.bee.model.Package;
import net.bluedash.bee.model.Package_;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * 12 26 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Named
@RequestScoped
public class PackageController {
    @Inject
    @MemberRepository
    private EntityManager em;

    private String taskName;

    @Named
    @Produces
    private Package currentPackage;

    public String getCurrentTaskName() {
        return taskName;
    }

    public void setCurrentTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Package getCurrentPackage() {
        return currentPackage;
    }

    public void setCurrentPackage(Package currentPackage) {
        this.currentPackage = currentPackage;
    }

    public void setupCurrentTask() {
        if (taskName == null) return;

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Package> packageC = builder.createQuery(net.bluedash.bee.model.Package.class);
        Root<Package> taskRoot = packageC.from(Package.class);
        packageC.select(taskRoot).where(builder.equal(taskRoot.get(Package_.name), taskName));

        Package aPackage;
        try {
            aPackage = em.createQuery(packageC).getSingleResult();
            setCurrentPackage(aPackage);
        } catch (javax.persistence.NoResultException e) {
            return;
        }

    }
}
