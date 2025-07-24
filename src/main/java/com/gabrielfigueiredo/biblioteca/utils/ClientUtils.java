package com.gabrielfigueiredo.biblioteca.utils;

import com.gabrielfigueiredo.biblioteca.infra.exceptions.InvalidPhoneException;

public final class ClientUtils {
    private ClientUtils() {
        throw new UnsupportedOperationException("Classe utilitária - não instanciar");
    }

    public static String cleanPhone(String phone) {
        String cleanPhone = phone.replaceAll(("\\D"), "");
        if (cleanPhone.length() < 10 || cleanPhone.length() > 16) {
            throw new InvalidPhoneException("O número de telefone informado é inválido");
        }
        return cleanPhone;
    }
}
