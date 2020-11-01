public enum status {
    ABERTO("Aberto"),
    ANDAMENTO("Em andamento"),
    FINALIZADO("Finalizado");

    private String status;

    private status(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}