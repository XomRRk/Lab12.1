import java.sql.*;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.concurrent.*;

import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.query.Query;

import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;

public class Main {
    static CountDownLatch countDownLatch = new CountDownLatch(8);
    public static int p1=0;
    public static int p2=0;
    public static int p3=0;
    public static int p4=0;
    public static int p5=0;
    public static int p6=0;
    public static int p7=0;
    public static int p8=0;


    public static void main(String[] args)  {
    //  Метод с оптимистичной блокировкой
       SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        try {
        new Thread(()->{

                while (p1!=20000){
                Long randomRow = (long) ((Math.random() * 40) + 1);
                //System.out.println(String.format("Thread #%s started", 1));
                Session session = factory.getCurrentSession();
                session.beginTransaction();
                Items item = session.get(Items.class, randomRow); // <- version= 1
                item.setVal(item.getVal() + 1);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                try {
                    session.save(item);
                    session.getTransaction().commit(); // в                момент подтверждения транзакции во втором потоке производится сравнениеверсии при старте транзакции (1) и текущим значением версии (2)
                    p1++;
                   // System.out.println(String.format("Thread #%s committed", 1));
                } catch (OptimisticLockException e) {
                    session.getTransaction().rollback();
                   // System.out.println(String.format("Thread #%s rollback", 1));
                   // e.printStackTrace();
                }
                if (session != null) {
                    session.close();
                }
                }countDownLatch.countDown();

        }).start();
        new Thread(()->{


                while (p2!=20000){
                    Long randomRow = (long) ((Math.random() * 40) + 1);
                   // System.out.println(String.format("Thread #%s started", 2));
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    Items item = session.get(Items.class, randomRow); // <- version= 1
                    item.setVal(item.getVal() + 1);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        session.save(item);
                        session.getTransaction().commit(); // в                момент подтверждения транзакции во втором потоке производится сравнениеверсии при старте транзакции (1) и текущим значением версии (2)
                        p2++;
                       // System.out.println(String.format("Thread #%s committed", 2));
                    } catch (OptimisticLockException e) {
                        session.getTransaction().rollback();
                       // System.out.println(String.format("Thread #%s rollback", 2));
                       // e.printStackTrace();
                    }
                    if (session != null) {
                        session.close();
                    }
                    }countDownLatch.countDown();

        }).start();
        new Thread(()->{
                while (p3!=20000){
                    Long randomRow = (long) ((Math.random() * 40) + 1);
                   // System.out.println(String.format("Thread #%s started", 3));
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    Items item = session.get(Items.class, randomRow); // <- version= 1
                    item.setVal(item.getVal() + 1);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //uncheckableSleep( 3000) ;
                    try {
                        session.save(item);
                        session.getTransaction().commit(); // в                момент подтверждения транзакции во втором потоке производится сравнениеверсии при старте транзакции (1) и текущим значением версии (2)
                        p3++;
                       // System.out.println(String.format("Thread #%s committed", 3));
                    } catch (OptimisticLockException e) {
                        session.getTransaction().rollback();
                       // System.out.println(String.format("Thread #%s rollback", 3));
                        //e.printStackTrace();
                    }
                    if (session != null) {
                        session.close();
                    }
                    }countDownLatch.countDown();

        }).start();
            new Thread(()->{
                while (p4!=20000){
                    Long randomRow = (long) ((Math.random() * 40) + 1);
                    //System.out.println(String.format("Thread #%s started", 1));
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    Items item = session.get(Items.class, randomRow); // <- version= 1
                    item.setVal(item.getVal() + 1);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //uncheckableSleep( 3000) ;
                    try {
                        session.save(item);
                        session.getTransaction().commit(); // в                момент подтверждения транзакции во втором потоке производится сравнениеверсии при старте транзакции (1) и текущим значением версии (2)
                        p4++;
                        // System.out.println(String.format("Thread #%s committed", 1));
                    } catch (OptimisticLockException e) {
                        session.getTransaction().rollback();
                        // System.out.println(String.format("Thread #%s rollback", 1));
                        // e.printStackTrace();
                    }
                    if (session != null) {
                        session.close();
                    }
                }countDownLatch.countDown();

            }).start();
            new Thread(()->{
                while (p5!=20000){
                    Long randomRow = (long) ((Math.random() * 40) + 1);
                    // System.out.println(String.format("Thread #%s started", 2));
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    Items item = session.get(Items.class, randomRow); // <- version= 1
                    item.setVal(item.getVal() + 1);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //uncheckableSleep( 3000) ;
                    try {
                        session.save(item);
                        session.getTransaction().commit(); // в                момент подтверждения транзакции во втором потоке производится сравнениеверсии при старте транзакции (1) и текущим значением версии (2)
                        p5++;
                        // System.out.println(String.format("Thread #%s committed", 2));
                    } catch (OptimisticLockException e) {
                        session.getTransaction().rollback();
                        // System.out.println(String.format("Thread #%s rollback", 2));
                        // e.printStackTrace();
                    }
                    if (session != null) {
                        session.close();
                    }
                }countDownLatch.countDown();

            }).start();
            new Thread(()->{
                while (p6!=20000){
                    Long randomRow = (long) ((Math.random() * 40) + 1);
                    // System.out.println(String.format("Thread #%s started", 3));
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    Items item = session.get(Items.class, randomRow); // <- version= 1
                    item.setVal(item.getVal() + 1);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //uncheckableSleep( 3000) ;
                    try {
                        session.save(item);
                        session.getTransaction().commit(); // в                момент подтверждения транзакции во втором потоке производится сравнениеверсии при старте транзакции (1) и текущим значением версии (2)
                        p6++;
                        // System.out.println(String.format("Thread #%s committed", 3));
                    } catch (OptimisticLockException e) {
                        session.getTransaction().rollback();
                        // System.out.println(String.format("Thread #%s rollback", 3));
                        //e.printStackTrace();
                    }
                    if (session != null) {
                        session.close();
                    }
                }countDownLatch.countDown();

            }).start();
            new Thread(()->{
                while (p7!=20000){
                    Long randomRow = (long) ((Math.random() * 40) + 1);
                    //System.out.println(String.format("Thread #%s started", 1));
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    Items item = session.get(Items.class, randomRow); // <- version= 1
                    item.setVal(item.getVal() + 1);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //uncheckableSleep( 3000) ;
                    try {
                        session.save(item);
                        session.getTransaction().commit(); // в                момент подтверждения транзакции во втором потоке производится сравнениеверсии при старте транзакции (1) и текущим значением версии (2)
                        p7++;
                        // System.out.println(String.format("Thread #%s committed", 1));
                    } catch (OptimisticLockException e) {
                        session.getTransaction().rollback();
                        // System.out.println(String.format("Thread #%s rollback", 1));
                        // e.printStackTrace();
                    }
                    if (session != null) {
                        session.close();
                    }
                }countDownLatch.countDown();

            }).start();
            new Thread(()->{
                while (p8!=20000){
                    Long randomRow = (long) ((Math.random() * 39) + 1);
                    // System.out.println(String.format("Thread #%s started", 2));
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    Items item = session.get(Items.class, randomRow); // <- version= 1
                    item.setVal(item.getVal() + 1);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //uncheckableSleep( 3000) ;
                    try {
                        session.save(item);
                        session.getTransaction().commit(); // в                момент подтверждения транзакции во втором потоке производится сравнениеверсии при старте транзакции (1) и текущим значением версии (2)
                        p8++;
                        // System.out.println(String.format("Thread #%s committed", 2));
                    } catch (OptimisticLockException e) {
                        session.getTransaction().rollback();
                        // System.out.println(String.format("Thread #%s rollback", 2));
                        // e.printStackTrace();
                    }
                    if (session != null) {
                        session.close();
                    }
                }countDownLatch.countDown();

            }).start();
            try {

                countDownLatch.await();
                //List<Items>listt = null;
                Session session=null;
                session = factory.getCurrentSession();
                session.beginTransaction();
                Object query = session.createNativeQuery("SELECT sum(val) FROM Items").getSingleResult();
                System.out.println(query);
                session.close();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("END");
        } finally {
            factory.close();
        }// нужное Метод с оптимистичной блокировкой
        //Пессимистическая блокировка
        /*SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        try {

            new Thread(() -> {
                while (p1!=20000) {
                    // System.out.println("Thread #1 started");
                    Long randomRow = (long) ((Math.random() * 19) + 1);
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    String s = String.format("From Items where id=%s", randomRow);//1=randomRow
                    Items items = session.createQuery(s, Items.class
                            ).setLockMode(LockModeType.PESSIMISTIC_WRITE)
                            .getSingleResult();
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                        items.setVal(items.getVal()+1);
                        session.save(items);
                        session.getTransaction().commit(); // в                момент подтверждения транзакции во втором потоке производится сравнениеверсии при старте транзакции (1) и текущим значением версии (2)
                        p1++;
                        // System.out.println(String.format("Thread #%s committed", 2));
                    if (session != null) {
                        session.close();
                    }
                }countDownLatch.countDown();
            }).start();
            new Thread(() -> {
                while (p2!=20000) {
                    // System.out.println("Thread #1 started");
                    Long randomRow = (long) ((Math.random() * 19) + 1);
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    String s = String.format("From Items where id=%s", randomRow);//1=randomRow
                    Items items = session.createQuery(s, Items.class
                            ).setLockMode(LockModeType.PESSIMISTIC_WRITE)
                            .getSingleResult();
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                        items.setVal(items.getVal()+1);
                        session.save(items);
                        session.getTransaction().commit(); // в                момент подтверждения транзакции во втором потоке производится сравнениеверсии при старте транзакции (1) и текущим значением версии (2)
                        p2++;
                        // System.out.println(String.format("Thread #%s committed", 2));
                    if (session != null) {
                        session.close();
                    }
                }countDownLatch.countDown();
            }).start();
            new Thread(() -> {
                while (p3!=20000) {
                    // System.out.println("Thread #1 started");
                    Long randomRow = (long) ((Math.random() * 19) + 1);
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    String s = String.format("From Items where id=%s", randomRow);//1=randomRow
                    Items items = session.createQuery(s, Items.class
                            ).setLockMode(LockModeType.PESSIMISTIC_WRITE)
                            .getSingleResult();
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                        items.setVal(items.getVal()+1);
                        session.save(items);
                        session.getTransaction().commit(); // в                момент подтверждения транзакции во втором потоке производится сравнениеверсии при старте транзакции (1) и текущим значением версии (2)
                        p3++;
                        // System.out.println(String.format("Thread #%s committed", 2));
                    if (session != null) {
                        session.close();
                    }
                }countDownLatch.countDown();
            }).start();
            new Thread(() -> {
                while (p4!=20000) {
                    // System.out.println("Thread #1 started");
                    Long randomRow = (long) ((Math.random() * 19) + 1);
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    String s = String.format("From Items where id=%s", randomRow);//1=randomRow
                    Items items = session.createQuery(s, Items.class
                            ).setLockMode(LockModeType.PESSIMISTIC_WRITE)
                            .getSingleResult();
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                        items.setVal(items.getVal()+1);
                        session.save(items);
                        session.getTransaction().commit(); // в                момент подтверждения транзакции во втором потоке производится сравнениеверсии при старте транзакции (1) и текущим значением версии (2)
                        p4++;
                        // System.out.println(String.format("Thread #%s committed", 2));
                    if (session != null) {
                        session.close();
                    }
                }countDownLatch.countDown();
            }).start();
            new Thread(() -> {
                while (p5!=20000) {
                    // System.out.println("Thread #1 started");
                    Long randomRow = (long) ((Math.random() * 19) + 1);
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    String s = String.format("From Items where id=%s", randomRow);//1=randomRow
                    Items items = session.createQuery(s, Items.class
                            ).setLockMode(LockModeType.PESSIMISTIC_WRITE)
                            .getSingleResult();
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                        items.setVal(items.getVal()+1);
                        session.save(items);
                        session.getTransaction().commit(); // в                момент подтверждения транзакции во втором потоке производится сравнениеверсии при старте транзакции (1) и текущим значением версии (2)
                        p5++;
                        // System.out.println(String.format("Thread #%s committed", 2));

                    if (session != null) {
                        session.close();
                    }
                }countDownLatch.countDown();
            }).start();
            new Thread(() -> {
                while (p6!=20000) {
                    // System.out.println("Thread #1 started");
                    Long randomRow = (long) ((Math.random() * 19) + 1);
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    String s = String.format("From Items where id=%s", randomRow);//1=randomRow
                    Items items = session.createQuery(s, Items.class
                            ).setLockMode(LockModeType.PESSIMISTIC_WRITE)
                            .getSingleResult();
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                        items.setVal(items.getVal()+1);
                        session.save(items);
                        session.getTransaction().commit(); // в                момент подтверждения транзакции во втором потоке производится сравнениеверсии при старте транзакции (1) и текущим значением версии (2)
                        p6++;
                        // System.out.println(String.format("Thread #%s committed", 2));
;
                    if (session != null) {
                        session.close();
                    }
                }countDownLatch.countDown();
            }).start();
            new Thread(() -> {
                while (p7!=20000) {
                    // System.out.println("Thread #1 started");
                    Long randomRow = (long) ((Math.random() * 19) + 1);
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    String s = String.format("From Items where id=%s", randomRow);//1=randomRow
                    Items items = session.createQuery(s, Items.class
                            ).setLockMode(LockModeType.PESSIMISTIC_WRITE)
                            .getSingleResult();
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                        items.setVal(items.getVal()+1);
                        session.save(items);
                        session.getTransaction().commit(); // в                момент подтверждения транзакции во втором потоке производится сравнениеверсии при старте транзакции (1) и текущим значением версии (2)
                        p7++;
                    if (session != null) {
                        session.close();
                    }
                }countDownLatch.countDown();
            }).start();
            new Thread(() -> {
                while (p8!=20000) {
                    // System.out.println("Thread #1 started");
                    Long randomRow = (long) ((Math.random() *19) + 1);
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    String s = String.format("From Items where id=%s", randomRow);//1=randomRow
                    Items items = session.createQuery(s, Items.class
                            ).setLockMode(LockModeType.PESSIMISTIC_WRITE)
                            .getSingleResult();
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                        items.setVal(items.getVal()+1);
                        session.save(items);
                        session.getTransaction().commit(); // в                момент подтверждения транзакции во втором потоке производится сравнениеверсии при старте транзакции (1) и текущим значением версии (2)
                        p8++;
                        // System.out.println(String.format("Thread #%s committed", 2));
                    if (session != null) {
                        session.close();
                    }
                }countDownLatch.countDown();
            }).start();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                countDownLatch.await();
                Session session=null;
                session = factory.getCurrentSession();
                session.beginTransaction();
                Object query = session.createNativeQuery("SELECT sum(val) FROM Items").getSingleResult();
                System.out.println(query);
                session.close();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("END");
        } finally {
            factory.close();
        }*///Пессимистическая блокировка

    }
}