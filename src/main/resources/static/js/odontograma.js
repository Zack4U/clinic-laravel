// Selecciona todas las imágenes de los dientes
var dientes = document.querySelectorAll(".diente");

// Selecciona el párrafo que muestra el número y la descripción del diente
var dienteDescripcion = document.getElementById("dienteDescripcion");
var fechaNota = document.getElementById("fechaNota");
var notasDiente = document.getElementById("notasDiente");
var numeroDiente = document.getElementById("numeroDiente");
var odontogramaId = document
    .querySelector("#addNota")
    .getAttribute("data-odontograma-id");

// Variable para almacenar el diente seleccionado
var dienteSeleccionado = null;

var estadoImagenes = {
    SANO: "/images/diente.png",
    AUSENTE: "/images/diente_ausente.png",
    EXTRACCION: "/images/diente_extraccion.png",
    CORONA_BUENA: "/images/diente_corona_buena.png",
    CORONA_MALA: "/images/diente_corona_mala.png",
    OBTURACION_BUENA: "/images/diente_obturacion_buena.png",
    OBTURACION_MALA: "/images/diente_obturacion_mala.png",
    SELLANTE_BUENO: "/images/diente_sellante_buena.png",
    SELLANTE_MALO: "/images/diente_sellante_mala.png",
    PROTESIS_BUENA: "/images/diente_protesis_buena.png",
    PROTESIS_MALA: "/images/diente_protesis_mala.png",
    PRESENCIA_APARATO: "/images/diente_presencia_aparato.png",
    FRACTURADO: "/images/diente_fractura_corona.png",
    CARIADO: "/images/diente_carie.png",
};

// Añade un evento de clic a cada imagen de los dientes
dientes.forEach(function (diente) {
    diente.addEventListener("click", function () {
        console.log(diente);
        dienteDescripcion.textContent = diente.alt;

        fetch("/odontogramas/diente/" + diente.id)
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
                if (data.nota && data.nota.length > 0) {
                    fechaNota.textContent =
                        data.nota[data.nota.length - 1].fecha;
                    notasDiente.textContent =
                        data.nota[data.nota.length - 1].contenido;
                } else {
                    fechaNota.textContent = "";
                    notasDiente.textContent = "";
                }
            })
            .catch((error) => console.error("Error:", error));

        // Almacena el diente seleccionado
        dienteSeleccionado = diente;
    });
});

// Selecciona los botones
var botonDienteSano = document.querySelector("#dienteSano");
var botonDienteAusente = document.querySelector("#dienteAusente");
var botonDienteExtraccion = document.querySelector("#dienteExtraccion");
var botonDienteCoronaBuena = document.querySelector("#dienteCoronaBuena");
var botonDienteCoronaMala = document.querySelector("#dienteCoronaMala");
var botonDienteObturacionBuena = document.querySelector(
    "#dienteObturacionBuena"
);
var botonDienteObturacionMala = document.querySelector("#dienteObturacionMala");
var botonDienteSellanteBuena = document.querySelector("#dienteSellanteBuena");
var botondienteSellanteMala = document.querySelector("#dienteSellanteMala");
var botondienteProtesisBuena = document.querySelector("#dienteProtesisBuena");
var botondienteProtesisMala = document.querySelector("#dienteProtesisMala");
var botondientePresenciaAparato = document.querySelector(
    "#dientePresenciaAparato"
);
var botondienteFractura = document.querySelector("#dienteFractura");
var botondienteCarie = document.querySelector("#dienteCarie");

botonDienteSano.addEventListener("click", function () {
    if (dienteSeleccionado) {
        cambiarEstadoDiente("SANO");
    }
});
botonDienteAusente.addEventListener("click", function () {
    if (dienteSeleccionado) {
        cambiarEstadoDiente("AUSENTE");
    }
});
botonDienteExtraccion.addEventListener("click", function () {
    if (dienteSeleccionado) {
        cambiarEstadoDiente("PARA_EXTRAER");
    }
});
botonDienteCoronaBuena.addEventListener("click", function () {
    if (dienteSeleccionado) {
        cambiarEstadoDiente("CORONA_BUENA");
    }
});
botonDienteCoronaMala.addEventListener("click", function () {
    if (dienteSeleccionado) {
        cambiarEstadoDiente("CORONA_MALA");
    }
});
botonDienteObturacionBuena.addEventListener("click", function () {
    if (dienteSeleccionado) {
        cambiarEstadoDiente("OBTURACION_BUENA");
    }
});
botonDienteObturacionMala.addEventListener("click", function () {
    if (dienteSeleccionado) {
        cambiarEstadoDiente("OBTURACION_MALA");
    }
});
botonDienteSellanteBuena.addEventListener("click", function () {
    if (dienteSeleccionado) {
        cambiarEstadoDiente("SELLANTE_BUENO");
    }
});
botondienteSellanteMala.addEventListener("click", function () {
    if (dienteSeleccionado) {
        cambiarEstadoDiente("SELLANTE_MALO");
    }
});
botondienteProtesisBuena.addEventListener("click", function () {
    if (dienteSeleccionado) {
        cambiarEstadoDiente("PROTESIS_BUENA");
    }
});
botondienteProtesisMala.addEventListener("click", function () {
    if (dienteSeleccionado) {
        cambiarEstadoDiente("PROTESIS_MALA");
    }
});
botondientePresenciaAparato.addEventListener("click", function () {
    if (dienteSeleccionado) {
        cambiarEstadoDiente("PRESENCIA_APARATO");
    }
});
botondienteFractura.addEventListener("click", function () {
    if (dienteSeleccionado) {
        cambiarEstadoDiente("FRACTURADO");
    }
});
botondienteCarie.addEventListener("click", function () {
    if (dienteSeleccionado) {
        cambiarEstadoDiente("CARIADO");
    }
});

var botonAddNota = document.querySelector("#addNota");

botonAddNota.addEventListener("click", function () {
    if (dienteSeleccionado) {
        window.location.href =
            odontogramaId + "/diente/" + dienteSeleccionado.id + "/nota/add";
    }
});

function cambiarEstadoDiente(estado) {
    if (dienteSeleccionado) {
        fetch(
            `${odontogramaId}/diente/${dienteSeleccionado.id}/diente/estado/change`,
            {
                method: "POST",
                body: estado,
            }
        )
            .then((response) => response.json())
            .then((data) => {
                if (data) {
                    dienteSeleccionado.src = estadoImagenes[estado];
                    console.log(data);
                }
            })
            .catch((error) => {
                console.error(error);
            });
    }
}