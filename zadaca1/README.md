README
Opis strukture klasa i odnosa
Projekt sadrži sledeću hijerarhiju i strukturu:
 
·Interfejs osoba
Definiše osnovne metode koje svaka osoba u sistemu mora imati:
-identitet() – vraća ime i prezime osobe
-profesionalnaTitula() – vraća profesionalnu titulu osobe
 
·Osnovna klasa inzenjer
Implementira interfejs osoba i sadrži zajedničke osobine svih inženjera:
-Ime, prezime, titula, godine iskustva, ekspertize
-Validacija unesenih podataka
-Metoda osnovneInformacije() za ispis osnovnih podataka
 
·Izvedene klase
Specifični tipovi inženjera sa dodatnim atributima:
-softverskiInzenjer – dodatno sadrži broj projekata
-inzenjerElektrotehnike – dodatno sadrži broj certifikata
Svaka izvedena klasa nadjačava metodu za prikaz informacija i ima metodu za ocjenu uspješnosti.
 
Objašnjenje korištenih funkcionalnih operacija
 
·fold()
Koristi se za grupisanje inženjera po ekspertizama.
Implementiran je ručni akumulator koji prolazi kroz listu i pravi mapu ekspertiza sa listama inženjera sa preko 5 godina iskustva.
 
·reduce()
Koristi se za pronalaženje najiskusnijeg inženjera u svakoj
Kombinuje elemente liste tako što bira inženjera sa najvećim brojem godina iskustva.
 
·sumOf()
Koristi se za sumiranje ukupnog broja projekata i certifikata svih inženjera u kompaniji.
 
Poređenje fold, reduce i aggregate
 
·fold
Inicijalizuje akumulator na početnu vrijednost i zatim prolazi kroz kolekciju, nadograđujući akumulator. Idealno za pravljenje novih struktura podataka ili kada treba imati početnu vrijednost.
 
 
·reduce
Kombinuje elemente kolekcije bez inicijalne vrijednosti (uzima prvi element kao početak). Koristi se za izračune poput pronalaska maksimuma/minimuma.
 
·aggregate
Nije ugrađena funkcija u Kotlin standardnoj biblioteci, ali u kontekstu zadatka označava operaciju agregacije (sabiranja) podataka po ključevima, slično kao kombinacija fold i map-reduce koncepta.
 
 
Napomena o korištenju AI alata
 
Tokom izrade zadatka koristio sam AI alat kao pomoć pri razumijevanju i savladavanju koncepta funkcija fold, reduce i aggregate. AI mi je pomogao da bolje shvatim razlike između ovih funkcija, njihove primjene i prednosti, što mi je omogućilo da implementiram potrebne operacije nad kolekcijama. Također, AI je služio kao podrška u razumijevanju i rješavanju grešaka koje su se pojavile tokom razvoja, kao i u proširivanju mog znanja o ugrađenim funkcijama u Kotlinu, poput filterIsInstance. Također AI je korišten kao pomoz za pisanje README dokumentacije.
