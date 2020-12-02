//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter


public class Lance {
    private double valorLance;
    private Cliente cliente;

    public Lance(double valorLance, Cliente cliente) {
        this.valorLance = valorLance;
        this.cliente = cliente;
    }

    public double getValorLance() {
        return valorLance;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setValorLance(double valorLance) {
        this.valorLance = valorLance;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
