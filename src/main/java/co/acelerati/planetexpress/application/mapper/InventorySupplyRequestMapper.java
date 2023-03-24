package co.acelerati.planetexpress.application.mapper;

import co.acelerati.planetexpress.domain.model.Brand;
import co.acelerati.planetexpress.domain.model.Category;
import co.acelerati.planetexpress.domain.model.DetailStock;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.model.Product;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.BrandResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.CategoryResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.DetailStockResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProductResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventorySupplyRequestMapper {

    public static Inventory toInventoryModel(InventorySupplyRequestDTO inventorySupplyRequestDTO) {
        return new Inventory(inventorySupplyRequestDTO.getProductId(),
                             inventorySupplyRequestDTO.getPersonSupplierId(),
                             inventorySupplyRequestDTO.getIncomingPrice(),
                             inventorySupplyRequestDTO.getCurrentPrice(),
                             inventorySupplyRequestDTO.getQuantity());
    }

    public static List<Inventory> toInventoryModelList(List<InventorySupplyRequestDTO> inventorySupplyRequestDTO) {
        return inventorySupplyRequestDTO.stream()
              .map(InventorySupplyRequestMapper::toInventoryModel)
              .collect(Collectors.toList());
    }

    public static DetailStockResponseDTO toProductDTO(DetailStock detailStock) {
        return new DetailStockResponseDTO(
          detailStock.getId(),
          detailStock.getName(),
          detailStock.getDescription(),
          detailStock.getModel(),
          detailStock.getBrand(),
          detailStock.getCategory(),
          detailStock.getQuantity(),
          detailStock.getIncomingPrice(),
          detailStock.getCurrentPrice());
    }

    public static List<DetailStockResponseDTO> toProductDTOList(List<DetailStock> detailStocks) {
        return detailStocks.stream()
          .map(InventorySupplyRequestMapper::toProductDTO)
          .collect(Collectors.toList());
    }

    public static List<Brand> toBrandList(List<BrandResponseDTO> brandResponseDTOS){
        List<Brand> brands = new ArrayList<>();
        for (BrandResponseDTO brandResponseDTO: brandResponseDTOS) {
            Brand brand = new Brand(brandResponseDTO.getId(), brandResponseDTO.getName());
            brands.add(brand);
        }
        return brands;
    }

    public static List<Category> toCategoryList(List<CategoryResponseDTO> categoryResponseDTOS){
        List<Category> categories = new ArrayList<>();
        for (CategoryResponseDTO categoryResponseDTO: categoryResponseDTOS) {
            Category category = new Category(categoryResponseDTO.getId(), categoryResponseDTO.getName());
            categories.add(category);
        }
        return categories;
    }

    public static List<Product> toProductList(List<ProductResponseDTO> inventorySupplyRequestDTO) {
        return inventorySupplyRequestDTO.stream()
          .map(InventorySupplyRequestMapper::toProduct)
          .collect(Collectors.toList());
    }

    public static Product toProduct(ProductResponseDTO productResponseDTO) {
        return new Product(
          productResponseDTO.getId(),
          productResponseDTO.getName(),
          productResponseDTO.getDescription(),
          productResponseDTO.getModel(),
          productResponseDTO.getIdBrand(),
          productResponseDTO.getIdCategory());
    }

    private InventorySupplyRequestMapper() {  }
}