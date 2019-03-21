const deuren = Array.from(document.getElementsByTagName("img"));
const nieuwSpelKnop = document.getElementsByTagName("button")[0];
nieuwSpelKnop.addEventListener("click", function (e) {
   setUp();
});
setUp();

function setUp() {
    const nrVanJuisteDeur = Math.floor(Math.random() * 7);

    deuren.forEach(function (deur, index) {
        deur.src = "images/deurtoe.png";
        if (index === nrVanJuisteDeur) {
            deur.addEventListener("click", function (e) {
                e.target.src = "images/gevonden.png";
            });
        } else {
            deur.addEventListener("click", function (e) {
                e.target.src = "images/deuropen.png";
            });
        }
    });
}