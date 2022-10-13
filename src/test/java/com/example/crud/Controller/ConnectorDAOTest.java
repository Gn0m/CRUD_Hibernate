package com.example.crud.Controller;

import com.example.crud.Model.DAO.ConnectorDAO;
import com.example.crud.Model.PictureEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ConnectorDAOTest {

    @BeforeAll
    static void beforeAll() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        PictureEntity picture = new PictureEntity();
        picture.setName("First");
        picture.setAuthor("Author");
        picture.setYear(2000);
        picture.setStorage("Ростовский Лувр");
        picture.setPrice(BigDecimal.valueOf(111.0));
        picture.setLink("img98765.jpg");


        try {
            transaction.begin();

            entityManager.persist(picture);

            transaction.commit();

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Test
    void selectAll() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        ConnectorDAO connectorDAO = new ConnectorDAO();
        try {

            transaction.begin();

            List<PictureEntity> extracted = connectorDAO.selectAll();

            transaction.commit();

            assertNotNull(extracted);
            assertEquals(1,extracted.size());
            assertEquals("Author",extracted.get(0).getAuthor());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Test
    void selectOne() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        ConnectorDAO connectorDAO = new ConnectorDAO();
        try {

            transaction.begin();

            PictureEntity extracted = connectorDAO.selectOne(1);

            transaction.commit();

            assertNotNull(extracted);
            assertEquals(1,extracted.getId());
            assertEquals("First",extracted.getName());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

    }


    @Test
    void insert() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        PictureEntity picture = new PictureEntity();
        picture.setName("Second");
        picture.setAuthor("Author");
        picture.setYear(2000);
        picture.setStorage("Ростовский Лувр");
        picture.setPrice(BigDecimal.valueOf(111.0));
        picture.setLink("img12345.jpg");

        ConnectorDAO connectorDAO = new ConnectorDAO();

        try {
            transaction.begin();

            entityManager.persist(picture);

            transaction.commit();

            transaction.begin();

            PictureEntity extracted = connectorDAO.selectOne(2);

            transaction.commit();

            assertNotNull(extracted);
            assertEquals("Second",extracted.getName());
            assertEquals(2,extracted.getId());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

    }

    @Test
    void update() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        ConnectorDAO connectorDAO = new ConnectorDAO();
        PictureEntity picture = connectorDAO.selectOne(2);

        picture.setName("3 Belorussian front");
        picture.setAuthor("Vasilevsky");
        picture.setYear(1945);
        picture.setStorage("Ростовский Лувр");
        picture.setPrice(BigDecimal.valueOf(111.0));
        picture.setLink("img12345.jpg");

        try {
            transaction.begin();

            connectorDAO.update(picture);
            PictureEntity extracted = connectorDAO.selectOne(2);
            assertNotNull(extracted);
            assertEquals("Vasilevsky",extracted.getAuthor());
            assertEquals("3 Belorussian front",extracted.getName());

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Test
    void delete() {
        PictureEntity extracted;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        ConnectorDAO connectorDAO = new ConnectorDAO();
        extracted = connectorDAO.selectOne(1);
        assertNotNull(extracted);
        try {
            transaction.begin();

            connectorDAO.delete(extracted.getId());

            transaction.commit();

            transaction.begin();

            extracted = connectorDAO.selectOne(1);

            transaction.commit();

            assertNull(extracted);
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}