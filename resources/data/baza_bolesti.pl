% BAZA SIMPTOMA
simptom(glavobolja, 0.24).
simptom(zacepljenost_nosa, 0.19).
simptom(apneja, 0.04).
simptom(povisena_temperatura, 0.36).
simptom(kasalj, 0.42).
simptom(bol_u_uhu, 0.12).
simptom(zujanje_u_uhu, 0.15).
simptom(upala_grla, 0.35).
simptom(crvenilo_uha, 0.14).
simptom(otoreja, 0.11).
simptom(povracanje, 0.12).
simptom(gubitak_sluha, 0.19).
simptom(vrtoglavica, 0.25).
simptom(upala_krajnika, 0.11).
simptom(alergijska_reakcija, 0.25).
simptom(kijavica, 0.35).
simptom(curenje_iz_nosa, 0.30).
simptom(promuklost, 0.26).
simptom(otezano_gutanje, 0.12).
%

% BAZA SLICNIH SIMPTOMA
slican_simptom(glavobolja, vrtoglavica).
slican_simptom(glavobolja, povisena_temperatura).
slican_simptom(glavobolja, upala_grla).
slican_simptom(glavobolja, bol_u_uhu).
slican_simptom(glavobolja, zujanje_u_uhu).
slican_simptom(zacepljenost_nosa, povisena_temperatura).
slican_simptom(zacepljenost_nosa, kasalj).
slican_simptom(zacepljenost_nosa, upala_grla).
slican_simptom(zacepljenost_nosa, kijavica).
slican_simptom(apneja, otezano_gutanje).
slican_simptom(povisena_temperatura, kasalj).
slican_simptom(povisena_temperatura, upala_grla).
slican_simptom(povisena_temperatura, povracanje).
slican_simptom(povisena_temperatura, upala_krajnika).
slican_simptom(povisena_temperatura, curenje_iz_nosa).
slican_simptom(povisena_temperatura, crvenilo_uha).
slican_simptom(kasalj, upala_grla).
slican_simptom(kasalj, povracanje).
slican_simptom(kasalj, alergijska_reakcija).
slican_simptom(kasalj, kijavica).
slican_simptom(kasalj, curenje_iz_nosa).
slican_simptom(kasalj, promuklost).
slican_simptom(bol_u_uhu, upala_grla).
slican_simptom(bol_u_uhu, otoreja).
slican_simptom(bol_u_uhu, zujanje_u_uhu).
slican_simptom(bol_u_uhu, gubitak_sluha).
slican_simptom(bol_u_uhu, crvenilo_uha).
slican_simptom(zujanje_u_uhu, vrtoglavica).
slican_simptom(zujanje_u_uhu, gubitak_sluha).
slican_simptom(zujanje_u_uhu, upala_grla).
slican_simptom(upala_grla, promuklost).
slican_simptom(upala_grla, otezano_gutanje).
slican_simptom(upala_grla, upala_krajnika).
%

% BAZA BOLESTI
bolest(adenoidne_vegetacije, 0.07).
bolest(upala_sinusa, 0.20).
bolest(zapaljenje_srednjeg_uha, 0.07).
bolest(tinitus, 0.13).
bolest(tonzilitis, 0.08).
bolest(sezonska_alergija, 0.20).
bolest(prehlada, 0.27).
bolest(laringitis, 0.06).
bolest(polip_na_glasnim_zicama, 0.04).
bolest(faringitis, 0.10).
%

% BAZA MULTIPLIKATORA

% GODINE [<1, 1-4, 5-14, 15-29, 30-44, 45-59, 60-74, 75+]
bolest_multiplikatori_godine(adenoidne_vegetacije, [0.2, 5.3, 4.9, 0.7, 0.3, 0.2, 0.1, 0.0]).
bolest_multiplikatori_godine(upala_sinusa, [0.3, 0.8, 1.2, 1.2, 1.5, 1.1, 0.6, 0.2]).
bolest_multiplikatori_godine(zapaljenje_srednjeg_uha, [4.5,6.4,2.2,0.5,0.4,0.3,0.2,0.1]).
bolest_multiplikatori_godine(tinitus, [0.1, 0.0, 0.3, 0.3, 0.9, 1.4, 2.2, 1.5]).
bolest_multiplikatori_godine(tonzilitis, [0.3, 2.8, 3.9, 1.3, 0.8, 0.3, 0.1, 0.0]).
bolest_multiplikatori_godine(sezonska_alergija, [0.3, 1.5, 2.6, 0.8, 1.0, 0.9, 0.7, 0.5]).
bolest_multiplikatori_godine(prehlada, [3.5, 3.7, 2.4, 1.0, 0.6, 0.4, 0.3, 0.2]).
bolest_multiplikatori_godine(laringitis, [0.3, 0.4, 0.7, 0.9, 1.4, 1.1, 1.2, 0.8]).
bolest_multiplikatori_godine(polip_na_glasnim_zicama, [0.4, 0.4, 1.0, 0.5, 0.6, 1.8, 1.8, 0.6]).
bolest_multiplikatori_godine(faringitis, [2.3, 3.5, 4.1, 0.8, 0.4, 0.0, 1.0, 0.0]).
%

