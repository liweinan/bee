package net.bluedash.bee.controller;

import net.bluedash.bee.data.MemberRepository;
import net.bluedash.bee.model.Task;
import net.bluedash.bee.model.Task_;

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
public class TaskController {
    @Inject
    @MemberRepository
    private EntityManager em;

    private String taskName;

    @Named
    @Produces
    private Task currentTask;

    public String getCurrentTaskName() {
        return taskName;
    }

    public void setCurrentTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    public void setupCurrentTask() {
        if (taskName == null) return;

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Task> taskCriteria = builder.createQuery(Task.class);
        Root<Task> taskRoot = taskCriteria.from(Task.class);
        taskCriteria.select(taskRoot).where(builder.equal(taskRoot.get(Task_.name), taskName));

        Task task;
        try {
            task = em.createQuery(taskCriteria).getSingleResult();
            setCurrentTask(task);
        } catch (javax.persistence.NoResultException e) {
            return;
        }

    }
}
