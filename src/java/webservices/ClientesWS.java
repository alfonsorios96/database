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
}
