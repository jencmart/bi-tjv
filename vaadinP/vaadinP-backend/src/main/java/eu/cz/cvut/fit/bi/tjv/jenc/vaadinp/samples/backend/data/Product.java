package eu.cz.cvut.fit.bi.tjv.jenc.vaadinp.samples.backend.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product implements Serializable {

    @NotNull
    private int id = -1;
    @NotNull
    @Size(min = 2, message = "Product name must have at least two characters")
    private String productName = "";
    @Min(0)
    private BigDecimal price = BigDecimal.ZERO;
    private Set<Category> category;
    @Min(value = 0, message = "Can't have negative amount in stock")
    private int stockCount = 0;
    @NotNull
    private Availability availability = Availability.COMING;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

}
