package pe.gob.minjus.sisarb.utils;

import java.util.regex.Pattern;

public class Validators {

    private Validators() {
    }

    public static boolean validOnlyAlfaNumericos(String textToValidate){
        Pattern patron = Pattern.compile("^[\\p{L}0-9\\sÁÉÍÓÚáéíóúñÑ]*$");
        return patron.matcher(textToValidate).matches();
    }
}
