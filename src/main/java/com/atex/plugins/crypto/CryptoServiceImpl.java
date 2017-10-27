package com.atex.plugins.crypto;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.polopoly.util.Base64;

import gnu.crypto.mac.HMacFactory;
import gnu.crypto.prng.IPBE;
import gnu.crypto.prng.LimitReachedException;
import gnu.crypto.prng.PBKDF2;

/**
 * Implementation of {@link CryptoService} using HMAC-SHA-256
 * with a fixed salt.
 *
 * @author mnova
 */
public class CryptoServiceImpl implements CryptoService {

    private static final Logger LOGGER = Logger.getLogger(CryptoServiceImpl.class.getName());

    public static final int DEFAULT_ITERATIONS = 10000;
    private static final String SECRET_KEY = "~b8M^(Yc!ZF$9(Fpz^Sg";

    @Override
    public String encrypt(final String value) {

        final PBKDF2 kdf = new PBKDF2(HMacFactory.getInstance("HMAC-SHA-256"));
        final Map<String, Object> attr = Maps.newHashMap();
        attr.put(IPBE.ITERATION_COUNT, new Integer(DEFAULT_ITERATIONS));
        attr.put(IPBE.PASSWORD, Strings.nullToEmpty(value).toCharArray());
        attr.put(IPBE.SALT, SECRET_KEY.getBytes(Charset.forName("UTF-8")));
        kdf.init(attr);

        try {
            byte[] dk = new byte[32];
            kdf.nextBytes(dk, 0, dk.length);
            return Base64.encodeBytes(dk);
        } catch (LimitReachedException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        return null;
    }

}
