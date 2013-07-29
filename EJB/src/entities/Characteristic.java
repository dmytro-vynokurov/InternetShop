package entities;

import javax.persistence.*;

/**
 * User: Dmytro_Vynokurov
 * Date: 6/20/13
 * Time: 5:19 PM
 */
@Entity
@Table(name = "Characteristic", catalog = "", schema = "INTERNETSHOP")
@NamedQuery(name="findCharacteristicsOfItem",query="SELECT ct FROM Characteristic ct WHERE ct.item=:item")
public final class Characteristic {
    @Column(name = "ID_CHARACTERISTIC", nullable = false, insertable = true, updatable = true, length = 6, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_Characteristic")
    @SequenceGenerator(name = "S_Characteristic", sequenceName = "S_CHARACTERISTIC", allocationSize = 1)
    private int idCharacteristic;
    @Column(name = "VALUE", nullable = true, insertable = true, updatable = true, length = 12, precision = 5)
    @Basic
    private Double value;
    @Column(name = "MEASURE", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    private String measure;
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 40, precision = 0)
    @Basic
    private String description;
    @ManyToOne
    @JoinColumn(name = "ID_CHARACTERISTIC_TYPE", referencedColumnName = "ID_CHARACTERISTIC_TYPE", nullable = false)
    private CharacteristicType characteristicType;
    @ManyToOne
    @JoinColumn(name = "ID_ITEM", referencedColumnName = "ID_ITEM", nullable = false)
    private Item item;

    public int getIdCharacteristic() {
        return idCharacteristic;
    }

    public void setIdCharacteristic(int idCharacteristic) {
        this.idCharacteristic = idCharacteristic;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String characteristicValueType) {
        this.description = characteristicValueType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Characteristic that = (Characteristic) o;

        if (idCharacteristic != that.idCharacteristic) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (measure != null ? !measure.equals(that.measure) : that.measure != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCharacteristic;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (measure != null ? measure.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Characteristic{" +
                "idCharacteristic=" + idCharacteristic +
                ", value=" + value +
                ", measure='" + measure + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public CharacteristicType getCharacteristicType() {
        return characteristicType;
    }

    public void setCharacteristicType(CharacteristicType characteristicType) {
        this.characteristicType = characteristicType;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
