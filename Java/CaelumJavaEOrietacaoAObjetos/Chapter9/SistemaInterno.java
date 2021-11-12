public class SistemaInterno {

    public static boolean login(Autenticavel autenticavel) {
        int senha = 0;
        return autenticavel.autentica(senha);
    }

}
