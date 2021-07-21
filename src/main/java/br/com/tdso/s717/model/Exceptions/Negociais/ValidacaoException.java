package br.com.tdso.s717.model.Exceptions.Negociais;

public class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ValidacaoException (String mensage) {
		super(mensage);
	}

}
