package entities.util;

import java.io.Serializable;
import java.util.List;

/**
 * User: Dmitry
 * Date: 7/23/13
 * Time: 2:53 PM
 */
public class PriceRange implements Serializable{
    private double lower;
    private double upper;
    private boolean checked=false;



    public static boolean priceFilteringEnabled(List<PriceRange> priceRanges) {
        if (priceRanges == null) return false;

        boolean atLeastOneChecked = false;
        boolean atLeastOneUnchecked = false;

        for (PriceRange priceRange : priceRanges) {
            if (priceRange.isChecked()) atLeastOneChecked = true;
            else atLeastOneUnchecked = true;

            if (atLeastOneChecked && atLeastOneUnchecked) return true;
        }
        return false;
    }

    public PriceRange() {
    }

    public PriceRange(double lower,double upper) {
        this.upper = upper;
        this.lower = lower;
    }

    public double getLower() {
        return lower;
    }

    public void setLower(double lower) {
        this.lower = lower;
    }

    public double getUpper() {
        return upper;
    }

    public void setUpper(double upper) {
        this.upper = upper;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceRange)) return false;

        PriceRange that = (PriceRange) o;

        if (checked != that.checked) return false;
        if (Double.compare(that.lower, lower) != 0) return false;
        if (Double.compare(that.upper, upper) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(lower);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(upper);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (checked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PriceRange{" +
                "lower=" + lower +
                ", upper=" + upper +
                ", checked=" + checked +
                '}';
    }
}
