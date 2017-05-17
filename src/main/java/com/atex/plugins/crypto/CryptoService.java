package com.atex.plugins.crypto;

/**
 * CryptoService.
 *
 * @author mnova
 */
public interface CryptoService {

    /**
     * Encrypt the given value.
     *
     * @param value
     * @return
     */
    String encrypt(final String value);

}