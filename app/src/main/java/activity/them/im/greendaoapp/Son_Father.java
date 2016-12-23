package activity.them.im.greendaoapp;

import android.content.Context;
import android.util.Log;

import com.model.person_father.DaoMaster;
import com.model.person_father.DaoMaster.DevOpenHelper;
import com.model.person_father.DaoSession;
import com.model.person_father.Father;
import com.model.person_father.FatherDao;
import com.model.person_father.Son;
import com.model.person_father.SonDao;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/23 0023.
 */
public class Son_Father {
    
    DaoMaster mDaoMaster;
    DaoSession mDaoSession;
    SonDao mSonDao;
    FatherDao mFatherDao;
    DevOpenHelper mHelper;
    
    public void openDb(Context _context){
        mHelper = new DevOpenHelper(_context, "son_father.db", null);
        mDaoMaster=new DaoMaster(mHelper.getWritableDatabase());
        mDaoSession=mDaoMaster.newSession();
        mSonDao=mDaoSession.getSonDao();
        mFatherDao=mDaoSession.getFatherDao();
    }

    public void save(){
        Father father=new Father("父亲",87);
        long father_id = mFatherDao.insert(father);
        Date date = new Date();
        Son son=new Son(45,"哈1哈",date,father_id);
        mSonDao.insert(son);
    }

    public void getAll(){
        List<Son> list = mDaoSession.queryBuilder(Son.class).list();
        for (int i = 0; i < list.size(); i++) {
            Son son = list.get(i);
            Father father = son.getFather();
            Log.d("TAG-list",son.toString()+"\n"+father.toString());
        }

    }
    

}
