package com.atex.plugins.crypto;

/**
 * CryptoServiceBuilder
 *
 * @author mnova
 */
public class CryptoServiceBuilder {

    public CryptoService build() {
        return new CryptoServiceImpl();
    }
    
}
