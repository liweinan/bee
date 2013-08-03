package net.bluedash.bee.data;

import net.bluedash.bee.model.*;
import net.bluedash.bee.model.Package;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * 12 11 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Startup
@Singleton
public class SeedDataImporter {

//    @Inject
//    @Category("BEE-DATA-IMPORTER")
//    private Logger log;

    @Inject
    @MemberRepository
    private EntityManager em;

    @PostConstruct
    public void importData() {

        User defaultUser = new User();
        defaultUser.setUsername("weli");
        em.persist(defaultUser);

        Task defaultTask = new Task();
        defaultTask.setName("mocked-jb-eap-6.1.1");
        defaultTask.setDescription("Mocked task");

        Status defaultStatus = new Status();
        defaultStatus.setName("mocked-Open");
        defaultStatus.setTimeTracked(false);
        defaultStatus.setTask(defaultTask);
        defaultTask.getStatuses().add(defaultStatus);

        Tag defaultTag = new Tag();
        defaultTag.setName("mocked-Tag");
        defaultTag.setTask(defaultTask);
        defaultTask.getTags().add(defaultTag);

        Tag defaultTag2 = new Tag();
        defaultTag2.setName("mocked-Tag2");
        defaultTag2.setTask(defaultTask);
        defaultTask.getTags().add(defaultTag2);

        Package defaultPackage = new Package();
        defaultPackage.setTask(defaultTask);
        defaultPackage.setAssignee(defaultUser);
        defaultPackage.setName("mocked-package");
        defaultPackage.getTags().add(defaultTag);
        defaultPackage.getTags().add(defaultTag2);
        defaultPackage.setStatus(defaultStatus);

        defaultTask.getPackages().add(defaultPackage);
        em.persist(defaultTask);

//        // Add global setting
//        DisplayPosition globalDisplayPosition = new DisplayPosition();
//        globalDisplayPosition.setPos(DisplayPosition.POS_ENTITY | DisplayPosition.POS_LIST);
//        globalDisplayPosition.setGlobal(true);
//        em.persist(globalDisplayPosition);
        Setting setting = new Setting();
        setting.setGlobal(true);
        em.persist(setting);

    }
}
