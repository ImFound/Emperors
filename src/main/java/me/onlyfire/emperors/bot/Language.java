package me.onlyfire.emperors.bot;

import me.onlyfire.emperors.utils.Emoji;

public enum Language {

    /* Multi Space */
    WELCOME("""
            <b>Ciao! Benvenuto in @EmperorsRobot!</b>
                                    
            👑 <b>Cosa è Emperors?</b>
            Emperors è un bot per far divertire gli utenti del vostro gruppo.
            Scrivendo il nome di un imperatore, i vostri utenti potranno diventare l'imperatore del giorno.
                                    
            ❔ <b>Come funziona?</b>
            Gli amministratori del gruppo avranno il permesso di creare un imperatore (e di rimuoverlo), inserendo una foto e il suo identificativo (che gli utenti dovranno scrivere per diventare l'imperatore del giorno)
                                    
            ⚡️ <b>Quanti imperatori si possono aggiungere in un gruppo?</b>
            La risposta è semplice. Infiniti :P
            Sbizzarritevi a creare i vostri imperatori senza nessun tipo di limite!
            """),

    ADD_EMPEROR_FIRST_STEP("""
            Adesso invia una foto (senza compressione di telegram) che identifica l'imperatore, con una didascalia contenente il suo nome.
            Gli utenti dovranno scrivere quel nome per diventare imperatori.

            <b>Esempio: https://imgur.com/a/chawtDS</b>

            Digita /cancel per annullare la creazione.
            """
    ),

    GENERAL_ERROR("""
            <b>Non è stato possibile eseguire la tua azione!</b>
            
            <i>Mi dispiace averti disturbato, ma c'è stato un problema tecnico nel bot! Molto probabilmente (anzi, sicuramente) questo errore è stato generato per via dell'incapacità dello sviluppatore nel controllare le exceptions in java.</i>
            
            <b>ERROR CODE:</b> <code>%s</code>
            <b>MESSAGE:</b> <code>%s</code>"""
    ),

    REMOVE_EMPEROR_USAGE("""
            <b>Utilizzo corretto del comando: </b><code>/removeemperor (nome)</code>
            
            Digita <code>/listemperors</code> per avere la lista degli imperatori disponibili
            """),

    /* Simple messages */
    NEW_EMPEROR_OF_DAY("<a href=\"%s\">&#8205</a>" + Emoji.PARTY + " ||• <b>Congratulazioni</b> %s •|| " +
            Emoji.PARTY + "\n\n" + "➥ Sei il nuovo imperatore <code>%s</code> di oggi!"),
    MAX_EMPERORS(Emoji.HEAVY_MULTIPLICATION_X + " <b>Hai già conquistato %s imperatori!</b> Gli amministratori di questo gruppo hanno impostato un limite di %s imperatori per membro."),
    IN_COOLDOWN("<b>Sei in cooldown!</b> Aspetta %ss prima di conquistare un nuovo imperatore."),
    ADDED_EMPEROR_SUCCESSFULLY(Emoji.PARTY + " ➥ Complimenti, l'imperatore <code>%s</code> è ora disponibile a tutti gli utenti!"),
    ALREADY_HAS_EMPEROR(Emoji.CRYING_FACE + " <b>Mi dispiace!</b> %s ha già preso il posto di re <code>%s</code>!"),
    ALREADY_HAS_EMPEROR_SELF("<b>Hey!</b> Hai già preso il posto di questo re, te ne sei dimenticato? " + Emoji.THINKING
            + "\nSe vuoi controllare la lista degli imperatori, digita /listemperors."),
    CREATION_IN_PROGRESS(Emoji.TECHNOLOGIST + "Il tuo imperatore è ora in fase di creazione, questa operazione potrà richiedere qualche minuto."),
    ALREADY_EXIST_EMPEROR(Emoji.HEAVY_MULTIPLICATION_X + " Esiste già un imperatore con questo nome!"),
    REMOVED_EMPEROR_SUCCESSFULLY("L'imperatore <code>%s</code> è stato rimosso dal gruppo!"),

    THERE_ARE_NO_EMPERORS("<b>Non ci sono imperatori in questo gruppo!</b> " + Emoji.CRYING_FACE),
    MODE_REMOVED("Sei stato rimosso dalla precedente sessione utente."),
    NOT_EXIST_EMPEROR(Emoji.HEAVY_MULTIPLICATION_X + " Non esiste un imperatore con questo nome!"),

    /* Logs */
    REMOVED_LOG("Removed emperor %s on group %s (Familiar name: %s)"),
    CREATED_LOG("Created emperor %s on group %s (Familiar name: %s)");

    final String s;

    Language(String lang) {
        this.s = lang;
    }

    @Override
    public String toString() {
        return s;
    }
}
