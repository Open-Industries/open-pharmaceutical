package org.openindustries.pharma.serialpkg;

import java.util.UUID;

/** SerialPackage aggregate — Serialized package format catalogue. */
public class SerialPackage {
    public UUID id;
        public String name;
        public String packageType;
        public int unitsPerPackage;
    public String status;

    public static SerialPackage create(String name, String packageType, int unitsPerPackage) {
        SerialPackage e = new SerialPackage();
        e.id = UUID.randomUUID();
        e.name = name;
        e.packageType = packageType;
        e.unitsPerPackage = unitsPerPackage;
        e.status = "PRINTED";
        return e;
    }
}
