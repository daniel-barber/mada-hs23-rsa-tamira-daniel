# rsa-tamira-daniel
Mathematik fur die Datenkommunikation | HS23 | Klasse 3Ib (3iCbb)

## Beschreibung
Dieses Projekt besteht aus drei ausführbare Java Klassen. Die Ausführung der Java Klassen überschreibt allfällige Dateien im `/target` Ordner, wenn diese bereits bestehen.

## RSA Generator

`RSAGenerator` generiert ein RSA-Schlüsselpaar `pk.txt` und `sk.txt` und speichert diese im Ordner [`/target`](target) ab. 


## TextEncryptor 

`TextEncryptor` verschlüsselt das File [`text.txt`](src%2Fmain%2Fresources%2Ftext.txt) mit dem öffentlichen Schlüssel [`pk.txt`](src%2Fmain%2Fresources%2Fpk.txt) im Ordner [`resources`](src%2Fmain%2Fresources) und speichert die verschlüsselte Nachricht im Ordner [`/target`](target) als [`chiffre.txt`](target%2Fchiffre.txt)  ab.

## TextDecryptor
`TextDecryptor` entschlüsselt das File [`chiffre.txt`](src%2Fmain%2Fresources%2Fchiffre.txt) mit dem privaten Schlüssel [`sk.txt`](src%2Fmain%2Fresources%2Fsk.txt) im Ordner [`resources`](src%2Fmain%2Fresources) und speichert die entschlüsselte Nachricht im Ordner [`/target`](target) als [`text-d.txt`](target%2Ftext-d.txt) ab. 