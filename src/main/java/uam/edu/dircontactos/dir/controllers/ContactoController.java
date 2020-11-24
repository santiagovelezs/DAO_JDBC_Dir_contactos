package uam.edu.dircontactos.dir.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uam.edu.dircontactos.dir.DAOMysql.ContactoDAOImpl;
import uam.edu.dircontactos.dir.DAOMysql.TelefonoDAOImpl;
import uam.edu.dircontactos.dir.db.DB;
import uam.edu.dircontactos.dir.iDAO.DAO;
import uam.edu.dircontactos.dir.model.Contacto;
import uam.edu.dircontactos.dir.model.Telefono;

@RestController
@CrossOrigin(origins = "localhost:4200", methods= {RequestMethod.GET,RequestMethod.POST})
public class ContactoController 
{	
			
	@PostMapping(path="/contacto")
	public boolean addContact(@RequestBody Contacto received) throws Exception
	{
		DAO<Contacto> contactoDAO = new ContactoDAOImpl(DB.getConnection());
		DAO<Telefono> telefonoDAO = new TelefonoDAOImpl(DB.getConnection());
		boolean control = contactoDAO.save(received);
		ArrayList<Telefono> telefonos = received.getTelefonos();
		if(control)
		{
			for(Telefono telefono: telefonos)
				control = telefonoDAO.save(telefono);
		}
		return control;
	}  
	
}
