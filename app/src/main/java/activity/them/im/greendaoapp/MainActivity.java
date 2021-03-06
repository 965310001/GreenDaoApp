package activity.them.im.greendaoapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.model.person.DaoMaster;
import com.model.person.DaoSession;
import com.model.person.Person;
import com.model.person.PersonDao;

import java.util.List;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    DaoMaster mDaoMaster;
    DaoMaster.DevOpenHelper mHelper;
    DaoSession mDaoSession;
    PersonDao mPersonDao;

    EditText et_name,et_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_remove).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);

        et_age= (EditText) findViewById(R.id.et_age);
        et_name= (EditText) findViewById(R.id.et_name);

        openDb();


        Son_Father son_father = new Son_Father();
        son_father.openDb(MainActivity.this);
        son_father.save();
        son_father.getAll();
    }

    private void openDb() {
        mHelper= new DaoMaster.DevOpenHelper(MainActivity.this,"Person.db",null);
        mDaoMaster=new DaoMaster(mHelper.getWritableDatabase());
        mDaoSession=mDaoMaster.newSession();
        mPersonDao=mDaoSession.getPersonDao();
    }

    private void save(){
        Person person=new Person(et_name.getText().toString().toString(),Integer.valueOf(et_age.getText().toString().trim()));
        mDaoSession.insert(person);
    }

    private void getAll(){
        List<Person> persons = mDaoSession.queryBuilder(Person.class).list();
        Log.d("TAG", persons.toString());
    }

    private void update(Person _person){
        mPersonDao.insertOrReplace(_person);
    }

    private void delete(Person _person){
        mPersonDao.delete(_person);
    }

    @Override
    public void onClick(View v) {

        if (TextUtils.isEmpty(et_name.getText().toString())||TextUtils.isEmpty(et_age.getText().toString())) {
            getAll();
            return;
        }

        switch (v.getId()) {
            case R.id.btn_add:
                save();
                break;

            case R.id.btn_remove:
                delete(new Person(1L, et_name.getText().toString().trim(), Integer.valueOf(et_age.getText().toString().trim())));
                break;

            case R.id.btn_update:
                update(new Person(1L, et_name.getText().toString().trim(), Integer.valueOf(et_age.getText().toString().trim())));
                break;
        }
        getAll();
    }
}
