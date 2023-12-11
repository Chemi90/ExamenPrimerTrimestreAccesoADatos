package cesur.examen.domain.car;

import cesur.examen.common.DAO;
import cesur.examen.common.HibernateUtil;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno: José Miguel Ruiz Guevara
 * Fecha: 11/12/2023
 */

@Log
public class CarDAO implements DAO<Car> {

    private SessionFactory sessionFactory;

    public CarDAO() {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Car save(Car car) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(car);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return car;
    }

    @Override
    public Car update(Car car) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(car);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
        return car;
    }

    @Override
    public boolean remove(Car car) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(car);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public Car get(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Car.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Car> getAll() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Car", Car.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Car> getAllByManufacturer(String manufacturer){
        Session session = sessionFactory.openSession();
        List<Car> cars = new ArrayList<>();
        try {
            String query = "from Car where manufacturer = :manufacturer";
            cars = session.createQuery(query, Car.class)
                    .setParameter("manufacturer", manufacturer)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cars;
    }
}
