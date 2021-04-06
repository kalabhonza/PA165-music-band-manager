package cz.fi.muni.pa165;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(MusicBandManagerApplicationContext.class);
        emf = Persistence.createEntityManagerFactory("default");
    }
}
