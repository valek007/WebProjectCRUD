package pildoras.es.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pildoras.es.controlador.entity.Cliente;
import pildoras.es.dao.ClienteDAO;

@Controller
@RequestMapping("/cliente")
public class Controlador {

	@Autowired
	private ClienteDAO clienteDAO;

	@RequestMapping("/lista")
	public String listaClientes(Model modelo) {

		// obtener los clientes desde DAO
		List<Cliente> clientes = clienteDAO.getClientes();

		// agregar los clientes al modelo
		modelo.addAttribute("clientes", clientes);

		return "lista-clientes";
	}

	@RequestMapping("/muestraFormularioAgregar")
	public String muestraFormularioAgregar(Model modelo) {

		// Bind datos clientes
		Cliente cliente = new Cliente();
		modelo.addAttribute("cliente", cliente);

		return "formulario-cliente";
	}

	@PostMapping("/insertarCliente")
	public String insertarCliente(@ModelAttribute("cliente") Cliente cliente) {

		//Insertar Clienter en BBDD
		
		clienteDAO.insertarCliente(cliente);

		return "redirect:/cliente/lista";
	}
	
	@GetMapping("/muestraFormularioActualizar")
	public String muestraFormularioActualizar(@RequestParam("clienteId") int Id, Model modelo) {

		//Obtener Cliente
		Cliente cliente=clienteDAO.getCliente(Id);
		
		//Establecer el cliente como atributo del modelo
		modelo.addAttribute("cliente", cliente);
		
		//Enviar al formulario
		return "formulario-cliente";
	}
	
	@GetMapping("/eliminar")
	public String eliminarCliente(@RequestParam("clienteId") int Id) {

		//Eliminar Cliente
		clienteDAO.eliminarCliente(Id);
		
		//Redireccionar a la lista de clientes
		return "redirect:/cliente/lista";
	}
}
