package com.serli.oracle.of.bacon.repository;

import java.util.ArrayList;
import java.util.List;
import redis.clients.jedis.Jedis;

public class RedisRepository {

    //Return the name of the ten first Actors of the DB
    public List<String> getLastTenSearches() {
        List<String> listLastTenSearches = new ArrayList<String>();
        Jedis jedis = new Jedis("redis://swann:henjad1452@redis-14808.c10.us-east-1-3.ec2.cloud.redislabs.com:14808");
        System.out.println("Connection to server sucessfully");
        //check whether server is running or not
        System.out.println("Server is running: "+jedis.ping());
        listLastTenSearches = jedis.lrange("Last10Actors", 0, 9);
        System.out.println("size : " + listLastTenSearches.size());
        jedis.quit();
        return listLastTenSearches;
    }

    //Save the name of the last researched actor in the redis DB (left side)
    public void addActorToLastTen(String s){
        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis("redis://swann:henjad1452@redis-14808.c10.us-east-1-3.ec2.cloud.redislabs.com:14808");
        System.out.println("Connection to server sucessfully");
        //check whether server is running or not
        System.out.println("Server is running: "+jedis.ping());
        jedis.lpush( "Last10Actors", s);
        jedis.quit();
    }
}
