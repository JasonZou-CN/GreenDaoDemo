package com.example.jason.greendaodemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.greendao.UserDao;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insert(String str) {
        User user = new User(null, str, 12);
        App.getInstances().getDaoSession().getUserDao().insert(user);
    }

    public void queryData(String str) {
        UserDao dao = App.getInstances().getDaoSession().getUserDao();
        List<User> users = dao.queryBuilder().where(UserDao.Properties.Name.like("%"+str+"%")).list();
        Toast.makeText(this, "users.size():" + users.size(), Toast.LENGTH_SHORT).show();
    }


    public void click(View view) {
        String str = ((EditText) findViewById(R.id.editText)).getText().toString();
        insert(str.isEmpty() ? "jason" : str);
    }

    public void query(View view) {
        String str = ((EditText) findViewById(R.id.editText)).getText().toString();
        if (str.isEmpty())
            return;
        else
            queryData(str);
    }
}
