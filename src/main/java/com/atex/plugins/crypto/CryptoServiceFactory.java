package com.atex.plugins.crypto;

import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

/**
 * CryptoServiceFactory
 *
 * @author mnova
 */
public abstract class CryptoServiceFactory {

    public static CryptoService create() {
        final ServiceLoader<CryptoService> serviceLoader = ServiceLoader.load(CryptoService.class);
        return StreamSupport
                .stream(serviceLoader.spliterator(), false)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

}
