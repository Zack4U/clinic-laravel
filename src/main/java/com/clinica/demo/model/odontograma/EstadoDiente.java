package com.clinica.demo.model.odontograma;

public enum EstadoDiente {
    SANO("Diente sano", "/images/diente.png"),
    AUSENTE("Diente ausente", "/images/diente_ausente.png"),
    PARA_EXTRAER("Diente marcado para extracción", "/images/diente_extraccion.png"),
    CORONA_BUENA("Corona del diente en buen estado", "/images/diente_corona_buena.png"),
    CORONA_MALA("Corona del diente en mal estado", "/images/diente_corona_mala.png"),
    OBTURACION_BUENA("Obturacion de diente en buen estado", "/images/diente_obturacion_buena.png"),
    OBTURACION_MALA("Obturacion de diente en mal estado", "/images/diente_obturacion_mala.png"),
    SELLANTE_BUENO("Sellante de fosas y fisuras en buen estado", "/images/diente_sellante_buena.png"),
    SELLANTE_MALO("Sellante de fosas y fisuras en mal estado", "/images/diente_sellante_mala.png"),
    PROTESIS_BUENA("Protesis parcial fija en buen estado", "/images/diente_protesis_buena.png"),
    PROTESIS_MALA("Protesis parcial ", "/images/diente_protesis_mala.png"),
    PRESENCIA_APARATO("Presencia de aparato de ortodoncia", "/images/diente_presencia_aparato.png"),
    FRACTURADO("Fractura de corona dental", "/images/diente_fractura_corona.png"),
    CARIADO("Caries dental", "/images/diente_carie.png"),

    ;

    private final String descripcion;
    private final String url;

    EstadoDiente(String descripcion, String url) {
        this.descripcion = descripcion;
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUrl() {
        return url;
    }
}
