# TVC_zadatak2
Aplikacija se treba sastojati od jedne stranice i zadovoljavati slijedeće uvjete:
- početna stranica treba imati naslov i poruku. Izgled naslova i poruke treba biti jasno različit,
te definiran preko stilova
- ispod poruke treba prikazati 3 tipke
- u prvoj tipci prikazati trenutni jezik aplikacije ili ‘-’ ako nijedan nije odabran. 
Prilikom prvog pokretanja na danom uređaju, nijedan nije odabran
- klikom na tipku, treba se otvoriti prozor koji će ponuditi izbor jezika: hrvatski ili engleski
- odabirom jezika aplikacija treba osvježiti trenutni ekran i prevesti tekstove na odabrani jezik
- odabrani jezik se treba očuvati nakon što korisnik ugasi aplikaciju, te je ponovno upali.
- za očuvanje odabranog jezika, koristiti SharedPreferences
- u slučaju da jezik nije odabran, klik na drugu tipku treba prikazati Toast sa porukom “Jezik
nije odabran”. Ova poruka se ne smije prevoditi
- ako je jezik odabran, klik na drugu tipku treba otvoriti prozor sa tekstom “Hello world!”
preveden na odabrani jezik.
- Klik izvan otvorenog prozora treba zatvoriti prozor
- treća tipka treba otvoriti prozor sa tipkom
- klikom na tipku, pokreće se FizzBuzz process koji treba producirati listu stringova i ispisati
ih u prozoru. Prozor treba imati tipku za zatvaranje i nesmije se moći zatvoriti klikom izvan
prozora
- tekstovi koji se ispisuju na ekranima se trebaju čitati iz resursa
