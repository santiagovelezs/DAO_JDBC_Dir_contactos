package uam.edu.dircontactos.dir.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uam.edu.dircontactos.dir.model.Telefono;

@RestController
@CrossOrigin(origins = "localhost:4200", methods= {RequestMethod.GET,RequestMethod.POST})
public class TelefonoController 
{
private Connection conection;
	
	@Autowired
	public TelefonoController(Connection conection)
	{
		this.conection = conection;
	}
	
	@PostMapping(path="/telefono")
	public String addTelefono(@RequestBody Telefono received) throws SQLException
	{		
		Telefono telefono = new Telefono();
		telefono.setIdContacto(received.getIdContacto());
		telefono.setNumero(received.getNumero());
		String sql = "INSERT INTO telefono(id_contacto, telefono) VALUES (?, ?)";
		
		PreparedStatement pstm = conection.prepareStatement(sql);
		pstm.setInt(1, telefono.getIdContacto());
		pstm.setString(2, telefono.getNumero());
		int control = pstm.executeUpdate();	   
		return "Control: "+control;
	}  
}
