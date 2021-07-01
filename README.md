# Password validation

Our department needs a passwords verification server.<br>

## Rules
Suppose you have the following list:

    1-3 a: abcde
    1-3 b: cdefg
    2-9 c: ccccccccc

Each line gives the password policy and then the password.<br>
The password policy indicates the lowest and highest number of times a given letter must appear for the password to be valid.<br>
For example,

    1-3 a

means that the password must contain a at least 1 time and at most 3 times.<br>
<br>
In the above example, 2 passwords are valid. The middle password, cdefg, is not; it contains no instances of b, but needs at least 1. The first and third passwords are valid: they contain one a or nine c, both within the limits of their respective policies.

## Task
Prepare a <b>REST-enabled application</b> which will offer a <b>POST</b> method named <b>validate</b> and thus enable the user to verify the validity of any line built using the same schema as in the previous examples:<br>

    {min-max} {character}: {password}

The <b>validate</b> method should take a simple string as input and return the input itself, followed by the token <b>VALID</b> or <b>INVALID</b> as output according to the rule verification result.

### Example
 
Given the following input for the validate method:

    1-3 a: abcde

the method should return:

    1-3 a: abcde -> VALID


#### Implementazione

a soluzione è stata strutturata come servizio Spring Boot, utilizzando per l'abilitazione REST le funzionalità del package spring web.

La soluzione è strutturata recuperando le stringhe in input, ed utilizzando un metodo factory per la conversione, sia della richiesta in un model impiegato nella logica di validazione, che in risposta per la produzione della lista in uscita.

I model factory sono estensione di una interfaccia comune che impiega generics per indicare origine e destinazione, le stringhe sono convertite secondo una logica di separazione per linea, per cui è possibile ottenere il risultato di più elementi, ed ottenere in risposta una stringa composta come:

stringa1
stringa2
.....

Il controller avrà come dependency injection i mapper delle richieste e risposte, e del service su cui viene effettuata la logica.

La classe interna utilizzata PasswordValidationData contiene indicazioni delle occorrenze minime e massime, del carattere da confrontare, della stringa per cui è necessaria validazione.

Il modello contiene inoltre lo stato di validità e la stringa originale, da riutilizzare per la produzione della risposta.

La logica di validazione è nascosta in un service, implementato su interfaccia, per cui segue la seguente logica di validazione:

Per ogni modello di validazione recupera la stringa da verificare, viene convertita in un array per split, e filtrato usando la funzione filter di Java Stream, collezionando il risultato in count.

Una stringa sara valida se la count è all'interno delle occorrenze.
