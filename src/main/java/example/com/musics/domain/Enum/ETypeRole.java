package example.com.musics.domain.Enum;

public enum ETypeRole {
    ADMIN("admin"),
    USER("user");

    private String valor;

    private ETypeRole(String valor) {
        this.valor = valor;
    }

    public String getValor(){
        return this.valor;
    }
}
