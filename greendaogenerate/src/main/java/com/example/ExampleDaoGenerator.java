package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Administrator on 2016/12/23 0023.
 */
public class ExampleDaoGenerator {

    public static void main(String[] args) throws Exception {

        //�汾��  ��������
        Schema mSchema=new Schema(1,"com.imooc.model");
        addPerson(mSchema);
        new DaoGenerator().generateAll(mSchema, "D:\\AndroidStudioCode\\GreenDaoApp\\app\\src\\main\\java-gen");
    }

    private static void addPerson(Schema _schema) {
        // һ��ʵ�壨�ࣩ�͹��������ݿ��е�һ�ű��˴�����Ϊ��Note������������
        Entity person = _schema.addEntity("Person");
        person.addIdProperty().autoincrement();
        person.addStringProperty("name").notNull();
        person.addIntProperty("age").notNull();
    }
}
