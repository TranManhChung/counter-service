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

}