% POL[MALE, FEMALE]
bolest_multiplikatori_pol(adenoidne_vegetacije, [1.2, 0.8]).
bolest_multiplikatori_pol(upala_sinusa, [0.9, 1.1]).
bolest_multiplikatori_pol(zapaljenje_srednjeg_uha, [1.2, 0.9]).
bolest_multiplikatori_pol(tinitus, [1.0, 1.0]).
bolest_multiplikatori_pol(tonzilitis, [1.0, 1.0]).
bolest_multiplikatori_pol(sezonska_alergija, [1.0, 1.0]).
bolest_multiplikatori_pol(prehlada, [1.0, 1.0]).
bolest_multiplikatori_pol(laringitis, [0.8, 1.1]).
bolest_multiplikatori_pol(polip_na_glasnim_zicama, [1.0, 1.0]).
bolest_multiplikatori_pol(faringitis, [1.5, 0.6]).
%

% RASA[WHITE, BLACK, ASIAN, HISPANIC, INDIAN]
bolest_multiplikatori_rasa(adenoidne_vegetacije, [0.9, 0.9, 1.2, 1.5, 1.2]).
bolest_multiplikatori_rasa(upala_sinusa, [1.2, 0.8, 0.7, 0.6, 0.7]).
bolest_multiplikatori_rasa(zapaljenje_srednjeg_uha, [0.9, 0.9, 1.0, 1.3, 1.0]).
bolest_multiplikatori_rasa(tinitus, [1.0, 0.7, 1.8, 0.9, 1.8]).
bolest_multiplikatori_rasa(tonzilitis, [1.1, 0.4, 0.8, 1.5, 0.8]).
bolest_multiplikatori_rasa(sezonska_alergija, [1.0, 1.0, 1.3, 0.9, 1.3]).
bolest_multiplikatori_rasa(prehlada, [0.9, 1.1, 1.2, 1.3, 1.2]).
bolest_multiplikatori_rasa(laringitis, [1.2, 0.8, 1.2, 0.6, 1.2]).
bolest_multiplikatori_rasa(polip_na_glasnim_zicama, [1.1, 0.9, 0.9, 0.9, 0.9]).
bolest_multiplikatori_rasa(faringitis, [0.7, 0.8, 2.7, 2.0, 2.7]).
%

