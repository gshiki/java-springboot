package br.shiki.java.spring.boot.template.enums;

import lombok.Getter;


/**
 * 
 * @author Shiki
 *
 */
@Getter
public enum UserStatus {

	CREATED('C', "Created", true),
    DISABLED('D', "Disabled", false)
    ;

	private Character character;
    private String description;
    private boolean active;

    private UserStatus(final Character character, final String description, final boolean active) {
    	this.character = character;
        this.description = description;
        this.active = active;
    }

    /**
     * 
     * @param character
     * @return
     */
    public static UserStatus getInstance(final Character character) {
        for (final UserStatus cursor : UserStatus.values()) {
            if (cursor.character.equals(character)) {
            	return cursor;
            }
        }
        return null;
    }

}
