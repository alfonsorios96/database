/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        json.put("fechaAlta", formateador.format(cliente.getFechaAlta()));
        json.put("credito", cliente.getCredito());
        json.put("deuda", cliente.getDeuda());
        return json;
    }

    public Cliente toObject(JSONObject json) {
        Cliente cliente;
        int id = (int) (long) json.get("idCliente");
        String nombre = json.get("nombre").toString();
        String ap = json.get("apPaterno").toString();
        String am = json.get("apMaterno").toString();
        int credito = (int) (long) json.get("credito");
        int deuda = (int) (long) json.get("deuda");
        cliente = new Cliente(id, nombre, ap, am, credito, deuda);
        return cliente;
    }

    public ArrayList<Cliente> readAll() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Database db = new Database();
        db.conectar();
        String json = db.leerArchivo();
        Object obj = JSONValue.parse(json);
        JSONObject object = (JSONObject) obj;
        Cliente cliente = toObject(object);
        System.out.println("Me llamo : " + cliente.getNombre() + " " + cliente.getApPaterno());
        db.cerrar();
        return clientes;
    }

    public void write(Cliente cliente) {
        Database db = new Database();
        db.conectar();
        JSONObject obj = toJSON(cliente);
        db.escribirArchivo(obj.toJSONString());
        db.cerrar();
    }
}
