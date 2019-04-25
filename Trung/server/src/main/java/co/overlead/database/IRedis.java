package co.overlead.database;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;

public interface IRedis{
    public static final String Link="redis://localhost";
    public static final RedisClient USERCLIENT = RedisClient.create(Link+"/1");

    public static final RedisClient SESSIONCLIENT = RedisClient.create(Link+"/2");
    public static final RedisClient client3 = RedisClient.create(Link+"/3");
    public static final RedisClient client4 = RedisClient.create(Link+"/4");
    public static final RedisClient client5 = RedisClient.create(Link+"/5");
    public static final RedisClient client6 = RedisClient.create(Link+"/6");
    //...


    public static final StatefulRedisConnection<String, String> USERCONNECTION = USERCLIENT.connect();
    public static final RedisStringCommands USER_SYNC_COMMAND = USERCONNECTION.sync();
    public static Long getSyncUserBalance(String userId){
        Object obj= USER_SYNC_COMMAND.get(userId);
        if (obj==null) return 0L;
        else return Long.parseLong(obj.toString());
    }
    public static void setSyncUserBalance(String userId, Long newBalance){
        USER_SYNC_COMMAND.set(userId,newBalance.toString());
    }
    public static void incrSyncUserBalance(String userId, Long newBalance){
        USER_SYNC_COMMAND.incrby(userId,newBalance);
    }
    public static void decrSyncUserBalance(String userId, Long newBalance){
        USER_SYNC_COMMAND.decrby(userId,newBalance);
    }
}

