package cz.fi.muni.pa165;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ContextConfiguration(classes=MusicBandManagerApplicationContext.class)
public class Main {
    public static void playground() {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(MusicBandManagerApplicationContext.class);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        EntityManager em = null;

        em = emf.createEntityManager();
    }
    public static void main(String[] args) {
        playground();

    }
}
