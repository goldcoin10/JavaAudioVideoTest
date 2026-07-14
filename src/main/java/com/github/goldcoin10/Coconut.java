package com.github.goldcoin10;

import java.io.IOException;
import java.util.Objects;

import org.apache.commons.codec.digest.DigestUtils;

import javax.swing.*;

public class Coconut {
    public static void sanityCheck() throws IOException {
        String hash = DigestUtils.md5Hex(Objects.requireNonNull(Coconut.class.getResourceAsStream("/coconut.jpg")));
        if (!hash.equalsIgnoreCase("dbd08dc0cd2ee4a016f5553927991bd6")) {
            JOptionPane.showMessageDialog(null, "Files unfound; fatal error");
            throw new AssertionError("Files unfound; fatal error");
        }
    }
}
