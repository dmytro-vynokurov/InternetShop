package dto.catalogue;

import dao.*;
import dto.cart.CartDTO;
import dto.category.CategoryModel;
import dto.item.ItemLazyModel;
import entities.Category;
import entities.Characteristic;
import entities.Item;
import entities.Photo;
import entities.util.PriceRange;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static dto.service.NavigationShop.*;
import static dto.service.Util.navigateTo;

/**
 * User: Dmitry
 * Date: 7/19/13
 * Time: 9:27 AM
 */
@ManagedBean(name = "catalogueDTO")
@SessionScoped
public class CatalogueDTO implements Serializable {
    private static final int NUMBER_OF_PRICE_RANGES = 4;
    private static final int DEFAULT_PAGE_SIZE = 20;

    @EJB
    CategoryDAO categoryDAO;
    @EJB
    ItemDAO itemDAO;
    @EJB
    PhotoDAO photoDAO;
    @EJB
    private VideoDAO videoDAO;
    @EJB
    private CharacteristicDAO characteristicDAO;
    @ManagedProperty(value = "#{cartDTO}")
    CartDTO cartDTO;

    Category selectedCategory;
    Item selectedItem;
    List<PriceRange> priceRanges;
    ItemLazyModel itemModel;

    private void initializePriceRanges() {
        double maxPrice = itemDAO.findMaxPrice();
        double minPrice = itemDAO.findMinPrice();
        double intervalLength = (maxPrice - minPrice) / NUMBER_OF_PRICE_RANGES;
        if (priceRanges == null) setUpNewPriceRanges(maxPrice, minPrice, intervalLength);
    }

    private void initializePriceRanges(Category category) {
        double maxPrice = itemDAO.findMaxPriceInCategory(category);
        double minPrice = itemDAO.findMinPriceInCategory(category);
        double intervalLength = (maxPrice - minPrice) / NUMBER_OF_PRICE_RANGES;
        setUpNewPriceRanges(maxPrice, minPrice, intervalLength);
    }

    private void setUpNewPriceRanges(double maxPrice, double minPrice, double intervalLength) {
        priceRanges = new ArrayList<>(NUMBER_OF_PRICE_RANGES);

        for (int i = 0; i < NUMBER_OF_PRICE_RANGES - 1; i++) {
            priceRanges.add(new PriceRange(minPrice + i * intervalLength, minPrice + (i + 1) * intervalLength));
        }

        priceRanges.add(new PriceRange(minPrice + (NUMBER_OF_PRICE_RANGES - 1) * intervalLength, maxPrice));
    }

    public Object getPhoto(Item item) {
        //todo: make loading images from database
        List<Photo> photos = photoDAO.findPhotosOfItem(item);
        return photos.get(0);
    }

    public String getVideo() {
        System.out.println("Getting video of item");
        System.out.println("Selected category: " + selectedCategory.getCategoryName());
        System.out.println("Selected item: " + ((selectedItem == null) ? "null" : selectedItem.getItemName()));
        System.out.println(videoDAO.findVideoOfItem(selectedItem).getUrl());
        return videoDAO.findVideoOfItem(selectedItem).getUrl();
    }

    public void viewItem() throws IOException {
        System.out.println("Viewing some item");
        navigateTo(ITEM_PAGE);
    }

    public void viewItems() throws IOException {
        navigateTo(SHOP_PREFIX + CATALOGUE_PAGE);
    }

    public void updatePrices() throws NamingException, IOException {
        navigateTo(CATALOGUE_PAGE);
    }

    public CategoryModel getCategoryModel() {
        return new CategoryModel(categoryDAO.findAll());
    }

    public CategoryModel getCategoriesWithItems() {
        return new CategoryModel(categoryDAO.findCategoriesWithItems());
    }

    public List<Characteristic> getCharacteristics() {
        return characteristicDAO.findCharacteristicsOfItem(selectedItem);
    }

    public ItemLazyModel getItemModel() throws NamingException {
        if (itemModel != null) return itemModel;
        else {
            return initializeItemModel();
        }
    }

    private ItemLazyModel initializeItemModel() throws NamingException {
        itemModel = new ItemLazyModel(selectedCategory, priceRanges);
        if (itemModel.getPageSize() < DEFAULT_PAGE_SIZE) {
            itemModel.setPageSize(DEFAULT_PAGE_SIZE);
        }
        itemModel.setRowCount(itemDAO.countItemsOfCategoryWithFilters(selectedCategory, priceRanges).intValue());
        System.out.println("Row count: " + itemModel.getRowCount());
        System.out.println("Page size: " + itemModel.getPageSize());
        return itemModel;
    }

    private void fixPageSize(ItemLazyModel model) {
        int pageSize = model.getPageSize();
        if (pageSize < 1) model.setPageSize(DEFAULT_PAGE_SIZE);
    }

    public void setCartDTO(CartDTO cartDTO) {
        this.cartDTO = cartDTO;
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
        initializePriceRanges(this.selectedCategory);
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) throws IOException {
        this.selectedItem = selectedItem;
        navigateTo(ITEM_PAGE);
    }

    public List<PriceRange> getPriceRanges() {
        return priceRanges;
    }

    public void setPriceRanges(List<PriceRange> priceRanges) throws NamingException {
        if (priceRangesChanged(priceRanges)) itemModel = initializeItemModel();
        this.priceRanges = priceRanges;
    }

    private boolean priceRangesChanged(List<PriceRange> priceRanges) {
        if (priceRanges == null && this.priceRanges == null) return false;
        if (priceRanges != null && this.priceRanges == null) return true;
        return !priceRanges.equals(this.priceRanges);
    }
}
