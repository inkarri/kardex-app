package ec.com.kardex.client.exception;

public class KardexExcepction extends RuntimeException {

	private static final long serialVersionUID = -1784180190395806924L;

	public KardexExcepction() {
	}

	public KardexExcepction(String mensaje) {
		super(mensaje);
	}

	public KardexExcepction(Throwable causa) {
		super(causa);
	}

	public KardexExcepction(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

}
