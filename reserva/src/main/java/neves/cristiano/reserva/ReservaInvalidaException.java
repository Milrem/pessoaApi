package neves.cristiano.reserva;

public class ReservaInvalidaException extends Exception {
    private final String motivo;

    public ReservaInvalidaException(String motivo) {
        super("Reserva invalida. Motivo: " + motivo);
        this.motivo = motivo;
    }
}
