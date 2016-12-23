package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Administrator on 2016/12/23 0023.
 */
public class ExampleDaoGenerator {

    public static void main(String[] args) throws Exception {

        Schema mSchema;

        //单表   版本号  包的名字
        mSchema=new Schema(1,"com.model.person");
        addPerson(mSchema);

        new DaoGenerator().generateAll(mSchema, "D:\\AndroidStudioCode\\GreenDaoApp\\app\\src\\main\\java-gen");

        //多表  一对一
        mSchema=new Schema(1,"com.model.person_father");
        addPersonAndFather(mSchema);
        new DaoGenerator().generateAll(mSchema, "D:\\AndroidStudioCode\\GreenDaoApp\\app\\src\\main\\java-gen");
    }

    private static void addPersonAndFather(Schema _schema) {
        Entity son = _schema.addEntity("Son");
        son.addIdProperty().autoincrement();
        son.addIntProperty("age").notNull();
        son.addStringProperty("name").notNull();
        son.addDateProperty("date");

//        通过外键
        Property fatherId = son.addLongProperty("fatherId").getProperty();

        Entity father = _schema.addEntity("Father");
        father.addIdProperty().autoincrement();
        father.addStringProperty("name");
        father.addIntProperty("age");

        son.addToOne(father, fatherId);
    }

    private static void addPerson(Schema _schema) {
        // 一个实体（类）就关联到数据库中的一张表，此处表名为「Person」（既类名）
        //表名最后使用大写
        Entity person = _schema.addEntity("Person");
        person.addIdProperty().autoincrement();
        person.addStringProperty("name").notNull();
        person.addIntProperty("age").notNull();
    }
}
