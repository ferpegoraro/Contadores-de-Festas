package entity;

import java.util.Date;

public class Agendamento {
    private Cliente cliente;
    private Buffet buffet;
    private Date data;
    private String horario;
    private boolean pago;

    public Agendamento(Cliente cliente, Buffet buffet, Date data, String horario) {
        this.cliente = cliente;
        this.buffet = buffet;
        this.data = data;
        this.horario = horario;
        this.pago = false;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Buffet getBuffet() {
        return buffet;
    }

    public void setBuffet(Buffet buffet) {
        this.buffet = buffet;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}
