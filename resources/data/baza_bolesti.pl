
% Baza simptoma
simptom(glavobolja, 0.24).
simptom(hrkanje, 0.23).
simptom(zacepljenost_nosa, 0.19).
simptom(zapaljenje_srednjeg_uha, 0.06).
simptom(apneja, 0.01).
simptom(bol_u_grlu, 0.14).
simptom(bol_u_uhu, 0.1).
simptom(bol_u_gornjoj_vilici, 0.03).
simptom(povisena_temperatura, 0.36).

% Baza bolesti
bolest(adenoidne_vegetacije, 0.01).
bolest(upala_sinusa, 0.12).

% Baza terapija
terapija(budesonide).
terapija(sevaflurane).
terapija(budesonide_nasal_product).
terapija(nitrofurazone_tropical).
terapija(augmentin).
terapija(mometasone_nasal_product).
terapija(gauifanesin).

% Baza testova i procedura
test_procedura(excision).
test_procedura(tonsillectomy).
test_procedura(x-ray_computed_tomography).

% Baza multiplikatora
bolest_multiplikatori(adenoidne_vegetacije, [0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8]).
bolest_multiplikatori(upala_sinusa, [0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8]).

pojava_simptoma_za_bolest(adenoidne_vegetacije, glavobolja, 0.12, X, Y) :- bolest(adenoidne_vegetacije, X), simptom(glavobolja, Y).
pojava_simptoma_za_bolest(adenoidne_vegetacije, hrkanje, 0.33, X, Y) :- bolest(adenoidne_vegetacije, X), simptom(hrkanje, Y).
pojava_simptoma_za_bolest(adenoidne_vegetacije, zacepljenost_nosa, 0.23, X, Y) :- bolest(adenoidne_vegetacije, X), simptom(zacepljenost_nosa, Y).
pojava_simptoma_za_bolest(adenoidne_vegetacije, zapaljenje_srednjeg_uha, 0.03, X, Y) :- bolest(adenoidne_vegetacije, X), simptom(zapaljenje_srednjeg_uha, Y).
pojava_simptoma_za_bolest(adenoidne_vegetacije, apneja, 0.11, X, Y) :- bolest(adenoidne_vegetacije, X), simptom(apneja, Y).
pojava_simptoma_za_bolest(upala_sinusa, glavobolja, 0.15, X, Y) :- bolest(upala_sinusa, X), simptom(glavobolja, Y).
pojava_simptoma_za_bolest(upala_sinusa, bol_u_grlu, 0.18, X, Y) :- bolest(upala_sinusa, X), simptom(bol_u_grlu, Y).
pojava_simptoma_za_bolest(upala_sinusa, bol_u_uhu, 0.22, X, Y) :- bolest(upala_sinusa, X), simptom(bol_u_uhu, Y).
pojava_simptoma_za_bolest(upala_sinusa, bol_u_gornjoj_vilici, 0.26, X, Y) :- bolest(upala_sinusa, X), simptom(bol_u_gornjoj_vilici, Y).
pojava_simptoma_za_bolest(upala_sinusa, povisena_temperatura, 0.33, X, Y) :- bolest(upala_sinusa, X), simptom(povisena_temperatura, Y).
pojava_simptoma_za_bolest(upala_sinusa, zacepljenost_nosa, 0.09, X, Y) :- bolest(upala_sinusa, X), simptom(zacepljenost_nosa, Y).

provera_simptoma([], _Bolest, 0).
provera_simptoma([H|T], Bolest, Ukupni_uticaj) :- pojava_simptoma_za_bolest(Bolest, H, Pojava_simptomaV, BolestV, SimptomV),
                                                  Uticaj is (Pojava_simptomaV * BolestV) / SimptomV,
                                                  provera_simptoma(T, Bolest, Uticaj2),
                                                  Ukupni_uticaj is Uticaj + Uticaj2.

terapija_za_bolest(adenoidne_vegetacije, budesonide, 1) :- bolest(adenoidne_vegetacije, _X), terapija(budesonide).
terapija_za_bolest(adenoidne_vegetacije, sevaflurane, 0.85) :- bolest(adenoidne_vegetacije, _X), terapija(sevaflurane).
terapija_za_bolest(adenoidne_vegetacije, budesonide_nasal_product, 0.69) :- bolest(adenoidne_vegetacije, _X), terapija(budesonide_nasal_product).
terapija_za_bolest(adenoidne_vegetacije, nitrofurazone_tropical, 0.54) :- bolest(adenoidne_vegetacije, _X), terapija(nitrofurazone_tropical).
terapija_za_bolest(upala_sinusa, augmentin, 1) :- bolest(upala_sinusa, _X), terapija(augmentin).
terapija_za_bolest(upala_sinusa, mometasone_nasal_product, 0.81) :- bolest(upala_sinusa, _X), terapija(mometasone_nasal_product).
terapija_za_bolest(upala_sinusa, gauifanesin, 0.65) :- bolest(upala_sinusa, _X), terapija(gauifanesin).

test_procedura_za_bolest(adenoidne_vegetacije, excision, 1) :- bolest(adenoidne_vegetacije, _X), test_procedura(excision).
test_procedura_za_bolest(adenoidne_vegetacije, tonsillectomy, 0.83) :- bolest(adenoidne_vegetacije, _X), test_procedura(tonsillectomy).
test_procedura_za_bolest(upala_sinus, x-ray_computed_tomography, 1) :- bolest(upala_sinus, _X), test_procedura(x-ray_computed_tomography).

opseg_godina(Godine, Opseg) :- Godine < 1, Opseg is 0.
opseg_godina(Godine, Opseg) :- Godine >= 1, Godine =< 4, Opseg is 1.
opseg_godina(Godine, Opseg) :- Godine >= 5, Godine =< 14, Opseg is 2.
opseg_godina(Godine, Opseg) :- Godine >= 15, Godine =< 29, Opseg is 3.
opseg_godina(Godine, Opseg) :- Godine >= 30, Godine =< 44, Opseg is 4.
opseg_godina(Godine, Opseg) :- Godine >= 45, Godine =< 59, Opseg is 5.
opseg_godina(Godine, Opseg) :- Godine >= 60, Godine =< 74, Opseg is 6.
opseg_godina(Godine, Opseg) :- Godine >= 75, Opseg is 7.

pronadji_multiplikator(Bolest, Godine, Multiplikator) :- bolest_multiplikatori(Bolest, Lista_multiplikatora), 
					              opseg_godina(Godine, Opseg),
                                                         nth0(Opseg, Lista_multiplikatora, Multiplikator).