% BAZA POJAVE SIMPTOMA KADA JE UTVRDJENA BOLEST
pojava_simptoma_za_bolest(adenoidne_vegetacije, upala_krajnika, 0.71).
pojava_simptoma_za_bolest(adenoidne_vegetacije, upala_grla, 0.56).
pojava_simptoma_za_bolest(adenoidne_vegetacije, zacepljenost_nosa, 0.46).
pojava_simptoma_za_bolest(adenoidne_vegetacije, apneja, 0.42).
pojava_simptoma_za_bolest(adenoidne_vegetacije, kasalj, 0.40).
pojava_simptoma_za_bolest(adenoidne_vegetacije, crvenilo_uha, 0.30).
pojava_simptoma_za_bolest(adenoidne_vegetacije, povisena_temperatura, 0.29).
pojava_simptoma_za_bolest(upala_sinusa, kasalj, 0.81).
pojava_simptoma_za_bolest(upala_sinusa, zacepljenost_nosa, 0.75).
pojava_simptoma_za_bolest(upala_sinusa, upala_grla, 0.66).
pojava_simptoma_za_bolest(upala_sinusa, glavobolja, 0.66).
pojava_simptoma_za_bolest(upala_sinusa, povisena_temperatura, 0.51).
pojava_simptoma_za_bolest(upala_sinusa, bol_u_uhu, 0.49).
pojava_simptoma_za_bolest(zapaljenje_srednjeg_uha, bol_u_uhu, 0.86).
pojava_simptoma_za_bolest(zapaljenje_srednjeg_uha, povisena_temperatura, 0.76).
pojava_simptoma_za_bolest(zapaljenje_srednjeg_uha, kasalj, 0.71).
pojava_simptoma_za_bolest(zapaljenje_srednjeg_uha, zacepljenost_nosa, 0.59).
pojava_simptoma_za_bolest(zapaljenje_srednjeg_uha, upala_grla, 0.41).
pojava_simptoma_za_bolest(zapaljenje_srednjeg_uha, crvenilo_uha, 0.33).
pojava_simptoma_za_bolest(zapaljenje_srednjeg_uha, povracanje, 0.33).
pojava_simptoma_za_bolest(zapaljenje_srednjeg_uha, otoreja, 0.18).
pojava_simptoma_za_bolest(tinitus, zujanje_u_uhu, 0.91).
pojava_simptoma_za_bolest(tinitus, gubitak_sluha, 0.69).
pojava_simptoma_za_bolest(tinitus, vrtoglavica, 0.6).
pojava_simptoma_za_bolest(tinitus, bol_u_uhu, 0.41).
pojava_simptoma_za_bolest(tinitus, glavobolja, 0.37).
pojava_simptoma_za_bolest(tonzilitis, upala_grla, 0.75).
pojava_simptoma_za_bolest(tonzilitis, upala_krajnika, 0.62).
pojava_simptoma_za_bolest(tonzilitis, zacepljenost_nosa, 0.37).
pojava_simptoma_za_bolest(tonzilitis, crvenilo_uha, 0.30).
pojava_simptoma_za_bolest(tonzilitis, apneja, 0.17).
pojava_simptoma_za_bolest(tonzilitis, gubitak_sluha, 0.17).
pojava_simptoma_za_bolest(sezonska_alergija, zacepljenost_nosa, 0.72).
pojava_simptoma_za_bolest(sezonska_alergija, kasalj, 0.65).
pojava_simptoma_za_bolest(sezonska_alergija, alergijska_reakcija, 0.53).
pojava_simptoma_za_bolest(sezonska_alergija, upala_grla, 0.45).
pojava_simptoma_za_bolest(sezonska_alergija, glavobolja, 0.32).
pojava_simptoma_za_bolest(sezonska_alergija, bol_u_uhu, 0.30).
pojava_simptoma_za_bolest(sezonska_alergija, kijavica, 0.25).
pojava_simptoma_za_bolest(sezonska_alergija, curenje_iz_nosa, 0.21).
pojava_simptoma_za_bolest(prehlada, kasalj, 0.85).
pojava_simptoma_za_bolest(prehlada, upala_grla, 0.82).
pojava_simptoma_za_bolest(prehlada, povisena_temperatura, 0.78).
pojava_simptoma_za_bolest(prehlada, zacepljenost_nosa, 0.69).
pojava_simptoma_za_bolest(prehlada, curenje_iz_nosa, 0.48).
pojava_simptoma_za_bolest(prehlada, povracanje, 0.34).
pojava_simptoma_za_bolest(prehlada, bol_u_uhu, 0.24).
pojava_simptoma_za_bolest(prehlada, promuklost, 0.18).
pojava_simptoma_za_bolest(prehlada, otezano_gutanje, 0.14).
pojava_simptoma_za_bolest(laringitis, upala_grla, 0.87).
pojava_simptoma_za_bolest(laringitis, promuklost, 0.83).
pojava_simptoma_za_bolest(laringitis, kasalj, 0.79).
pojava_simptoma_za_bolest(laringitis, zacepljenost_nosa, 0.55).
pojava_simptoma_za_bolest(laringitis, povisena_temperatura, 0.39).
pojava_simptoma_za_bolest(laringitis, curenje_iz_nosa, 0.37).
pojava_simptoma_za_bolest(laringitis, otezano_gutanje, 0.19).
pojava_simptoma_za_bolest(laringitis, alergijska_reakcija, 0.16).
pojava_simptoma_za_bolest(polip_na_glasnim_zicama, promuklost, 0.91).
pojava_simptoma_za_bolest(polip_na_glasnim_zicama, upala_grla, 0.47).
pojava_simptoma_za_bolest(polip_na_glasnim_zicama, kasalj, 0.27).
pojava_simptoma_za_bolest(faringitis, kasalj, 0.88).
pojava_simptoma_za_bolest(faringitis, povisena_temperatura, 0.85).
pojava_simptoma_za_bolest(faringitis, upala_grla, 0.80).
pojava_simptoma_za_bolest(faringitis, otezano_gutanje, 0.45).
pojava_simptoma_za_bolest(faringitis, promuklost, 0.45).
%

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


