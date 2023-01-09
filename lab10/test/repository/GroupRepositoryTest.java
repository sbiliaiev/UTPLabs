package repository;

import dto.Group;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupRepositoryTest {
    static GroupRepository rep;
    static int groupId;
    static String groupName;
    static String groupDesc;
    static Group group;

    @BeforeEach
    void setUp() {
        rep = GroupRepository.getInstance();

        groupId = 456;
        groupName = "TestGroupName";
        groupDesc = "TestGroupDesc";

        group = new Group(groupId, groupName, groupDesc, null);
    }

    @Test
    public void should_create_group() {
        int counterBefore = rep.getCount();
        Group createdGroup = rep.add(group);
        int counterAfter = rep.getCount();

        Assertions.assertEquals(1, counterAfter - counterBefore);
        Assertions.assertTrue(rep.exists(createdGroup));

        rep.delete(createdGroup);
    }

    @Test
    public void should_findById_group() {
        rep.addOrUpdate(group);
        Group foundGroup = rep.findById(groupId);

        Assertions.assertEquals(group, foundGroup);

        rep.delete(group);
    }

    @Test
    public void should_findByLogin_group() {
        rep.addOrUpdate(group);
        Group createdGroup = rep.add(new Group(groupName, groupDesc));
        List<Group> foundGroupsList = rep.findByName(groupName);

        List<Group> list = new ArrayList<>();
        list.add(group);
        list.add(createdGroup);

        Assertions.assertEquals(list, foundGroupsList);

        rep.delete(group);
        rep.delete(createdGroup);
    }

    @Test
    public void should_update_group() {
        rep.addOrUpdate(group);
        String newGroupName = "newGroupName";
        group.setName(newGroupName);
        rep.update(group);
        Group foundGroup = rep.findById(groupId);

        Assertions.assertEquals(group, foundGroup);

        rep.delete(group);
    }

    @Test
    public void should_delete_group() {
        int counterBefore = rep.getCount();

        rep.addOrUpdate(group);
        rep.delete(group);

        int counterAfter = rep.getCount();

        Group foundGroup = rep.findById(groupId);

        Assertions.assertNull(foundGroup);
        Assertions.assertEquals(counterBefore, counterAfter);
    }
}