package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Administrator on 2016/12/23 0023.
 */
public class ExampleDaoGenerator {

    public static void main(String[] args) throws Exception {

        //版本号  包的名字
        Schema mSchema=new Schema(1,"com.imooc.model");
        addPerson(mSchema);
        new DaoGenerator().generateAll(mSchema, "D:\\AndroidStudioCode\\GreenDaoApp\\app\\src\\main\\java-gen");
    }

    private static void addPerson(Schema _schema) {
        // 一个实体（类）就关联到数据库中的一张表，此处表名为「Note」（既类名）
        Entity person = _schema.addEntity("Person");
        person.addIdProperty().autoincrement();
        person.addStringProperty("name").notNull();
        person.addIntProperty("age").notNull();
    }
}
