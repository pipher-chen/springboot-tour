package com.example.demo;

import com.example.demo.mongodb.bean.MongodbTestModel;
import com.example.demo.mongodb.component.MongoUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbTest {

    @Test
    public void testSave1() {
        MongodbTestModel mo = new MongodbTestModel();
        mo.setMid("123");
        mo.setName("MongodbTestModel");
        mo.setAge("22");
        MongoUtils.save(mo);
    }

    @Test
    public void testSave2() {
        MongodbTestModel mo = new MongodbTestModel();
        mo.setMid(UUID.randomUUID().toString());
        mo.setName("MongodbTestModel");
        mo.setAge("22");
        MongoUtils.save(mo, "test23");

    }

    @Test
    public void testRemove1() {
        MongodbTestModel mo = new MongodbTestModel();
        mo.setMid("5eb1091b-b2a8-4a92-b1a3-4d515e837823");
        mo.setName("MongodbTestModel");
        mo.setAge("22");
        MongoUtils.remove(mo);
    }

    @Test
    public void testRemove2() {
        MongodbTestModel mo = new MongodbTestModel();
        mo.setMid("a27a0652-a0ed-4d7d-a100-d5f11fe1b844");
        mo.setName("MongodbTestModel");
        mo.setAge("22");
        MongoUtils.remove(mo, "test23");
    }

    @Test
    public void testRemove3() {
        MongoUtils.removeById("_id", "123", "mongodbTestModel");
    }

    @Test
    public void testUpdate1() {
        String accordingKey = "_id";
        String accordingValue = "e933f48a-cc68-4993-9069-820ff3adab34";
        String[] updateKeys = { "name", "age" };
        Object[] updateValues = { "tat", "22222" };
        String collectionName = "test23";
        MongoUtils.updateFirst(accordingKey, accordingValue, updateKeys, updateValues, collectionName);
    }

    @Test
    public void testUpdate2() {
        String accordingKey = "name";
        String accordingValue = "tat";
        String[] updateKeys = { "age" };
        Object[] updateValues = { "000000" };
        String collectionName = "test23";
        MongoUtils.updateMulti(accordingKey, accordingValue, updateKeys, updateValues, collectionName);
    }

    @Test
    public void testFind1() {
        MongodbTestModel obj = new MongodbTestModel();
        String[] findKeys = { "age", "name" };
        String[] findValues = { "22", "MongodbTestModel" };
        List<? extends Object> find = MongoUtils.find(obj, findKeys, findValues);
        System.out.println(find);
    }

    @Test
    public void testFind2() {
        MongodbTestModel obj = new MongodbTestModel();
        String[] findKeys = { "name" };
        String[] findValues = { "tat" };
        String collectionName = "test23";
        List<? extends Object> find = MongoUtils.find(obj, findKeys, findValues, collectionName);
        System.out.println(find);
    }

    @Test
    public void testFind3() {
        MongodbTestModel obj = new MongodbTestModel();
        String[] findKeys = { "name" };
        String[] findValues = { "tat" };
        String collectionName = "test23";
        Object findOne = MongoUtils.findOne(obj, findKeys, findValues, collectionName);
        System.out.println(findOne);
    }

    @Test
    public void testFind4() {
        List<? extends Object> findAll = MongoUtils.findAll(new MongodbTestModel());
        System.out.println(findAll);
    }

    @Test
    public void testFind5() {
        List<? extends Object> findAll = MongoUtils.findAll(new MongodbTestModel(),"test23");
        System.out.println(findAll);
    }

}
