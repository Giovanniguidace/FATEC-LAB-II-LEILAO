public enum Status {
    ABERTO("Aberto"),
    ANDAMENTO("Em andamento"),
    FINALIZADO("Finalizado");

    private String status;

    private Status(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}