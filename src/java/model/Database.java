/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author 0264ARIOS
 */
public class Database {

    String path;
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    FileWriter fichero = null;
    PrintWriter pw = null;

    public void crearArchivo(String path) {
        path += "\\db.conf";
        archivo = new File(path);
        this.path = path;

        BufferedWriter bw = null;
        if (!archivo.exists()) {
            try {
                bw = new BufferedWriter(new FileWriter(archivo));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void crearRuta(String path) {
        path += "\\database";
        File file = new File(path);
        file.mkdirs();
        crearArchivo(path);
    }

    public void conectar() {
        try {
            path = new File(".").getCanonicalPath();
            crearRuta(path);

            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(this.path);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            cerrar();
        }
    }

    public String leerArchivo() {
        // Lectura del fichero
        String linea;
        String texto = "";

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                texto += linea;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return texto;
    }

    public void escribirArchivo(String texto) {
        try {
            fichero = new FileWriter(path);
            pw = new PrintWriter(fichero);
            pw.println(texto);
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cerrar();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void cerrar() {
        try {
            if (null != fr) {
                fr.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
