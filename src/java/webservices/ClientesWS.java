/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import controller.ClienteController;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.Cliente;
import org.json.simple.JSONArray;

/**
 *
 * @author 0264ARIOS
 */
@WebService(serviceName = "ClientesWS")
public class ClientesWS {

    @WebMethod(operationName = "getClientes")
    public String getClientes() {
        String json = "";
        ClienteController controller = new ClienteController();
        ArrayList<Cliente> clientes = controller.read();
        JSONArray array = controller.toJSON(clientes);
        json = array.toJSONString();
        return json;
    }

    @WebMethod(operationName = "newCliente")
    public int newCliente(
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "ap") String ap,
            @WebParam(name = "am") String am,
            @WebParam(name = "credito") long credito,
            @WebParam(name = "deuda") long deuda) {
        ClienteController controller = new ClienteController();
        try {
            Cliente cliente = new Cliente(nombre, ap, am, credito, deuda);
            controller.add(cliente);
            controller.fixId();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @WebMethod(operationName = "delCliente")
    public int delCliente(
            @WebParam(name = "id") long id) {
        ClienteController controller = new ClienteController();
        try {
            controller.remove(id);
            controller.fixId();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @WebMethod(operationName = "updCliente")
    public int updCliente(
            @WebParam(name = "id") long id,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "ap") String ap,
            @WebParam(name = "am") String am,
            @WebParam(name = "credito") long credito,
            @WebParam(name = "deuda") long deuda) {
        ClienteController controller = new ClienteController();
        try {
            Cliente cliente = new Cliente(id, nombre, ap, am, credito, deuda);
            controller.update(cliente);
            controller.fixId();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
