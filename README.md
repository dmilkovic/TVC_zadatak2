# TVC_zadatak2
The app should consist of one page and satisfy the following conditions:
- start page needs to contain a title and a message. Style of title and message should be different and defined using styles
- there should be three buttons underneath the message
- show the current language in the first button of "-" if the language isn't chosen
When starting the device no language is chosen
- on the button click open a window which offers to select a language: Croatian or English
- when the language is chosen app should refresh the screen and translate text to the selected language
- chosen language should be saved after the user restarts the app
- use SharedPreferences for saving the language
- if the language isn't chosen click on the second button should show a Toast with message “Jezik
nije odabran”. This message shouldn't be translated
- if the language is chosen click on the second button should open a window with text "Hello World!" translated to the selected language
- click outside of the opened window should close the window
- the third button should open a window with a button
- on button click start FizzBuzz process which reproduces a list of strings and writes them in the window. The window needs to have
a closing button and shouldn't be closed on click outside of the window
- texts which are written on the screen should be read from resources

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
