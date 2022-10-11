package com.example.crud.Controller;

import com.example.crud.Model.PictureEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConnectorDAO implements IConnection {

    public List<PictureEntity> selectAll() {
        List<PictureEntity> pictures;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            pictures = entityManager.createQuery("select p from PictureEntity p", PictureEntity.class).getResultList();

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return pictures;
    }

    public PictureEntity selectOne(int id) {
        PictureEntity picture;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Query query = entityManager.createQuery("select p from PictureEntity p where p.id=:id");
            query.setParameter("id", id);

            try {
                picture = (PictureEntity) query.getSingleResult();
            } catch (NoResultException e){
                picture = null;
            }

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return picture;
    }

    public int insert(PictureEntity picture) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

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

        return 0;
    }

    public int update(PictureEntity picture) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Query query = entityManager.createQuery("update PictureEntity set name=:name, author=:author,year=:year,storage=:storage," +
                    "price=:price,link=:link where id=:id");
            query.setParameter("id", picture.getId());
            query.setParameter("name", picture.getName());
            query.setParameter("author", picture.getAuthor());
            query.setParameter("year", picture.getYear());
            query.setParameter("storage", picture.getStorage());
            query.setParameter("price", picture.getPrice());
            query.setParameter("link", picture.getLink());
            query.executeUpdate();

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return 0;
    }

    public int delete(int id) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            entityManager.createQuery("delete from PictureEntity where id=:id").setParameter("id", id).executeUpdate();

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return 0;
    }
}
