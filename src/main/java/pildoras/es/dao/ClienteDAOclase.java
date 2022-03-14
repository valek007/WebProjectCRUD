package pildoras.es.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pildoras.es.controlador.entity.Cliente;

@Repository
public class ClienteDAOclase implements ClienteDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Cliente> getClientes() {

		// Obtener la session
		Session session = sessionFactory.getCurrentSession();

		// Crear la consulta (Query)
		Query<Cliente> miQuery = session.createQuery("from Cliente", Cliente.class);

		// Ejecutar la Query y devolver resultados
		List<Cliente> clientes = miQuery.getResultList();

		return clientes;
	}

	@Override
	@Transactional
	public void insertarCliente(Cliente cliente) {

		// Obtener la session
		Session session = sessionFactory.getCurrentSession();
		
		// Insertar el cliente
		session.saveOrUpdate(cliente);
	}
	
	@Override
	@Transactional
	public Cliente getCliente(int id) {

		// Obtener la session
		Session session = sessionFactory.getCurrentSession();
		
		//Obtener la información del cliente seleccionado
		
		Cliente cliente = session.get(Cliente.class, id);
		
		return cliente;
		
	}

}
