package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 0264ARIOS
 */
public class Cliente {

    private long idCliente;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private Date fechaAlta;
    private long credito;
    private long deuda;

    public Cliente(long id, String nombre, String ap, String am, Date fecha, long credito, long deuda) {
        this.idCliente = id;
        this.nombre = nombre;
        this.apPaterno = ap;
        this.apMaterno = am;
        this.fechaAlta = fecha;
        this.credito = credito;
        this.deuda = deuda;
    }

    public Cliente(long id, String nombre, String ap, String am, String fecha, long credito, long deuda) {
        this.idCliente = id;
        this.nombre = nombre;
        this.apPaterno = ap;
        this.apMaterno = am;
        this.fechaAlta = this.stringToDate(fecha);
        this.credito = credito;
        this.deuda = deuda;
    }

    public Cliente(String nombre, String ap, String am, long credito, long deuda) {
        this.nombre = nombre;
        this.apPaterno = ap;
        this.apMaterno = am;
        this.fechaAlta = new Date();
        this.credito = credito;
        this.deuda = deuda;
    }

    /**
     * @return the idCliente
     */
    public long getIdCliente() {
        return idCliente;
    }

    public String dateToString() {
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        return formateador.format(this.fechaAlta);
    }

    public Date stringToDate(String fecha) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = format.parse(fecha);
        } catch (ParseException ex) {
            date = new Date();
            ex.printStackTrace();
        }
        return date;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apPaterno
     */
    public String getApPaterno() {
        return apPaterno;
    }

    /**
     * @param apPaterno the apPaterno to set
     */
    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    /**
     * @return the apMaterno
     */
    public String getApMaterno() {
        return apMaterno;
    }

    /**
     * @param apMaterno the apMaterno to set
     */
    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    /**
     * @return the fechaAlta
     */
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * @param fechaAlta the fechaAlta to set
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * @return the credito
     */
    public long getCredito() {
        return credito;
    }

    /**
     * @param credito the credito to set
     */
    public void setCredito(long credito) {
        this.credito = credito;
    }

    /**
     * @return the deuda
     */
    public long getDeuda() {
        return deuda;
    }

    /**
     * @param deuda the deuda to set
     */
    public void setDeuda(long deuda) {
        this.deuda = deuda;
    }
}
