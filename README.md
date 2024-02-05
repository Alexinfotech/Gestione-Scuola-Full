# Gestione-Scuola-Full
# Sistema Informativo di Gestione Scolastica
## Descrizione del Progetto

Questo sistema informativo è una piattaforma web progettata per facilitare la gestione complessiva di una scuola. Funge da interfaccia centralizzata per la registrazione e l'autenticazione degli utenti, la generazione di codici fiscali, la ricerca e la gestione dei dettagli degli utenti, e offre funzionalità di amministrazione.

Le pagine JSP utilizzate per l'interfaccia utente sono strutturate per fornire un'esperienza utente coesa e intuitiva, con un design reattivo supportato da Bootstrap. L'applicazione implementa una struttura MVC, con servlet che gestiscono la logica di business e interagiscono con un database MySQL per la persistenza dei dati. 

## Tecnologie Utilizzate

- **Java/Jakarta EE**: Utilizzato per lo sviluppo del backend e per gestire la logica di business dell'applicazione.
- **JSP e Servlets**: Per il front-end dinamico e la gestione delle richieste HTTP.
- **Bootstrap (Tema Cerulean di Bootswatch)**: Per un design frontend responsive e moderno.
- **MySQL**: Come database per archiviare e gestire i dati utente e della scuola.
- **Apache Tomcat**: Server di applicazioni per il deployment dell'applicazione.

## Funzionalità Chiave

### Autenticazione e Registrazione
- Pagina di registrazione dove nuovi utenti possono creare un account.
- Pagina di login dove gli utenti registrati possono accedere.
- Sistema di gestione delle sessioni per mantenere lo stato dell'utente.

### Gestione Utenti
- Form per la creazione di un nuovo utente con la generazione automatica del codice fiscale tramite chiamate API esterne.
- La possibilità di cercare, visualizzare e modificare i dettagli degli utenti registrati.
- Funzionalità per eliminare gli utenti esistenti dal sistema.

### Interazioni con API Esterna
- Integrazione con un'API esterna per la generazione di codici fiscali, con gestione degli errori e feedback all'utente.

### Messaggi di Errore e Feedback
- Gestione personalizzata dei messaggi di errore che vengono mostrati agli utenti in caso di problemi, come credenziali errate o tentativi di registrazione con un'email già esistente.

## Installazione e Avvio

Per installare e avviare il sistema informativo di gestione scolastica, seguire questi passaggi:

1. Clonare il repository dal GitHub.
2. Configurare un server MySQL e creare il database necessario.
3. Configurare il server di applicazioni Apache Tomcat.
4. Impostare le credenziali del database e altre configurazioni nel file di properties dell'applicazione.
5. Eseguire il build e il deploy dell'applicazione sul server Tomcat.

## Note Aggiuntive

La piattaforma è ancora in fase di sviluppo, e ulteriori funzionalità sono previste per rilasci futuri. Si invita la comunità di sviluppatori a contribuire al progetto seguendo le linee guida fornite nel documento CONTRIBUTING.md.

