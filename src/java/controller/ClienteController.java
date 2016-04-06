/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Cliente;
import model.Database;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author 0264ARIOS
 */


public class ClienteController {

    public JSONObject toJSON(Cliente cliente) {
        JSONObject json = new JSONObject();
        json.put("idCliente", cliente.getIdCliente());
        json.put("nombre", cliente.getNombre());
        json.put("apPaterno", cliente.getApPaterno());
        json.put("apMaterno", cliente.getApMaterno());
        json.put("fechaAlta", cliente.dateToString());
        json.put("credito", cliente.getCredito());
        json.put("deuda", cliente.getDeuda());
        return json;
    }

    public JSONArray toJSON(ArrayList<Cliente> clientes) {
        JSONArray array = new JSONArray();
        for (Cliente cliente : clientes) {
            array.add(toJSON(cliente));
        }
        return array;
    }

    public Cliente toObject(JSONObject json) {
        Cliente cliente;
        long id = Long.parseLong(json.get("idCliente").toString());
        String nombre = json.get("nombre").toString();
        String ap = json.get("apPaterno").toString();
        String am = json.get("apMaterno").toString();
        long credito = Long.parseLong(json.get("credito").toString());
        long deuda = Long.parseLong(json.get("deuda").toString());
        String fecha = json.get("fechaAlta").toString();
        cliente = new Cliente(id, nombre, ap, am, fecha, credito, deuda);
        return cliente;
    }

    public ArrayList<Cliente> read() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Database db = new Database();
        db.conectar();
        String json = db.leerArchivo();
        Object obj = JSONValue.parse(json);
        JSONArray array = (JSONArray) obj;
        for (Object object : array) {
            JSONObject o = new JSONObject();
            o = (JSONObject) object;
            Cliente cliente = toObject(o);
            clientes.add(cliente);
        }
        db.cerrar();
        return clientes;
    }

    public void write(ArrayList<Cliente> clientes) {
        Database db = new Database();
        db.conectar();
        JSONArray array = toJSON(clientes);
        db.escribirArchivo(array.toJSONString());
        db.cerrar();
    }

    public void add(Cliente cliente) {
        ArrayList<Cliente> clientes = read();
        long id = 1;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdCliente() > id) {
                id = clientes.get(i).getIdCliente();
                id++;
            }
        }
        cliente.setIdCliente(id);
        clientes.add(cliente);
        write(clientes);
    }

    public void remove(long id) {
        ArrayList<Cliente> clientes = read();
        ArrayList<Cliente> out = new ArrayList<Cliente>();
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() != id) {
                out.add(cliente);
            }
        }
        write(out);
    }

    public void fixId() {
        ArrayList<Cliente> actual = read();
        ArrayList<Cliente> nuevo = new ArrayList<Cliente>();

        long id = 1;
        
        for (Cliente cliente : actual) {
            Cliente aux = cliente;
            aux.setIdCliente(id);
            nuevo.add(aux);
            id++;
        }

        write(nuevo);
    }

    public void update(Cliente cliente) {
        ArrayList<Cliente> clientes = read();
        ArrayList<Cliente> out = new ArrayList<Cliente>();

        for (Cliente item : clientes) {
            if (item.getIdCliente() == cliente.getIdCliente()) {
                out.add(cliente);
            } else {
                out.add(item);
            }
        }

        write(out);
    }
}
