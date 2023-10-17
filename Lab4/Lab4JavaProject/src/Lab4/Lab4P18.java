package Lab4;

import java.util.Map;
import redis.clients.jedis.Jedis;

/**
 * 实验 4 
 * 问题 18. 使用 Java 编程查询 Redis 键值数据。
 */

public class Lab4P18 {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Jedis jedis = new Jedis("localhost");
        String value=jedis.hget("student.scofield", "English");
        System.out.println("scofield's English score is:    "+value);
    }
}
