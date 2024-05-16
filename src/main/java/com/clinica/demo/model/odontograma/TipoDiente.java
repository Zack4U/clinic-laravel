package com.clinica.demo.model.odontograma;

public enum TipoDiente {
    TIPO_1("Incisivo central superior derecho"),
    TIPO_2("Incisivo lateral superior derecho"),
    TIPO_3("Canino superior derecho"),
    TIPO_4("Primer premolar superior derecho"),
    TIPO_5("Segundo premolar superior derecho"),
    TIPO_6("Primer molar superior derecho"),
    TIPO_7("Segundo molar superior derecho"),
    TIPO_8("Tercer molar superior derecho (muela del juicio)"),

    TIPO_9("Incisivo central inferior derecho"),
    TIPO_10("Incisivo lateral inferior derecho"),
    TIPO_11("Canino inferior derecho"),
    TIPO_12("Primer premolar inferior derecho"),
    TIPO_13("Segundo premolar inferior derecho"),
    TIPO_14("Primer molar inferior derecho"),
    TIPO_15("Segundo molar inferior derecho"),
    TIPO_16("Tercer molar inferior derecho (muela del juicio)"),

    TIPO_17("Incisivo central superior izquierdo"),
    TIPO_18("Incisivo lateral superior izquierdo"),
    TIPO_19("Canino superior izquierdo"),
    TIPO_20("Primer premolar superior izquierdo"),
    TIPO_21("Segundo premolar superior izquierdo"),
    TIPO_22("Primer molar superior izquierdo"),
    TIPO_23("Segundo molar superior izquierdo"),
    TIPO_24("Tercer molar superior izquierdo (muela del juicio)"),

    TIPO_25("Incisivo central inferior izquierdo"),
    TIPO_26("Incisivo lateral inferior izquierdo"),
    TIPO_27("Canino inferior izquierdo"),
    TIPO_28("Primer premolar inferior izquierdo"),
    TIPO_29("Segundo premolar inferior izquierdo"),
    TIPO_30("Primer molar inferior izquierdo"),
    TIPO_31("Segundo molar inferior izquierdo"),
    TIPO_32("Tercer molar inferior izquierdo (muela del juicio)");

    private final String descripcion;

    TipoDiente(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
