package client.models;

import java.io.Serializable;

public class Chapter implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String parentLegion;
    private static int lastSetID = 0;
    private int id;
    /**
     * пустой конструктор класса Chapter
     */
    public Chapter(){}

    /**
     * Конструктор класса Chapter
     * @param name название главы
     * @param parentLegion название подглавы
     */
    public Chapter (String name, String parentLegion){
        this.name = name;
        this.parentLegion = parentLegion;
    }

    /**
     * Конструктор для заполнения только обязательных полей
     * @param name название главы
     */
    public Chapter(String name){
        this.name = name;
    }

    /**
     * Функция проверяет Chapter на валидность
     * @return
     */

    public boolean isValid() {
        try {
            validateName();
            return true;
        } catch (ValidationError e) {
            return false;
        }
    }

    public void validateName() throws ValidationError {
        validateName(this.name);
    }

    public void validateName(String name) throws ValidationError {
        if (name == null || name.isEmpty()) {
            throw new ValidationError("Name must be not null or empty.");
        }
    }

    /**
     * Возвращает название главы
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Задает название главы
     * @param name название главы
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает имя parentLegion
     * @return
     */
    public String getParentLegion() {
        return parentLegion;
    }

    /**
     * задает значение parentLegion
     * @param parentLegion
     */
    public void setParentLegion(String parentLegion) {
        this.parentLegion = parentLegion;
    }

    private boolean isExistsUnusedIDs() {
        return id + 1 > 0;
    }

    private void generateAndSetID() {
        if (isExistsUnusedIDs()) {
            id = ++lastSetID;
        } else {
            throw new IdentifierError();
        }
    }

    public boolean initialize() {
        if (!isInitialized() && isValid()) {
            generateAndSetID();
            return true;
        } else {
            return false;
        }
    }

    public boolean isInitialized() {
        return id != 0;
    }

    public boolean isLowerThan(Chapter other) {
        return (this.isInitialized() && other.isInitialized()) &&
                (this.name.length() < other.name.length() || this.parentLegion.length() < other.parentLegion.length());
    }

    /**
     * Переопределение метода toString
     * @return
     */
    @Override
    public String toString(){
        return "chapter = \""+name+"\" parentLegion = \""+parentLegion+"\"";
    }
}
