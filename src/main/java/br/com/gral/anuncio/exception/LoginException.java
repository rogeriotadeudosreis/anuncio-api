package br.com.gral.anuncio.exception;

public class LoginException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private static final String message = "Usuario ou senha inválidos, Tente novamente.";

    public LoginException() {
        super();
    }

    public LoginException(String str) {
        super(String.format("%s%s", message, str));
    }

}
