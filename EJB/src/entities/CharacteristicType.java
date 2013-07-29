package entities;

import javax.persistence.*;
import java.util.List;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/20/13
 * Time: 5:19 PM
 */
@Entity
@Table(name = "CHARACTERISTIC_TYPE", schema = "INTERNETSHOP", catalog = "")
@NamedQuery(name="findCharacteristicTypesOfCategory",query="SELECT ct FROM CharacteristicType ct WHERE ct.category=:category")
public final class CharacteristicType {
    @Column(name = "ID_CHARACTERISTIC_TYPE", nullable = false, insertable = true, updatable = true, length = 6, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_Characteristic_Type")
    @SequenceGenerator(name = "S_Characteristic_Type", sequenceName = "S_CHARACTERISTIC_TYPE", allocationSize = 1)
    private int idCharacteristicType;
    @Column(name = "CHARACTERISTIC_TYPE_NAME", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Basic
    private String characteristicTypeName;
    @OneToMany(mappedBy = "characteristicType")
    private List<Characteristic> characteristics;
    @ManyToOne
    @JoinColumn(name = "ID_CATEGORY", referencedColumnName = "ID_CATEGORY", nullable = false)
    private Category category;

    public int getIdCharacteristicType() {
        return idCharacteristicType;
    }

    public void setIdCharacteristicType(int idCharacteristic) {
        this.idCharacteristicType = idCharacteristic;
    }

    public String getCharacteristicTypeName() {
        return characteristicTypeName;
    }

    public void setCharacteristicTypeName(String characteristicTypeName) {
        this.characteristicTypeName = characteristicTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacteristicType that = (CharacteristicType) o;

        if (idCharacteristicType != that.idCharacteristicType) return false;
        if (characteristicTypeName != null ? !characteristicTypeName.equals(that.characteristicTypeName) : that.characteristicTypeName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCharacteristicType;
        result = 31 * result + (characteristicTypeName != null ? characteristicTypeName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CharacteristicType{" +
                "idCharacteristic=" + idCharacteristicType +
                ", characteristicTypeName='" + characteristicTypeName + '\'' +
                '}';
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
