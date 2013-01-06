package net.bluedash.bee.controller;

import net.bluedash.bee.data.MemberRepository;
import net.bluedash.bee.form.TagForm;
import net.bluedash.bee.model.Product;
import net.bluedash.bee.model.Product_;
import net.bluedash.bee.model.Tag;

import javax.ejb.*;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: weinanli
 * Date: 1/5/13
 * Time: 7:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Model
public class TagController {

    @Inject
    @MemberRepository
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    @Inject
    private TagForm form;

    @Named
    @Produces
    private List<Tag> tags;

    @Named
    @Produces
    private Tag currentTag;

    public Tag getCurrentTag() {
        return currentTag;
    }

    public void setCurrentTag(Tag currentTag) {
        this.currentTag = currentTag;
    }

    public void create() {
        try {
            utx.begin();
            em.persist(form.toTag());
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tags() {
        CriteriaBuilder builder = em.getCriteriaBuilder();

        // get target product
        CriteriaQuery<Tag> tagCriteria = builder.createQuery(Tag.class);
        Root<Tag> tagRoot = tagCriteria.from(Tag.class);
        tagCriteria.select(tagRoot);
        try {
            tags = em.createQuery(tagCriteria).getResultList();
            Collections.sort(tags);
        } catch (javax.persistence.NoResultException e) {
            return;
        }
    }
}
