package net.bluedash.bee.controller;

import com.sun.jarsigner.ContentSigner;
import net.bluedash.bee.data.MemberRepository;
import net.bluedash.bee.data.PageStatus;
import net.bluedash.bee.form.TagForm;
import net.bluedash.bee.helper.Constants;
import net.bluedash.bee.helper.FlashUtil;
import net.bluedash.bee.model.Tag;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.*;
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

    public TagController() {
        System.out.println("[+] TAG-CONTROLLER");
    }

    @Inject
    @MemberRepository
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    @Inject
    private TagForm form;

    // Everytime this is accessed, a new tagController will be initialized.
    // TODO Maybe I could move this one into session scope
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

    public String create() {
        try {
            utx.begin();
            em.persist(form.toTag());
            utx.commit();
            FlashUtil.scope().put(Constants.NOTICE, "Tag Created.");

            return PageStatus.CREATED;
        } catch (Exception e) {
            return PageStatus.INTERNAL_ERROR;
        }
    }

    public void tags() {
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Tag> tagCriteria = builder.createQuery(Tag.class);
        Root<Tag> tagRoot = tagCriteria.from(Tag.class);
        tagCriteria.select(tagRoot);
        try {
            tags = em.createQuery(tagCriteria).getResultList();
            Collections.sort(tags);
        } catch (javax.persistence.NoResultException e) {

        }
    }
}
