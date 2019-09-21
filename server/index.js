var numRandom;
var express = require("express");
var bodyParser = require("body-parser");
var app = express();
app.use(bodyParser.urlencoded({ extended: true }));
const showTodayMessage = (req, res) => {
    numRandom = Math.floor((Math.random() * 13) + 0);
    res.send(
        arrayCookies[numRandom]
    );
    arrayCookies[numRandom] = { id: arrayCookies[numRandom].id, frase: arrayCookies[numRandom].frase, veces: arrayCookies[numRandom].veces + 1 };
};
const port = 40000;

app.get("/today", showTodayMessage);
app.get("/list", (req, res) => {
    var arrayCookiesEnabled = new Array();
    var i = 0;
    for (i = 0; i <= 13; i++) {
        if (arrayCookies[i].veces > 0) {
            arrayCookiesEnabled.push(arrayCookies[i]);
        }
    }
    res.send(
        arrayCookiesEnabled
    );
});
app.get("/search", (req, res) => {
    var searchId = req.query.sid;
    var reset = req.query.rese;
    var valido = false;
    var i;
    if (searchId >= 0 & searchId <= 13) {
        for (i = 0; i <= 13; i++) {
            if (arrayCookies[i].id == searchId) {
                res.send(
                    arrayCookies[i]
                );
                break;
            }
        }
    } else {
        res.send(
            "El id buscado no existe"
        );
    }
    console.log(i);
    if (sid = -1){
        arrayCookies[i].veces = 0;
    }
    
})

var arrayCookies = [
    {
        id: 0,
        frase: "Vale más un diamante con defecto que un pedrusco sin ninguno",
        veces: 0
    },
    {
        id: 1,
        frase: "Es una locura amar, a menos que se ame con locura",
        veces: 0
    },
    {
        id: 2,
        frase: "Habrá un romance feliz para ti dentro de muy poco",
        veces: 0
    },
    {
        id: 3,
        frase: "Atraigo el éxito y la prosperidad con todas mis ideas",
        veces: 0
    },
    {
        id: 4,
        frase: "Estás en el lugar perfecto para llegar desde aquí",
        veces: 0
    },
    {
        id: 5,
        frase: "Las oportunidades y las ventajas están detrás de cada puerta que abro",
        veces: 0
    },
    {
        id: 6,
        frase: "Estar en la ruina es una situación temporal. Ser pobre es un estado mental",
        veces: 0
    },
    {
        id: 7,
        frase: "Tengo la mayor de todas riquezas: la de no desearla",
        veces: 0
    },
    {
        id: 8,
        frase: "Me estoy volviendo mejor en lo que hago todos los días",
        veces: 0
    },
    {
        id: 9,
        frase: "A cada paso, una oportunidad aparece frente a mí",
        veces: 0
    },
    {
        id: 10,
        frase: "No te metas en el mundo de las drogas",
        veces: 0
    },
    {
        id: 11,
        frase: "La salud no es sólo la ausencia de enfermedad, sino también la armonía con uno mismo y el entorno",
        veces: 0
    },
    {
        id: 12,
        frase: "Tienes que dormir más",
        veces: 0
    },
    {
        id: 13,
        frase: "La salud es la verdadera riqueza, y no las monedas de oro y plata",
        veces: 0
    }
]

app.listen(port, () => {
    console.log("Running on http://localhost:" + port);
});