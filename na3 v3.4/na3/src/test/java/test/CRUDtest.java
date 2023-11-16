package test;

import com.example.dao.entity.User;
import com.example.dao.mapper.UserMapper;
import com.example.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CRUDtest {
    static SqlSession sqlSession = null;  //访问数据库的会话对象
    static UserMapper mapper=null;  //定义映射器接口类型的对象

    static {
        sqlSession= MyBatisUtil.getSqlSession();
        mapper=sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void loginUser(){
        try{
            System.out.println(mapper);
            String un1="touce";
            String pw1="ceci";
            List<User> users=mapper.getAllUsers();
            boolean flag=false;

            for (User user : users) {
                String un=user.getUsername();
                String pw=user.getPassword();
                if(un1.equals(un)&&pw.equals(pw1)){
                    flag=true;
                }
            }

            if(flag)System.out.println("成功登录");
            else System.out.println("登录失败");
        }catch (Exception e){
            //System.out.println("登录失败了");
            //sqlSession.rollback(); //回滚
        }finally {
            //testGetAllUsers();
            //MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testGetAllUsers() {   //查找所有用户
        try {
            System.out.println(mapper);
            List<User> users=mapper.getAllUsers();
            for (User user : users) {
                System.out.println(user); // 输出对象
            }
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testAddUSer()
    {
        try{
            User user =new User();user.setUsername("lt");
            user.setPassword("g");user.setPhonenumber("21312321");
            user.setRealname("dog");
            //调用映射文件定义的接口方法
            int i=mapper.addUser(user);
            System.out.println(i);
            if(i>0){
                System.out.println("yep!!!!");
            }
            else{
                System.out.println("NO!!!");
            }
            sqlSession.commit();
        }catch (Exception e){
            System.out.println("添加失败了");
            sqlSession.rollback(); //回滚
        }finally {
            testGetAllUsers();
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testDeleteUser()
    {
        try{
            int i = mapper.deleteUser("lt");
            if(i>0) {
                System.out.println("删除成功！");
            }else {
                System.out.println("操作未完成！");
            }
            sqlSession.commit();  //CUD需要以事务方式提交！
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            testGetAllUsers();
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testUpdateUser() {
        try {
            User user=new User();
            //先得到要修改的数据
            user.setUsername("lt");user.setPassword("123");user.setRealname("dsadad");user.setPhonenumber("2131231");//全部赋值才能动

            int i= mapper.updateUser(user);
            if(i>0) {
                System.out.println("修改成功！");
            }else {
                System.out.println("操作未完成！");
            }
            sqlSession.commit();  //CUD需要以事务方式提交！
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            testGetAllUsers();
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testQueryUser()
    {
        try {
            User user=mapper.getUserByUsername("tou1ce");
            if(user!=null)
                System.out.println(user); // 输出对象
            else {
                System.out.println("无此人！");
            }
        }finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }
}
