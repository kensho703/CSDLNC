/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redisconnector;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import redis.clients.jedis.Jedis;

/**
 *
 * @author kensho703
 */
public class RedisConnector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter redis_host:");
        String redis_host = scanner.nextLine();
        System.out.println("enter redis_port:");
        int redis_port = 6379;
        try {
            redis_port = scanner.nextInt();
        } catch (Exception e) {
            redis_port = 6379;
        }
        try {
            Jedis jedis = new Jedis(redis_host, redis_port);
            System.err.println("-> Connection to server sucessfully. ");

            scanner = new Scanner(System.in);
            System.out.println("Enter number to choose option: ");
            System.out.println("0. Exit ");
            System.out.println("1. Get");
            System.out.println("2. Set");
            System.out.println("3. Delete");
            int option = scanner.nextInt();

            while (option != 0) {
                switch (option) {
                    case 1: {
                        scanner = new Scanner(System.in);
                        System.out.println("enter key:");
                        String keyname = scanner.nextLine();
                        Set<String> names = jedis.keys(keyname);
                        java.util.Iterator<String> it = names.iterator();
                        long start = System.currentTimeMillis();
                        while (it.hasNext()) {
                            String s = it.next();
                            System.out.println("key:" + s + " - value :" + jedis.get(s));
//                            jedis.get(s);
                        }
                        long end = System.currentTimeMillis();
                        long duration = end - start;
                        System.out.println("Executed time of query : " + duration + " ms");

                        System.out.println("Enter number to choose option: ");
                        System.out.println("0. Exit ");
                        System.out.println("1. Get");
                        System.out.println("2. Set");
                        System.out.println("3. Delete");
                        option = scanner.nextInt();
                    }

                    break;
                    case 2: {
                        scanner = new Scanner(System.in);
                        System.out.println("enter key:");
                        String keyname = scanner.nextLine();
                        System.out.println("enter value:");
                        String value = scanner.nextLine();
                        long start = System.currentTimeMillis();
                        jedis.set(keyname, value);
                        System.out.println("Set key:" + keyname + " -> value :" + jedis.get(keyname) + " success.");
                        long end = System.currentTimeMillis();
                        long duration = end - start;
                        System.out.println("Executed time of query : " + duration + " ms");
                        System.out.println("Enter number to choose option: ");
                        System.out.println("0. Exit ");
                        System.out.println("1. Get");
                        System.out.println("2. Set");
                        System.out.println("3. Delete");
                        option = scanner.nextInt();
                    }
                    break;
                    case 3: {
                        scanner = new Scanner(System.in);
                        System.out.println("enter key:");
                        String keyname = scanner.nextLine();
                        long start = System.currentTimeMillis();
                        jedis.del(keyname);
                        long end = System.currentTimeMillis();
                        long duration = end - start;
                        System.out.println("Executed time of query : " + duration + " ms");
                        System.out.println("Enter number to choose option: ");
                        System.out.println("0. Exit ");
                        System.out.println("1. Get");
                        System.out.println("2. Set");
                        System.out.println("3. Delete");
                        option = scanner.nextInt();
                    }
                    break;
                    default:
                        throw new AssertionError();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
