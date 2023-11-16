package test;

import com.example.dao.entity.Spot;
import com.example.dao.entity.User;
import com.example.dao.mapper.SpotMapper;
import com.example.util.MyBatisUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;


public class CRUDtestSpot {
    static SqlSession sqlSession = null;  //访问数据库的会话对象
    static SpotMapper mapper=null;  //定义映射器接口类型的对象

    static {
        sqlSession= MyBatisUtil.getSqlSession();
        mapper=sqlSession.getMapper(SpotMapper.class);
    }

    @Test
    public void testGetAllSpots() {   //查找所有用户
        try {
            System.out.println(mapper);
            List<Spot> spots=mapper.getAllSpots();
            for (Spot spot : spots) {
                System.out.println(spot); // 输出对象
            }
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testAddSpot()
    {
        try{
            Spot spot=new Spot();
            spot.setSpid("6");spot.setSpname("341ni");spot.setDescription("hen414412dahenda");
            //调用映射文件定义的接口方法
            int i=mapper.addSpot(spot);
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
            testGetAllSpots();
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testDeleteSpot() {
        try{
            int i = mapper.deleteSpot("1");
            if(i>0) {
                System.out.println("删除成功！");
            }else {
                System.out.println("操作未完成！");
            }
            sqlSession.commit();  //CUD需要以事务方式提交！
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            testGetAllSpots();
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testUpadteSpot() {
        try {
            Spot spot=new Spot();
            //先得到要修改的数据
            spot.setSpid("6");spot.setDescription("dsasa");spot.setSpname("adadsad");//要取得id才能改

            int i= mapper.updateSpot(spot);
            if(i>0) {
                System.out.println("修改成功！");
            }else {
                System.out.println("操作未完成！");
            }
            sqlSession.commit();  //CUD需要以事务方式提交！
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            testGetAllSpots();
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testGetSpot(){
        try{
            Spot spot=new Spot();
            spot=mapper.getSpot("6");
            if(spot!=null){
                System.out.println("查找成功！");
                System.out.println(spot);
            } else{
                System.out.println("查找失败");
            }
        }catch (Exception e){
            System.out.println("shujukuzhale");
        }

    }

    @Test
    public void testDeleteAll(){
        try{
            int i=mapper.deleteAllSpots();
            sqlSession.commit();  //CUD需要以事务方式提交！
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
